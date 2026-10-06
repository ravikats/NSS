package binsvc

import "testing"

// The status view is what the Settlement -> BIN Files tab renders, so the
// network -> range-table mapping is load-bearing: an operator uses it to tell
// which file feeds which table. UAESWITCH deliberately points at
// JAYWAN_ISS_ACC_RANGE because the uaeswitch POS BIN file is Jaywan's.
func TestRangeTableMappingCoversTheSupportedNetworks(t *testing.T) {
	want := map[string]string{
		"MASTERCARD": "MC_ISS_ACC_RANGE",
		"VISA":       "VISA_ISS_ACC_RANGE",
		"UAESWITCH":  "JAYWAN_ISS_ACC_RANGE",
		"JAYWAN":     "JAYWAN_ISS_ACC_RANGE",
	}
	for network, table := range want {
		if got := networkRangeTable[network]; got != table {
			t.Errorf("networkRangeTable[%s] = %q, want %q", network, got, table)
		}
	}
}

func TestRangeTablesReturnsACopy(t *testing.T) {
	a := RangeTables()
	a["MASTERCARD"] = "tampered"
	if networkRangeTable["MASTERCARD"] != "MC_ISS_ACC_RANGE" {
		t.Error("RangeTables leaked the package map; callers must not be able to mutate it")
	}
}
