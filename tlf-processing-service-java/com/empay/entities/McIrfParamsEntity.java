// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "MC_IRF_PARAMS")
public class McIrfParamsEntity
{
    @Id
    @Column(name = "MIP_SER_NUMBER")
    private Integer serNumber;
    @Column(name = "MIP_INS_CODE")
    private Integer insCode;
    @Column(name = "MIP_PAN")
    private String pan;
    @Column(name = "MIP_TXN_AMOUNT")
    private Double txnAmount;
    @Column(name = "MIP_CASHBK_AMOUNT")
    private Double cashBackAmount;
    @Column(name = "MIP_TRL_TYPE")
    private String terminalType;
    @Column(name = "MIP_TXN_DATE_TIME")
    private LocalDateTime txnDateTime;
    @Column(name = "MIP_MCC")
    private String mcc;
    @Column(name = "MIP_NETWORK")
    private String network;
    @Column(name = "MIP_TXN_TYPE")
    private String txnType;
    @Column(name = "MIP_APPR_CODE")
    private String approvalCode;
    @Column(name = "MIP_TXN_ID")
    private String txnId;
    @Column(name = "MIP_POS_ENTRY_MODE")
    private String posEntryMode;
    @Column(name = "MIP_SERVICE_CODE")
    private String serviceCode;
    @Column(name = "MIP_CARD_INPUT_ABILITY")
    private Character cardInputAbility;
    @Column(name = "MIP_CH_AUTH_ABILITY")
    private Character chAuthAbility;
    @Column(name = "MIP_CARD_CAPTURE_ABILITY")
    private Character cardCaptureAbility;
    @Column(name = "MIP_OPRT_ENVIRONMENT")
    private Character oprtEnvironment;
    @Column(name = "MIP_CH_PRESENT")
    private Character chPresent;
    @Column(name = "MIP_CARD_PRESENT")
    private Character cardPresent;
    @Column(name = "MIP_CARD_INPUT_MODE")
    private Character cardInputMode;
    @Column(name = "MIP_MER_TYPE")
    private Character merType;
    @Column(name = "MIP_MAID")
    private String maid;
    @Column(name = "MIP_PROG_REGION")
    private Character progRegion;
    @Column(name = "MIP_TIMELINE")
    private Integer timeLine;
    @Column(name = "MIP_APPR_CODE_FLAG")
    private Integer apprCodeFlag;
    @Column(name = "MIP_MAGSTRIPE_FLAG")
    private Integer magStripeFlag;
    @Column(name = "MIP_TRACE_ID_FLAG")
    private Integer traceIdFlag;
    @Column(name = "MIP_MC_ASSIG_ID_FLAG")
    private Integer mcAssignIdFlag;
    @Column(name = "MIP_ISSUER_REGION")
    private Character issuerRegion;
    @Column(name = "MIP_CARD_PROG_ID")
    private String cardProgId;
    @Column(name = "MIP_GCMS_PROD_ID")
    private String gcmsProdId;
    @Column(name = "MIP_ECOM_INDICATOR")
    private String ecomIndicator;
    
    public static McIrfParamsEntityBuilder builder() {
        return new McIrfParamsEntityBuilder();
    }
    
    public Integer getSerNumber() {
        return this.serNumber;
    }
    
    public Integer getInsCode() {
        return this.insCode;
    }
    
    public String getPan() {
        return this.pan;
    }
    
    public Double getTxnAmount() {
        return this.txnAmount;
    }
    
    public Double getCashBackAmount() {
        return this.cashBackAmount;
    }
    
    public String getTerminalType() {
        return this.terminalType;
    }
    
    public LocalDateTime getTxnDateTime() {
        return this.txnDateTime;
    }
    
    public String getMcc() {
        return this.mcc;
    }
    
    public String getNetwork() {
        return this.network;
    }
    
    public String getTxnType() {
        return this.txnType;
    }
    
    public String getApprovalCode() {
        return this.approvalCode;
    }
    
    public String getTxnId() {
        return this.txnId;
    }
    
    public String getPosEntryMode() {
        return this.posEntryMode;
    }
    
    public String getServiceCode() {
        return this.serviceCode;
    }
    
    public Character getCardInputAbility() {
        return this.cardInputAbility;
    }
    
    public Character getChAuthAbility() {
        return this.chAuthAbility;
    }
    
    public Character getCardCaptureAbility() {
        return this.cardCaptureAbility;
    }
    
    public Character getOprtEnvironment() {
        return this.oprtEnvironment;
    }
    
    public Character getChPresent() {
        return this.chPresent;
    }
    
    public Character getCardPresent() {
        return this.cardPresent;
    }
    
    public Character getCardInputMode() {
        return this.cardInputMode;
    }
    
