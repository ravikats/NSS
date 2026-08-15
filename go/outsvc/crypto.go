package outsvc

import "empay/irf/cryptapi"

// CryptoConfig, CardCrypto and CryptoClient are re-exported from the shared
// cryptapi package (moved there so outsvc and tlfsvc share one client).
type CryptoConfig = cryptapi.CryptoConfig
type CardCrypto = cryptapi.CardCrypto
type CryptoClient = cryptapi.CryptoClient

// NewCryptoClient builds a CryptoClient with a shared HTTP client.
func NewCryptoClient(cfg CryptoConfig) *CryptoClient {
	return cryptapi.NewCryptoClient(cfg)
}
