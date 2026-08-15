/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.entities.MCIssAcqRangeEntity
 *  jakarta.persistence.Column
 *  jakarta.persistence.Entity
 *  jakarta.persistence.GeneratedValue
 *  jakarta.persistence.GenerationType
 *  jakarta.persistence.Id
 *  jakarta.persistence.Table
 */
package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="MC_ISS_ACC_RANGE")
public class MCIssAcqRangeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MAR_SER_NUMBER")
    private Integer serialNumber;
    @Column(name="MAR_LAST_UPDATED")
    private LocalDateTime lastUpdated;
    @Column(name="MAR_UPDATED_USER")
    private int updatedUser;
    @Column(name="MAR_PRJ_SER_NUMBER")
    private int jobSerialNumber;
    @Column(name="MAR_EFFECTIVE_DATE")
    private LocalDate effectiveDate;
    @Column(name="MAR_ACTIVE_CODE")
    private Character activeCode;
    @Column(name="MAR_ISS_RANGE_LOW")
    private String issRangeLow;
    @Column(name="MAR_GCMS_PROD_ID")
    private String gcmsProductId;
    @Column(name="MAR_ISS_RANGE_HIGH")
    private String issRangeHigh;
    @Column(name="MAR_CARD_PROG_ID")
    private String cardProgId;
    @Column(name="MAR_PRIORITY_CODE")
    private String priorityCode;
    @Column(name="MAR_MEMBER_ID")
    private String memberId;
    @Column(name="MAR_PROD_TYPE_ID")
    private Character prodTypeId;
    @Column(name="MAR_END_POINT")
    private String endPoint;
    @Column(name="MAR_COUNTRY_ALPHA_CODE")
    private String countryAlphaCode;
    @Column(name="MAR_COUNTRY_CODE")
    private String countryCode;
    @Column(name="MAR_REGION")
    private Character region;
    @Column(name="MAR_PRODUCT_CLASS")
    private String productClass;
    @Column(name="MAR_TXN_ROUT_IND")
    private Character txnRoutInd;
    @Column(name="MAR_FP_REASSIGN_SWITCH")
    private Character fpReasignSwitch;
    @Column(name="MAR_PROD_REASSIGN_SWITCH")
    private Character prodReasignSwitch;
    @Column(name="MAR_PWCB_SWITCH")
    private Character pwcbSwitch;
    @Column(name="MAR_LIC_PROD_ID")
    private String licProdId;
    @Column(name="MAR_MAP_SERV_IND")
    private Character mapServInd;
    @Column(name="MAR_ACC_LEVEL_IND")
    private Character accLevelInd;
    @Column(name="MAR_ACC_LEVEL_ACT_DATE")
    private LocalDate accLevelActDate;
    @Column(name="MAR_CH_BILL_CURR")
    private String chBillCurr;
    @Column(name="MAR_CH_BILL_CURR_EXP")
    private Character chBillCurrExp;
    @Column(name="MAR_CHIP_SERV_IND")
    private Character chipServInd;
    @Column(name="MAR_FLOOR_EXP_DATE")
    private String floorExpDate;
    @Column(name="MAR_CO_BRAND_SWITCH")
    private Character coBrandSwitch;
    @Column(name="MAR_SPEND_CONTROL_SWITCH")
    private Character spendControlSwitch;
    @Column(name="MAR_ME_CLEANSING_SERVICE")
    private String meCleansingService;
    @Column(name="MAR_ME_CLEANSING_ACTIVE_DATE")
    private LocalDate meCleansingActiveDate;
    @Column(name="MAR_ME_PAYPASS_IND")
    private Character mePayPassInd;
    @Column(name="MAR_RATE_TYPE_IND")
    private Character rateTypeInd;
    @Column(name="MAR_PSN_ROUTE_IND")
    private Character psnRouteInd;
    @Column(name="MAR_CB_WITHOUT_PURCHASE")
    private Character cbWithoutPurchase;
    @Column(name="MAR_REPOWER_RELOAD_IND")
    private Character repowerReloadInd;
    @Column(name="MAR_MONEYSEND_IND")
    private Character moneySendInd;
    @Column(name="MAR_DURBIN_RATE_IND")
    private Character durbinRateInd;
    @Column(name="MAR_BUSS_DATE")
    private LocalDate bussDate;
    @Column(name="MAR_GEN_STATUS")
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

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setUpdatedUser(int updatedUser) {
        this.updatedUser = updatedUser;
    }

    public void setJobSerialNumber(int jobSerialNumber) {
        this.jobSerialNumber = jobSerialNumber;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setActiveCode(Character activeCode) {
        this.activeCode = activeCode;
    }

    public void setIssRangeLow(String issRangeLow) {
        this.issRangeLow = issRangeLow;
    }

    public void setGcmsProductId(String gcmsProductId) {
        this.gcmsProductId = gcmsProductId;
    }

    public void setIssRangeHigh(String issRangeHigh) {
        this.issRangeHigh = issRangeHigh;
    }

    public void setCardProgId(String cardProgId) {
        this.cardProgId = cardProgId;
    }

    public void setPriorityCode(String priorityCode) {
        this.priorityCode = priorityCode;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setProdTypeId(Character prodTypeId) {
        this.prodTypeId = prodTypeId;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public void setCountryAlphaCode(String countryAlphaCode) {
        this.countryAlphaCode = countryAlphaCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setRegion(Character region) {
        this.region = region;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public void setTxnRoutInd(Character txnRoutInd) {
        this.txnRoutInd = txnRoutInd;
    }

    public void setFpReasignSwitch(Character fpReasignSwitch) {
        this.fpReasignSwitch = fpReasignSwitch;
    }

    public void setProdReasignSwitch(Character prodReasignSwitch) {
        this.prodReasignSwitch = prodReasignSwitch;
    }

    public void setPwcbSwitch(Character pwcbSwitch) {
        this.pwcbSwitch = pwcbSwitch;
    }

    public void setLicProdId(String licProdId) {
        this.licProdId = licProdId;
    }

    public void setMapServInd(Character mapServInd) {
        this.mapServInd = mapServInd;
    }

    public void setAccLevelInd(Character accLevelInd) {
        this.accLevelInd = accLevelInd;
    }

    public void setAccLevelActDate(LocalDate accLevelActDate) {
        this.accLevelActDate = accLevelActDate;
    }

    public void setChBillCurr(String chBillCurr) {
        this.chBillCurr = chBillCurr;
    }

    public void setChBillCurrExp(Character chBillCurrExp) {
        this.chBillCurrExp = chBillCurrExp;
    }

    public void setChipServInd(Character chipServInd) {
        this.chipServInd = chipServInd;
    }

    public void setFloorExpDate(String floorExpDate) {
        this.floorExpDate = floorExpDate;
    }

    public void setCoBrandSwitch(Character coBrandSwitch) {
        this.coBrandSwitch = coBrandSwitch;
    }

    public void setSpendControlSwitch(Character spendControlSwitch) {
        this.spendControlSwitch = spendControlSwitch;
    }

    public void setMeCleansingService(String meCleansingService) {
        this.meCleansingService = meCleansingService;
    }

    public void setMeCleansingActiveDate(LocalDate meCleansingActiveDate) {
        this.meCleansingActiveDate = meCleansingActiveDate;
    }

    public void setMePayPassInd(Character mePayPassInd) {
        this.mePayPassInd = mePayPassInd;
    }

    public void setRateTypeInd(Character rateTypeInd) {
        this.rateTypeInd = rateTypeInd;
    }

    public void setPsnRouteInd(Character psnRouteInd) {
        this.psnRouteInd = psnRouteInd;
    }

    public void setCbWithoutPurchase(Character cbWithoutPurchase) {
        this.cbWithoutPurchase = cbWithoutPurchase;
    }

    public void setRepowerReloadInd(Character repowerReloadInd) {
        this.repowerReloadInd = repowerReloadInd;
    }

    public void setMoneySendInd(Character moneySendInd) {
        this.moneySendInd = moneySendInd;
    }

    public void setDurbinRateInd(Character durbinRateInd) {
        this.durbinRateInd = durbinRateInd;
    }

    public void setBussDate(LocalDate bussDate) {
        this.bussDate = bussDate;
    }

    public void setGenStatus(int genStatus) {
        this.genStatus = genStatus;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MCIssAcqRangeEntity)) {
            return false;
        }
        MCIssAcqRangeEntity other = (MCIssAcqRangeEntity)o;
        if (!other.canEqual((Object)this)) {
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
        Integer this$serialNumber = this.getSerialNumber();
        Integer other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !((Object)this$serialNumber).equals(other$serialNumber)) {
            return false;
        }
        Character this$activeCode = this.getActiveCode();
        Character other$activeCode = other.getActiveCode();
        if (this$activeCode == null ? other$activeCode != null : !((Object)this$activeCode).equals(other$activeCode)) {
            return false;
        }
        Character this$prodTypeId = this.getProdTypeId();
        Character other$prodTypeId = other.getProdTypeId();
        if (this$prodTypeId == null ? other$prodTypeId != null : !((Object)this$prodTypeId).equals(other$prodTypeId)) {
            return false;
        }
        Character this$region = this.getRegion();
        Character other$region = other.getRegion();
        if (this$region == null ? other$region != null : !((Object)this$region).equals(other$region)) {
            return false;
        }
        Character this$txnRoutInd = this.getTxnRoutInd();
        Character other$txnRoutInd = other.getTxnRoutInd();
        if (this$txnRoutInd == null ? other$txnRoutInd != null : !((Object)this$txnRoutInd).equals(other$txnRoutInd)) {
            return false;
        }
        Character this$fpReasignSwitch = this.getFpReasignSwitch();
        Character other$fpReasignSwitch = other.getFpReasignSwitch();
        if (this$fpReasignSwitch == null ? other$fpReasignSwitch != null : !((Object)this$fpReasignSwitch).equals(other$fpReasignSwitch)) {
            return false;
        }
        Character this$prodReasignSwitch = this.getProdReasignSwitch();
        Character other$prodReasignSwitch = other.getProdReasignSwitch();
        if (this$prodReasignSwitch == null ? other$prodReasignSwitch != null : !((Object)this$prodReasignSwitch).equals(other$prodReasignSwitch)) {
            return false;
        }
        Character this$pwcbSwitch = this.getPwcbSwitch();
        Character other$pwcbSwitch = other.getPwcbSwitch();
        if (this$pwcbSwitch == null ? other$pwcbSwitch != null : !((Object)this$pwcbSwitch).equals(other$pwcbSwitch)) {
            return false;
        }
        Character this$mapServInd = this.getMapServInd();
        Character other$mapServInd = other.getMapServInd();
        if (this$mapServInd == null ? other$mapServInd != null : !((Object)this$mapServInd).equals(other$mapServInd)) {
            return false;
        }
        Character this$accLevelInd = this.getAccLevelInd();
        Character other$accLevelInd = other.getAccLevelInd();
        if (this$accLevelInd == null ? other$accLevelInd != null : !((Object)this$accLevelInd).equals(other$accLevelInd)) {
            return false;
        }
        Character this$chBillCurrExp = this.getChBillCurrExp();
        Character other$chBillCurrExp = other.getChBillCurrExp();
        if (this$chBillCurrExp == null ? other$chBillCurrExp != null : !((Object)this$chBillCurrExp).equals(other$chBillCurrExp)) {
            return false;
        }
        Character this$chipServInd = this.getChipServInd();
        Character other$chipServInd = other.getChipServInd();
        if (this$chipServInd == null ? other$chipServInd != null : !((Object)this$chipServInd).equals(other$chipServInd)) {
            return false;
        }
        Character this$coBrandSwitch = this.getCoBrandSwitch();
        Character other$coBrandSwitch = other.getCoBrandSwitch();
        if (this$coBrandSwitch == null ? other$coBrandSwitch != null : !((Object)this$coBrandSwitch).equals(other$coBrandSwitch)) {
            return false;
        }
        Character this$spendControlSwitch = this.getSpendControlSwitch();
        Character other$spendControlSwitch = other.getSpendControlSwitch();
        if (this$spendControlSwitch == null ? other$spendControlSwitch != null : !((Object)this$spendControlSwitch).equals(other$spendControlSwitch)) {
            return false;
        }
        Character this$mePayPassInd = this.getMePayPassInd();
        Character other$mePayPassInd = other.getMePayPassInd();
        if (this$mePayPassInd == null ? other$mePayPassInd != null : !((Object)this$mePayPassInd).equals(other$mePayPassInd)) {
            return false;
        }
        Character this$rateTypeInd = this.getRateTypeInd();
        Character other$rateTypeInd = other.getRateTypeInd();
        if (this$rateTypeInd == null ? other$rateTypeInd != null : !((Object)this$rateTypeInd).equals(other$rateTypeInd)) {
            return false;
        }
        Character this$psnRouteInd = this.getPsnRouteInd();
        Character other$psnRouteInd = other.getPsnRouteInd();
        if (this$psnRouteInd == null ? other$psnRouteInd != null : !((Object)this$psnRouteInd).equals(other$psnRouteInd)) {
            return false;
        }
        Character this$cbWithoutPurchase = this.getCbWithoutPurchase();
        Character other$cbWithoutPurchase = other.getCbWithoutPurchase();
        if (this$cbWithoutPurchase == null ? other$cbWithoutPurchase != null : !((Object)this$cbWithoutPurchase).equals(other$cbWithoutPurchase)) {
            return false;
        }
        Character this$repowerReloadInd = this.getRepowerReloadInd();
        Character other$repowerReloadInd = other.getRepowerReloadInd();
        if (this$repowerReloadInd == null ? other$repowerReloadInd != null : !((Object)this$repowerReloadInd).equals(other$repowerReloadInd)) {
            return false;
        }
        Character this$moneySendInd = this.getMoneySendInd();
        Character other$moneySendInd = other.getMoneySendInd();
        if (this$moneySendInd == null ? other$moneySendInd != null : !((Object)this$moneySendInd).equals(other$moneySendInd)) {
            return false;
        }
        Character this$durbinRateInd = this.getDurbinRateInd();
        Character other$durbinRateInd = other.getDurbinRateInd();
        if (this$durbinRateInd == null ? other$durbinRateInd != null : !((Object)this$durbinRateInd).equals(other$durbinRateInd)) {
            return false;
        }
        LocalDateTime this$lastUpdated = this.getLastUpdated();
        LocalDateTime other$lastUpdated = other.getLastUpdated();
        if (this$lastUpdated == null ? other$lastUpdated != null : !((Object)this$lastUpdated).equals(other$lastUpdated)) {
            return false;
        }
        LocalDate this$effectiveDate = this.getEffectiveDate();
        LocalDate other$effectiveDate = other.getEffectiveDate();
        if (this$effectiveDate == null ? other$effectiveDate != null : !((Object)this$effectiveDate).equals(other$effectiveDate)) {
            return false;
        }
        String this$issRangeLow = this.getIssRangeLow();
        String other$issRangeLow = other.getIssRangeLow();
        if (this$issRangeLow == null ? other$issRangeLow != null : !this$issRangeLow.equals(other$issRangeLow)) {
            return false;
        }
        String this$gcmsProductId = this.getGcmsProductId();
        String other$gcmsProductId = other.getGcmsProductId();
        if (this$gcmsProductId == null ? other$gcmsProductId != null : !this$gcmsProductId.equals(other$gcmsProductId)) {
            return false;
        }
        String this$issRangeHigh = this.getIssRangeHigh();
        String other$issRangeHigh = other.getIssRangeHigh();
        if (this$issRangeHigh == null ? other$issRangeHigh != null : !this$issRangeHigh.equals(other$issRangeHigh)) {
            return false;
        }
        String this$cardProgId = this.getCardProgId();
        String other$cardProgId = other.getCardProgId();
        if (this$cardProgId == null ? other$cardProgId != null : !this$cardProgId.equals(other$cardProgId)) {
            return false;
        }
        String this$priorityCode = this.getPriorityCode();
        String other$priorityCode = other.getPriorityCode();
        if (this$priorityCode == null ? other$priorityCode != null : !this$priorityCode.equals(other$priorityCode)) {
            return false;
        }
        String this$memberId = this.getMemberId();
        String other$memberId = other.getMemberId();
        if (this$memberId == null ? other$memberId != null : !this$memberId.equals(other$memberId)) {
            return false;
        }
        String this$endPoint = this.getEndPoint();
        String other$endPoint = other.getEndPoint();
        if (this$endPoint == null ? other$endPoint != null : !this$endPoint.equals(other$endPoint)) {
            return false;
        }
        String this$countryAlphaCode = this.getCountryAlphaCode();
        String other$countryAlphaCode = other.getCountryAlphaCode();
        if (this$countryAlphaCode == null ? other$countryAlphaCode != null : !this$countryAlphaCode.equals(other$countryAlphaCode)) {
            return false;
        }
        String this$countryCode = this.getCountryCode();
        String other$countryCode = other.getCountryCode();
        if (this$countryCode == null ? other$countryCode != null : !this$countryCode.equals(other$countryCode)) {
            return false;
        }
        String this$productClass = this.getProductClass();
        String other$productClass = other.getProductClass();
        if (this$productClass == null ? other$productClass != null : !this$productClass.equals(other$productClass)) {
            return false;
        }
        String this$licProdId = this.getLicProdId();
        String other$licProdId = other.getLicProdId();
        if (this$licProdId == null ? other$licProdId != null : !this$licProdId.equals(other$licProdId)) {
            return false;
        }
        LocalDate this$accLevelActDate = this.getAccLevelActDate();
        LocalDate other$accLevelActDate = other.getAccLevelActDate();
        if (this$accLevelActDate == null ? other$accLevelActDate != null : !((Object)this$accLevelActDate).equals(other$accLevelActDate)) {
            return false;
        }
        String this$chBillCurr = this.getChBillCurr();
        String other$chBillCurr = other.getChBillCurr();
        if (this$chBillCurr == null ? other$chBillCurr != null : !this$chBillCurr.equals(other$chBillCurr)) {
            return false;
        }
        String this$floorExpDate = this.getFloorExpDate();
        String other$floorExpDate = other.getFloorExpDate();
        if (this$floorExpDate == null ? other$floorExpDate != null : !this$floorExpDate.equals(other$floorExpDate)) {
            return false;
        }
        String this$meCleansingService = this.getMeCleansingService();
        String other$meCleansingService = other.getMeCleansingService();
        if (this$meCleansingService == null ? other$meCleansingService != null : !this$meCleansingService.equals(other$meCleansingService)) {
            return false;
        }
        LocalDate this$meCleansingActiveDate = this.getMeCleansingActiveDate();
        LocalDate other$meCleansingActiveDate = other.getMeCleansingActiveDate();
        if (this$meCleansingActiveDate == null ? other$meCleansingActiveDate != null : !((Object)this$meCleansingActiveDate).equals(other$meCleansingActiveDate)) {
            return false;
        }
        LocalDate this$bussDate = this.getBussDate();
        LocalDate other$bussDate = other.getBussDate();
        return !(this$bussDate == null ? other$bussDate != null : !((Object)this$bussDate).equals(other$bussDate));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MCIssAcqRangeEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getUpdatedUser();
        result = result * 59 + this.getJobSerialNumber();
        result = result * 59 + this.getGenStatus();
        Integer $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : ((Object)$serialNumber).hashCode());
        Character $activeCode = this.getActiveCode();
        result = result * 59 + ($activeCode == null ? 43 : ((Object)$activeCode).hashCode());
        Character $prodTypeId = this.getProdTypeId();
        result = result * 59 + ($prodTypeId == null ? 43 : ((Object)$prodTypeId).hashCode());
        Character $region = this.getRegion();
        result = result * 59 + ($region == null ? 43 : ((Object)$region).hashCode());
        Character $txnRoutInd = this.getTxnRoutInd();
        result = result * 59 + ($txnRoutInd == null ? 43 : ((Object)$txnRoutInd).hashCode());
        Character $fpReasignSwitch = this.getFpReasignSwitch();
        result = result * 59 + ($fpReasignSwitch == null ? 43 : ((Object)$fpReasignSwitch).hashCode());
        Character $prodReasignSwitch = this.getProdReasignSwitch();
        result = result * 59 + ($prodReasignSwitch == null ? 43 : ((Object)$prodReasignSwitch).hashCode());
        Character $pwcbSwitch = this.getPwcbSwitch();
        result = result * 59 + ($pwcbSwitch == null ? 43 : ((Object)$pwcbSwitch).hashCode());
        Character $mapServInd = this.getMapServInd();
        result = result * 59 + ($mapServInd == null ? 43 : ((Object)$mapServInd).hashCode());
        Character $accLevelInd = this.getAccLevelInd();
        result = result * 59 + ($accLevelInd == null ? 43 : ((Object)$accLevelInd).hashCode());
        Character $chBillCurrExp = this.getChBillCurrExp();
        result = result * 59 + ($chBillCurrExp == null ? 43 : ((Object)$chBillCurrExp).hashCode());
        Character $chipServInd = this.getChipServInd();
        result = result * 59 + ($chipServInd == null ? 43 : ((Object)$chipServInd).hashCode());
        Character $coBrandSwitch = this.getCoBrandSwitch();
        result = result * 59 + ($coBrandSwitch == null ? 43 : ((Object)$coBrandSwitch).hashCode());
        Character $spendControlSwitch = this.getSpendControlSwitch();
        result = result * 59 + ($spendControlSwitch == null ? 43 : ((Object)$spendControlSwitch).hashCode());
        Character $mePayPassInd = this.getMePayPassInd();
        result = result * 59 + ($mePayPassInd == null ? 43 : ((Object)$mePayPassInd).hashCode());
        Character $rateTypeInd = this.getRateTypeInd();
        result = result * 59 + ($rateTypeInd == null ? 43 : ((Object)$rateTypeInd).hashCode());
        Character $psnRouteInd = this.getPsnRouteInd();
        result = result * 59 + ($psnRouteInd == null ? 43 : ((Object)$psnRouteInd).hashCode());
        Character $cbWithoutPurchase = this.getCbWithoutPurchase();
        result = result * 59 + ($cbWithoutPurchase == null ? 43 : ((Object)$cbWithoutPurchase).hashCode());
        Character $repowerReloadInd = this.getRepowerReloadInd();
        result = result * 59 + ($repowerReloadInd == null ? 43 : ((Object)$repowerReloadInd).hashCode());
        Character $moneySendInd = this.getMoneySendInd();
        result = result * 59 + ($moneySendInd == null ? 43 : ((Object)$moneySendInd).hashCode());
        Character $durbinRateInd = this.getDurbinRateInd();
        result = result * 59 + ($durbinRateInd == null ? 43 : ((Object)$durbinRateInd).hashCode());
        LocalDateTime $lastUpdated = this.getLastUpdated();
        result = result * 59 + ($lastUpdated == null ? 43 : ((Object)$lastUpdated).hashCode());
        LocalDate $effectiveDate = this.getEffectiveDate();
        result = result * 59 + ($effectiveDate == null ? 43 : ((Object)$effectiveDate).hashCode());
        String $issRangeLow = this.getIssRangeLow();
        result = result * 59 + ($issRangeLow == null ? 43 : $issRangeLow.hashCode());
        String $gcmsProductId = this.getGcmsProductId();
        result = result * 59 + ($gcmsProductId == null ? 43 : $gcmsProductId.hashCode());
        String $issRangeHigh = this.getIssRangeHigh();
        result = result * 59 + ($issRangeHigh == null ? 43 : $issRangeHigh.hashCode());
        String $cardProgId = this.getCardProgId();
        result = result * 59 + ($cardProgId == null ? 43 : $cardProgId.hashCode());
        String $priorityCode = this.getPriorityCode();
        result = result * 59 + ($priorityCode == null ? 43 : $priorityCode.hashCode());
        String $memberId = this.getMemberId();
        result = result * 59 + ($memberId == null ? 43 : $memberId.hashCode());
        String $endPoint = this.getEndPoint();
        result = result * 59 + ($endPoint == null ? 43 : $endPoint.hashCode());
        String $countryAlphaCode = this.getCountryAlphaCode();
        result = result * 59 + ($countryAlphaCode == null ? 43 : $countryAlphaCode.hashCode());
        String $countryCode = this.getCountryCode();
        result = result * 59 + ($countryCode == null ? 43 : $countryCode.hashCode());
        String $productClass = this.getProductClass();
        result = result * 59 + ($productClass == null ? 43 : $productClass.hashCode());
        String $licProdId = this.getLicProdId();
        result = result * 59 + ($licProdId == null ? 43 : $licProdId.hashCode());
        LocalDate $accLevelActDate = this.getAccLevelActDate();
        result = result * 59 + ($accLevelActDate == null ? 43 : ((Object)$accLevelActDate).hashCode());
        String $chBillCurr = this.getChBillCurr();
        result = result * 59 + ($chBillCurr == null ? 43 : $chBillCurr.hashCode());
        String $floorExpDate = this.getFloorExpDate();
        result = result * 59 + ($floorExpDate == null ? 43 : $floorExpDate.hashCode());
        String $meCleansingService = this.getMeCleansingService();
        result = result * 59 + ($meCleansingService == null ? 43 : $meCleansingService.hashCode());
        LocalDate $meCleansingActiveDate = this.getMeCleansingActiveDate();
        result = result * 59 + ($meCleansingActiveDate == null ? 43 : ((Object)$meCleansingActiveDate).hashCode());
        LocalDate $bussDate = this.getBussDate();
        result = result * 59 + ($bussDate == null ? 43 : ((Object)$bussDate).hashCode());
        return result;
    }

    public String toString() {
        return "MCIssAcqRangeEntity(serialNumber=" + this.getSerialNumber() + ", lastUpdated=" + String.valueOf(this.getLastUpdated()) + ", updatedUser=" + this.getUpdatedUser() + ", jobSerialNumber=" + this.getJobSerialNumber() + ", effectiveDate=" + String.valueOf(this.getEffectiveDate()) + ", activeCode=" + this.getActiveCode() + ", issRangeLow=" + this.getIssRangeLow() + ", gcmsProductId=" + this.getGcmsProductId() + ", issRangeHigh=" + this.getIssRangeHigh() + ", cardProgId=" + this.getCardProgId() + ", priorityCode=" + this.getPriorityCode() + ", memberId=" + this.getMemberId() + ", prodTypeId=" + this.getProdTypeId() + ", endPoint=" + this.getEndPoint() + ", countryAlphaCode=" + this.getCountryAlphaCode() + ", countryCode=" + this.getCountryCode() + ", region=" + this.getRegion() + ", productClass=" + this.getProductClass() + ", txnRoutInd=" + this.getTxnRoutInd() + ", fpReasignSwitch=" + this.getFpReasignSwitch() + ", prodReasignSwitch=" + this.getProdReasignSwitch() + ", pwcbSwitch=" + this.getPwcbSwitch() + ", licProdId=" + this.getLicProdId() + ", mapServInd=" + this.getMapServInd() + ", accLevelInd=" + this.getAccLevelInd() + ", accLevelActDate=" + String.valueOf(this.getAccLevelActDate()) + ", chBillCurr=" + this.getChBillCurr() + ", chBillCurrExp=" + this.getChBillCurrExp() + ", chipServInd=" + this.getChipServInd() + ", floorExpDate=" + this.getFloorExpDate() + ", coBrandSwitch=" + this.getCoBrandSwitch() + ", spendControlSwitch=" + this.getSpendControlSwitch() + ", meCleansingService=" + this.getMeCleansingService() + ", meCleansingActiveDate=" + String.valueOf(this.getMeCleansingActiveDate()) + ", mePayPassInd=" + this.getMePayPassInd() + ", rateTypeInd=" + this.getRateTypeInd() + ", psnRouteInd=" + this.getPsnRouteInd() + ", cbWithoutPurchase=" + this.getCbWithoutPurchase() + ", repowerReloadInd=" + this.getRepowerReloadInd() + ", moneySendInd=" + this.getMoneySendInd() + ", durbinRateInd=" + this.getDurbinRateInd() + ", bussDate=" + String.valueOf(this.getBussDate()) + ", genStatus=" + this.getGenStatus() + ")";
    }
}

