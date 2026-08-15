// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessing.vo;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotNull;
import com.google.gson.annotations.SerializedName;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SwitchExtractVo
{
    @JsonProperty("bank_id")
    @SerializedName("bank_id")
    @NotNull(message = "The bank_id cannot be null")
    @Length(max = 4, message = "The maximum length is 4 ")
    private String bankCode;
    @JsonProperty("sub_route")
    @NotNull(message = "The sub_route cannot be null")
    @NotBlank(message = "The sub_route cannot be blank")
    @Length(max = 20, message = "The maximum length is 20")
    @Pattern(regexp = "^(?i)(MASTERCARD|VISA|RUPAY|AMEX|UAESWITCH|JAYWAN|omannet_tps|ONUS)$", message = "INVALID SUBROUTE ; Accept MASTERCARD/VISA/RUPAY/AMEX/UAESWITCH/JAYWAN/omannet_tps/ONUS/MERCURY Only")
    private String network;
    @JsonProperty("scheme")
    @NotNull(message = "The scheme cannot be null")
    @NotBlank(message = "The scheme cannot be blank")
    @Length(max = 20, message = "The maximum length is 20")
    @Pattern(regexp = "^(?i)(MASTERCARD|VISA|RUPAY|AMEX|UAESWITCH|JAYWAN|ONUS|MAAL)$", message = "INVALID SCHEME ; Accept MASTERCARD/VISA/RUPAY/AMEX/UAESWITCH/JAYWAN/ONUS/DISCOVER/DINERS/MERCURY Only")
    private String scheme;
    @JsonProperty("ref_id")
    @SerializedName("ref_id")
    @NotNull(message = "The ref_id cannot be null")
    @NotBlank(message = "The ref_id cannot be blank")
    @Length(max = 36, message = "The maximum length is 36")
    private String uniqueId;
    @JsonProperty("switch_mti")
    @SerializedName("switch_mti")
    @NotNull(message = "The switch_mti cannot be null")
    @NotBlank(message = "The switch_mti cannot be blank")
    @Length(max = 4, message = "Max Length 4")
    @Pattern(regexp = "^(0110|0130|0210|0410|0430)$", message = "Invalid MTI value. Allowed values are: 0110, 0130, 0210, 0410, 0430.")
    private String mti;
    @JsonProperty("pan")
    @SerializedName("pan")
    @NotNull(message = "The pan cannot be null")
    @NotBlank(message = "The pan cannot be blank")
    @Length(max = 19, message = "Max Length 19")
    private String cardNumber;
    @JsonProperty("switch_crypt_token")
    @SerializedName("switch_crypt_token")
    @NotNull(message = "The switch_crypt_token cannot be null")
    @NotBlank(message = "The switch_crypt_token cannot be blank")
    @Length(max = 256, message = "Max Length 256")
    private String tokenIdentifier;
    @JsonProperty("processing_code")
    @SerializedName("processing_code")
    @NotNull(message = "The processing_code cannot Be null")
    @NotBlank(message = "The processing_code cannot be blank")
    @Length(max = 6, message = "Max length 6")
    @Pattern(regexp = "^(000000|200000|010000|090000|710000|610000|620000|210000)$", message = "Invalid process code value. Allowed values are: 000000, 200000, 010000, 090000, 710000, 610000, 620000, 210000.")
    private String processCode;
    @JsonProperty("amount")
    @SerializedName("amount")
    @NotNull(message = "The amount cannot be null")
    @NotBlank(message = "The amount cannot be blank")
    @Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "INVALID AMOUNT ; AMOUNT Should be Greater than Zero")
    private String amountTransaction;
    @JsonProperty("settlement_amount")
    @SerializedName("settlement_amount")
    private String amountSettlement;
    @JsonProperty("transmission_date")
    @SerializedName("transmission_date")
    @NotNull(message = "The transmission_date cannot be Null")
    @NotBlank(message = "The transmission_date cannot be Blank")
    @Length(max = 10, min = 10, message = "TxnDateTime must be in 'MMDDhhmmss' format")
    @Pattern(regexp = "^\\d+$", message = "Number only Allowed for TxnDateTime ")
    private String txnDateTime;
    @NotNull(message = "The Stan cannot be null")
    @NotBlank(message = "The Stan cannot be blank")
    @Length(max = 6, min = 6, message = "Length should be 6")
    private String stan;
    @JsonProperty("local_time")
    @SerializedName("local_time")
    @NotNull(message = "The local_time cannot be null")
    @NotBlank(message = "The local_time cannot be blank")
    @Length(max = 6, min = 6, message = "localTxnDate must be in 'hhmmss' format")
    private String localTxnTime;
    @JsonProperty("local_date")
    @SerializedName("local_date")
    @NotNull(message = "The local_date cannot be null")
    @NotBlank(message = "The local_date cannot be blank")
    @Length(max = 4, min = 4, message = "localTxnDate must be in 'MMDD' format")
    private String localTxnDate;
    @JsonProperty("exp_date")
    @SerializedName("exp_date")
    private String expiryDate;
    @JsonProperty("settlement_date")
    @SerializedName("settlement_date")
    @Length(max = 4, message = "Length should be 4")
    private String settlementDate;
    @NotNull(message = "The MCC cannot be Null")
    @NotBlank(message = "The MCC cannot be Blank")
    @Length(min = 4, max = 4, message = "Length should be 4")
    private String mcc;
    @JsonProperty("acq_inst_country_code")
    @SerializedName("acq_inst_country_code")
    @Length(max = 3, message = "Length should be 3")
    private String acqInsConCode;
    @JsonProperty("pos_entry_mode")
    @SerializedName("pos_entry_mode")
    @NotNull(message = "The pos_entry_mode cannot be null")
    @NotBlank(message = "The pos_entry_mode cannot be blank")
    @Length(min = 3, max = 12, message = "Length should be 12")
    private String posEntryMode;
    @JsonProperty("pan_sequence_number")
    @SerializedName("pan_sequence_number")
    @Length(max = 3, message = "Length should be 3")
    private String panSequence;
    @JsonProperty("pos_condition_code")
    @SerializedName("pos_condition_code")
    @Length(max = 2, message = "Length should be 2")
    private String posCode;
    @JsonProperty("txn_fee_amount")
    @SerializedName("txn_fee_amount")
    private String txnFeeAmount;
    @JsonProperty("acquier_id")
    @SerializedName("acquier_id")
    @NotNull(message = "accquier_id cannot be null")
    @NotBlank(message = "The accquier_id cannot be blank")
    @Length(max = 11, message = "Length should be 11")
    private String acqInsIdCode;
    @JsonProperty("rrn")
    @SerializedName("rrn")
    @NotNull(message = "RRN Must not Be NULL")
    @NotBlank(message = "The RRN cannot be Blank")
    @Length(max = 12, message = "Length must be 12")
    private String retRefNumber;
    @JsonProperty("auth_code")
    @SerializedName("auth_code")
    @Length(max = 6, message = "Max Length 6")
    private String authIdResponse;
    @JsonProperty("network_response_code")
    @SerializedName("network_response_code")
    @NotNull(message = "network_response_code cannot be null")
    @NotBlank(message = "The network_response_code cannot be blank")
    @Length(max = 3, message = "Length Should be 3")
    private String responseCode;
    @JsonProperty("service_restriction_code")
    @SerializedName("service_restriction_code")
    @Length(max = 3, message = "Max Length Should be 3")
    private String serviceRestrictionCode;
    @JsonProperty("terminal_id")
    @SerializedName("terminal_id")
    @NotNull(message = "terminal_id cannot be null")
    @NotBlank(message = "The terminal_id cannot be blank")
    @Length(min = 8, max = 8, message = "The terminal_id Length Should be 8")
    private String cardAcceptorTid;
    @JsonProperty("merchant_id")
    @SerializedName("merchant_id")
    @NotNull(message = "merchant_id cannot Be null")
    @NotBlank(message = "The merchant_id cannot be blank")
    @Length(min = 15, max = 15, message = "The merchant_id Length Should be 15")
    private String cardAcceptorId;
    @JsonProperty("card_acceptor_name")
    @SerializedName("card_acceptor_name")
    @NotNull(message = "card_acceptor_name Must not Be null")
    @NotBlank(message = "The card_acceptor_name cannot be blank")
    @Length(max = 25, message = "Max Length Mustbe 25")
    private String cardAcceptorName;
    @JsonProperty("card_acceptor_st_addr")
    @SerializedName("card_acceptor_st_addr")
    @NotNull(message = "card_acceptor_st_addr cannot Be null")
    @NotBlank(message = "The card_acceptor_st_addr cannot be blank")
    @Length(max = 45, message = "Max Length is 45")
    private String cardAcceptorStreetAddress;
    @JsonProperty("card_acceptor_city")
    @SerializedName("card_acceptor_city")
    @NotNull(message = "card_acceptor_city cannot Be null")
    @NotBlank(message = "The card_acceptor_city cannot be blank")
    @Length(max = 20, message = "Max Length is 20")
    private String cardAcceptorCity;
    @JsonProperty("card_acceptor_state_code")
    @SerializedName("card_acceptor_state_code")
    @NotNull(message = "card_acceptor_state_code Must not Be null")
    @NotBlank(message = "The card_acceptor_state_code cannot be blank")
    @Length(max = 3, message = "Max Length is 3")
    private String cardAcceptorStateCode;
    @JsonProperty("card_acceptor_pin_code")
    @SerializedName("card_acceptor_pin_code")
    @NotNull(message = "card_acceptor_pin_code cannot Be null")
    @NotBlank(message = "The card_acceptor_pin_code cannot be blank")
    @Length(min = 6, max = 6, message = "Length must be 6")
    private String cardAcceptorPinCode;
    @JsonProperty("card_acceptor_country_code")
    @SerializedName("card_acceptor_country_code")
    @NotNull(message = "card_acceptor_country_code cannot be NULL")
    @NotBlank(message = "The card_acceptor_country_code cannot be Blank")
    private String cardAcceptorCountryCode;
    @JsonProperty("currency_code")
    @SerializedName("currency_code")
    @NotNull(message = "currency_code cannot Be NULL")
    @NotBlank(message = "The currency_code cannot be Blank")
    @Length(max = 3, message = "Max Length is 3")
    private String txnCurrencyCode;
    @JsonProperty("settlement_code")
    @SerializedName("settlement_code")
    @Length(max = 3, message = "Max Length is 3")
    private String settleCurrencyCode;
    @JsonProperty("additional_amount")
    @SerializedName("additional_amount")
    private String cashBackAmount;
    @JsonProperty("channel")
    @SerializedName("channel")
    @NotNull(message = "channel Must not Be NULL")
    @NotBlank(message = "The channel cannot be Blank")
    @Pattern(regexp = "^(?i)(MPOS|POS|E-COM|PG)$", message = "INVALID CHANNEL ; Accept MPOS/POS/E-COM Only")
    @Length(max = 6, message = "Maximum Length is 6")
    private String txnSource;
    @JsonProperty("server_date_time")
    @SerializedName("server_date_time")
    @NotNull(message = "server_date_time Must not Be NULL")
    @NotBlank(message = "The server_date_time cannot be Blank")
    private String serverDateTime;
    @JsonProperty("settlement_indicator")
    @SerializedName("settlement_indicator")
    @NotNull(message = "settlement_indicator Must not Be NULL")
    private Character settlementIndicator;
    @JsonProperty("onus_offus_indicator")
    @SerializedName("onus_offus_indicator")
    @NotNull(message = "onus_offus_indicator Must not Be NULL")
    @NotBlank(message = "The onus_offus_indicator cannot be Blank")
    @Pattern(regexp = "^(?i)(OFFUS|ONUS)$", message = "INVALID ONUS_OFFUS_INDICATOR; Accept ONUS/OFFUS Only")
    private String onusOffusIndicator;
    @JsonProperty("sms_dms_indicator")
    @SerializedName("sms_dms_indicator")
    @NotNull(message = "sms_dms_indicator Must not Be NULL")
    @NotBlank(message = "The sms_dms_indicator cannot be Blank")
    @Pattern(regexp = "^(?i)(SMS|DMS|RTS)$", message = "INVALID SMS_DMS_INDICATOR ; Accept SMS/DMS Only")
    @Length(min = 3, max = 3, message = "Max Length 3")
    private String smsDmsIndicator;
    @JsonProperty("merchant_category_type")
    @SerializedName("merchant_category_type")
    @NotNull(message = "merchant_category_type Must not Be NULL")
    @NotBlank(message = "The merchant_category_type cannot be Blank")
    @Pattern(regexp = "^[SsBb]$", message = "INVALID MERCHANT_CATEGORY_TYPE ; Accept S/B Only")
    private String meCategoryType;
    @JsonProperty("auth_amount")
    @SerializedName("auth_amount")
    @Length(max = 12, message = "Length must be 12")
    private String authAmount;
    @JsonProperty("original_rrn")
    @SerializedName("original_rrn")
    @Length(max = 12, message = "Length must be 12")
    private String originalRRN;
    @JsonProperty("amex_merchant_id")
    @SerializedName("amex_merchant_id")
    @Length(max = 15, message = "Length Should be 15")
    private String amexMerchantId;
    @JsonProperty("merchant_contact_information")
    @SerializedName("merchant_contact_info")
    @Length(max = 252, message = "Length Should be 252")
    private String merchantContactInfo;
    @JsonProperty("merchant_location_id")
    @SerializedName("merchant_location_id")
    @Length(max = 40, message = "Length Should be 40")
    private String merchantLocationId;
    @JsonProperty("location_region_code")
    @SerializedName("location_region_code")
    @Length(max = 3, message = "Length Should be 3")
    private String locationRegionCode;
    @JsonProperty("invoiceNumber")
    @SerializedName("invoiceNumber")
    @Length(max = 30, message = "Length must be 12")
    private String invoiceNumber;
    @JsonProperty("tip_amount")
    @SerializedName("tip_amount")
    private String tipAmount;
    private de48Vo de48_json;
    private de55Vo de55_json;
    private de60_jsonVo de60_json;
    private de61Vo de61_json;
    private de62Vo de62_json;
    private de63Vo de63_json;
    private de111Vo de111_json;
    private de126Vo de126_json;
    private dccVo dcc;
    
    public String getBankCode() {
        return this.bankCode;
    }
    
    public String getNetwork() {
        return this.network;
    }
    
    public String getScheme() {
        return this.scheme;
    }
    
    public String getUniqueId() {
        return this.uniqueId;
    }
    
    public String getMti() {
        return this.mti;
    }
    
    public String getCardNumber() {
        return this.cardNumber;
    }
    
    public String getTokenIdentifier() {
        return this.tokenIdentifier;
    }
    
    public String getProcessCode() {
        return this.processCode;
    }
    
    public String getAmountTransaction() {
        return this.amountTransaction;
    }
    
    public String getAmountSettlement() {
        return this.amountSettlement;
    }
    
    public String getTxnDateTime() {
        return this.txnDateTime;
    }
    
    public String getStan() {
        return this.stan;
    }
    
    public String getLocalTxnTime() {
        return this.localTxnTime;
    }
    
    public String getLocalTxnDate() {
        return this.localTxnDate;
    }
    
    public String getExpiryDate() {
        return this.expiryDate;
    }
    
    public String getSettlementDate() {
        return this.settlementDate;
    }
    
    public String getMcc() {
        return this.mcc;
    }
    
    public String getAcqInsConCode() {
        return this.acqInsConCode;
    }
    
    public String getPosEntryMode() {
        return this.posEntryMode;
    }
    
    public String getPanSequence() {
        return this.panSequence;
    }
    
    public String getPosCode() {
        return this.posCode;
    }
    
    public String getTxnFeeAmount() {
        return this.txnFeeAmount;
    }
    
    public String getAcqInsIdCode() {
        return this.acqInsIdCode;
    }
    
    public String getRetRefNumber() {
        return this.retRefNumber;
    }
    
    public String getAuthIdResponse() {
        return this.authIdResponse;
    }
    
    public String getResponseCode() {
        return this.responseCode;
    }
    
    public String getServiceRestrictionCode() {
        return this.serviceRestrictionCode;
    }
    
    public String getCardAcceptorTid() {
        return this.cardAcceptorTid;
    }
    
    public String getCardAcceptorId() {
        return this.cardAcceptorId;
    }
    
    public String getCardAcceptorName() {
        return this.cardAcceptorName;
    }
    
    public String getCardAcceptorStreetAddress() {
        return this.cardAcceptorStreetAddress;
    }
    
    public String getCardAcceptorCity() {
        return this.cardAcceptorCity;
    }
    
    public String getCardAcceptorStateCode() {
        return this.cardAcceptorStateCode;
    }
    
    public String getCardAcceptorPinCode() {
        return this.cardAcceptorPinCode;
    }
    
    public String getCardAcceptorCountryCode() {
        return this.cardAcceptorCountryCode;
    }
    
    public String getTxnCurrencyCode() {
        return this.txnCurrencyCode;
    }
    
    public String getSettleCurrencyCode() {
        return this.settleCurrencyCode;
    }
    
    public String getCashBackAmount() {
        return this.cashBackAmount;
    }
    
    public String getTxnSource() {
        return this.txnSource;
    }
    
    public String getServerDateTime() {
        return this.serverDateTime;
    }
    
    public Character getSettlementIndicator() {
        return this.settlementIndicator;
    }
    
    public String getOnusOffusIndicator() {
        return this.onusOffusIndicator;
    }
    
    public String getSmsDmsIndicator() {
        return this.smsDmsIndicator;
    }
    
    public String getMeCategoryType() {
        return this.meCategoryType;
    }
    
    public String getAuthAmount() {
        return this.authAmount;
    }
    
    public String getOriginalRRN() {
        return this.originalRRN;
    }
    
    public String getAmexMerchantId() {
        return this.amexMerchantId;
    }
    
    public String getMerchantContactInfo() {
        return this.merchantContactInfo;
    }
    
    public String getMerchantLocationId() {
        return this.merchantLocationId;
    }
    
    public String getLocationRegionCode() {
        return this.locationRegionCode;
    }
    
    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }
    
    public String getTipAmount() {
        return this.tipAmount;
    }
    
    public de48Vo getDe48_json() {
        return this.de48_json;
    }
    
    public de55Vo getDe55_json() {
        return this.de55_json;
    }
    
    public de60_jsonVo getDe60_json() {
        return this.de60_json;
    }
    
    public de61Vo getDe61_json() {
        return this.de61_json;
    }
    
    public de62Vo getDe62_json() {
        return this.de62_json;
    }
    
    public de63Vo getDe63_json() {
        return this.de63_json;
    }
    
    public de111Vo getDe111_json() {
        return this.de111_json;
    }
    
    public de126Vo getDe126_json() {
        return this.de126_json;
    }
    
    public dccVo getDcc() {
        return this.dcc;
    }
    
    @JsonProperty("bank_id")
    public void setBankCode(final String bankCode) {
        this.bankCode = bankCode;
    }
    
    @JsonProperty("sub_route")
    public void setNetwork(final String network) {
        this.network = network;
    }
    
    @JsonProperty("scheme")
    public void setScheme(final String scheme) {
        this.scheme = scheme;
    }
    
    @JsonProperty("ref_id")
    public void setUniqueId(final String uniqueId) {
        this.uniqueId = uniqueId;
    }
    
    @JsonProperty("switch_mti")
    public void setMti(final String mti) {
        this.mti = mti;
    }
    
    @JsonProperty("pan")
    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @JsonProperty("switch_crypt_token")
    public void setTokenIdentifier(final String tokenIdentifier) {
        this.tokenIdentifier = tokenIdentifier;
    }
    
    @JsonProperty("processing_code")
    public void setProcessCode(final String processCode) {
        this.processCode = processCode;
    }
    
    @JsonProperty("amount")
    public void setAmountTransaction(final String amountTransaction) {
        this.amountTransaction = amountTransaction;
    }
    
    @JsonProperty("settlement_amount")
    public void setAmountSettlement(final String amountSettlement) {
        this.amountSettlement = amountSettlement;
    }
    
    @JsonProperty("transmission_date")
    public void setTxnDateTime(final String txnDateTime) {
        this.txnDateTime = txnDateTime;
    }
    
    public void setStan(final String stan) {
        this.stan = stan;
    }
    
    @JsonProperty("local_time")
    public void setLocalTxnTime(final String localTxnTime) {
        this.localTxnTime = localTxnTime;
    }
    
    @JsonProperty("local_date")
    public void setLocalTxnDate(final String localTxnDate) {
        this.localTxnDate = localTxnDate;
    }
    
    @JsonProperty("exp_date")
    public void setExpiryDate(final String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    @JsonProperty("settlement_date")
    public void setSettlementDate(final String settlementDate) {
        this.settlementDate = settlementDate;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    @JsonProperty("acq_inst_country_code")
    public void setAcqInsConCode(final String acqInsConCode) {
        this.acqInsConCode = acqInsConCode;
    }
    
    @JsonProperty("pos_entry_mode")
    public void setPosEntryMode(final String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }
    
    @JsonProperty("pan_sequence_number")
    public void setPanSequence(final String panSequence) {
        this.panSequence = panSequence;
    }
    
    @JsonProperty("pos_condition_code")
    public void setPosCode(final String posCode) {
        this.posCode = posCode;
    }
    
    @JsonProperty("txn_fee_amount")
    public void setTxnFeeAmount(final String txnFeeAmount) {
        this.txnFeeAmount = txnFeeAmount;
    }
    
    @JsonProperty("acquier_id")
    public void setAcqInsIdCode(final String acqInsIdCode) {
        this.acqInsIdCode = acqInsIdCode;
    }
    
    @JsonProperty("rrn")
    public void setRetRefNumber(final String retRefNumber) {
        this.retRefNumber = retRefNumber;
    }
    
    @JsonProperty("auth_code")
    public void setAuthIdResponse(final String authIdResponse) {
        this.authIdResponse = authIdResponse;
    }
    
    @JsonProperty("network_response_code")
    public void setResponseCode(final String responseCode) {
        this.responseCode = responseCode;
    }
    
    @JsonProperty("service_restriction_code")
    public void setServiceRestrictionCode(final String serviceRestrictionCode) {
        this.serviceRestrictionCode = serviceRestrictionCode;
    }
    
    @JsonProperty("terminal_id")
    public void setCardAcceptorTid(final String cardAcceptorTid) {
        this.cardAcceptorTid = cardAcceptorTid;
    }
    
    @JsonProperty("merchant_id")
    public void setCardAcceptorId(final String cardAcceptorId) {
        this.cardAcceptorId = cardAcceptorId;
    }
    
    @JsonProperty("card_acceptor_name")
    public void setCardAcceptorName(final String cardAcceptorName) {
        this.cardAcceptorName = cardAcceptorName;
    }
    
    @JsonProperty("card_acceptor_st_addr")
    public void setCardAcceptorStreetAddress(final String cardAcceptorStreetAddress) {
        this.cardAcceptorStreetAddress = cardAcceptorStreetAddress;
    }
    
    @JsonProperty("card_acceptor_city")
    public void setCardAcceptorCity(final String cardAcceptorCity) {
        this.cardAcceptorCity = cardAcceptorCity;
    }
    
    @JsonProperty("card_acceptor_state_code")
    public void setCardAcceptorStateCode(final String cardAcceptorStateCode) {
        this.cardAcceptorStateCode = cardAcceptorStateCode;
    }
    
    @JsonProperty("card_acceptor_pin_code")
    public void setCardAcceptorPinCode(final String cardAcceptorPinCode) {
        this.cardAcceptorPinCode = cardAcceptorPinCode;
    }
    
    @JsonProperty("card_acceptor_country_code")
    public void setCardAcceptorCountryCode(final String cardAcceptorCountryCode) {
        this.cardAcceptorCountryCode = cardAcceptorCountryCode;
    }
    
    @JsonProperty("currency_code")
    public void setTxnCurrencyCode(final String txnCurrencyCode) {
        this.txnCurrencyCode = txnCurrencyCode;
    }
    
    @JsonProperty("settlement_code")
    public void setSettleCurrencyCode(final String settleCurrencyCode) {
        this.settleCurrencyCode = settleCurrencyCode;
    }
    
    @JsonProperty("additional_amount")
    public void setCashBackAmount(final String cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
    }
    
    @JsonProperty("channel")
    public void setTxnSource(final String txnSource) {
        this.txnSource = txnSource;
    }
    
    @JsonProperty("server_date_time")
    public void setServerDateTime(final String serverDateTime) {
        this.serverDateTime = serverDateTime;
    }
    
    @JsonProperty("settlement_indicator")
    public void setSettlementIndicator(final Character settlementIndicator) {
        this.settlementIndicator = settlementIndicator;
    }
    
    @JsonProperty("onus_offus_indicator")
    public void setOnusOffusIndicator(final String onusOffusIndicator) {
        this.onusOffusIndicator = onusOffusIndicator;
    }
    
    @JsonProperty("sms_dms_indicator")
    public void setSmsDmsIndicator(final String smsDmsIndicator) {
        this.smsDmsIndicator = smsDmsIndicator;
    }
    
    @JsonProperty("merchant_category_type")
    public void setMeCategoryType(final String meCategoryType) {
        this.meCategoryType = meCategoryType;
    }
    
    @JsonProperty("auth_amount")
    public void setAuthAmount(final String authAmount) {
        this.authAmount = authAmount;
    }
    
    @JsonProperty("original_rrn")
    public void setOriginalRRN(final String originalRRN) {
        this.originalRRN = originalRRN;
    }
    
    @JsonProperty("amex_merchant_id")
    public void setAmexMerchantId(final String amexMerchantId) {
        this.amexMerchantId = amexMerchantId;
    }
    
    @JsonProperty("merchant_contact_information")
    public void setMerchantContactInfo(final String merchantContactInfo) {
        this.merchantContactInfo = merchantContactInfo;
    }
    
    @JsonProperty("merchant_location_id")
    public void setMerchantLocationId(final String merchantLocationId) {
        this.merchantLocationId = merchantLocationId;
    }
    
    @JsonProperty("location_region_code")
    public void setLocationRegionCode(final String locationRegionCode) {
        this.locationRegionCode = locationRegionCode;
    }
    
    @JsonProperty("invoiceNumber")
    public void setInvoiceNumber(final String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    
    @JsonProperty("tip_amount")
    public void setTipAmount(final String tipAmount) {
        this.tipAmount = tipAmount;
    }
    
    public void setDe48_json(final de48Vo de48_json) {
        this.de48_json = de48_json;
    }
    
    public void setDe55_json(final de55Vo de55_json) {
        this.de55_json = de55_json;
    }
    
    public void setDe60_json(final de60_jsonVo de60_json) {
        this.de60_json = de60_json;
    }
    
    public void setDe61_json(final de61Vo de61_json) {
        this.de61_json = de61_json;
    }
    
    public void setDe62_json(final de62Vo de62_json) {
        this.de62_json = de62_json;
    }
    
    public void setDe63_json(final de63Vo de63_json) {
        this.de63_json = de63_json;
    }
    
    public void setDe111_json(final de111Vo de111_json) {
        this.de111_json = de111_json;
    }
    
    public void setDe126_json(final de126Vo de126_json) {
        this.de126_json = de126_json;
    }
    
    public void setDcc(final dccVo dcc) {
        this.dcc = dcc;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SwitchExtractVo)) {
            return false;
        }
        final SwitchExtractVo other = (SwitchExtractVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$settlementIndicator = this.getSettlementIndicator();
        final Object other$settlementIndicator = other.getSettlementIndicator();
        Label_0065: {
            if (this$settlementIndicator == null) {
                if (other$settlementIndicator == null) {
                    break Label_0065;
                }
            }
            else if (this$settlementIndicator.equals(other$settlementIndicator)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$bankCode = this.getBankCode();
        final Object other$bankCode = other.getBankCode();
        Label_0102: {
            if (this$bankCode == null) {
                if (other$bankCode == null) {
                    break Label_0102;
                }
            }
            else if (this$bankCode.equals(other$bankCode)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$network = this.getNetwork();
        final Object other$network = other.getNetwork();
        Label_0139: {
            if (this$network == null) {
                if (other$network == null) {
                    break Label_0139;
                }
            }
            else if (this$network.equals(other$network)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$scheme = this.getScheme();
        final Object other$scheme = other.getScheme();
        Label_0176: {
            if (this$scheme == null) {
                if (other$scheme == null) {
                    break Label_0176;
                }
            }
            else if (this$scheme.equals(other$scheme)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$uniqueId = this.getUniqueId();
        final Object other$uniqueId = other.getUniqueId();
        Label_0213: {
            if (this$uniqueId == null) {
                if (other$uniqueId == null) {
                    break Label_0213;
                }
            }
            else if (this$uniqueId.equals(other$uniqueId)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$mti = this.getMti();
        final Object other$mti = other.getMti();
        Label_0250: {
            if (this$mti == null) {
                if (other$mti == null) {
                    break Label_0250;
                }
            }
            else if (this$mti.equals(other$mti)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$cardNumber = this.getCardNumber();
        final Object other$cardNumber = other.getCardNumber();
        Label_0287: {
            if (this$cardNumber == null) {
                if (other$cardNumber == null) {
                    break Label_0287;
                }
            }
            else if (this$cardNumber.equals(other$cardNumber)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$tokenIdentifier = this.getTokenIdentifier();
        final Object other$tokenIdentifier = other.getTokenIdentifier();
        Label_0324: {
            if (this$tokenIdentifier == null) {
                if (other$tokenIdentifier == null) {
                    break Label_0324;
                }
            }
            else if (this$tokenIdentifier.equals(other$tokenIdentifier)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$processCode = this.getProcessCode();
        final Object other$processCode = other.getProcessCode();
        Label_0361: {
            if (this$processCode == null) {
                if (other$processCode == null) {
                    break Label_0361;
                }
            }
            else if (this$processCode.equals(other$processCode)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$amountTransaction = this.getAmountTransaction();
        final Object other$amountTransaction = other.getAmountTransaction();
        Label_0398: {
            if (this$amountTransaction == null) {
                if (other$amountTransaction == null) {
                    break Label_0398;
                }
            }
            else if (this$amountTransaction.equals(other$amountTransaction)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$amountSettlement = this.getAmountSettlement();
        final Object other$amountSettlement = other.getAmountSettlement();
        Label_0435: {
            if (this$amountSettlement == null) {
                if (other$amountSettlement == null) {
                    break Label_0435;
                }
            }
            else if (this$amountSettlement.equals(other$amountSettlement)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$txnDateTime = this.getTxnDateTime();
        final Object other$txnDateTime = other.getTxnDateTime();
        Label_0472: {
            if (this$txnDateTime == null) {
                if (other$txnDateTime == null) {
                    break Label_0472;
                }
            }
            else if (this$txnDateTime.equals(other$txnDateTime)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$stan = this.getStan();
        final Object other$stan = other.getStan();
        Label_0509: {
            if (this$stan == null) {
                if (other$stan == null) {
                    break Label_0509;
                }
            }
            else if (this$stan.equals(other$stan)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$localTxnTime = this.getLocalTxnTime();
        final Object other$localTxnTime = other.getLocalTxnTime();
        Label_0546: {
            if (this$localTxnTime == null) {
                if (other$localTxnTime == null) {
                    break Label_0546;
                }
            }
            else if (this$localTxnTime.equals(other$localTxnTime)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$localTxnDate = this.getLocalTxnDate();
        final Object other$localTxnDate = other.getLocalTxnDate();
        Label_0583: {
            if (this$localTxnDate == null) {
                if (other$localTxnDate == null) {
                    break Label_0583;
                }
            }
            else if (this$localTxnDate.equals(other$localTxnDate)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$expiryDate = this.getExpiryDate();
        final Object other$expiryDate = other.getExpiryDate();
        Label_0620: {
            if (this$expiryDate == null) {
                if (other$expiryDate == null) {
                    break Label_0620;
                }
            }
            else if (this$expiryDate.equals(other$expiryDate)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$settlementDate = this.getSettlementDate();
        final Object other$settlementDate = other.getSettlementDate();
        Label_0657: {
            if (this$settlementDate == null) {
                if (other$settlementDate == null) {
                    break Label_0657;
                }
            }
            else if (this$settlementDate.equals(other$settlementDate)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_0694: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_0694;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_0694;
            }
            return false;
        }
        final Object this$acqInsConCode = this.getAcqInsConCode();
        final Object other$acqInsConCode = other.getAcqInsConCode();
        Label_0731: {
            if (this$acqInsConCode == null) {
                if (other$acqInsConCode == null) {
                    break Label_0731;
                }
            }
            else if (this$acqInsConCode.equals(other$acqInsConCode)) {
                break Label_0731;
            }
            return false;
        }
        final Object this$posEntryMode = this.getPosEntryMode();
        final Object other$posEntryMode = other.getPosEntryMode();
        Label_0768: {
            if (this$posEntryMode == null) {
                if (other$posEntryMode == null) {
                    break Label_0768;
                }
            }
            else if (this$posEntryMode.equals(other$posEntryMode)) {
                break Label_0768;
            }
            return false;
        }
        final Object this$panSequence = this.getPanSequence();
        final Object other$panSequence = other.getPanSequence();
        Label_0805: {
            if (this$panSequence == null) {
                if (other$panSequence == null) {
                    break Label_0805;
                }
            }
            else if (this$panSequence.equals(other$panSequence)) {
                break Label_0805;
            }
            return false;
        }
        final Object this$posCode = this.getPosCode();
        final Object other$posCode = other.getPosCode();
        Label_0842: {
            if (this$posCode == null) {
                if (other$posCode == null) {
                    break Label_0842;
                }
            }
            else if (this$posCode.equals(other$posCode)) {
                break Label_0842;
            }
            return false;
        }
        final Object this$txnFeeAmount = this.getTxnFeeAmount();
        final Object other$txnFeeAmount = other.getTxnFeeAmount();
        Label_0879: {
            if (this$txnFeeAmount == null) {
                if (other$txnFeeAmount == null) {
                    break Label_0879;
                }
            }
            else if (this$txnFeeAmount.equals(other$txnFeeAmount)) {
                break Label_0879;
            }
            return false;
        }
        final Object this$acqInsIdCode = this.getAcqInsIdCode();
        final Object other$acqInsIdCode = other.getAcqInsIdCode();
        Label_0916: {
            if (this$acqInsIdCode == null) {
                if (other$acqInsIdCode == null) {
                    break Label_0916;
                }
            }
            else if (this$acqInsIdCode.equals(other$acqInsIdCode)) {
                break Label_0916;
            }
            return false;
        }
        final Object this$retRefNumber = this.getRetRefNumber();
        final Object other$retRefNumber = other.getRetRefNumber();
        Label_0953: {
            if (this$retRefNumber == null) {
                if (other$retRefNumber == null) {
                    break Label_0953;
                }
            }
            else if (this$retRefNumber.equals(other$retRefNumber)) {
                break Label_0953;
            }
            return false;
        }
        final Object this$authIdResponse = this.getAuthIdResponse();
        final Object other$authIdResponse = other.getAuthIdResponse();
        Label_0990: {
            if (this$authIdResponse == null) {
                if (other$authIdResponse == null) {
                    break Label_0990;
                }
            }
            else if (this$authIdResponse.equals(other$authIdResponse)) {
                break Label_0990;
            }
            return false;
        }
        final Object this$responseCode = this.getResponseCode();
        final Object other$responseCode = other.getResponseCode();
        Label_1027: {
            if (this$responseCode == null) {
                if (other$responseCode == null) {
                    break Label_1027;
                }
            }
            else if (this$responseCode.equals(other$responseCode)) {
                break Label_1027;
            }
            return false;
        }
        final Object this$serviceRestrictionCode = this.getServiceRestrictionCode();
        final Object other$serviceRestrictionCode = other.getServiceRestrictionCode();
        Label_1064: {
            if (this$serviceRestrictionCode == null) {
                if (other$serviceRestrictionCode == null) {
                    break Label_1064;
                }
            }
            else if (this$serviceRestrictionCode.equals(other$serviceRestrictionCode)) {
                break Label_1064;
            }
            return false;
        }
        final Object this$cardAcceptorTid = this.getCardAcceptorTid();
        final Object other$cardAcceptorTid = other.getCardAcceptorTid();
        Label_1101: {
            if (this$cardAcceptorTid == null) {
                if (other$cardAcceptorTid == null) {
                    break Label_1101;
                }
            }
            else if (this$cardAcceptorTid.equals(other$cardAcceptorTid)) {
                break Label_1101;
            }
            return false;
        }
        final Object this$cardAcceptorId = this.getCardAcceptorId();
        final Object other$cardAcceptorId = other.getCardAcceptorId();
        Label_1138: {
            if (this$cardAcceptorId == null) {
                if (other$cardAcceptorId == null) {
                    break Label_1138;
                }
            }
            else if (this$cardAcceptorId.equals(other$cardAcceptorId)) {
                break Label_1138;
            }
            return false;
        }
        final Object this$cardAcceptorName = this.getCardAcceptorName();
        final Object other$cardAcceptorName = other.getCardAcceptorName();
        Label_1175: {
            if (this$cardAcceptorName == null) {
                if (other$cardAcceptorName == null) {
                    break Label_1175;
                }
            }
            else if (this$cardAcceptorName.equals(other$cardAcceptorName)) {
                break Label_1175;
            }
            return false;
        }
        final Object this$cardAcceptorStreetAddress = this.getCardAcceptorStreetAddress();
        final Object other$cardAcceptorStreetAddress = other.getCardAcceptorStreetAddress();
        Label_1212: {
            if (this$cardAcceptorStreetAddress == null) {
                if (other$cardAcceptorStreetAddress == null) {
                    break Label_1212;
                }
            }
            else if (this$cardAcceptorStreetAddress.equals(other$cardAcceptorStreetAddress)) {
                break Label_1212;
            }
            return false;
        }
        final Object this$cardAcceptorCity = this.getCardAcceptorCity();
        final Object other$cardAcceptorCity = other.getCardAcceptorCity();
        Label_1249: {
            if (this$cardAcceptorCity == null) {
                if (other$cardAcceptorCity == null) {
                    break Label_1249;
                }
            }
            else if (this$cardAcceptorCity.equals(other$cardAcceptorCity)) {
                break Label_1249;
            }
            return false;
        }
        final Object this$cardAcceptorStateCode = this.getCardAcceptorStateCode();
        final Object other$cardAcceptorStateCode = other.getCardAcceptorStateCode();
        Label_1286: {
            if (this$cardAcceptorStateCode == null) {
                if (other$cardAcceptorStateCode == null) {
                    break Label_1286;
                }
            }
            else if (this$cardAcceptorStateCode.equals(other$cardAcceptorStateCode)) {
                break Label_1286;
            }
            return false;
        }
        final Object this$cardAcceptorPinCode = this.getCardAcceptorPinCode();
        final Object other$cardAcceptorPinCode = other.getCardAcceptorPinCode();
        Label_1323: {
            if (this$cardAcceptorPinCode == null) {
                if (other$cardAcceptorPinCode == null) {
                    break Label_1323;
                }
            }
            else if (this$cardAcceptorPinCode.equals(other$cardAcceptorPinCode)) {
                break Label_1323;
            }
            return false;
        }
        final Object this$cardAcceptorCountryCode = this.getCardAcceptorCountryCode();
        final Object other$cardAcceptorCountryCode = other.getCardAcceptorCountryCode();
        Label_1360: {
            if (this$cardAcceptorCountryCode == null) {
                if (other$cardAcceptorCountryCode == null) {
                    break Label_1360;
                }
            }
            else if (this$cardAcceptorCountryCode.equals(other$cardAcceptorCountryCode)) {
                break Label_1360;
            }
            return false;
        }
        final Object this$txnCurrencyCode = this.getTxnCurrencyCode();
        final Object other$txnCurrencyCode = other.getTxnCurrencyCode();
        Label_1397: {
            if (this$txnCurrencyCode == null) {
                if (other$txnCurrencyCode == null) {
                    break Label_1397;
                }
            }
            else if (this$txnCurrencyCode.equals(other$txnCurrencyCode)) {
                break Label_1397;
            }
            return false;
        }
        final Object this$settleCurrencyCode = this.getSettleCurrencyCode();
        final Object other$settleCurrencyCode = other.getSettleCurrencyCode();
        Label_1434: {
            if (this$settleCurrencyCode == null) {
                if (other$settleCurrencyCode == null) {
                    break Label_1434;
                }
            }
            else if (this$settleCurrencyCode.equals(other$settleCurrencyCode)) {
                break Label_1434;
            }
            return false;
        }
        final Object this$cashBackAmount = this.getCashBackAmount();
        final Object other$cashBackAmount = other.getCashBackAmount();
        Label_1471: {
            if (this$cashBackAmount == null) {
                if (other$cashBackAmount == null) {
                    break Label_1471;
                }
            }
            else if (this$cashBackAmount.equals(other$cashBackAmount)) {
                break Label_1471;
            }
            return false;
        }
        final Object this$txnSource = this.getTxnSource();
        final Object other$txnSource = other.getTxnSource();
        Label_1508: {
            if (this$txnSource == null) {
                if (other$txnSource == null) {
                    break Label_1508;
                }
            }
            else if (this$txnSource.equals(other$txnSource)) {
                break Label_1508;
            }
            return false;
        }
        final Object this$serverDateTime = this.getServerDateTime();
        final Object other$serverDateTime = other.getServerDateTime();
        Label_1545: {
            if (this$serverDateTime == null) {
                if (other$serverDateTime == null) {
                    break Label_1545;
                }
            }
            else if (this$serverDateTime.equals(other$serverDateTime)) {
                break Label_1545;
            }
            return false;
        }
        final Object this$onusOffusIndicator = this.getOnusOffusIndicator();
        final Object other$onusOffusIndicator = other.getOnusOffusIndicator();
        Label_1582: {
            if (this$onusOffusIndicator == null) {
                if (other$onusOffusIndicator == null) {
                    break Label_1582;
                }
            }
            else if (this$onusOffusIndicator.equals(other$onusOffusIndicator)) {
                break Label_1582;
            }
            return false;
        }
        final Object this$smsDmsIndicator = this.getSmsDmsIndicator();
        final Object other$smsDmsIndicator = other.getSmsDmsIndicator();
        Label_1619: {
            if (this$smsDmsIndicator == null) {
                if (other$smsDmsIndicator == null) {
                    break Label_1619;
                }
            }
            else if (this$smsDmsIndicator.equals(other$smsDmsIndicator)) {
                break Label_1619;
            }
            return false;
        }
        final Object this$meCategoryType = this.getMeCategoryType();
        final Object other$meCategoryType = other.getMeCategoryType();
        Label_1656: {
            if (this$meCategoryType == null) {
                if (other$meCategoryType == null) {
                    break Label_1656;
                }
            }
            else if (this$meCategoryType.equals(other$meCategoryType)) {
                break Label_1656;
            }
            return false;
        }
        final Object this$authAmount = this.getAuthAmount();
        final Object other$authAmount = other.getAuthAmount();
        Label_1693: {
            if (this$authAmount == null) {
                if (other$authAmount == null) {
                    break Label_1693;
                }
            }
            else if (this$authAmount.equals(other$authAmount)) {
                break Label_1693;
            }
            return false;
        }
        final Object this$originalRRN = this.getOriginalRRN();
        final Object other$originalRRN = other.getOriginalRRN();
        Label_1730: {
            if (this$originalRRN == null) {
                if (other$originalRRN == null) {
                    break Label_1730;
                }
            }
            else if (this$originalRRN.equals(other$originalRRN)) {
                break Label_1730;
            }
            return false;
        }
        final Object this$amexMerchantId = this.getAmexMerchantId();
        final Object other$amexMerchantId = other.getAmexMerchantId();
        Label_1767: {
            if (this$amexMerchantId == null) {
                if (other$amexMerchantId == null) {
                    break Label_1767;
                }
            }
            else if (this$amexMerchantId.equals(other$amexMerchantId)) {
                break Label_1767;
            }
            return false;
        }
        final Object this$merchantContactInfo = this.getMerchantContactInfo();
        final Object other$merchantContactInfo = other.getMerchantContactInfo();
        Label_1804: {
            if (this$merchantContactInfo == null) {
                if (other$merchantContactInfo == null) {
                    break Label_1804;
                }
            }
            else if (this$merchantContactInfo.equals(other$merchantContactInfo)) {
                break Label_1804;
            }
            return false;
        }
        final Object this$merchantLocationId = this.getMerchantLocationId();
        final Object other$merchantLocationId = other.getMerchantLocationId();
        Label_1841: {
            if (this$merchantLocationId == null) {
                if (other$merchantLocationId == null) {
                    break Label_1841;
                }
            }
            else if (this$merchantLocationId.equals(other$merchantLocationId)) {
                break Label_1841;
            }
            return false;
        }
        final Object this$locationRegionCode = this.getLocationRegionCode();
        final Object other$locationRegionCode = other.getLocationRegionCode();
        Label_1878: {
            if (this$locationRegionCode == null) {
                if (other$locationRegionCode == null) {
                    break Label_1878;
                }
            }
            else if (this$locationRegionCode.equals(other$locationRegionCode)) {
                break Label_1878;
            }
            return false;
        }
        final Object this$invoiceNumber = this.getInvoiceNumber();
        final Object other$invoiceNumber = other.getInvoiceNumber();
        Label_1915: {
            if (this$invoiceNumber == null) {
                if (other$invoiceNumber == null) {
                    break Label_1915;
                }
            }
            else if (this$invoiceNumber.equals(other$invoiceNumber)) {
                break Label_1915;
            }
            return false;
        }
        final Object this$tipAmount = this.getTipAmount();
        final Object other$tipAmount = other.getTipAmount();
        Label_1952: {
            if (this$tipAmount == null) {
                if (other$tipAmount == null) {
                    break Label_1952;
                }
            }
            else if (this$tipAmount.equals(other$tipAmount)) {
                break Label_1952;
            }
            return false;
        }
        final Object this$de48_json = this.getDe48_json();
        final Object other$de48_json = other.getDe48_json();
        Label_1989: {
            if (this$de48_json == null) {
                if (other$de48_json == null) {
                    break Label_1989;
                }
            }
            else if (this$de48_json.equals(other$de48_json)) {
                break Label_1989;
            }
            return false;
        }
        final Object this$de55_json = this.getDe55_json();
        final Object other$de55_json = other.getDe55_json();
        Label_2026: {
            if (this$de55_json == null) {
                if (other$de55_json == null) {
                    break Label_2026;
                }
            }
            else if (this$de55_json.equals(other$de55_json)) {
                break Label_2026;
            }
            return false;
        }
        final Object this$de60_json = this.getDe60_json();
        final Object other$de60_json = other.getDe60_json();
        Label_2063: {
            if (this$de60_json == null) {
                if (other$de60_json == null) {
                    break Label_2063;
                }
            }
            else if (this$de60_json.equals(other$de60_json)) {
                break Label_2063;
            }
            return false;
        }
        final Object this$de61_json = this.getDe61_json();
        final Object other$de61_json = other.getDe61_json();
        Label_2100: {
            if (this$de61_json == null) {
                if (other$de61_json == null) {
                    break Label_2100;
                }
            }
            else if (this$de61_json.equals(other$de61_json)) {
                break Label_2100;
            }
            return false;
        }
        final Object this$de62_json = this.getDe62_json();
        final Object other$de62_json = other.getDe62_json();
        Label_2137: {
            if (this$de62_json == null) {
                if (other$de62_json == null) {
                    break Label_2137;
                }
            }
            else if (this$de62_json.equals(other$de62_json)) {
                break Label_2137;
            }
            return false;
        }
        final Object this$de63_json = this.getDe63_json();
        final Object other$de63_json = other.getDe63_json();
        Label_2174: {
            if (this$de63_json == null) {
                if (other$de63_json == null) {
                    break Label_2174;
                }
            }
            else if (this$de63_json.equals(other$de63_json)) {
                break Label_2174;
            }
            return false;
        }
        final Object this$de111_json = this.getDe111_json();
        final Object other$de111_json = other.getDe111_json();
        Label_2211: {
            if (this$de111_json == null) {
                if (other$de111_json == null) {
                    break Label_2211;
                }
            }
            else if (this$de111_json.equals(other$de111_json)) {
                break Label_2211;
            }
            return false;
        }
        final Object this$de126_json = this.getDe126_json();
        final Object other$de126_json = other.getDe126_json();
        Label_2248: {
            if (this$de126_json == null) {
                if (other$de126_json == null) {
                    break Label_2248;
                }
            }
            else if (this$de126_json.equals(other$de126_json)) {
                break Label_2248;
            }
            return false;
        }
        final Object this$dcc = this.getDcc();
        final Object other$dcc = other.getDcc();
        if (this$dcc == null) {
            if (other$dcc == null) {
                return true;
            }
        }
        else if (this$dcc.equals(other$dcc)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof SwitchExtractVo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $settlementIndicator = this.getSettlementIndicator();
        result = result * 59 + (($settlementIndicator == null) ? 43 : $settlementIndicator.hashCode());
        final Object $bankCode = this.getBankCode();
        result = result * 59 + (($bankCode == null) ? 43 : $bankCode.hashCode());
        final Object $network = this.getNetwork();
        result = result * 59 + (($network == null) ? 43 : $network.hashCode());
        final Object $scheme = this.getScheme();
        result = result * 59 + (($scheme == null) ? 43 : $scheme.hashCode());
        final Object $uniqueId = this.getUniqueId();
        result = result * 59 + (($uniqueId == null) ? 43 : $uniqueId.hashCode());
        final Object $mti = this.getMti();
        result = result * 59 + (($mti == null) ? 43 : $mti.hashCode());
        final Object $cardNumber = this.getCardNumber();
        result = result * 59 + (($cardNumber == null) ? 43 : $cardNumber.hashCode());
        final Object $tokenIdentifier = this.getTokenIdentifier();
        result = result * 59 + (($tokenIdentifier == null) ? 43 : $tokenIdentifier.hashCode());
        final Object $processCode = this.getProcessCode();
        result = result * 59 + (($processCode == null) ? 43 : $processCode.hashCode());
        final Object $amountTransaction = this.getAmountTransaction();
        result = result * 59 + (($amountTransaction == null) ? 43 : $amountTransaction.hashCode());
        final Object $amountSettlement = this.getAmountSettlement();
        result = result * 59 + (($amountSettlement == null) ? 43 : $amountSettlement.hashCode());
        final Object $txnDateTime = this.getTxnDateTime();
        result = result * 59 + (($txnDateTime == null) ? 43 : $txnDateTime.hashCode());
        final Object $stan = this.getStan();
        result = result * 59 + (($stan == null) ? 43 : $stan.hashCode());
        final Object $localTxnTime = this.getLocalTxnTime();
        result = result * 59 + (($localTxnTime == null) ? 43 : $localTxnTime.hashCode());
        final Object $localTxnDate = this.getLocalTxnDate();
        result = result * 59 + (($localTxnDate == null) ? 43 : $localTxnDate.hashCode());
        final Object $expiryDate = this.getExpiryDate();
        result = result * 59 + (($expiryDate == null) ? 43 : $expiryDate.hashCode());
        final Object $settlementDate = this.getSettlementDate();
        result = result * 59 + (($settlementDate == null) ? 43 : $settlementDate.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $acqInsConCode = this.getAcqInsConCode();
        result = result * 59 + (($acqInsConCode == null) ? 43 : $acqInsConCode.hashCode());
        final Object $posEntryMode = this.getPosEntryMode();
        result = result * 59 + (($posEntryMode == null) ? 43 : $posEntryMode.hashCode());
        final Object $panSequence = this.getPanSequence();
        result = result * 59 + (($panSequence == null) ? 43 : $panSequence.hashCode());
        final Object $posCode = this.getPosCode();
        result = result * 59 + (($posCode == null) ? 43 : $posCode.hashCode());
        final Object $txnFeeAmount = this.getTxnFeeAmount();
        result = result * 59 + (($txnFeeAmount == null) ? 43 : $txnFeeAmount.hashCode());
        final Object $acqInsIdCode = this.getAcqInsIdCode();
        result = result * 59 + (($acqInsIdCode == null) ? 43 : $acqInsIdCode.hashCode());
        final Object $retRefNumber = this.getRetRefNumber();
        result = result * 59 + (($retRefNumber == null) ? 43 : $retRefNumber.hashCode());
        final Object $authIdResponse = this.getAuthIdResponse();
        result = result * 59 + (($authIdResponse == null) ? 43 : $authIdResponse.hashCode());
        final Object $responseCode = this.getResponseCode();
        result = result * 59 + (($responseCode == null) ? 43 : $responseCode.hashCode());
        final Object $serviceRestrictionCode = this.getServiceRestrictionCode();
        result = result * 59 + (($serviceRestrictionCode == null) ? 43 : $serviceRestrictionCode.hashCode());
        final Object $cardAcceptorTid = this.getCardAcceptorTid();
        result = result * 59 + (($cardAcceptorTid == null) ? 43 : $cardAcceptorTid.hashCode());
        final Object $cardAcceptorId = this.getCardAcceptorId();
        result = result * 59 + (($cardAcceptorId == null) ? 43 : $cardAcceptorId.hashCode());
        final Object $cardAcceptorName = this.getCardAcceptorName();
        result = result * 59 + (($cardAcceptorName == null) ? 43 : $cardAcceptorName.hashCode());
        final Object $cardAcceptorStreetAddress = this.getCardAcceptorStreetAddress();
        result = result * 59 + (($cardAcceptorStreetAddress == null) ? 43 : $cardAcceptorStreetAddress.hashCode());
        final Object $cardAcceptorCity = this.getCardAcceptorCity();
        result = result * 59 + (($cardAcceptorCity == null) ? 43 : $cardAcceptorCity.hashCode());
        final Object $cardAcceptorStateCode = this.getCardAcceptorStateCode();
        result = result * 59 + (($cardAcceptorStateCode == null) ? 43 : $cardAcceptorStateCode.hashCode());
        final Object $cardAcceptorPinCode = this.getCardAcceptorPinCode();
        result = result * 59 + (($cardAcceptorPinCode == null) ? 43 : $cardAcceptorPinCode.hashCode());
        final Object $cardAcceptorCountryCode = this.getCardAcceptorCountryCode();
        result = result * 59 + (($cardAcceptorCountryCode == null) ? 43 : $cardAcceptorCountryCode.hashCode());
        final Object $txnCurrencyCode = this.getTxnCurrencyCode();
        result = result * 59 + (($txnCurrencyCode == null) ? 43 : $txnCurrencyCode.hashCode());
        final Object $settleCurrencyCode = this.getSettleCurrencyCode();
        result = result * 59 + (($settleCurrencyCode == null) ? 43 : $settleCurrencyCode.hashCode());
        final Object $cashBackAmount = this.getCashBackAmount();
        result = result * 59 + (($cashBackAmount == null) ? 43 : $cashBackAmount.hashCode());
        final Object $txnSource = this.getTxnSource();
        result = result * 59 + (($txnSource == null) ? 43 : $txnSource.hashCode());
        final Object $serverDateTime = this.getServerDateTime();
        result = result * 59 + (($serverDateTime == null) ? 43 : $serverDateTime.hashCode());
        final Object $onusOffusIndicator = this.getOnusOffusIndicator();
        result = result * 59 + (($onusOffusIndicator == null) ? 43 : $onusOffusIndicator.hashCode());
        final Object $smsDmsIndicator = this.getSmsDmsIndicator();
        result = result * 59 + (($smsDmsIndicator == null) ? 43 : $smsDmsIndicator.hashCode());
        final Object $meCategoryType = this.getMeCategoryType();
        result = result * 59 + (($meCategoryType == null) ? 43 : $meCategoryType.hashCode());
        final Object $authAmount = this.getAuthAmount();
        result = result * 59 + (($authAmount == null) ? 43 : $authAmount.hashCode());
        final Object $originalRRN = this.getOriginalRRN();
        result = result * 59 + (($originalRRN == null) ? 43 : $originalRRN.hashCode());
        final Object $amexMerchantId = this.getAmexMerchantId();
        result = result * 59 + (($amexMerchantId == null) ? 43 : $amexMerchantId.hashCode());
        final Object $merchantContactInfo = this.getMerchantContactInfo();
        result = result * 59 + (($merchantContactInfo == null) ? 43 : $merchantContactInfo.hashCode());
        final Object $merchantLocationId = this.getMerchantLocationId();
        result = result * 59 + (($merchantLocationId == null) ? 43 : $merchantLocationId.hashCode());
        final Object $locationRegionCode = this.getLocationRegionCode();
        result = result * 59 + (($locationRegionCode == null) ? 43 : $locationRegionCode.hashCode());
        final Object $invoiceNumber = this.getInvoiceNumber();
        result = result * 59 + (($invoiceNumber == null) ? 43 : $invoiceNumber.hashCode());
        final Object $tipAmount = this.getTipAmount();
        result = result * 59 + (($tipAmount == null) ? 43 : $tipAmount.hashCode());
        final Object $de48_json = this.getDe48_json();
        result = result * 59 + (($de48_json == null) ? 43 : $de48_json.hashCode());
        final Object $de55_json = this.getDe55_json();
        result = result * 59 + (($de55_json == null) ? 43 : $de55_json.hashCode());
        final Object $de60_json = this.getDe60_json();
        result = result * 59 + (($de60_json == null) ? 43 : $de60_json.hashCode());
        final Object $de61_json = this.getDe61_json();
        result = result * 59 + (($de61_json == null) ? 43 : $de61_json.hashCode());
        final Object $de62_json = this.getDe62_json();
        result = result * 59 + (($de62_json == null) ? 43 : $de62_json.hashCode());
        final Object $de63_json = this.getDe63_json();
        result = result * 59 + (($de63_json == null) ? 43 : $de63_json.hashCode());
        final Object $de111_json = this.getDe111_json();
        result = result * 59 + (($de111_json == null) ? 43 : $de111_json.hashCode());
        final Object $de126_json = this.getDe126_json();
        result = result * 59 + (($de126_json == null) ? 43 : $de126_json.hashCode());
        final Object $dcc = this.getDcc();
        result = result * 59 + (($dcc == null) ? 43 : $dcc.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "SwitchExtractVo(bankCode=" + this.getBankCode() + ", network=" + this.getNetwork() + ", scheme=" + this.getScheme() + ", uniqueId=" + this.getUniqueId() + ", mti=" + this.getMti() + ", cardNumber=" + this.getCardNumber() + ", tokenIdentifier=" + this.getTokenIdentifier() + ", processCode=" + this.getProcessCode() + ", amountTransaction=" + this.getAmountTransaction() + ", amountSettlement=" + this.getAmountSettlement() + ", txnDateTime=" + this.getTxnDateTime() + ", stan=" + this.getStan() + ", localTxnTime=" + this.getLocalTxnTime() + ", localTxnDate=" + this.getLocalTxnDate() + ", expiryDate=" + this.getExpiryDate() + ", settlementDate=" + this.getSettlementDate() + ", mcc=" + this.getMcc() + ", acqInsConCode=" + this.getAcqInsConCode() + ", posEntryMode=" + this.getPosEntryMode() + ", panSequence=" + this.getPanSequence() + ", posCode=" + this.getPosCode() + ", txnFeeAmount=" + this.getTxnFeeAmount() + ", acqInsIdCode=" + this.getAcqInsIdCode() + ", retRefNumber=" + this.getRetRefNumber() + ", authIdResponse=" + this.getAuthIdResponse() + ", responseCode=" + this.getResponseCode() + ", serviceRestrictionCode=" + this.getServiceRestrictionCode() + ", cardAcceptorTid=" + this.getCardAcceptorTid() + ", cardAcceptorId=" + this.getCardAcceptorId() + ", cardAcceptorName=" + this.getCardAcceptorName() + ", cardAcceptorStreetAddress=" + this.getCardAcceptorStreetAddress() + ", cardAcceptorCity=" + this.getCardAcceptorCity() + ", cardAcceptorStateCode=" + this.getCardAcceptorStateCode() + ", cardAcceptorPinCode=" + this.getCardAcceptorPinCode() + ", cardAcceptorCountryCode=" + this.getCardAcceptorCountryCode() + ", txnCurrencyCode=" + this.getTxnCurrencyCode() + ", settleCurrencyCode=" + this.getSettleCurrencyCode() + ", cashBackAmount=" + this.getCashBackAmount() + ", txnSource=" + this.getTxnSource() + ", serverDateTime=" + this.getServerDateTime() + ", settlementIndicator=" + this.getSettlementIndicator() + ", onusOffusIndicator=" + this.getOnusOffusIndicator() + ", smsDmsIndicator=" + this.getSmsDmsIndicator() + ", meCategoryType=" + this.getMeCategoryType() + ", authAmount=" + this.getAuthAmount() + ", originalRRN=" + this.getOriginalRRN() + ", amexMerchantId=" + this.getAmexMerchantId() + ", merchantContactInfo=" + this.getMerchantContactInfo() + ", merchantLocationId=" + this.getMerchantLocationId() + ", locationRegionCode=" + this.getLocationRegionCode() + ", invoiceNumber=" + this.getInvoiceNumber() + ", tipAmount=" + this.getTipAmount() + ", de48_json=" + String.valueOf(this.getDe48_json()) + ", de55_json=" + String.valueOf(this.getDe55_json()) + ", de60_json=" + String.valueOf(this.getDe60_json()) + ", de61_json=" + String.valueOf(this.getDe61_json()) + ", de62_json=" + String.valueOf(this.getDe62_json()) + ", de63_json=" + String.valueOf(this.getDe63_json()) + ", de111_json=" + String.valueOf(this.getDe111_json()) + ", de126_json=" + String.valueOf(this.getDe126_json()) + ", dcc=" + String.valueOf(this.getDcc());
    }
}
