// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "MC_ISS_ACC_RANGE")
public class McIssAcqRangeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAR_SER_NUMBER")
    private Integer serialNumber;
    @Column(name = "MAR_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name = "MAR_UPDATED_USER")
    private int updatedUser;
    @Column(name = "MAR_PRJ_SER_NUMBER")
    private int jobSerialNumber;
    @Column(name = "MAR_EFFECTIVE_DATE")
    private LocalDate effectiveDate;
    @Column(name = "MAR_ACTIVE_CODE")
    private Character activeCode;
    @Column(name = "MAR_ISS_RANGE_LOW")
    private String issRangeLow;
    @Column(name = "MAR_GCMS_PROD_ID")
    private String gcmsProductId;
    @Column(name = "MAR_ISS_RANGE_HIGH")
    private String issRangeHigh;
    @Column(name = "MAR_CARD_PROG_ID")
    private String cardProgId;
    @Column(name = "MAR_PRIORITY_CODE")
    private String priorityCode;
    @Column(name = "MAR_MEMBER_ID")
    private String memberId;
    @Column(name = "MAR_PROD_TYPE_ID")
    private Character prodTypeId;
    @Column(name = "MAR_END_POINT")
    private String endPoint;
    @Column(name = "MAR_COUNTRY_ALPHA_CODE")
    private String countryAlphaCode;
    @Column(name = "MAR_COUNTRY_CODE")
    private String countryCode;
    @Column(name = "MAR_REGION")
    private Character region;
    @Column(name = "MAR_PRODUCT_CLASS")
    private String productClass;
    @Column(name = "MAR_TXN_ROUT_IND")
    private Character txnRoutInd;
    @Column(name = "MAR_FP_REASSIGN_SWITCH")
    private Character fpReasignSwitch;
    @Column(name = "MAR_PROD_REASSIGN_SWITCH")
    private Character prodReasignSwitch;
    @Column(name = "MAR_PWCB_SWITCH")
    private Character pwcbSwitch;
    @Column(name = "MAR_LIC_PROD_ID")
    private String licProdId;
    @Column(name = "MAR_MAP_SERV_IND")
    private Character mapServInd;
    @Column(name = "MAR_ACC_LEVEL_IND")
    private Character accLevelInd;
    @Column(name = "MAR_ACC_LEVEL_ACT_DATE")
    private LocalDate accLevelActDate;
    @Column(name = "MAR_CH_BILL_CURR")
    private String chBillCurr;
    @Column(name = "MAR_CH_BILL_CURR_EXP")
    private Character chBillCurrExp;
    @Column(name = "MAR_CHIP_SERV_IND")
    private Character chipServInd;
    @Column(name = "MAR_FLOOR_EXP_DATE")
    private String floorExpDate;
    @Column(name = "MAR_CO_BRAND_SWITCH")
    private Character coBrandSwitch;
    @Column(name = "MAR_SPEND_CONTROL_SWITCH")
    private Character spendControlSwitch;
    @Column(name = "MAR_ME_CLEANSING_SERVICE")
    private String meCleansingService;
    @Column(name = "MAR_ME_CLEANSING_ACTIVE_DATE")
    private LocalDate meCleansingActiveDate;
    @Column(name = "MAR_ME_PAYPASS_IND")
    private Character mePayPassInd;
    @Column(name = "MAR_RATE_TYPE_IND")
    private Character rateTypeInd;
    @Column(name = "MAR_PSN_ROUTE_IND")
    private Character psnRouteInd;
    @Column(name = "MAR_CB_WITHOUT_PURCHASE")
    private Character cbWithoutPurchase;
    @Column(name = "MAR_REPOWER_RELOAD_IND")
    private Character repowerReloadInd;
    @Column(name = "MAR_MONEYSEND_IND")
    private Character moneySendInd;
    @Column(name = "MAR_DURBIN_RATE_IND")
    private Character durbinRateInd;
    @Column(name = "MAR_BUSS_DATE")
    private LocalDate bussDate;
    @Column(name = "MAR_GEN_STATUS")
    private int genStatus;
    
    public Integer getSerialNumber() {
        return this.serialNumber;
    }
    
    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }
    
    public int getUpdatedUser() {
        return this.updatedUser;
    }
    
    public int getJobSerialNumber() {
        return this.jobSerialNumber;
    }
    
    public LocalDate getEffectiveDate() {
        return this.effectiveDate;
    }
    
    public Character getActiveCode() {
        return this.activeCode;
    }
    
    public String getIssRangeLow() {
        return this.issRangeLow;
    }
    
    public String getGcmsProductId() {
        return this.gcmsProductId;
    }
    
    public String getIssRangeHigh() {
        return this.issRangeHigh;
    }
    
    public String getCardProgId() {
        return this.cardProgId;
    }
    
    public String getPriorityCode() {
        return this.priorityCode;
    }
    
    public String getMemberId() {
        return this.memberId;
    }
    
    public Character getProdTypeId() {
        return this.prodTypeId;
    }
    
    public String getEndPoint() {
        return this.endPoint;
    }
    
    public String getCountryAlphaCode() {
        return this.countryAlphaCode;
    }
    
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public Character getRegion() {
        return this.region;
    }
    
    public String getProductClass() {
        return this.productClass;
    }
    
    public Character getTxnRoutInd() {
        return this.txnRoutInd;
    }
    
    public Character getFpReasignSwitch() {
        return this.fpReasignSwitch;
    }
    
    public Character getProdReasignSwitch() {
        return this.prodReasignSwitch;
    }
    
    public Character getPwcbSwitch() {
        return this.pwcbSwitch;
    }
    
    public String getLicProdId() {
        return this.licProdId;
    }
    
    public Character getMapServInd() {
        return this.mapServInd;
    }
    
    public Character getAccLevelInd() {
        return this.accLevelInd;
    }
    
    public LocalDate getAccLevelActDate() {
        return this.accLevelActDate;
    }
    
    public String getChBillCurr() {
        return this.chBillCurr;
    }
    
    public Character getChBillCurrExp() {
        return this.chBillCurrExp;
    }
    
    public Character getChipServInd() {
        return this.chipServInd;
    }
    
    public String getFloorExpDate() {
        return this.floorExpDate;
    }
    
    public Character getCoBrandSwitch() {
        return this.coBrandSwitch;
    }
    
    public Character getSpendControlSwitch() {
        return this.spendControlSwitch;
    }
    
    public String getMeCleansingService() {
        return this.meCleansingService;
    }
    
    public LocalDate getMeCleansingActiveDate() {
        return this.meCleansingActiveDate;
    }
    
    public Character getMePayPassInd() {
        return this.mePayPassInd;
    }
    
    public Character getRateTypeInd() {
        return this.rateTypeInd;
    }
    
    public Character getPsnRouteInd() {
        return this.psnRouteInd;
    }
    
    public Character getCbWithoutPurchase() {
        return this.cbWithoutPurchase;
    }
    
    public Character getRepowerReloadInd() {
        return this.repowerReloadInd;
    }
    
    public Character getMoneySendInd() {
        return this.moneySendInd;
    }
    
    public Character getDurbinRateInd() {
        return this.durbinRateInd;
    }
    
    public LocalDate getBussDate() {
        return this.bussDate;
    }
    
    public int getGenStatus() {
        return this.genStatus;
    }
    
    public void setSerialNumber(final Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setUpdatedUser(final int updatedUser) {
        this.updatedUser = updatedUser;
    }
    
    public void setJobSerialNumber(final int jobSerialNumber) {
        this.jobSerialNumber = jobSerialNumber;
    }
    
    public void setEffectiveDate(final LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    
    public void setActiveCode(final Character activeCode) {
        this.activeCode = activeCode;
    }
    
    public void setIssRangeLow(final String issRangeLow) {
        this.issRangeLow = issRangeLow;
    }
    
    public void setGcmsProductId(final String gcmsProductId) {
        this.gcmsProductId = gcmsProductId;
    }
    
    public void setIssRangeHigh(final String issRangeHigh) {
        this.issRangeHigh = issRangeHigh;
    }
    
    public void setCardProgId(final String cardProgId) {
        this.cardProgId = cardProgId;
    }
    
    public void setPriorityCode(final String priorityCode) {
        this.priorityCode = priorityCode;
    }
    
    public void setMemberId(final String memberId) {
        this.memberId = memberId;
    }
    
    public void setProdTypeId(final Character prodTypeId) {
        this.prodTypeId = prodTypeId;
    }
    
    public void setEndPoint(final String endPoint) {
        this.endPoint = endPoint;
    }
    
    public void setCountryAlphaCode(final String countryAlphaCode) {
        this.countryAlphaCode = countryAlphaCode;
    }
    
    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }
    
    public void setRegion(final Character region) {
        this.region = region;
    }
    
    public void setProductClass(final String productClass) {
        this.productClass = productClass;
    }
    
    public void setTxnRoutInd(final Character txnRoutInd) {
        this.txnRoutInd = txnRoutInd;
    }
    
    public void setFpReasignSwitch(final Character fpReasignSwitch) {
        this.fpReasignSwitch = fpReasignSwitch;
    }
    
    public void setProdReasignSwitch(final Character prodReasignSwitch) {
        this.prodReasignSwitch = prodReasignSwitch;
    }
    
    public void setPwcbSwitch(final Character pwcbSwitch) {
        this.pwcbSwitch = pwcbSwitch;
    }
    
    public void setLicProdId(final String licProdId) {
        this.licProdId = licProdId;
    }
    
    public void setMapServInd(final Character mapServInd) {
        this.mapServInd = mapServInd;
    }
    
    public void setAccLevelInd(final Character accLevelInd) {
        this.accLevelInd = accLevelInd;
    }
    
    public void setAccLevelActDate(final LocalDate accLevelActDate) {
        this.accLevelActDate = accLevelActDate;
    }
    
    public void setChBillCurr(final String chBillCurr) {
        this.chBillCurr = chBillCurr;
    }
    
    public void setChBillCurrExp(final Character chBillCurrExp) {
        this.chBillCurrExp = chBillCurrExp;
    }
    
    public void setChipServInd(final Character chipServInd) {
        this.chipServInd = chipServInd;
    }
    
    public void setFloorExpDate(final String floorExpDate) {
        this.floorExpDate = floorExpDate;
    }
    
    public void setCoBrandSwitch(final Character coBrandSwitch) {
        this.coBrandSwitch = coBrandSwitch;
    }
    
    public void setSpendControlSwitch(final Character spendControlSwitch) {
        this.spendControlSwitch = spendControlSwitch;
    }
    
    public void setMeCleansingService(final String meCleansingService) {
        this.meCleansingService = meCleansingService;
    }
    
    public void setMeCleansingActiveDate(final LocalDate meCleansingActiveDate) {
        this.meCleansingActiveDate = meCleansingActiveDate;
    }
    
    public void setMePayPassInd(final Character mePayPassInd) {
        this.mePayPassInd = mePayPassInd;
    }
    
    public void setRateTypeInd(final Character rateTypeInd) {
        this.rateTypeInd = rateTypeInd;
    }
    
    public void setPsnRouteInd(final Character psnRouteInd) {
        this.psnRouteInd = psnRouteInd;
    }
    
    public void setCbWithoutPurchase(final Character cbWithoutPurchase) {
        this.cbWithoutPurchase = cbWithoutPurchase;
    }
    
    public void setRepowerReloadInd(final Character repowerReloadInd) {
        this.repowerReloadInd = repowerReloadInd;
    }
    
    public void setMoneySendInd(final Character moneySendInd) {
        this.moneySendInd = moneySendInd;
    }
    
    public void setDurbinRateInd(final Character durbinRateInd) {
        this.durbinRateInd = durbinRateInd;
    }
    
    public void setBussDate(final LocalDate bussDate) {
        this.bussDate = bussDate;
    }
    
    public void setGenStatus(final int genStatus) {
        this.genStatus = genStatus;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof McIssAcqRangeEntity)) {
            return false;
        }
        final McIssAcqRangeEntity other = (McIssAcqRangeEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getUpdatedUser() != other.getUpdatedUser()) {
            return false;
        }
        if (this.getJobSerialNumber() != other.getJobSerialNumber()) {
            return false;
        }
        if (this.getGenStatus() != other.getGenStatus()) {
            return false;
        }
        final Object this$serialNumber = this.getSerialNumber();
        final Object other$serialNumber = other.getSerialNumber();
        Label_0104: {
            if (this$serialNumber == null) {
                if (other$serialNumber == null) {
                    break Label_0104;
                }
            }
            else if (this$serialNumber.equals(other$serialNumber)) {
                break Label_0104;
            }
            return false;
        }
        final Object this$activeCode = this.getActiveCode();
        final Object other$activeCode = other.getActiveCode();
        Label_0141: {
            if (this$activeCode == null) {
                if (other$activeCode == null) {
                    break Label_0141;
                }
            }
            else if (this$activeCode.equals(other$activeCode)) {
                break Label_0141;
            }
            return false;
        }
        final Object this$prodTypeId = this.getProdTypeId();
        final Object other$prodTypeId = other.getProdTypeId();
        Label_0178: {
            if (this$prodTypeId == null) {
                if (other$prodTypeId == null) {
                    break Label_0178;
                }
            }
            else if (this$prodTypeId.equals(other$prodTypeId)) {
                break Label_0178;
            }
            return false;
        }
        final Object this$region = this.getRegion();
        final Object other$region = other.getRegion();
        Label_0215: {
            if (this$region == null) {
                if (other$region == null) {
                    break Label_0215;
                }
            }
            else if (this$region.equals(other$region)) {
                break Label_0215;
            }
            return false;
        }
        final Object this$txnRoutInd = this.getTxnRoutInd();
        final Object other$txnRoutInd = other.getTxnRoutInd();
        Label_0252: {
            if (this$txnRoutInd == null) {
                if (other$txnRoutInd == null) {
                    break Label_0252;
                }
            }
            else if (this$txnRoutInd.equals(other$txnRoutInd)) {
                break Label_0252;
            }
            return false;
        }
        final Object this$fpReasignSwitch = this.getFpReasignSwitch();
        final Object other$fpReasignSwitch = other.getFpReasignSwitch();
        Label_0289: {
            if (this$fpReasignSwitch == null) {
                if (other$fpReasignSwitch == null) {
                    break Label_0289;
                }
            }
            else if (this$fpReasignSwitch.equals(other$fpReasignSwitch)) {
                break Label_0289;
            }
            return false;
        }
        final Object this$prodReasignSwitch = this.getProdReasignSwitch();
        final Object other$prodReasignSwitch = other.getProdReasignSwitch();
        Label_0326: {
            if (this$prodReasignSwitch == null) {
                if (other$prodReasignSwitch == null) {
                    break Label_0326;
                }
            }
            else if (this$prodReasignSwitch.equals(other$prodReasignSwitch)) {
                break Label_0326;
            }
            return false;
        }
        final Object this$pwcbSwitch = this.getPwcbSwitch();
        final Object other$pwcbSwitch = other.getPwcbSwitch();
        Label_0363: {
            if (this$pwcbSwitch == null) {
                if (other$pwcbSwitch == null) {
                    break Label_0363;
                }
            }
            else if (this$pwcbSwitch.equals(other$pwcbSwitch)) {
                break Label_0363;
            }
            return false;
        }
        final Object this$mapServInd = this.getMapServInd();
        final Object other$mapServInd = other.getMapServInd();
        Label_0400: {
            if (this$mapServInd == null) {
                if (other$mapServInd == null) {
                    break Label_0400;
                }
            }
            else if (this$mapServInd.equals(other$mapServInd)) {
                break Label_0400;
            }
            return false;
        }
        final Object this$accLevelInd = this.getAccLevelInd();
        final Object other$accLevelInd = other.getAccLevelInd();
        Label_0437: {
            if (this$accLevelInd == null) {
                if (other$accLevelInd == null) {
                    break Label_0437;
                }
            }
            else if (this$accLevelInd.equals(other$accLevelInd)) {
                break Label_0437;
            }
            return false;
        }
        final Object this$chBillCurrExp = this.getChBillCurrExp();
        final Object other$chBillCurrExp = other.getChBillCurrExp();
        Label_0474: {
            if (this$chBillCurrExp == null) {
                if (other$chBillCurrExp == null) {
                    break Label_0474;
                }
            }
            else if (this$chBillCurrExp.equals(other$chBillCurrExp)) {
                break Label_0474;
            }
            return false;
        }
        final Object this$chipServInd = this.getChipServInd();
        final Object other$chipServInd = other.getChipServInd();
        Label_0511: {
            if (this$chipServInd == null) {
                if (other$chipServInd == null) {
                    break Label_0511;
                }
            }
            else if (this$chipServInd.equals(other$chipServInd)) {
                break Label_0511;
            }
            return false;
        }
        final Object this$coBrandSwitch = this.getCoBrandSwitch();
        final Object other$coBrandSwitch = other.getCoBrandSwitch();
        Label_0548: {
            if (this$coBrandSwitch == null) {
                if (other$coBrandSwitch == null) {
                    break Label_0548;
                }
            }
            else if (this$coBrandSwitch.equals(other$coBrandSwitch)) {
                break Label_0548;
            }
            return false;
        }
        final Object this$spendControlSwitch = this.getSpendControlSwitch();
        final Object other$spendControlSwitch = other.getSpendControlSwitch();
        Label_0585: {
            if (this$spendControlSwitch == null) {
                if (other$spendControlSwitch == null) {
                    break Label_0585;
                }
            }
            else if (this$spendControlSwitch.equals(other$spendControlSwitch)) {
                break Label_0585;
            }
            return false;
        }
        final Object this$mePayPassInd = this.getMePayPassInd();
        final Object other$mePayPassInd = other.getMePayPassInd();
        Label_0622: {
            if (this$mePayPassInd == null) {
                if (other$mePayPassInd == null) {
                    break Label_0622;
                }
            }
            else if (this$mePayPassInd.equals(other$mePayPassInd)) {
                break Label_0622;
            }
            return false;
        }
        final Object this$rateTypeInd = this.getRateTypeInd();
        final Object other$rateTypeInd = other.getRateTypeInd();
        Label_0659: {
            if (this$rateTypeInd == null) {
                if (other$rateTypeInd == null) {
                    break Label_0659;
                }
            }
            else if (this$rateTypeInd.equals(other$rateTypeInd)) {
                break Label_0659;
            }
            return false;
        }
        final Object this$psnRouteInd = this.getPsnRouteInd();
        final Object other$psnRouteInd = other.getPsnRouteInd();
        Label_0696: {
            if (this$psnRouteInd == null) {
                if (other$psnRouteInd == null) {
                    break Label_0696;
                }
            }
            else if (this$psnRouteInd.equals(other$psnRouteInd)) {
                break Label_0696;
            }
            return false;
        }
        final Object this$cbWithoutPurchase = this.getCbWithoutPurchase();
        final Object other$cbWithoutPurchase = other.getCbWithoutPurchase();
        Label_0733: {
            if (this$cbWithoutPurchase == null) {
                if (other$cbWithoutPurchase == null) {
                    break Label_0733;
                }
            }
            else if (this$cbWithoutPurchase.equals(other$cbWithoutPurchase)) {
                break Label_0733;
            }
            return false;
        }
        final Object this$repowerReloadInd = this.getRepowerReloadInd();
        final Object other$repowerReloadInd = other.getRepowerReloadInd();
        Label_0770: {
            if (this$repowerReloadInd == null) {
                if (other$repowerReloadInd == null) {
                    break Label_0770;
                }
            }
            else if (this$repowerReloadInd.equals(other$repowerReloadInd)) {
                break Label_0770;
            }
            return false;
        }
        final Object this$moneySendInd = this.getMoneySendInd();
        final Object other$moneySendInd = other.getMoneySendInd();
        Label_0807: {
            if (this$moneySendInd == null) {
                if (other$moneySendInd == null) {
                    break Label_0807;
                }
            }
            else if (this$moneySendInd.equals(other$moneySendInd)) {
                break Label_0807;
            }
            return false;
        }
        final Object this$durbinRateInd = this.getDurbinRateInd();
        final Object other$durbinRateInd = other.getDurbinRateInd();
        Label_0844: {
            if (this$durbinRateInd == null) {
                if (other$durbinRateInd == null) {
                    break Label_0844;
                }
            }
            else if (this$durbinRateInd.equals(other$durbinRateInd)) {
                break Label_0844;
            }
            return false;
        }
        final Object this$lastUpdated = this.getLastUpdated();
        final Object other$lastUpdated = other.getLastUpdated();
        Label_0881: {
            if (this$lastUpdated == null) {
                if (other$lastUpdated == null) {
                    break Label_0881;
                }
            }
            else if (this$lastUpdated.equals(other$lastUpdated)) {
                break Label_0881;
            }
            return false;
        }
        final Object this$effectiveDate = this.getEffectiveDate();
        final Object other$effectiveDate = other.getEffectiveDate();
        Label_0918: {
            if (this$effectiveDate == null) {
                if (other$effectiveDate == null) {
                    break Label_0918;
                }
            }
            else if (this$effectiveDate.equals(other$effectiveDate)) {
                break Label_0918;
            }
            return false;
        }
        final Object this$issRangeLow = this.getIssRangeLow();
        final Object other$issRangeLow = other.getIssRangeLow();
        Label_0955: {
            if (this$issRangeLow == null) {
                if (other$issRangeLow == null) {
                    break Label_0955;
                }
            }
            else if (this$issRangeLow.equals(other$issRangeLow)) {
                break Label_0955;
            }
            return false;
        }
        final Object this$gcmsProductId = this.getGcmsProductId();
        final Object other$gcmsProductId = other.getGcmsProductId();
        Label_0992: {
            if (this$gcmsProductId == null) {
                if (other$gcmsProductId == null) {
                    break Label_0992;
                }
            }
            else if (this$gcmsProductId.equals(other$gcmsProductId)) {
                break Label_0992;
            }
            return false;
        }
        final Object this$issRangeHigh = this.getIssRangeHigh();
        final Object other$issRangeHigh = other.getIssRangeHigh();
        Label_1029: {
            if (this$issRangeHigh == null) {
                if (other$issRangeHigh == null) {
                    break Label_1029;
                }
            }
            else if (this$issRangeHigh.equals(other$issRangeHigh)) {
                break Label_1029;
            }
            return false;
        }
        final Object this$cardProgId = this.getCardProgId();
        final Object other$cardProgId = other.getCardProgId();
        Label_1066: {
            if (this$cardProgId == null) {
                if (other$cardProgId == null) {
                    break Label_1066;
                }
            }
            else if (this$cardProgId.equals(other$cardProgId)) {
                break Label_1066;
            }
            return false;
        }
        final Object this$priorityCode = this.getPriorityCode();
        final Object other$priorityCode = other.getPriorityCode();
        Label_1103: {
            if (this$priorityCode == null) {
                if (other$priorityCode == null) {
                    break Label_1103;
                }
            }
            else if (this$priorityCode.equals(other$priorityCode)) {
                break Label_1103;
            }
            return false;
        }
        final Object this$memberId = this.getMemberId();
        final Object other$memberId = other.getMemberId();
        Label_1140: {
            if (this$memberId == null) {
                if (other$memberId == null) {
                    break Label_1140;
                }
            }
            else if (this$memberId.equals(other$memberId)) {
                break Label_1140;
            }
            return false;
        }
        final Object this$endPoint = this.getEndPoint();
        final Object other$endPoint = other.getEndPoint();
        Label_1177: {
            if (this$endPoint == null) {
                if (other$endPoint == null) {
                    break Label_1177;
                }
            }
            else if (this$endPoint.equals(other$endPoint)) {
                break Label_1177;
            }
            return false;
        }
        final Object this$countryAlphaCode = this.getCountryAlphaCode();
        final Object other$countryAlphaCode = other.getCountryAlphaCode();
        Label_1214: {
            if (this$countryAlphaCode == null) {
                if (other$countryAlphaCode == null) {
                    break Label_1214;
                }
            }
            else if (this$countryAlphaCode.equals(other$countryAlphaCode)) {
                break Label_1214;
            }
            return false;
        }
        final Object this$countryCode = this.getCountryCode();
        final Object other$countryCode = other.getCountryCode();
        Label_1251: {
            if (this$countryCode == null) {
                if (other$countryCode == null) {
                    break Label_1251;
                }
            }
            else if (this$countryCode.equals(other$countryCode)) {
                break Label_1251;
            }
            return false;
        }
        final Object this$productClass = this.getProductClass();
        final Object other$productClass = other.getProductClass();
        Label_1288: {
            if (this$productClass == null) {
                if (other$productClass == null) {
                    break Label_1288;
                }
            }
            else if (this$productClass.equals(other$productClass)) {
                break Label_1288;
            }
            return false;
        }
        final Object this$licProdId = this.getLicProdId();
        final Object other$licProdId = other.getLicProdId();
        Label_1325: {
            if (this$licProdId == null) {
                if (other$licProdId == null) {
                    break Label_1325;
                }
            }
            else if (this$licProdId.equals(other$licProdId)) {
                break Label_1325;
            }
            return false;
        }
        final Object this$accLevelActDate = this.getAccLevelActDate();
        final Object other$accLevelActDate = other.getAccLevelActDate();
        Label_1362: {
            if (this$accLevelActDate == null) {
                if (other$accLevelActDate == null) {
                    break Label_1362;
                }
            }
            else if (this$accLevelActDate.equals(other$accLevelActDate)) {
                break Label_1362;
            }
            return false;
        }
        final Object this$chBillCurr = this.getChBillCurr();
        final Object other$chBillCurr = other.getChBillCurr();
        Label_1399: {
            if (this$chBillCurr == null) {
                if (other$chBillCurr == null) {
                    break Label_1399;
                }
            }
            else if (this$chBillCurr.equals(other$chBillCurr)) {
                break Label_1399;
            }
            return false;
        }
        final Object this$floorExpDate = this.getFloorExpDate();
        final Object other$floorExpDate = other.getFloorExpDate();
        Label_1436: {
            if (this$floorExpDate == null) {
                if (other$floorExpDate == null) {
                    break Label_1436;
                }
            }
            else if (this$floorExpDate.equals(other$floorExpDate)) {
                break Label_1436;
            }
            return false;
        }
        final Object this$meCleansingService = this.getMeCleansingService();
        final Object other$meCleansingService = other.getMeCleansingService();
        Label_1473: {
            if (this$meCleansingService == null) {
                if (other$meCleansingService == null) {
                    break Label_1473;
                }
            }
            else if (this$meCleansingService.equals(other$meCleansingService)) {
                break Label_1473;
            }
            return false;
        }
        final Object this$meCleansingActiveDate = this.getMeCleansingActiveDate();
        final Object other$meCleansingActiveDate = other.getMeCleansingActiveDate();
        Label_1510: {
            if (this$meCleansingActiveDate == null) {
                if (other$meCleansingActiveDate == null) {
                    break Label_1510;
                }
            }
            else if (this$meCleansingActiveDate.equals(other$meCleansingActiveDate)) {
                break Label_1510;
            }
            return false;
        }
        final Object this$bussDate = this.getBussDate();
        final Object other$bussDate = other.getBussDate();
        if (this$bussDate == null) {
            if (other$bussDate == null) {
                return true;
            }
        }
        else if (this$bussDate.equals(other$bussDate)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof McIssAcqRangeEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getUpdatedUser();
        result = result * 59 + this.getJobSerialNumber();
        result = result * 59 + this.getGenStatus();
        final Object $serialNumber = this.getSerialNumber();
        result = result * 59 + (($serialNumber == null) ? 43 : $serialNumber.hashCode());
        final Object $activeCode = this.getActiveCode();
        result = result * 59 + (($activeCode == null) ? 43 : $activeCode.hashCode());
        final Object $prodTypeId = this.getProdTypeId();
        result = result * 59 + (($prodTypeId == null) ? 43 : $prodTypeId.hashCode());
        final Object $region = this.getRegion();
        result = result * 59 + (($region == null) ? 43 : $region.hashCode());
        final Object $txnRoutInd = this.getTxnRoutInd();
        result = result * 59 + (($txnRoutInd == null) ? 43 : $txnRoutInd.hashCode());
        final Object $fpReasignSwitch = this.getFpReasignSwitch();
        result = result * 59 + (($fpReasignSwitch == null) ? 43 : $fpReasignSwitch.hashCode());
        final Object $prodReasignSwitch = this.getProdReasignSwitch();
        result = result * 59 + (($prodReasignSwitch == null) ? 43 : $prodReasignSwitch.hashCode());
        final Object $pwcbSwitch = this.getPwcbSwitch();
        result = result * 59 + (($pwcbSwitch == null) ? 43 : $pwcbSwitch.hashCode());
        final Object $mapServInd = this.getMapServInd();
        result = result * 59 + (($mapServInd == null) ? 43 : $mapServInd.hashCode());
        final Object $accLevelInd = this.getAccLevelInd();
        result = result * 59 + (($accLevelInd == null) ? 43 : $accLevelInd.hashCode());
        final Object $chBillCurrExp = this.getChBillCurrExp();
        result = result * 59 + (($chBillCurrExp == null) ? 43 : $chBillCurrExp.hashCode());
        final Object $chipServInd = this.getChipServInd();
        result = result * 59 + (($chipServInd == null) ? 43 : $chipServInd.hashCode());
        final Object $coBrandSwitch = this.getCoBrandSwitch();
        result = result * 59 + (($coBrandSwitch == null) ? 43 : $coBrandSwitch.hashCode());
        final Object $spendControlSwitch = this.getSpendControlSwitch();
        result = result * 59 + (($spendControlSwitch == null) ? 43 : $spendControlSwitch.hashCode());
        final Object $mePayPassInd = this.getMePayPassInd();
        result = result * 59 + (($mePayPassInd == null) ? 43 : $mePayPassInd.hashCode());
        final Object $rateTypeInd = this.getRateTypeInd();
        result = result * 59 + (($rateTypeInd == null) ? 43 : $rateTypeInd.hashCode());
        final Object $psnRouteInd = this.getPsnRouteInd();
        result = result * 59 + (($psnRouteInd == null) ? 43 : $psnRouteInd.hashCode());
        final Object $cbWithoutPurchase = this.getCbWithoutPurchase();
        result = result * 59 + (($cbWithoutPurchase == null) ? 43 : $cbWithoutPurchase.hashCode());
        final Object $repowerReloadInd = this.getRepowerReloadInd();
        result = result * 59 + (($repowerReloadInd == null) ? 43 : $repowerReloadInd.hashCode());
        final Object $moneySendInd = this.getMoneySendInd();
        result = result * 59 + (($moneySendInd == null) ? 43 : $moneySendInd.hashCode());
        final Object $durbinRateInd = this.getDurbinRateInd();
        result = result * 59 + (($durbinRateInd == null) ? 43 : $durbinRateInd.hashCode());
        final Object $lastUpdated = this.getLastUpdated();
        result = result * 59 + (($lastUpdated == null) ? 43 : $lastUpdated.hashCode());
        final Object $effectiveDate = this.getEffectiveDate();
        result = result * 59 + (($effectiveDate == null) ? 43 : $effectiveDate.hashCode());
        final Object $issRangeLow = this.getIssRangeLow();
        result = result * 59 + (($issRangeLow == null) ? 43 : $issRangeLow.hashCode());
        final Object $gcmsProductId = this.getGcmsProductId();
        result = result * 59 + (($gcmsProductId == null) ? 43 : $gcmsProductId.hashCode());
        final Object $issRangeHigh = this.getIssRangeHigh();
        result = result * 59 + (($issRangeHigh == null) ? 43 : $issRangeHigh.hashCode());
        final Object $cardProgId = this.getCardProgId();
        result = result * 59 + (($cardProgId == null) ? 43 : $cardProgId.hashCode());
        final Object $priorityCode = this.getPriorityCode();
        result = result * 59 + (($priorityCode == null) ? 43 : $priorityCode.hashCode());
        final Object $memberId = this.getMemberId();
        result = result * 59 + (($memberId == null) ? 43 : $memberId.hashCode());
        final Object $endPoint = this.getEndPoint();
        result = result * 59 + (($endPoint == null) ? 43 : $endPoint.hashCode());
        final Object $countryAlphaCode = this.getCountryAlphaCode();
        result = result * 59 + (($countryAlphaCode == null) ? 43 : $countryAlphaCode.hashCode());
        final Object $countryCode = this.getCountryCode();
        result = result * 59 + (($countryCode == null) ? 43 : $countryCode.hashCode());
        final Object $productClass = this.getProductClass();
        result = result * 59 + (($productClass == null) ? 43 : $productClass.hashCode());
        final Object $licProdId = this.getLicProdId();
        result = result * 59 + (($licProdId == null) ? 43 : $licProdId.hashCode());
        final Object $accLevelActDate = this.getAccLevelActDate();
        result = result * 59 + (($accLevelActDate == null) ? 43 : $accLevelActDate.hashCode());
        final Object $chBillCurr = this.getChBillCurr();
        result = result * 59 + (($chBillCurr == null) ? 43 : $chBillCurr.hashCode());
        final Object $floorExpDate = this.getFloorExpDate();
        result = result * 59 + (($floorExpDate == null) ? 43 : $floorExpDate.hashCode());
        final Object $meCleansingService = this.getMeCleansingService();
        result = result * 59 + (($meCleansingService == null) ? 43 : $meCleansingService.hashCode());
        final Object $meCleansingActiveDate = this.getMeCleansingActiveDate();
        result = result * 59 + (($meCleansingActiveDate == null) ? 43 : $meCleansingActiveDate.hashCode());
        final Object $bussDate = this.getBussDate();
        result = result * 59 + (($bussDate == null) ? 43 : $bussDate.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "McIssAcqRangeEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", jobSerialNumber=" + this.getJobSerialNumber() + ", effectiveDate=" + String.valueOf(this.getEffectiveDate()) + ", activeCode=" + this.getActiveCode() + ", issRangeLow=" + this.getIssRangeLow() + ", gcmsProductId=" + this.getGcmsProductId() + ", issRangeHigh=" + this.getIssRangeHigh() + ", cardProgId=" + this.getCardProgId() + ", priorityCode=" + this.getPriorityCode() + ", memberId=" + this.getMemberId() + ", prodTypeId=" + this.getProdTypeId() + ", endPoint=" + this.getEndPoint() + ", countryAlphaCode=" + this.getCountryAlphaCode() + ", countryCode=" + this.getCountryCode() + ", region=" + this.getRegion() + ", productClass=" + this.getProductClass() + ", txnRoutInd=" + this.getTxnRoutInd() + ", fpReasignSwitch=" + this.getFpReasignSwitch() + ", prodReasignSwitch=" + this.getProdReasignSwitch() + ", pwcbSwitch=" + this.getPwcbSwitch() + ", licProdId=" + this.getLicProdId() + ", mapServInd=" + this.getMapServInd() + ", accLevelInd=" + this.getAccLevelInd() + ", accLevelActDate=" + String.valueOf(this.getAccLevelActDate()) + ", chBillCurr=" + this.getChBillCurr() + ", chBillCurrExp=" + this.getChBillCurrExp() + ", chipServInd=" + this.getChipServInd() + ", floorExpDate=" + this.getFloorExpDate() + ", coBrandSwitch=" + this.getCoBrandSwitch() + ", spendControlSwitch=" + this.getSpendControlSwitch() + ", meCleansingService=" + this.getMeCleansingService() + ", meCleansingActiveDate=" + String.valueOf(this.getMeCleansingActiveDate()) + ", mePayPassInd=" + this.getMePayPassInd() + ", rateTypeInd=" + this.getRateTypeInd() + ", psnRouteInd=" + this.getPsnRouteInd() + ", cbWithoutPurchase=" + this.getCbWithoutPurchase() + ", repowerReloadInd=" + this.getRepowerReloadInd() + ", moneySendInd=" + this.getMoneySendInd() + ", durbinRateInd=" + this.getDurbinRateInd() + ", bussDate=" + String.valueOf(this.getBussDate()) + ", genStatus=" + this.getGenStatus();
    }
}