    public Character getMerType() {
        return this.merType;
    }
    
    public String getMaid() {
        return this.maid;
    }
    
    public Character getProgRegion() {
        return this.progRegion;
    }
    
    public Integer getTimeLine() {
        return this.timeLine;
    }
    
    public Integer getApprCodeFlag() {
        return this.apprCodeFlag;
    }
    
    public Integer getMagStripeFlag() {
        return this.magStripeFlag;
    }
    
    public Integer getTraceIdFlag() {
        return this.traceIdFlag;
    }
    
    public Integer getMcAssignIdFlag() {
        return this.mcAssignIdFlag;
    }
    
    public Character getIssuerRegion() {
        return this.issuerRegion;
    }
    
    public String getCardProgId() {
        return this.cardProgId;
    }
    
    public String getGcmsProdId() {
        return this.gcmsProdId;
    }
    
    public String getEcomIndicator() {
        return this.ecomIndicator;
    }
    
    public void setSerNumber(final Integer serNumber) {
        this.serNumber = serNumber;
    }
    
    public void setInsCode(final Integer insCode) {
        this.insCode = insCode;
    }
    
    public void setPan(final String pan) {
        this.pan = pan;
    }
    
    public void setTxnAmount(final Double txnAmount) {
        this.txnAmount = txnAmount;
    }
    
    public void setCashBackAmount(final Double cashBackAmount) {
        this.cashBackAmount = cashBackAmount;
    }
    
    public void setTerminalType(final String terminalType) {
        this.terminalType = terminalType;
    }
    
    public void setTxnDateTime(final LocalDateTime txnDateTime) {
        this.txnDateTime = txnDateTime;
    }
    
    public void setMcc(final String mcc) {
        this.mcc = mcc;
    }
    
    public void setNetwork(final String network) {
        this.network = network;
    }
    
    public void setTxnType(final String txnType) {
        this.txnType = txnType;
    }
    
    public void setApprovalCode(final String approvalCode) {
        this.approvalCode = approvalCode;
    }
    
    public void setTxnId(final String txnId) {
        this.txnId = txnId;
    }
    
    public void setPosEntryMode(final String posEntryMode) {
        this.posEntryMode = posEntryMode;
    }
    
    public void setServiceCode(final String serviceCode) {
        this.serviceCode = serviceCode;
    }
    
    public void setCardInputAbility(final Character cardInputAbility) {
        this.cardInputAbility = cardInputAbility;
    }
    
    public void setChAuthAbility(final Character chAuthAbility) {
        this.chAuthAbility = chAuthAbility;
    }
    
    public void setCardCaptureAbility(final Character cardCaptureAbility) {
        this.cardCaptureAbility = cardCaptureAbility;
    }
    
    public void setOprtEnvironment(final Character oprtEnvironment) {
        this.oprtEnvironment = oprtEnvironment;
    }
    
    public void setChPresent(final Character chPresent) {
        this.chPresent = chPresent;
    }
    
    public void setCardPresent(final Character cardPresent) {
        this.cardPresent = cardPresent;
    }
    
    public void setCardInputMode(final Character cardInputMode) {
        this.cardInputMode = cardInputMode;
    }
    
    public void setMerType(final Character merType) {
        this.merType = merType;
    }
    
    public void setMaid(final String maid) {
        this.maid = maid;
    }
    
    public void setProgRegion(final Character progRegion) {
        this.progRegion = progRegion;
    }
    
    public void setTimeLine(final Integer timeLine) {
        this.timeLine = timeLine;
    }
    
    public void setApprCodeFlag(final Integer apprCodeFlag) {
        this.apprCodeFlag = apprCodeFlag;
    }
    
    public void setMagStripeFlag(final Integer magStripeFlag) {
        this.magStripeFlag = magStripeFlag;
    }
    
    public void setTraceIdFlag(final Integer traceIdFlag) {
        this.traceIdFlag = traceIdFlag;
    }
    
    public void setMcAssignIdFlag(final Integer mcAssignIdFlag) {
        this.mcAssignIdFlag = mcAssignIdFlag;
    }
    
    public void setIssuerRegion(final Character issuerRegion) {
        this.issuerRegion = issuerRegion;
    }
    
    public void setCardProgId(final String cardProgId) {
        this.cardProgId = cardProgId;
    }
    
    public void setGcmsProdId(final String gcmsProdId) {
        this.gcmsProdId = gcmsProdId;
    }
    
