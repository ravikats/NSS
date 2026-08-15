package main

import (
	"context"
	"database/sql"
	"fmt"
	"log"
	"os"
	"strings"
	"time"

	_ "github.com/sijms/go-ora/v2"
)

func main() {
	db, err := sql.Open("oracle", os.Getenv("ORACLE_DSN"))
	if err != nil {
		log.Fatal(err)
	}
	defer db.Close()
	ctx, cancel := context.WithTimeout(context.Background(), 300*time.Second)
	defer cancel()

	// Strip storage/tablespace/physical attributes so the DDL runs on the
	// local free edition untouched; keep SQL terminators for easy splitting.
	// BOOLEAN literals are inlined because go-ora cannot bind PL/SQL BOOLEAN.
	for _, p := range []struct{ name, val string }{
		{"STORAGE", "FALSE"}, {"TABLESPACE", "FALSE"}, {"SEGMENT_ATTRIBUTES", "FALSE"},
		{"SQLTERMINATOR", "TRUE"}, {"PRETTY", "TRUE"}, {"CONSTRAINTS", "TRUE"},
		{"REF_CONSTRAINTS", "TRUE"}, {"FORCE", "FALSE"},
	} {
		stmt := fmt.Sprintf("BEGIN DBMS_METADATA.SET_TRANSFORM_PARAM(DBMS_METADATA.SESSION_TRANSFORM, '%s', %s); END;", p.name, p.val)
		if _, err := db.ExecContext(ctx, stmt); err != nil {
			log.Fatalf("set transform %v: %v", p.name, err)
		}
	}

	var sb strings.Builder
	sb.WriteString("SET DEFINE OFF;\nSET SERVEROUTPUT OFF;\nALTER SESSION SET NLS_NUMERIC_CHARACTERS='.,';\n")

	getDDL := func(dtype, name string) (string, error) {
		var out sql.NullString
		err := db.QueryRowContext(ctx,
			"SELECT DBMS_METADATA.GET_DDL(:1, :2) FROM dual", dtype, name).Scan(&out)
		if err != nil {
			return "", err
		}
		return out.String, nil
	}

	appendDDL := func(dtype, name string) {
		ddl, err := getDDL(dtype, name)
		if err != nil {
			log.Printf("SKIP %s %s: %v", dtype, name, err)
			return
		}
		sb.WriteString("\n-- ===== " + dtype + " " + name + " =====\n")
		sb.WriteString(ddl)
		sb.WriteString("\n/\n")
	}

	// Sequences first (identity legacy generators).
	rows, err := db.QueryContext(ctx, `SELECT sequence_name FROM user_sequences ORDER BY sequence_name`)
	if err != nil {
		log.Fatal(err)
	}
	var seqs []string
	for rows.Next() {
		var s string
		rows.Scan(&s)
		seqs = append(seqs, s)
	}
	rows.Close()
	for _, s := range seqs {
		appendDDL("SEQUENCE", s)
	}

	// Tables.
	rows, err = db.QueryContext(ctx, `SELECT table_name FROM user_tables ORDER BY table_name`)
	if err != nil {
		log.Fatal(err)
	}
	var tabs []string
	for rows.Next() {
		var t string
		rows.Scan(&t)
		tabs = append(tabs, t)
	}
	rows.Close()
	for _, t := range tabs {
		appendDDL("TABLE", t)
	}

	// Non-constraint indexes only (PK/unique indexes come with the table DDL).
	rows, err = db.QueryContext(ctx, `
		SELECT index_name FROM user_indexes
		WHERE index_name NOT IN (SELECT index_name FROM user_constraints WHERE index_name IS NOT NULL)
		ORDER BY index_name`)
	if err != nil {
		log.Fatal(err)
	}
	var idxs []string
	for rows.Next() {
		var i string
		rows.Scan(&i)
		idxs = append(idxs, i)
	}
	rows.Close()
	for _, i := range idxs {
		appendDDL("INDEX", i)
	}

	// Views.
	rows, err = db.QueryContext(ctx, `SELECT view_name FROM user_views ORDER BY view_name`)
	if err != nil {
		log.Fatal(err)
	}
	var views []string
	for rows.Next() {
		var v string
		rows.Scan(&v)
		views = append(views, v)
	}
	rows.Close()
	for _, v := range views {
		appendDDL("VIEW", v)
	}

	// Functions and types.
	rows, err = db.QueryContext(ctx, `SELECT object_name FROM user_objects WHERE object_type='FUNCTION' ORDER BY object_name`)
	if err != nil {
		log.Fatal(err)
	}
	for rows.Next() {
		var n string
		rows.Scan(&n)
		appendDDL("FUNCTION", n)
	}
	rows.Close()
	rows, err = db.QueryContext(ctx, `SELECT object_name FROM user_objects WHERE object_type='TYPE' ORDER BY object_name`)
	if err != nil {
		log.Fatal(err)
	}
	for rows.Next() {
		var n string
		rows.Scan(&n)
		appendDDL("TYPE", n)
	}
	rows.Close()

	if err := os.WriteFile("/tmp/opencode/replica/ddl.sql", []byte(sb.String()), 0o644); err != nil {
		log.Fatal(err)
	}
	fmt.Println("WROTE /tmp/opencode/replica/ddl.sql", len(sb.String()), "bytes;", len(tabs), "tables,", len(seqs), "seqs,", len(idxs), "idx,", len(views), "views")
}
