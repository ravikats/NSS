package binsvc

import (
	"bytes"
	"net/http"
	"os"
	"time"
)

// SyncACL mirrors ACLIntegration.sendPermissionFIle: on startup, when
// ACL_INTEGRATION_FLAG=1, POST the permission file to the ACL endpoint with a
// Bearer token.
func (s *Service) SyncACL() {
	if !s.Cfg.ACLFlag {
		s.log().Info("ACL_INTEGRATION_FLAG is not enabled")
		return
	}
	s.log().Info("----------Permission File----------")
	content, err := os.ReadFile(s.Cfg.ACLPermFile)
	if err != nil {
		s.log().Error("Error while reading Permission File", "err", err)
		return
	}
	s.log().Info(string(content))
	s.log().Info("----------Permission File----------")

	req, err := http.NewRequest(http.MethodPost, s.Cfg.ACLURL, bytes.NewReader(content))
	if err != nil {
		s.log().Error("build acl request", "err", err)
		return
	}
	req.Header.Set("Content-Type", "application/json")
	req.Header.Set("Authorization", "Bearer "+s.Cfg.ACLUserAppID)

	client := &http.Client{Timeout: 30 * time.Second}
	s.log().Info("Sending Permission File")
	resp, err := client.Do(req)
	if err != nil {
		s.log().Error("Sending Permission File Failed", "err", err)
		return
	}
	defer resp.Body.Close()
	s.log().Info("ACL response", "code", resp.StatusCode)
}