    public void setEcomIndicator(final String ecomIndicator) {
        this.ecomIndicator = ecomIndicator;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof McIrfParamsEntity)) {
            return false;
        }
        final McIrfParamsEntity other = (McIrfParamsEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serNumber = this.getSerNumber();
        final Object other$serNumber = other.getSerNumber();
        Label_0065: {
            if (this$serNumber == null) {
                if (other$serNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$serNumber.equals(other$serNumber)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$insCode = this.getInsCode();
        final Object other$insCode = other.getInsCode();
        Label_0102: {
            if (this$insCode == null) {
                if (other$insCode == null) {
                    break Label_0102;
                }
            }
            else if (this$insCode.equals(other$insCode)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$txnAmount = this.getTxnAmount();
        final Object other$txnAmount = other.getTxnAmount();
        Label_0139: {
            if (this$txnAmount == null) {
                if (other$txnAmount == null) {
                    break Label_0139;
                }
            }
            else if (this$txnAmount.equals(other$txnAmount)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$cashBackAmount = this.getCashBackAmount();
        final Object other$cashBackAmount = other.getCashBackAmount();
        Label_0176: {
            if (this$cashBackAmount == null) {
                if (other$cashBackAmount == null) {
                    break Label_0176;
                }
            }
            else if (this$cashBackAmount.equals(other$cashBackAmount)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$cardInputAbility = this.getCardInputAbility();
        final Object other$cardInputAbility = other.getCardInputAbility();
        Label_0213: {
            if (this$cardInputAbility == null) {
                if (other$cardInputAbility == null) {
                    break Label_0213;
                }
            }
            else if (this$cardInputAbility.equals(other$cardInputAbility)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$chAuthAbility = this.getChAuthAbility();
        final Object other$chAuthAbility = other.getChAuthAbility();
        Label_0250: {
            if (this$chAuthAbility == null) {
                if (other$chAuthAbility == null) {
                    break Label_0250;
                }
            }
            else if (this$chAuthAbility.equals(other$chAuthAbility)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$cardCaptureAbility = this.getCardCaptureAbility();
        final Object other$cardCaptureAbility = other.getCardCaptureAbility();
        Label_0287: {
            if (this$cardCaptureAbility == null) {
                if (other$cardCaptureAbility == null) {
                    break Label_0287;
                }
            }
            else if (this$cardCaptureAbility.equals(other$cardCaptureAbility)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$oprtEnvironment = this.getOprtEnvironment();
        final Object other$oprtEnvironment = other.getOprtEnvironment();
        Label_0324: {
            if (this$oprtEnvironment == null) {
                if (other$oprtEnvironment == null) {
                    break Label_0324;
                }
            }
            else if (this$oprtEnvironment.equals(other$oprtEnvironment)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$chPresent = this.getChPresent();
        final Object other$chPresent = other.getChPresent();
        Label_0361: {
            if (this$chPresent == null) {
                if (other$chPresent == null) {
                    break Label_0361;
                }
            }
            else if (this$chPresent.equals(other$chPresent)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$cardPresent = this.getCardPresent();
        final Object other$cardPresent = other.getCardPresent();
        Label_0398: {
            if (this$cardPresent == null) {
                if (other$cardPresent == null) {
                    break Label_0398;
                }
            }
            else if (this$cardPresent.equals(other$cardPresent)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$cardInputMode = this.getCardInputMode();
        final Object other$cardInputMode = other.getCardInputMode();
        Label_0435: {
            if (this$cardInputMode == null) {
                if (other$cardInputMode == null) {
                    break Label_0435;
                }
            }
            else if (this$cardInputMode.equals(other$cardInputMode)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$merType = this.getMerType();
        final Object other$merType = other.getMerType();
        Label_0472: {
            if (this$merType == null) {
                if (other$merType == null) {
                    break Label_0472;
                }
            }
            else if (this$merType.equals(other$merType)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$progRegion = this.getProgRegion();
        final Object other$progRegion = other.getProgRegion();
        Label_0509: {
            if (this$progRegion == null) {
                if (other$progRegion == null) {
                    break Label_0509;
                }
            }
            else if (this$progRegion.equals(other$progRegion)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$timeLine = this.getTimeLine();
        final Object other$timeLine = other.getTimeLine();
        Label_0546: {
            if (this$timeLine == null) {
                if (other$timeLine == null) {
                    break Label_0546;
                }
            }
            else if (this$timeLine.equals(other$timeLine)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$apprCodeFlag = this.getApprCodeFlag();
        final Object other$apprCodeFlag = other.getApprCodeFlag();
        Label_0583: {
            if (this$apprCodeFlag == null) {
                if (other$apprCodeFlag == null) {
                    break Label_0583;
                }
            }
            else if (this$apprCodeFlag.equals(other$apprCodeFlag)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$magStripeFlag = this.getMagStripeFlag();
        final Object other$magStripeFlag = other.getMagStripeFlag();
        Label_0620: {
            if (this$magStripeFlag == null) {
                if (other$magStripeFlag == null) {
                    break Label_0620;
                }
            }
            else if (this$magStripeFlag.equals(other$magStripeFlag)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$traceIdFlag = this.getTraceIdFlag();
        final Object other$traceIdFlag = other.getTraceIdFlag();
        Label_0657: {
            if (this$traceIdFlag == null) {
                if (other$traceIdFlag == null) {
                    break Label_0657;
                }
            }
            else if (this$traceIdFlag.equals(other$traceIdFlag)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$mcAssignIdFlag = this.getMcAssignIdFlag();
        final Object other$mcAssignIdFlag = other.getMcAssignIdFlag();
        Label_0694: {
            if (this$mcAssignIdFlag == null) {
                if (other$mcAssignIdFlag == null) {
                    break Label_0694;
                }
            }
            else if (this$mcAssignIdFlag.equals(other$mcAssignIdFlag)) {
                break Label_0694;
            }
            return false;
        }
        final Object this$issuerRegion = this.getIssuerRegion();
        final Object other$issuerRegion = other.getIssuerRegion();
        Label_0731: {
            if (this$issuerRegion == null) {
                if (other$issuerRegion == null) {
                    break Label_0731;
                }
            }
            else if (this$issuerRegion.equals(other$issuerRegion)) {
                break Label_0731;
            }
            return false;
        }
        final Object this$pan = this.getPan();
        final Object other$pan = other.getPan();
        Label_0768: {
            if (this$pan == null) {
                if (other$pan == null) {
                    break Label_0768;
                }
            }
            else if (this$pan.equals(other$pan)) {
                break Label_0768;
            }
            return false;
        }
        final Object this$terminalType = this.getTerminalType();
        final Object other$terminalType = other.getTerminalType();
        Label_0805: {
            if (this$terminalType == null) {
                if (other$terminalType == null) {
                    break Label_0805;
                }
            }
            else if (this$terminalType.equals(other$terminalType)) {
                break Label_0805;
            }
            return false;
        }
        final Object this$txnDateTime = this.getTxnDateTime();
        final Object other$txnDateTime = other.getTxnDateTime();
        Label_0842: {
            if (this$txnDateTime == null) {
                if (other$txnDateTime == null) {
                    break Label_0842;
                }
            }
            else if (this$txnDateTime.equals(other$txnDateTime)) {
                break Label_0842;
            }
            return false;
        }
        final Object this$mcc = this.getMcc();
        final Object other$mcc = other.getMcc();
        Label_0879: {
            if (this$mcc == null) {
                if (other$mcc == null) {
                    break Label_0879;
                }
            }
            else if (this$mcc.equals(other$mcc)) {
                break Label_0879;
            }
            return false;
        }
        final Object this$network = this.getNetwork();
        final Object other$network = other.getNetwork();
        Label_0916: {
            if (this$network == null) {
                if (other$network == null) {
                    break Label_0916;
                }
            }
            else if (this$network.equals(other$network)) {
                break Label_0916;
            }
            return false;
        }
        final Object this$txnType = this.getTxnType();
        final Object other$txnType = other.getTxnType();
        Label_0953: {
            if (this$txnType == null) {
                if (other$txnType == null) {
                    break Label_0953;
                }
            }
            else if (this$txnType.equals(other$txnType)) {
                break Label_0953;
            }
            return false;
        }
        final Object this$approvalCode = this.getApprovalCode();
        final Object other$approvalCode = other.getApprovalCode();
        Label_0990: {
            if (this$approvalCode == null) {
                if (other$approvalCode == null) {
                    break Label_0990;
                }
            }
            else if (this$approvalCode.equals(other$approvalCode)) {
                break Label_0990;
            }
            return false;
        }
        final Object this$txnId = this.getTxnId();
        final Object other$txnId = other.getTxnId();
        Label_1027: {
            if (this$txnId == null) {
                if (other$txnId == null) {
                    break Label_1027;
                }
            }
            else if (this$txnId.equals(other$txnId)) {
                break Label_1027;
            }
            return false;
        }
        final Object this$posEntryMode = this.getPosEntryMode();
        final Object other$posEntryMode = other.getPosEntryMode();
        Label_1064: {
            if (this$posEntryMode == null) {
                if (other$posEntryMode == null) {
                    break Label_1064;
                }
            }
            else if (this$posEntryMode.equals(other$posEntryMode)) {
                break Label_1064;
            }
            return false;
        }
        final Object this$serviceCode = this.getServiceCode();
        final Object other$serviceCode = other.getServiceCode();
        Label_1101: {
            if (this$serviceCode == null) {
                if (other$serviceCode == null) {
                    break Label_1101;
                }
            }
            else if (this$serviceCode.equals(other$serviceCode)) {
                break Label_1101;
            }
            return false;
        }
        final Object this$maid = this.getMaid();
        final Object other$maid = other.getMaid();
        Label_1138: {
            if (this$maid == null) {
                if (other$maid == null) {
                    break Label_1138;
                }
            }
            else if (this$maid.equals(other$maid)) {
                break Label_1138;
            }
            return false;
        }
        final Object this$cardProgId = this.getCardProgId();
        final Object other$cardProgId = other.getCardProgId();
        Label_1175: {
            if (this$cardProgId == null) {
                if (other$cardProgId == null) {
                    break Label_1175;
                }
            }
            else if (this$cardProgId.equals(other$cardProgId)) {
                break Label_1175;
            }
            return false;
        }
        final Object this$gcmsProdId = this.getGcmsProdId();
        final Object other$gcmsProdId = other.getGcmsProdId();
        Label_1212: {
            if (this$gcmsProdId == null) {
                if (other$gcmsProdId == null) {
                    break Label_1212;
                }
            }
            else if (this$gcmsProdId.equals(other$gcmsProdId)) {
                break Label_1212;
            }
            return false;
        }
        final Object this$ecomIndicator = this.getEcomIndicator();
        final Object other$ecomIndicator = other.getEcomIndicator();
        if (this$ecomIndicator == null) {
            if (other$ecomIndicator == null) {
                return true;
            }
        }
        else if (this$ecomIndicator.equals(other$ecomIndicator)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof McIrfParamsEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serNumber = this.getSerNumber();
        result = result * 59 + (($serNumber == null) ? 43 : $serNumber.hashCode());
        final Object $insCode = this.getInsCode();
        result = result * 59 + (($insCode == null) ? 43 : $insCode.hashCode());
        final Object $txnAmount = this.getTxnAmount();
        result = result * 59 + (($txnAmount == null) ? 43 : $txnAmount.hashCode());
        final Object $cashBackAmount = this.getCashBackAmount();
        result = result * 59 + (($cashBackAmount == null) ? 43 : $cashBackAmount.hashCode());
        final Object $cardInputAbility = this.getCardInputAbility();
        result = result * 59 + (($cardInputAbility == null) ? 43 : $cardInputAbility.hashCode());
        final Object $chAuthAbility = this.getChAuthAbility();
        result = result * 59 + (($chAuthAbility == null) ? 43 : $chAuthAbility.hashCode());
        final Object $cardCaptureAbility = this.getCardCaptureAbility();
        result = result * 59 + (($cardCaptureAbility == null) ? 43 : $cardCaptureAbility.hashCode());
        final Object $oprtEnvironment = this.getOprtEnvironment();
        result = result * 59 + (($oprtEnvironment == null) ? 43 : $oprtEnvironment.hashCode());
        final Object $chPresent = this.getChPresent();
        result = result * 59 + (($chPresent == null) ? 43 : $chPresent.hashCode());
        final Object $cardPresent = this.getCardPresent();
        result = result * 59 + (($cardPresent == null) ? 43 : $cardPresent.hashCode());
        final Object $cardInputMode = this.getCardInputMode();
        result = result * 59 + (($cardInputMode == null) ? 43 : $cardInputMode.hashCode());
        final Object $merType = this.getMerType();
        result = result * 59 + (($merType == null) ? 43 : $merType.hashCode());
        final Object $progRegion = this.getProgRegion();
        result = result * 59 + (($progRegion == null) ? 43 : $progRegion.hashCode());
        final Object $timeLine = this.getTimeLine();
        result = result * 59 + (($timeLine == null) ? 43 : $timeLine.hashCode());
        final Object $apprCodeFlag = this.getApprCodeFlag();
        result = result * 59 + (($apprCodeFlag == null) ? 43 : $apprCodeFlag.hashCode());
        final Object $magStripeFlag = this.getMagStripeFlag();
        result = result * 59 + (($magStripeFlag == null) ? 43 : $magStripeFlag.hashCode());
        final Object $traceIdFlag = this.getTraceIdFlag();
        result = result * 59 + (($traceIdFlag == null) ? 43 : $traceIdFlag.hashCode());
        final Object $mcAssignIdFlag = this.getMcAssignIdFlag();
        result = result * 59 + (($mcAssignIdFlag == null) ? 43 : $mcAssignIdFlag.hashCode());
        final Object $issuerRegion = this.getIssuerRegion();
        result = result * 59 + (($issuerRegion == null) ? 43 : $issuerRegion.hashCode());
        final Object $pan = this.getPan();
        result = result * 59 + (($pan == null) ? 43 : $pan.hashCode());
        final Object $terminalType = this.getTerminalType();
        result = result * 59 + (($terminalType == null) ? 43 : $terminalType.hashCode());
        final Object $txnDateTime = this.getTxnDateTime();
        result = result * 59 + (($txnDateTime == null) ? 43 : $txnDateTime.hashCode());
        final Object $mcc = this.getMcc();
        result = result * 59 + (($mcc == null) ? 43 : $mcc.hashCode());
        final Object $network = this.getNetwork();
        result = result * 59 + (($network == null) ? 43 : $network.hashCode());
        final Object $txnType = this.getTxnType();
        result = result * 59 + (($txnType == null) ? 43 : $txnType.hashCode());
        final Object $approvalCode = this.getApprovalCode();
        result = result * 59 + (($approvalCode == null) ? 43 : $approvalCode.hashCode());
        final Object $txnId = this.getTxnId();
        result = result * 59 + (($txnId == null) ? 43 : $txnId.hashCode());
        final Object $posEntryMode = this.getPosEntryMode();
        result = result * 59 + (($posEntryMode == null) ? 43 : $posEntryMode.hashCode());
        final Object $serviceCode = this.getServiceCode();
        result = result * 59 + (($serviceCode == null) ? 43 : $serviceCode.hashCode());
        final Object $maid = this.getMaid();
        result = result * 59 + (($maid == null) ? 43 : $maid.hashCode());
        final Object $cardProgId = this.getCardProgId();
        result = result * 59 + (($cardProgId == null) ? 43 : $cardProgId.hashCode());
        final Object $gcmsProdId = this.getGcmsProdId();
        result = result * 59 + (($gcmsProdId == null) ? 43 : $gcmsProdId.hashCode());
        final Object $ecomIndicator = this.getEcomIndicator();
        result = result * 59 + (($ecomIndicator == null) ? 43 : $ecomIndicator.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "McIrfParamsEntity(serNumber=" + this.getSerNumber() + ", insCode=" + this.getInsCode() + ", pan=" + this.getPan() + ", txnAmount=" + this.getTxnAmount() + ", cashBackAmount=" + this.getCashBackAmount() + ", terminalType=" + this.getTerminalType() + ", txnDateTime=" + String.valueOf(this.getTxnDateTime()) + ", mcc=" + this.getMcc() + ", network=" + this.getNetwork() + ", txnType=" + this.getTxnType() + ", approvalCode=" + this.getApprovalCode() + ", txnId=" + this.getTxnId() + ", posEntryMode=" + this.getPosEntryMode() + ", serviceCode=" + this.getServiceCode() + ", cardInputAbility=" + this.getCardInputAbility() + ", chAuthAbility=" + this.getChAuthAbility() + ", cardCaptureAbility=" + this.getCardCaptureAbility() + ", oprtEnvironment=" + this.getOprtEnvironment() + ", chPresent=" + this.getChPresent() + ", cardPresent=" + this.getCardPresent() + ", cardInputMode=" + this.getCardInputMode() + ", merType=" + this.getMerType() + ", maid=" + this.getMaid() + ", progRegion=" + this.getProgRegion() + ", timeLine=" + this.getTimeLine() + ", apprCodeFlag=" + this.getApprCodeFlag() + ", magStripeFlag=" + this.getMagStripeFlag() + ", traceIdFlag=" + this.getTraceIdFlag() + ", mcAssignIdFlag=" + this.getMcAssignIdFlag() + ", issuerRegion=" + this.getIssuerRegion() + ", cardProgId=" + this.getCardProgId() + ", gcmsProdId=" + this.getGcmsProdId() + ", ecomIndicator=" + this.getEcomIndicator();
    }
    
    public McIrfParamsEntity(final Integer serNumber, final Integer insCode, final String pan, final Double txnAmount, final Double cashBackAmount, final String terminalType, final LocalDateTime txnDateTime, final String mcc, final String network, final String txnType, final String approvalCode, final String txnId, final String posEntryMode, final String serviceCode, final Character cardInputAbility, final Character chAuthAbility, final Character cardCaptureAbility, final Character oprtEnvironment, final Character chPresent, final Character cardPresent, final Character cardInputMode, final Character merType, final String maid, final Character progRegion, final Integer timeLine, final Integer apprCodeFlag, final Integer magStripeFlag, final Integer traceIdFlag, final Integer mcAssignIdFlag, final Character issuerRegion, final String cardProgId, final String gcmsProdId, final String ecomIndicator) {
        this.serNumber = serNumber;
        this.insCode = insCode;
        this.pan = pan;
        this.txnAmount = txnAmount;
        this.cashBackAmount = cashBackAmount;
        this.terminalType = terminalType;
        this.txnDateTime = txnDateTime;
        this.mcc = mcc;
        this.network = network;
        this.txnType = txnType;
        this.approvalCode = approvalCode;
        this.txnId = txnId;
        this.posEntryMode = posEntryMode;
        this.serviceCode = serviceCode;
        this.cardInputAbility = cardInputAbility;
        this.chAuthAbility = chAuthAbility;
        this.cardCaptureAbility = cardCaptureAbility;
        this.oprtEnvironment = oprtEnvironment;
        this.chPresent = chPresent;
        this.cardPresent = cardPresent;
        this.cardInputMode = cardInputMode;
        this.merType = merType;
        this.maid = maid;
        this.progRegion = progRegion;
        this.timeLine = timeLine;
        this.apprCodeFlag = apprCodeFlag;
        this.magStripeFlag = magStripeFlag;
        this.traceIdFlag = traceIdFlag;
        this.mcAssignIdFlag = mcAssignIdFlag;
        this.issuerRegion = issuerRegion;
        this.cardProgId = cardProgId;
        this.gcmsProdId = gcmsProdId;
        this.ecomIndicator = ecomIndicator;
    }
    
    public McIrfParamsEntity() {
    }
    
    public static class McIrfParamsEntityBuilder
    {
        private Integer serNumber;
        private Integer insCode;
        private String pan;
        private Double txnAmount;
        private Double cashBackAmount;
        private String terminalType;
        private LocalDateTime txnDateTime;
        private String mcc;
        private String network;
        private String txnType;
        private String approvalCode;
        private String txnId;
        private String posEntryMode;
        private String serviceCode;
        private Character cardInputAbility;
        private Character chAuthAbility;
        private Character cardCaptureAbility;
        private Character oprtEnvironment;
        private Character chPresent;
        private Character cardPresent;
        private Character cardInputMode;
        private Character merType;
        private String maid;
        private Character progRegion;
        private Integer timeLine;
        private Integer apprCodeFlag;
        private Integer magStripeFlag;
        private Integer traceIdFlag;
        private Integer mcAssignIdFlag;
        private Character issuerRegion;
        private String cardProgId;
        private String gcmsProdId;
        private String ecomIndicator;
        
        McIrfParamsEntityBuilder() {
        }
        
        public McIrfParamsEntityBuilder serNumber(final Integer serNumber) {
            this.serNumber = serNumber;
            return this;
        }
        
        public McIrfParamsEntityBuilder insCode(final Integer insCode) {
            this.insCode = insCode;
            return this;
        }
        
        public McIrfParamsEntityBuilder pan(final String pan) {
            this.pan = pan;
            return this;
        }
        
        public McIrfParamsEntityBuilder txnAmount(final Double txnAmount) {
            this.txnAmount = txnAmount;
            return this;
        }
        
        public McIrfParamsEntityBuilder cashBackAmount(final Double cashBackAmount) {
            this.cashBackAmount = cashBackAmount;
            return this;
        }
        
        public McIrfParamsEntityBuilder terminalType(final String terminalType) {
            this.terminalType = terminalType;
            return this;
        }
        
        public McIrfParamsEntityBuilder txnDateTime(final LocalDateTime txnDateTime) {
            this.txnDateTime = txnDateTime;
            return this;
        }
        
        public McIrfParamsEntityBuilder mcc(final String mcc) {
            this.mcc = mcc;
            return this;
        }
        
        public McIrfParamsEntityBuilder network(final String network) {
            this.network = network;
            return this;
        }
        
        public McIrfParamsEntityBuilder txnType(final String txnType) {
            this.txnType = txnType;
            return this;
        }
        
        public McIrfParamsEntityBuilder approvalCode(final String approvalCode) {
            this.approvalCode = approvalCode;
            return this;
        }
        
        public McIrfParamsEntityBuilder txnId(final String txnId) {
            this.txnId = txnId;
            return this;
        }
        
        public McIrfParamsEntityBuilder posEntryMode(final String posEntryMode) {
            this.posEntryMode = posEntryMode;
            return this;
        }
        
        public McIrfParamsEntityBuilder serviceCode(final String serviceCode) {
            this.serviceCode = serviceCode;
            return this;
        }
        
        public McIrfParamsEntityBuilder cardInputAbility(final Character cardInputAbility) {
            this.cardInputAbility = cardInputAbility;
            return this;
        }
        
        public McIrfParamsEntityBuilder chAuthAbility(final Character chAuthAbility) {
            this.chAuthAbility = chAuthAbility;
            return this;
        }
        
        public McIrfParamsEntityBuilder cardCaptureAbility(final Character cardCaptureAbility) {
            this.cardCaptureAbility = cardCaptureAbility;
            return this;
        }
        
        public McIrfParamsEntityBuilder oprtEnvironment(final Character oprtEnvironment) {
            this.oprtEnvironment = oprtEnvironment;
            return this;
        }
        
        public McIrfParamsEntityBuilder chPresent(final Character chPresent) {
            this.chPresent = chPresent;
            return this;
        }
        
        public McIrfParamsEntityBuilder cardPresent(final Character cardPresent) {
            this.cardPresent = cardPresent;
            return this;
        }
        
        public McIrfParamsEntityBuilder cardInputMode(final Character cardInputMode) {
            this.cardInputMode = cardInputMode;
            return this;
        }
        
        public McIrfParamsEntityBuilder merType(final Character merType) {
            this.merType = merType;
            return this;
        }
        
        public McIrfParamsEntityBuilder maid(final String maid) {
            this.maid = maid;
            return this;
        }
        
        public McIrfParamsEntityBuilder progRegion(final Character progRegion) {
            this.progRegion = progRegion;
            return this;
        }
        
        public McIrfParamsEntityBuilder timeLine(final Integer timeLine) {
            this.timeLine = timeLine;
            return this;
        }
        
        public McIrfParamsEntityBuilder apprCodeFlag(final Integer apprCodeFlag) {
            this.apprCodeFlag = apprCodeFlag;
            return this;
        }
        
        public McIrfParamsEntityBuilder magStripeFlag(final Integer magStripeFlag) {
            this.magStripeFlag = magStripeFlag;
            return this;
        }
        
        public McIrfParamsEntityBuilder traceIdFlag(final Integer traceIdFlag) {
            this.traceIdFlag = traceIdFlag;
            return this;
        }
        
        public McIrfParamsEntityBuilder mcAssignIdFlag(final Integer mcAssignIdFlag) {
            this.mcAssignIdFlag = mcAssignIdFlag;
            return this;
        }
        
        public McIrfParamsEntityBuilder issuerRegion(final Character issuerRegion) {
            this.issuerRegion = issuerRegion;
            return this;
        }
        
        public McIrfParamsEntityBuilder cardProgId(final String cardProgId) {
            this.cardProgId = cardProgId;
            return this;
        }
        
        public McIrfParamsEntityBuilder gcmsProdId(final String gcmsProdId) {
            this.gcmsProdId = gcmsProdId;
            return this;
        }
        
        public McIrfParamsEntityBuilder ecomIndicator(final String ecomIndicator) {
            this.ecomIndicator = ecomIndicator;
            return this;
        }
        
        public McIrfParamsEntity build() {
            return new McIrfParamsEntity(this.serNumber, this.insCode, this.pan, this.txnAmount, this.cashBackAmount, this.terminalType, this.txnDateTime, this.mcc, this.network, this.txnType, this.approvalCode, this.txnId, this.posEntryMode, this.serviceCode, this.cardInputAbility, this.chAuthAbility, this.cardCaptureAbility, this.oprtEnvironment, this.chPresent, this.cardPresent, this.cardInputMode, this.merType, this.maid, this.progRegion, this.timeLine, this.apprCodeFlag, this.magStripeFlag, this.traceIdFlag, this.mcAssignIdFlag, this.issuerRegion, this.cardProgId, this.gcmsProdId, this.ecomIndicator);
        }
        
        @Override
        public String toString() {
            return "McIrfParamsEntity.McIrfParamsEntityBuilder(serNumber=" + this.serNumber + ", insCode=" + this.insCode + ", pan=" + this.pan + ", txnAmount=" + this.txnAmount + ", cashBackAmount=" + this.cashBackAmount + ", terminalType=" + this.terminalType + ", txnDateTime=" + String.valueOf(this.txnDateTime) + ", mcc=" + this.mcc + ", network=" + this.network + ", txnType=" + this.txnType + ", approvalCode=" + this.approvalCode + ", txnId=" + this.txnId + ", posEntryMode=" + this.posEntryMode + ", serviceCode=" + this.serviceCode + ", cardInputAbility=" + this.cardInputAbility + ", chAuthAbility=" + this.chAuthAbility + ", cardCaptureAbility=" + this.cardCaptureAbility + ", oprtEnvironment=" + this.oprtEnvironment + ", chPresent=" + this.chPresent + ", cardPresent=" + this.cardPresent + ", cardInputMode=" + this.cardInputMode + ", merType=" + this.merType + ", maid=" + this.maid + ", progRegion=" + this.progRegion + ", timeLine=" + this.timeLine + ", apprCodeFlag=" + this.apprCodeFlag + ", magStripeFlag=" + this.magStripeFlag + ", traceIdFlag=" + this.traceIdFlag + ", mcAssignIdFlag=" + this.mcAssignIdFlag + ", issuerRegion=" + this.issuerRegion + ", cardProgId=" + this.cardProgId + ", gcmsProdId=" + this.gcmsProdId + ", ecomIndicator=" + this.ecomIndicator;
        }
    }
}
