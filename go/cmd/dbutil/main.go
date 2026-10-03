package main

import (
	"database/sql"
	"fmt"
	"os"
	"strings"

	_ "github.com/sijms/go-ora/v2"
)

func main() {
	dsn := os.Getenv("ORACLE_DSN")
	if dsn == "" {
		dsn = "oracle://NETWORK_SETTLEMENT_UAT:J6erQ%24o6E24@localhost:1521/FREEPDB1"
	}
	db, err := sql.Open("oracle", dsn)
	if err != nil {
		fmt.Fprintln(os.Stderr, "open:", err)
		os.Exit(1)
	}
	defer db.Close()
	if err := db.Ping(); err != nil {
		fmt.Fprintln(os.Stderr, "ping:", err)
		os.Exit(1)
	}
	fmt.Println("Connected OK")

	args := os.Args[1:]
	if len(args) == 0 {
		fmt.Println("Usage: dbutil <SQL>  or  dbutil -f <file.sql>")
		return
	}

	var queries []string
	if args[0] == "-f" && len(args) > 1 {
		data, err := os.ReadFile(args[1])
		if err != nil {
			fmt.Fprintln(os.Stderr, "read file:", err)
			os.Exit(1)
		}
		// Split on semicolons (simple approach for DDL/DML)
		parts := strings.Split(string(data), ";")
		for _, p := range parts {
			s := strings.TrimSpace(p)
			if s != "" {
				queries = append(queries, s)
			}
		}
	} else {
		queries = append(queries, strings.Join(args, " "))
	}

	for i, q := range queries {
		upper := strings.ToUpper(strings.TrimSpace(q))
		if strings.HasPrefix(upper, "SELECT") {
			rows, err := db.Query(q)
			if err != nil {
				fmt.Fprintf(os.Stderr, "query %d: %v\n", i+1, err)
				continue
			}
			cols, _ := rows.Columns()
			for _, c := range cols {
				fmt.Printf("%-30s", c)
			}
			fmt.Println()
			for rows.Next() {
				vals := make([]interface{}, len(cols))
				ptrs := make([]interface{}, len(cols))
				for j := range vals {
					ptrs[j] = &vals[j]
				}
				rows.Scan(ptrs...)
				for _, v := range vals {
					fmt.Printf("%-30v", v)
				}
				fmt.Println()
			}
			rows.Close()
		} else {
			result, err := db.Exec(q)
			if err != nil {
				fmt.Fprintf(os.Stderr, "exec %d: %v\n  SQL: %s\n", i+1, err, q[:min(80, len(q))])
				continue
			}
			n, _ := result.RowsAffected()
			fmt.Printf("OK (%d rows affected) - %s\n", n, upper[:min(40, len(upper))])
		}
	}
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
