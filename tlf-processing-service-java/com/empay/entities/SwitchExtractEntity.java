// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

@Entity
@Table(name = "SWITCH_EXTRACT")
public class SwitchExtractEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ser_number")
    private Integer serilaNumber;
    @Column(name = "bank_code")
    private String bank_code;
    @Column(name = "network")
    private String network;
    @Column(name = "unique_id")
    private String unique_id;
    @Column(name = "mti")
    private String mti;
    @Column(name = "pan")
    private String pan;
    @Column(name = "proc_code")
    private String proc_code;
    @Column(name = "txn_amount")
    private String txn_amount;
    @Column(name = "setl_amount")
    private String setl_amount;
    @Column(name = "txn_date_time ")
    private String txn_date_time;
    @Column(name = "convert_rate")
    private String convert_rate;
    @Column(name = "stan")
    private String stan;
    @Column(name = "local_txn_time ")
    private String local_txn_time;
    @Column(name = "local_txn_date ")
    private String local_txn_date;
    @Column(name = "expiry_date")
    private String expiry_date;
    @Column(name = "setl_date")
    private String setl_date;
    @Column(name = "merchant_type")
    private String merchant_type;
    @Column(name = "acquiring_inst_con_code ")
    private String acquiring_inst_con_code;
    @Column(name = "pos_entry_mode")
    private String pos_entry_mode;
    @Column(name = "pan_seq_number")
    private String pan_seq_number;
    @Column(name = "pos_condition_code")
    private String pos_condition_code;
    @Column(name = "txn_fee")
    private String txn_fee;
    @Column(name = "acquiring_inst_id_code")
    private String acquiring_inst_id_code;
    @Column(name = "forward_inst_id_code")
    private String forward_inst_id_code;
    @Column(name = "rrn")
    private String rrn;
    @Column(name = "approval_code")
    private String approval_code;
    @Column(name = "resp_code")
    private String resp_code;
    @Column(name = "srev_rest_code")
    private String srev_rest_code;
    @Column(name = "terminal_id")
    private String terminal_id;
    @Column(name = "merchant_id")
    private String merchant_id;
    @Column(name = "card_accp_name_loc")
    private String card_accp_name_loc;
    @Column(name = "adtl_private_data ")
    private String adtl_private_data;
    @Column(name = "txn_cur_code")
    private String txn_cur_code;
    @Column(name = "setl_cur_code")
    private String setl_cur_code;
    @Column(name = "adtl_amounts")
    private String adtl_amounts;
    @Column(name = "advice_reason_code")
    private String advice_reason_code;
    @Column(name = "res_private_data_de61")
    private String res_private_data_de61;
    @Column(name = "res_private_data_de62")
    private String res_private_data_de62;
    @Column(name = "res_private_data_de63")
    private String res_private_data_de63;
    @Column(name = "setl_code")
    private String setl_code;
    @Column(name = "rec_ins_code")
    private String rec_ins_code;
    @Column(name = "replacement_amount")
    private String replacement_amount;
    @Column(name = "channel_type")
    private String channel_type;
    @Column(name = "sys_date_time")
    private String sys_date_time;
    @Column(name = "setl_flag")
    private String setl_flag;
    @Column(name = "setle_date")
    private String setle_date;
    @Column(name = "partial_rev_flag")
    private String partial_rev_flag;
    @Column(name = "original_txn_amount")
    private String original_txn_amount;
    @Column(name = "onus_offus_flag ")
    private String onus_offus_flag;
    @Column(name = "dms_sms_flag")
    private String dms_sms_flag;
    @Column(name = "pin_code")
    private String pin_code;
    @Column(name = "ecom_indicator")
    private String ecom_indicator;
    @Column(name = "void_rev_indicator")
    private String void_rev_indicator;
    @Column(name = "emv")
    private String emv;
    @Column(name = "service_fee")
    private String service_fee;
    @Column(name = "cgst")
    private String cgst;
    @Column(name = "sgst")
    private String sgst;
    @Column(name = "igst")
    private String igst;
    
    SwitchExtractEntity(final Integer serilaNumber, final String bank_code, final String network, final String unique_id, final String mti, final String pan, final String proc_code, final String txn_amount, final String setl_amount, final String txn_date_time, final String convert_rate, final String stan, final String local_txn_time, final String local_txn_date, final String expiry_date, final String setl_date, final String merchant_type, final String acquiring_inst_con_code, final String pos_entry_mode, final String pan_seq_number, final String pos_condition_code, final String txn_fee, final String acquiring_inst_id_code, final String forward_inst_id_code, final String rrn, final String approval_code, final String resp_code, final String srev_rest_code, final String terminal_id, final String merchant_id, final String card_accp_name_loc, final String adtl_private_data, final String txn_cur_code, final String setl_cur_code, final String adtl_amounts, final String advice_reason_code, final String res_private_data_de61, final String res_private_data_de62, final String res_private_data_de63, final String setl_code, final String rec_ins_code, final String replacement_amount, final String channel_type, final String sys_date_time, final String setl_flag, final String setle_date, final String partial_rev_flag, final String original_txn_amount, final String onus_offus_flag, final String dms_sms_flag, final String pin_code, final String ecom_indicator, final String void_rev_indicator, final String emv, final String service_fee, final String cgst, final String sgst, final String igst) {
        this.serilaNumber = serilaNumber;
        this.bank_code = bank_code;
        this.network = network;
        this.unique_id = unique_id;
        this.mti = mti;
        this.pan = pan;
        this.proc_code = proc_code;
        this.txn_amount = txn_amount;
        this.setl_amount = setl_amount;
        this.txn_date_time = txn_date_time;
        this.convert_rate = convert_rate;
        this.stan = stan;
        this.local_txn_time = local_txn_time;
        this.local_txn_date = local_txn_date;
        this.expiry_date = expiry_date;
        this.setl_date = setl_date;
        this.merchant_type = merchant_type;
        this.acquiring_inst_con_code = acquiring_inst_con_code;
        this.pos_entry_mode = pos_entry_mode;
        this.pan_seq_number = pan_seq_number;
        this.pos_condition_code = pos_condition_code;
        this.txn_fee = txn_fee;
        this.acquiring_inst_id_code = acquiring_inst_id_code;
        this.forward_inst_id_code = forward_inst_id_code;
        this.rrn = rrn;
        this.approval_code = approval_code;
        this.resp_code = resp_code;
        this.srev_rest_code = srev_rest_code;
        this.terminal_id = terminal_id;
        this.merchant_id = merchant_id;
        this.card_accp_name_loc = card_accp_name_loc;
        this.adtl_private_data = adtl_private_data;
        this.txn_cur_code = txn_cur_code;
        this.setl_cur_code = setl_cur_code;
        this.adtl_amounts = adtl_amounts;
        this.advice_reason_code = advice_reason_code;
        this.res_private_data_de61 = res_private_data_de61;
        this.res_private_data_de62 = res_private_data_de62;
        this.res_private_data_de63 = res_private_data_de63;
        this.setl_code = setl_code;
        this.rec_ins_code = rec_ins_code;
        this.replacement_amount = replacement_amount;
        this.channel_type = channel_type;
        this.sys_date_time = sys_date_time;
        this.setl_flag = setl_flag;
        this.setle_date = setle_date;
        this.partial_rev_flag = partial_rev_flag;
        this.original_txn_amount = original_txn_amount;
        this.onus_offus_flag = onus_offus_flag;
        this.dms_sms_flag = dms_sms_flag;
        this.pin_code = pin_code;
        this.ecom_indicator = ecom_indicator;
        this.void_rev_indicator = void_rev_indicator;
        this.emv = emv;
        this.service_fee = service_fee;
        this.cgst = cgst;
        this.sgst = sgst;
        this.igst = igst;
    }
    
    public static SwitchExtractEntityBuilder builder() {
        return new SwitchExtractEntityBuilder();
    }
    
    public Integer getSerilaNumber() {
        return this.serilaNumber;
    }
    
    public String getBank_code() {
        return this.bank_code;
    }
    
    public String getNetwork() {
        return this.network;
    }
    
    public String getUnique_id() {
        return this.unique_id;
    }
    
    public String getMti() {
        return this.mti;
    }
    
    public String getPan() {
        return this.pan;
    }
    
    public String getProc_code() {
        return this.proc_code;
    }
    
    public String getTxn_amount() {
        return this.txn_amount;
    }
    
    public String getSetl_amount() {
        return this.setl_amount;
    }
    
    public String getTxn_date_time() {
        return this.txn_date_time;
    }
    
    public String getConvert_rate() {
        return this.convert_rate;
    }
    
    public String getStan() {
        return this.stan;
    }
    
    public String getLocal_txn_time() {
        return this.local_txn_time;
    }
    
    public String getLocal_txn_date() {
        return this.local_txn_date;
    }
    
    public String getExpiry_date() {
        return this.expiry_date;
    }
    
    public String getSetl_date() {
        return this.setl_date;
    }
    
    public String getMerchant_type() {
        return this.merchant_type;
    }
    
    public String getAcquiring_inst_con_code() {
        return this.acquiring_inst_con_code;
    }
    
    public String getPos_entry_mode() {
        return this.pos_entry_mode;
    }
    
    public String getPan_seq_number() {
        return this.pan_seq_number;
    }
    
    public String getPos_condition_code() {
        return this.pos_condition_code;
    }
    
    public String getTxn_fee() {
        return this.txn_fee;
    }
    
    public String getAcquiring_inst_id_code() {
        return this.acquiring_inst_id_code;
    }
    
    public String getForward_inst_id_code() {
        return this.forward_inst_id_code;
    }
    
    public String getRrn() {
        return this.rrn;
    }
    
    public String getApproval_code() {
        return this.approval_code;
    }
    
    public String getResp_code() {
        return this.resp_code;
    }
    
    public String getSrev_rest_code() {
        return this.srev_rest_code;
    }
    
    public String getTerminal_id() {
        return this.terminal_id;
    }
    
    public String getMerchant_id() {
        return this.merchant_id;
    }
    
    public String getCard_accp_name_loc() {
        return this.card_accp_name_loc;
    }
    
    public String getAdtl_private_data() {
        return this.adtl_private_data;
    }
    
    public String getTxn_cur_code() {
        return this.txn_cur_code;
    }
    
    public String getSetl_cur_code() {
        return this.setl_cur_code;
    }
    
    public String getAdtl_amounts() {
        return this.adtl_amounts;
    }
    
    public String getAdvice_reason_code() {
        return this.advice_reason_code;
    }
    
    public String getRes_private_data_de61() {
        return this.res_private_data_de61;
    }
    
    public String getRes_private_data_de62() {
        return this.res_private_data_de62;
    }
    
    public String getRes_private_data_de63() {
        return this.res_private_data_de63;
    }
    
    public String getSetl_code() {
        return this.setl_code;
    }
    
    public String getRec_ins_code() {
        return this.rec_ins_code;
    }
    
    public String getReplacement_amount() {
        return this.replacement_amount;
    }
    
    public String getChannel_type() {
        return this.channel_type;
    }
    
    public String getSys_date_time() {
        return this.sys_date_time;
    }
    
    public String getSetl_flag() {
        return this.setl_flag;
    }
    
    public String getSetle_date() {
        return this.setle_date;
    }
    
    public String getPartial_rev_flag() {
        return this.partial_rev_flag;
    }
    
    public String getOriginal_txn_amount() {
        return this.original_txn_amount;
    }
    
    public String getOnus_offus_flag() {
        return this.onus_offus_flag;
    }
    
    public String getDms_sms_flag() {
        return this.dms_sms_flag;
    }
    
    public String getPin_code() {
        return this.pin_code;
    }
    
    public String getEcom_indicator() {
        return this.ecom_indicator;
    }
    
    public String getVoid_rev_indicator() {
        return this.void_rev_indicator;
    }
    
    public String getEmv() {
        return this.emv;
    }
    
    public String getService_fee() {
        return this.service_fee;
    }
    
    public String getCgst() {
        return this.cgst;
    }
    
    public String getSgst() {
        return this.sgst;
    }
    
    public String getIgst() {
        return this.igst;
    }
    
    public void setSerilaNumber(final Integer serilaNumber) {
        this.serilaNumber = serilaNumber;
    }
    
    public void setBank_code(final String bank_code) {
        this.bank_code = bank_code;
    }
    
    public void setNetwork(final String network) {
        this.network = network;
    }
    
    public void setUnique_id(final String unique_id) {
        this.unique_id = unique_id;
    }
    
    public void setMti(final String mti) {
        this.mti = mti;
    }
    
    public void setPan(final String pan) {
        this.pan = pan;
    }
    
    public void setProc_code(final String proc_code) {
        this.proc_code = proc_code;
    }
    
    public void setTxn_amount(final String txn_amount) {
        this.txn_amount = txn_amount;
    }
    
    public void setSetl_amount(final String setl_amount) {
        this.setl_amount = setl_amount;
    }
    
    public void setTxn_date_time(final String txn_date_time) {
        this.txn_date_time = txn_date_time;
    }
    
    public void setConvert_rate(final String convert_rate) {
        this.convert_rate = convert_rate;
    }
    
    public void setStan(final String stan) {
        this.stan = stan;
    }
    
    public void setLocal_txn_time(final String local_txn_time) {
        this.local_txn_time = local_txn_time;
    }
    
    public void setLocal_txn_date(final String local_txn_date) {
        this.local_txn_date = local_txn_date;
    }
    
    public void setExpiry_date(final String expiry_date) {
        this.expiry_date = expiry_date;
    }
    
    public void setSetl_date(final String setl_date) {
        this.setl_date = setl_date;
    }
    
    public void setMerchant_type(final String merchant_type) {
        this.merchant_type = merchant_type;
    }
    
    public void setAcquiring_inst_con_code(final String acquiring_inst_con_code) {
        this.acquiring_inst_con_code = acquiring_inst_con_code;
    }
    
    public void setPos_entry_mode(final String pos_entry_mode) {
        this.pos_entry_mode = pos_entry_mode;
    }
    
    public void setPan_seq_number(final String pan_seq_number) {
        this.pan_seq_number = pan_seq_number;
    }
    
    public void setPos_condition_code(final String pos_condition_code) {
        this.pos_condition_code = pos_condition_code;
    }
    
    public void setTxn_fee(final String txn_fee) {
        this.txn_fee = txn_fee;
    }
    
    public void setAcquiring_inst_id_code(final String acquiring_inst_id_code) {
        this.acquiring_inst_id_code = acquiring_inst_id_code;
    }
    
    public void setForward_inst_id_code(final String forward_inst_id_code) {
        this.forward_inst_id_code = forward_inst_id_code;
    }
    
    public void setRrn(final String rrn) {
        this.rrn = rrn;
    }
    
    public void setApproval_code(final String approval_code) {
        this.approval_code = approval_code;
    }
    
    public void setResp_code(final String resp_code) {
        this.resp_code = resp_code;
    }
    
    public void setSrev_rest_code(final String srev_rest_code) {
        this.srev_rest_code = srev_rest_code;
    }
    
    public void setTerminal_id(final String terminal_id) {
        this.terminal_id = terminal_id;
    }
    
    public void setMerchant_id(final String merchant_id) {
        this.merchant_id = merchant_id;
    }
    
    public void setCard_accp_name_loc(final String card_accp_name_loc) {
        this.card_accp_name_loc = card_accp_name_loc;
    }
    
    public void setAdtl_private_data(final String adtl_private_data) {
        this.adtl_private_data = adtl_private_data;
    }
    
    public void setTxn_cur_code(final String txn_cur_code) {
        this.txn_cur_code = txn_cur_code;
    }
    
    public void setSetl_cur_code(final String setl_cur_code) {
        this.setl_cur_code = setl_cur_code;
    }
    
    public void setAdtl_amounts(final String adtl_amounts) {
        this.adtl_amounts = adtl_amounts;
    }
    
    public void setAdvice_reason_code(final String advice_reason_code) {
        this.advice_reason_code = advice_reason_code;
    }
    
    public void setRes_private_data_de61(final String res_private_data_de61) {
        this.res_private_data_de61 = res_private_data_de61;
    }
    
    public void setRes_private_data_de62(final String res_private_data_de62) {
        this.res_private_data_de62 = res_private_data_de62;
    }
    
    public void setRes_private_data_de63(final String res_private_data_de63) {
        this.res_private_data_de63 = res_private_data_de63;
    }
    
    public void setSetl_code(final String setl_code) {
        this.setl_code = setl_code;
    }
    
    public void setRec_ins_code(final String rec_ins_code) {
        this.rec_ins_code = rec_ins_code;
    }
    
    public void setReplacement_amount(final String replacement_amount) {
        this.replacement_amount = replacement_amount;
    }
    
    public void setChannel_type(final String channel_type) {
        this.channel_type = channel_type;
    }
    
    public void setSys_date_time(final String sys_date_time) {
        this.sys_date_time = sys_date_time;
    }
    
    public void setSetl_flag(final String setl_flag) {
        this.setl_flag = setl_flag;
    }
    
    public void setSetle_date(final String setle_date) {
        this.setle_date = setle_date;
    }
    
    public void setPartial_rev_flag(final String partial_rev_flag) {
        this.partial_rev_flag = partial_rev_flag;
    }
    
    public void setOriginal_txn_amount(final String original_txn_amount) {
        this.original_txn_amount = original_txn_amount;
    }
    
    public void setOnus_offus_flag(final String onus_offus_flag) {
        this.onus_offus_flag = onus_offus_flag;
    }
    
    public void setDms_sms_flag(final String dms_sms_flag) {
        this.dms_sms_flag = dms_sms_flag;
    }
    
    public void setPin_code(final String pin_code) {
        this.pin_code = pin_code;
    }
    
    public void setEcom_indicator(final String ecom_indicator) {
        this.ecom_indicator = ecom_indicator;
    }
    
    public void setVoid_rev_indicator(final String void_rev_indicator) {
        this.void_rev_indicator = void_rev_indicator;
    }
    
    public void setEmv(final String emv) {
        this.emv = emv;
    }
    
    public void setService_fee(final String service_fee) {
        this.service_fee = service_fee;
    }
    
    public void setCgst(final String cgst) {
        this.cgst = cgst;
    }
    
    public void setSgst(final String sgst) {
        this.sgst = sgst;
    }
    
    public void setIgst(final String igst) {
        this.igst = igst;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SwitchExtractEntity)) {
            return false;
        }
        final SwitchExtractEntity other = (SwitchExtractEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serilaNumber = this.getSerilaNumber();
        final Object other$serilaNumber = other.getSerilaNumber();
        Label_0065: {
            if (this$serilaNumber == null) {
                if (other$serilaNumber == null) {
                    break Label_0065;
                }
            }
            else if (this$serilaNumber.equals(other$serilaNumber)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$bank_code = this.getBank_code();
        final Object other$bank_code = other.getBank_code();
        Label_0102: {
            if (this$bank_code == null) {
                if (other$bank_code == null) {
                    break Label_0102;
                }
            }
            else if (this$bank_code.equals(other$bank_code)) {
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
        final Object this$unique_id = this.getUnique_id();
        final Object other$unique_id = other.getUnique_id();
        Label_0176: {
            if (this$unique_id == null) {
                if (other$unique_id == null) {
                    break Label_0176;
                }
            }
            else if (this$unique_id.equals(other$unique_id)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$mti = this.getMti();
        final Object other$mti = other.getMti();
        Label_0213: {
            if (this$mti == null) {
                if (other$mti == null) {
                    break Label_0213;
                }
            }
            else if (this$mti.equals(other$mti)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$pan = this.getPan();
        final Object other$pan = other.getPan();
        Label_0250: {
            if (this$pan == null) {
                if (other$pan == null) {
                    break Label_0250;
                }
            }
            else if (this$pan.equals(other$pan)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$proc_code = this.getProc_code();
        final Object other$proc_code = other.getProc_code();
        Label_0287: {
            if (this$proc_code == null) {
                if (other$proc_code == null) {
                    break Label_0287;
                }
            }
            else if (this$proc_code.equals(other$proc_code)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$txn_amount = this.getTxn_amount();
        final Object other$txn_amount = other.getTxn_amount();
        Label_0324: {
            if (this$txn_amount == null) {
                if (other$txn_amount == null) {
                    break Label_0324;
                }
            }
            else if (this$txn_amount.equals(other$txn_amount)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$setl_amount = this.getSetl_amount();
        final Object other$setl_amount = other.getSetl_amount();
        Label_0361: {
            if (this$setl_amount == null) {
                if (other$setl_amount == null) {
                    break Label_0361;
                }
            }
            else if (this$setl_amount.equals(other$setl_amount)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$txn_date_time = this.getTxn_date_time();
        final Object other$txn_date_time = other.getTxn_date_time();
        Label_0398: {
            if (this$txn_date_time == null) {
                if (other$txn_date_time == null) {
                    break Label_0398;
                }
            }
            else if (this$txn_date_time.equals(other$txn_date_time)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$convert_rate = this.getConvert_rate();
        final Object other$convert_rate = other.getConvert_rate();
        Label_0435: {
            if (this$convert_rate == null) {
                if (other$convert_rate == null) {
                    break Label_0435;
                }
            }
            else if (this$convert_rate.equals(other$convert_rate)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$stan = this.getStan();
        final Object other$stan = other.getStan();
        Label_0472: {
            if (this$stan == null) {
                if (other$stan == null) {
                    break Label_0472;
                }
            }
            else if (this$stan.equals(other$stan)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$local_txn_time = this.getLocal_txn_time();
        final Object other$local_txn_time = other.getLocal_txn_time();
        Label_0509: {
            if (this$local_txn_time == null) {
                if (other$local_txn_time == null) {
                    break Label_0509;
                }
            }
            else if (this$local_txn_time.equals(other$local_txn_time)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$local_txn_date = this.getLocal_txn_date();
        final Object other$local_txn_date = other.getLocal_txn_date();
        Label_0546: {
            if (this$local_txn_date == null) {
                if (other$local_txn_date == null) {
                    break Label_0546;
                }
            }
            else if (this$local_txn_date.equals(other$local_txn_date)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$expiry_date = this.getExpiry_date();
        final Object other$expiry_date = other.getExpiry_date();
        Label_0583: {
            if (this$expiry_date == null) {
                if (other$expiry_date == null) {
                    break Label_0583;
                }
            }
            else if (this$expiry_date.equals(other$expiry_date)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$setl_date = this.getSetl_date();
        final Object other$setl_date = other.getSetl_date();
        Label_0620: {
            if (this$setl_date == null) {
                if (other$setl_date == null) {
                    break Label_0620;
                }
            }
            else if (this$setl_date.equals(other$setl_date)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$merchant_type = this.getMerchant_type();
        final Object other$merchant_type = other.getMerchant_type();
        Label_0657: {
            if (this$merchant_type == null) {
                if (other$merchant_type == null) {
                    break Label_0657;
                }
            }
            else if (this$merchant_type.equals(other$merchant_type)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$acquiring_inst_con_code = this.getAcquiring_inst_con_code();
        final Object other$acquiring_inst_con_code = other.getAcquiring_inst_con_code();
        Label_0694: {
            if (this$acquiring_inst_con_code == null) {
                if (other$acquiring_inst_con_code == null) {
                    break Label_0694;
                }
            }
            else if (this$acquiring_inst_con_code.equals(other$acquiring_inst_con_code)) {
                break Label_0694;
            }
            return false;
        }
        final Object this$pos_entry_mode = this.getPos_entry_mode();
        final Object other$pos_entry_mode = other.getPos_entry_mode();
        Label_0731: {
            if (this$pos_entry_mode == null) {
                if (other$pos_entry_mode == null) {
                    break Label_0731;
                }
            }
            else if (this$pos_entry_mode.equals(other$pos_entry_mode)) {
                break Label_0731;
            }
            return false;
        }
        final Object this$pan_seq_number = this.getPan_seq_number();
        final Object other$pan_seq_number = other.getPan_seq_number();
        Label_0768: {
            if (this$pan_seq_number == null) {
                if (other$pan_seq_number == null) {
                    break Label_0768;
                }
            }
            else if (this$pan_seq_number.equals(other$pan_seq_number)) {
                break Label_0768;
            }
            return false;
        }
        final Object this$pos_condition_code = this.getPos_condition_code();
        final Object other$pos_condition_code = other.getPos_condition_code();
        Label_0805: {
            if (this$pos_condition_code == null) {
                if (other$pos_condition_code == null) {
                    break Label_0805;
                }
            }
            else if (this$pos_condition_code.equals(other$pos_condition_code)) {
                break Label_0805;
            }
            return false;
        }
        final Object this$txn_fee = this.getTxn_fee();
        final Object other$txn_fee = other.getTxn_fee();
        Label_0842: {
            if (this$txn_fee == null) {
                if (other$txn_fee == null) {
                    break Label_0842;
                }
            }
            else if (this$txn_fee.equals(other$txn_fee)) {
                break Label_0842;
            }
            return false;
        }
        final Object this$acquiring_inst_id_code = this.getAcquiring_inst_id_code();
        final Object other$acquiring_inst_id_code = other.getAcquiring_inst_id_code();
        Label_0879: {
            if (this$acquiring_inst_id_code == null) {
                if (other$acquiring_inst_id_code == null) {
                    break Label_0879;
                }
            }
            else if (this$acquiring_inst_id_code.equals(other$acquiring_inst_id_code)) {
                break Label_0879;
            }
            return false;
        }
        final Object this$forward_inst_id_code = this.getForward_inst_id_code();
        final Object other$forward_inst_id_code = other.getForward_inst_id_code();
        Label_0916: {
            if (this$forward_inst_id_code == null) {
                if (other$forward_inst_id_code == null) {
                    break Label_0916;
                }
            }
            else if (this$forward_inst_id_code.equals(other$forward_inst_id_code)) {
                break Label_0916;
            }
            return false;
        }
        final Object this$rrn = this.getRrn();
        final Object other$rrn = other.getRrn();
        Label_0953: {
            if (this$rrn == null) {
                if (other$rrn == null) {
                    break Label_0953;
                }
            }
            else if (this$rrn.equals(other$rrn)) {
                break Label_0953;
            }
            return false;
        }
        final Object this$approval_code = this.getApproval_code();
        final Object other$approval_code = other.getApproval_code();
        Label_0990: {
            if (this$approval_code == null) {
                if (other$approval_code == null) {
                    break Label_0990;
                }
            }
            else if (this$approval_code.equals(other$approval_code)) {
                break Label_0990;
            }
            return false;
        }
        final Object this$resp_code = this.getResp_code();
        final Object other$resp_code = other.getResp_code();
        Label_1027: {
            if (this$resp_code == null) {
                if (other$resp_code == null) {
                    break Label_1027;
                }
            }
            else if (this$resp_code.equals(other$resp_code)) {
                break Label_1027;
            }
            return false;
        }
        final Object this$srev_rest_code = this.getSrev_rest_code();
        final Object other$srev_rest_code = other.getSrev_rest_code();
        Label_1064: {
            if (this$srev_rest_code == null) {
                if (other$srev_rest_code == null) {
                    break Label_1064;
                }
            }
            else if (this$srev_rest_code.equals(other$srev_rest_code)) {
                break Label_1064;
            }
            return false;
        }
        final Object this$terminal_id = this.getTerminal_id();
        final Object other$terminal_id = other.getTerminal_id();
        Label_1101: {
            if (this$terminal_id == null) {
                if (other$terminal_id == null) {
                    break Label_1101;
                }
            }
            else if (this$terminal_id.equals(other$terminal_id)) {
                break Label_1101;
            }
            return false;
        }
        final Object this$merchant_id = this.getMerchant_id();
        final Object other$merchant_id = other.getMerchant_id();
        Label_1138: {
            if (this$merchant_id == null) {
                if (other$merchant_id == null) {
                    break Label_1138;
                }
            }
            else if (this$merchant_id.equals(other$merchant_id)) {
                break Label_1138;
            }
            return false;
        }
        final Object this$card_accp_name_loc = this.getCard_accp_name_loc();
        final Object other$card_accp_name_loc = other.getCard_accp_name_loc();
        Label_1175: {
            if (this$card_accp_name_loc == null) {
                if (other$card_accp_name_loc == null) {
                    break Label_1175;
                }
            }
            else if (this$card_accp_name_loc.equals(other$card_accp_name_loc)) {
                break Label_1175;
            }
            return false;
        }
        final Object this$adtl_private_data = this.getAdtl_private_data();
        final Object other$adtl_private_data = other.getAdtl_private_data();
        Label_1212: {
            if (this$adtl_private_data == null) {
                if (other$adtl_private_data == null) {
                    break Label_1212;
                }
            }
            else if (this$adtl_private_data.equals(other$adtl_private_data)) {
                break Label_1212;
            }
            return false;
        }
        final Object this$txn_cur_code = this.getTxn_cur_code();
        final Object other$txn_cur_code = other.getTxn_cur_code();
        Label_1249: {
            if (this$txn_cur_code == null) {
                if (other$txn_cur_code == null) {
                    break Label_1249;
                }
            }
            else if (this$txn_cur_code.equals(other$txn_cur_code)) {
                break Label_1249;
            }
            return false;
        }
        final Object this$setl_cur_code = this.getSetl_cur_code();
        final Object other$setl_cur_code = other.getSetl_cur_code();
        Label_1286: {
            if (this$setl_cur_code == null) {
                if (other$setl_cur_code == null) {
                    break Label_1286;
                }
            }
            else if (this$setl_cur_code.equals(other$setl_cur_code)) {
                break Label_1286;
            }
            return false;
        }
        final Object this$adtl_amounts = this.getAdtl_amounts();
        final Object other$adtl_amounts = other.getAdtl_amounts();
        Label_1323: {
            if (this$adtl_amounts == null) {
                if (other$adtl_amounts == null) {
                    break Label_1323;
                }
            }
            else if (this$adtl_amounts.equals(other$adtl_amounts)) {
                break Label_1323;
            }
            return false;
        }
        final Object this$advice_reason_code = this.getAdvice_reason_code();
        final Object other$advice_reason_code = other.getAdvice_reason_code();
        Label_1360: {
            if (this$advice_reason_code == null) {
                if (other$advice_reason_code == null) {
                    break Label_1360;
                }
            }
            else if (this$advice_reason_code.equals(other$advice_reason_code)) {
                break Label_1360;
            }
            return false;
        }
        final Object this$res_private_data_de61 = this.getRes_private_data_de61();
        final Object other$res_private_data_de61 = other.getRes_private_data_de61();
        Label_1397: {
            if (this$res_private_data_de61 == null) {
                if (other$res_private_data_de61 == null) {
                    break Label_1397;
                }
            }
            else if (this$res_private_data_de61.equals(other$res_private_data_de61)) {
                break Label_1397;
            }
            return false;
        }
        final Object this$res_private_data_de62 = this.getRes_private_data_de62();
        final Object other$res_private_data_de62 = other.getRes_private_data_de62();
        Label_1434: {
            if (this$res_private_data_de62 == null) {
                if (other$res_private_data_de62 == null) {
                    break Label_1434;
                }
            }
            else if (this$res_private_data_de62.equals(other$res_private_data_de62)) {
                break Label_1434;
            }
            return false;
        }
        final Object this$res_private_data_de63 = this.getRes_private_data_de63();
        final Object other$res_private_data_de63 = other.getRes_private_data_de63();
        Label_1471: {
            if (this$res_private_data_de63 == null) {
                if (other$res_private_data_de63 == null) {
                    break Label_1471;
                }
            }
            else if (this$res_private_data_de63.equals(other$res_private_data_de63)) {
                break Label_1471;
            }
            return false;
        }
        final Object this$setl_code = this.getSetl_code();
        final Object other$setl_code = other.getSetl_code();
        Label_1508: {
            if (this$setl_code == null) {
                if (other$setl_code == null) {
                    break Label_1508;
                }
            }
            else if (this$setl_code.equals(other$setl_code)) {
                break Label_1508;
            }
            return false;
        }
        final Object this$rec_ins_code = this.getRec_ins_code();
        final Object other$rec_ins_code = other.getRec_ins_code();
        Label_1545: {
            if (this$rec_ins_code == null) {
                if (other$rec_ins_code == null) {
                    break Label_1545;
                }
            }
            else if (this$rec_ins_code.equals(other$rec_ins_code)) {
                break Label_1545;
            }
            return false;
        }
        final Object this$replacement_amount = this.getReplacement_amount();
        final Object other$replacement_amount = other.getReplacement_amount();
        Label_1582: {
            if (this$replacement_amount == null) {
                if (other$replacement_amount == null) {
                    break Label_1582;
                }
            }
            else if (this$replacement_amount.equals(other$replacement_amount)) {
                break Label_1582;
            }
            return false;
        }
        final Object this$channel_type = this.getChannel_type();
        final Object other$channel_type = other.getChannel_type();
        Label_1619: {
            if (this$channel_type == null) {
                if (other$channel_type == null) {
                    break Label_1619;
                }
            }
            else if (this$channel_type.equals(other$channel_type)) {
                break Label_1619;
            }
            return false;
        }
        final Object this$sys_date_time = this.getSys_date_time();
        final Object other$sys_date_time = other.getSys_date_time();
        Label_1656: {
            if (this$sys_date_time == null) {
                if (other$sys_date_time == null) {
                    break Label_1656;
                }
            }
            else if (this$sys_date_time.equals(other$sys_date_time)) {
                break Label_1656;
            }
            return false;
        }
        final Object this$setl_flag = this.getSetl_flag();
        final Object other$setl_flag = other.getSetl_flag();
        Label_1693: {
            if (this$setl_flag == null) {
                if (other$setl_flag == null) {
                    break Label_1693;
                }
            }
            else if (this$setl_flag.equals(other$setl_flag)) {
                break Label_1693;
            }
            return false;
        }
        final Object this$setle_date = this.getSetle_date();
        final Object other$setle_date = other.getSetle_date();
        Label_1730: {
            if (this$setle_date == null) {
                if (other$setle_date == null) {
                    break Label_1730;
                }
            }
            else if (this$setle_date.equals(other$setle_date)) {
                break Label_1730;
            }
            return false;
        }
        final Object this$partial_rev_flag = this.getPartial_rev_flag();
        final Object other$partial_rev_flag = other.getPartial_rev_flag();
        Label_1767: {
            if (this$partial_rev_flag == null) {
                if (other$partial_rev_flag == null) {
                    break Label_1767;
                }
            }
            else if (this$partial_rev_flag.equals(other$partial_rev_flag)) {
                break Label_1767;
            }
            return false;
        }
        final Object this$original_txn_amount = this.getOriginal_txn_amount();
        final Object other$original_txn_amount = other.getOriginal_txn_amount();
        Label_1804: {
            if (this$original_txn_amount == null) {
                if (other$original_txn_amount == null) {
                    break Label_1804;
                }
            }
            else if (this$original_txn_amount.equals(other$original_txn_amount)) {
                break Label_1804;
            }
            return false;
        }
        final Object this$onus_offus_flag = this.getOnus_offus_flag();
        final Object other$onus_offus_flag = other.getOnus_offus_flag();
        Label_1841: {
            if (this$onus_offus_flag == null) {
                if (other$onus_offus_flag == null) {
                    break Label_1841;
                }
            }
            else if (this$onus_offus_flag.equals(other$onus_offus_flag)) {
                break Label_1841;
            }
            return false;
        }
        final Object this$dms_sms_flag = this.getDms_sms_flag();
        final Object other$dms_sms_flag = other.getDms_sms_flag();
        Label_1878: {
            if (this$dms_sms_flag == null) {
                if (other$dms_sms_flag == null) {
                    break Label_1878;
                }
            }
            else if (this$dms_sms_flag.equals(other$dms_sms_flag)) {
                break Label_1878;
            }
            return false;
        }
        final Object this$pin_code = this.getPin_code();
        final Object other$pin_code = other.getPin_code();
        Label_1915: {
            if (this$pin_code == null) {
                if (other$pin_code == null) {
                    break Label_1915;
                }
            }
            else if (this$pin_code.equals(other$pin_code)) {
                break Label_1915;
            }
            return false;
        }
        final Object this$ecom_indicator = this.getEcom_indicator();
        final Object other$ecom_indicator = other.getEcom_indicator();
        Label_1952: {
            if (this$ecom_indicator == null) {
                if (other$ecom_indicator == null) {
                    break Label_1952;
                }
            }
            else if (this$ecom_indicator.equals(other$ecom_indicator)) {
                break Label_1952;
            }
            return false;
        }
        final Object this$void_rev_indicator = this.getVoid_rev_indicator();
        final Object other$void_rev_indicator = other.getVoid_rev_indicator();
        Label_1989: {
            if (this$void_rev_indicator == null) {
                if (other$void_rev_indicator == null) {
                    break Label_1989;
                }
            }
            else if (this$void_rev_indicator.equals(other$void_rev_indicator)) {
                break Label_1989;
            }
            return false;
        }
        final Object this$emv = this.getEmv();
        final Object other$emv = other.getEmv();
        Label_2026: {
            if (this$emv == null) {
                if (other$emv == null) {
                    break Label_2026;
                }
            }
            else if (this$emv.equals(other$emv)) {
                break Label_2026;
            }
            return false;
        }
        final Object this$service_fee = this.getService_fee();
        final Object other$service_fee = other.getService_fee();
        Label_2063: {
            if (this$service_fee == null) {
                if (other$service_fee == null) {
                    break Label_2063;
                }
            }
            else if (this$service_fee.equals(other$service_fee)) {
                break Label_2063;
            }
            return false;
        }
        final Object this$cgst = this.getCgst();
        final Object other$cgst = other.getCgst();
        Label_2100: {
            if (this$cgst == null) {
                if (other$cgst == null) {
                    break Label_2100;
                }
            }
            else if (this$cgst.equals(other$cgst)) {
                break Label_2100;
            }
            return false;
        }
        final Object this$sgst = this.getSgst();
        final Object other$sgst = other.getSgst();
        Label_2137: {
            if (this$sgst == null) {
                if (other$sgst == null) {
                    break Label_2137;
                }
            }
            else if (this$sgst.equals(other$sgst)) {
                break Label_2137;
            }
            return false;
        }
        final Object this$igst = this.getIgst();
        final Object other$igst = other.getIgst();
        if (this$igst == null) {
            if (other$igst == null) {
                return true;
            }
        }
        else if (this$igst.equals(other$igst)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof SwitchExtractEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serilaNumber = this.getSerilaNumber();
        result = result * 59 + (($serilaNumber == null) ? 43 : $serilaNumber.hashCode());
        final Object $bank_code = this.getBank_code();
        result = result * 59 + (($bank_code == null) ? 43 : $bank_code.hashCode());
        final Object $network = this.getNetwork();
        result = result * 59 + (($network == null) ? 43 : $network.hashCode());
        final Object $unique_id = this.getUnique_id();
        result = result * 59 + (($unique_id == null) ? 43 : $unique_id.hashCode());
        final Object $mti = this.getMti();
        result = result * 59 + (($mti == null) ? 43 : $mti.hashCode());
        final Object $pan = this.getPan();
        result = result * 59 + (($pan == null) ? 43 : $pan.hashCode());
        final Object $proc_code = this.getProc_code();
        result = result * 59 + (($proc_code == null) ? 43 : $proc_code.hashCode());
        final Object $txn_amount = this.getTxn_amount();
        result = result * 59 + (($txn_amount == null) ? 43 : $txn_amount.hashCode());
        final Object $setl_amount = this.getSetl_amount();
        result = result * 59 + (($setl_amount == null) ? 43 : $setl_amount.hashCode());
        final Object $txn_date_time = this.getTxn_date_time();
        result = result * 59 + (($txn_date_time == null) ? 43 : $txn_date_time.hashCode());
        final Object $convert_rate = this.getConvert_rate();
        result = result * 59 + (($convert_rate == null) ? 43 : $convert_rate.hashCode());
        final Object $stan = this.getStan();
        result = result * 59 + (($stan == null) ? 43 : $stan.hashCode());
        final Object $local_txn_time = this.getLocal_txn_time();
        result = result * 59 + (($local_txn_time == null) ? 43 : $local_txn_time.hashCode());
        final Object $local_txn_date = this.getLocal_txn_date();
        result = result * 59 + (($local_txn_date == null) ? 43 : $local_txn_date.hashCode());
        final Object $expiry_date = this.getExpiry_date();
        result = result * 59 + (($expiry_date == null) ? 43 : $expiry_date.hashCode());
        final Object $setl_date = this.getSetl_date();
        result = result * 59 + (($setl_date == null) ? 43 : $setl_date.hashCode());
        final Object $merchant_type = this.getMerchant_type();
        result = result * 59 + (($merchant_type == null) ? 43 : $merchant_type.hashCode());
        final Object $acquiring_inst_con_code = this.getAcquiring_inst_con_code();
        result = result * 59 + (($acquiring_inst_con_code == null) ? 43 : $acquiring_inst_con_code.hashCode());
        final Object $pos_entry_mode = this.getPos_entry_mode();
        result = result * 59 + (($pos_entry_mode == null) ? 43 : $pos_entry_mode.hashCode());
        final Object $pan_seq_number = this.getPan_seq_number();
        result = result * 59 + (($pan_seq_number == null) ? 43 : $pan_seq_number.hashCode());
        final Object $pos_condition_code = this.getPos_condition_code();
        result = result * 59 + (($pos_condition_code == null) ? 43 : $pos_condition_code.hashCode());
        final Object $txn_fee = this.getTxn_fee();
        result = result * 59 + (($txn_fee == null) ? 43 : $txn_fee.hashCode());
        final Object $acquiring_inst_id_code = this.getAcquiring_inst_id_code();
        result = result * 59 + (($acquiring_inst_id_code == null) ? 43 : $acquiring_inst_id_code.hashCode());
        final Object $forward_inst_id_code = this.getForward_inst_id_code();
        result = result * 59 + (($forward_inst_id_code == null) ? 43 : $forward_inst_id_code.hashCode());
        final Object $rrn = this.getRrn();
        result = result * 59 + (($rrn == null) ? 43 : $rrn.hashCode());
        final Object $approval_code = this.getApproval_code();
        result = result * 59 + (($approval_code == null) ? 43 : $approval_code.hashCode());
        final Object $resp_code = this.getResp_code();
        result = result * 59 + (($resp_code == null) ? 43 : $resp_code.hashCode());
        final Object $srev_rest_code = this.getSrev_rest_code();
        result = result * 59 + (($srev_rest_code == null) ? 43 : $srev_rest_code.hashCode());
        final Object $terminal_id = this.getTerminal_id();
        result = result * 59 + (($terminal_id == null) ? 43 : $terminal_id.hashCode());
        final Object $merchant_id = this.getMerchant_id();
        result = result * 59 + (($merchant_id == null) ? 43 : $merchant_id.hashCode());
        final Object $card_accp_name_loc = this.getCard_accp_name_loc();
        result = result * 59 + (($card_accp_name_loc == null) ? 43 : $card_accp_name_loc.hashCode());
        final Object $adtl_private_data = this.getAdtl_private_data();
        result = result * 59 + (($adtl_private_data == null) ? 43 : $adtl_private_data.hashCode());
        final Object $txn_cur_code = this.getTxn_cur_code();
        result = result * 59 + (($txn_cur_code == null) ? 43 : $txn_cur_code.hashCode());
        final Object $setl_cur_code = this.getSetl_cur_code();
        result = result * 59 + (($setl_cur_code == null) ? 43 : $setl_cur_code.hashCode());
        final Object $adtl_amounts = this.getAdtl_amounts();
        result = result * 59 + (($adtl_amounts == null) ? 43 : $adtl_amounts.hashCode());
        final Object $advice_reason_code = this.getAdvice_reason_code();
        result = result * 59 + (($advice_reason_code == null) ? 43 : $advice_reason_code.hashCode());
        final Object $res_private_data_de61 = this.getRes_private_data_de61();
        result = result * 59 + (($res_private_data_de61 == null) ? 43 : $res_private_data_de61.hashCode());
        final Object $res_private_data_de62 = this.getRes_private_data_de62();
        result = result * 59 + (($res_private_data_de62 == null) ? 43 : $res_private_data_de62.hashCode());
        final Object $res_private_data_de63 = this.getRes_private_data_de63();
        result = result * 59 + (($res_private_data_de63 == null) ? 43 : $res_private_data_de63.hashCode());
        final Object $setl_code = this.getSetl_code();
        result = result * 59 + (($setl_code == null) ? 43 : $setl_code.hashCode());
        final Object $rec_ins_code = this.getRec_ins_code();
        result = result * 59 + (($rec_ins_code == null) ? 43 : $rec_ins_code.hashCode());
        final Object $replacement_amount = this.getReplacement_amount();
        result = result * 59 + (($replacement_amount == null) ? 43 : $replacement_amount.hashCode());
        final Object $channel_type = this.getChannel_type();
        result = result * 59 + (($channel_type == null) ? 43 : $channel_type.hashCode());
        final Object $sys_date_time = this.getSys_date_time();
        result = result * 59 + (($sys_date_time == null) ? 43 : $sys_date_time.hashCode());
        final Object $setl_flag = this.getSetl_flag();
        result = result * 59 + (($setl_flag == null) ? 43 : $setl_flag.hashCode());
        final Object $setle_date = this.getSetle_date();
        result = result * 59 + (($setle_date == null) ? 43 : $setle_date.hashCode());
        final Object $partial_rev_flag = this.getPartial_rev_flag();
        result = result * 59 + (($partial_rev_flag == null) ? 43 : $partial_rev_flag.hashCode());
        final Object $original_txn_amount = this.getOriginal_txn_amount();
        result = result * 59 + (($original_txn_amount == null) ? 43 : $original_txn_amount.hashCode());
        final Object $onus_offus_flag = this.getOnus_offus_flag();
        result = result * 59 + (($onus_offus_flag == null) ? 43 : $onus_offus_flag.hashCode());
        final Object $dms_sms_flag = this.getDms_sms_flag();
        result = result * 59 + (($dms_sms_flag == null) ? 43 : $dms_sms_flag.hashCode());
        final Object $pin_code = this.getPin_code();
        result = result * 59 + (($pin_code == null) ? 43 : $pin_code.hashCode());
        final Object $ecom_indicator = this.getEcom_indicator();
        result = result * 59 + (($ecom_indicator == null) ? 43 : $ecom_indicator.hashCode());
        final Object $void_rev_indicator = this.getVoid_rev_indicator();
        result = result * 59 + (($void_rev_indicator == null) ? 43 : $void_rev_indicator.hashCode());
        final Object $emv = this.getEmv();
        result = result * 59 + (($emv == null) ? 43 : $emv.hashCode());
        final Object $service_fee = this.getService_fee();
        result = result * 59 + (($service_fee == null) ? 43 : $service_fee.hashCode());
        final Object $cgst = this.getCgst();
        result = result * 59 + (($cgst == null) ? 43 : $cgst.hashCode());
        final Object $sgst = this.getSgst();
        result = result * 59 + (($sgst == null) ? 43 : $sgst.hashCode());
        final Object $igst = this.getIgst();
        result = result * 59 + (($igst == null) ? 43 : $igst.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "SwitchExtractEntity(serilaNumber=" + this.getSerilaNumber() + ", bank_code=" + this.getBank_code() + ", network=" + this.getNetwork() + ", unique_id=" + this.getUnique_id() + ", mti=" + this.getMti() + ", pan=" + this.getPan() + ", proc_code=" + this.getProc_code() + ", txn_amount=" + this.getTxn_amount() + ", setl_amount=" + this.getSetl_amount() + ", txn_date_time=" + this.getTxn_date_time() + ", convert_rate=" + this.getConvert_rate() + ", stan=" + this.getStan() + ", local_txn_time=" + this.getLocal_txn_time() + ", local_txn_date=" + this.getLocal_txn_date() + ", expiry_date=" + this.getExpiry_date() + ", setl_date=" + this.getSetl_date() + ", merchant_type=" + this.getMerchant_type() + ", acquiring_inst_con_code=" + this.getAcquiring_inst_con_code() + ", pos_entry_mode=" + this.getPos_entry_mode() + ", pan_seq_number=" + this.getPan_seq_number() + ", pos_condition_code=" + this.getPos_condition_code() + ", txn_fee=" + this.getTxn_fee() + ", acquiring_inst_id_code=" + this.getAcquiring_inst_id_code() + ", forward_inst_id_code=" + this.getForward_inst_id_code() + ", rrn=" + this.getRrn() + ", approval_code=" + this.getApproval_code() + ", resp_code=" + this.getResp_code() + ", srev_rest_code=" + this.getSrev_rest_code() + ", terminal_id=" + this.getTerminal_id() + ", merchant_id=" + this.getMerchant_id() + ", card_accp_name_loc=" + this.getCard_accp_name_loc() + ", adtl_private_data=" + this.getAdtl_private_data() + ", txn_cur_code=" + this.getTxn_cur_code() + ", setl_cur_code=" + this.getSetl_cur_code() + ", adtl_amounts=" + this.getAdtl_amounts() + ", advice_reason_code=" + this.getAdvice_reason_code() + ", res_private_data_de61=" + this.getRes_private_data_de61() + ", res_private_data_de62=" + this.getRes_private_data_de62() + ", res_private_data_de63=" + this.getRes_private_data_de63() + ", setl_code=" + this.getSetl_code() + ", rec_ins_code=" + this.getRec_ins_code() + ", replacement_amount=" + this.getReplacement_amount() + ", channel_type=" + this.getChannel_type() + ", sys_date_time=" + this.getSys_date_time() + ", setl_flag=" + this.getSetl_flag() + ", setle_date=" + this.getSetle_date() + ", partial_rev_flag=" + this.getPartial_rev_flag() + ", original_txn_amount=" + this.getOriginal_txn_amount() + ", onus_offus_flag=" + this.getOnus_offus_flag() + ", dms_sms_flag=" + this.getDms_sms_flag() + ", pin_code=" + this.getPin_code() + ", ecom_indicator=" + this.getEcom_indicator() + ", void_rev_indicator=" + this.getVoid_rev_indicator() + ", emv=" + this.getEmv() + ", service_fee=" + this.getService_fee() + ", cgst=" + this.getCgst() + ", sgst=" + this.getSgst() + ", igst=" + this.getIgst();
    }
    
    public static class SwitchExtractEntityBuilder
    {
        private Integer serilaNumber;
        private String bank_code;
        private String network;
        private String unique_id;
        private String mti;
        private String pan;
        private String proc_code;
        private String txn_amount;
        private String setl_amount;
        private String txn_date_time;
        private String convert_rate;
        private String stan;
        private String local_txn_time;
        private String local_txn_date;
        private String expiry_date;
        private String setl_date;
        private String merchant_type;
        private String acquiring_inst_con_code;
        private String pos_entry_mode;
        private String pan_seq_number;
        private String pos_condition_code;
        private String txn_fee;
        private String acquiring_inst_id_code;
        private String forward_inst_id_code;
        private String rrn;
        private String approval_code;
        private String resp_code;
        private String srev_rest_code;
        private String terminal_id;
        private String merchant_id;
        private String card_accp_name_loc;
        private String adtl_private_data;
        private String txn_cur_code;
        private String setl_cur_code;
        private String adtl_amounts;
        private String advice_reason_code;
        private String res_private_data_de61;
        private String res_private_data_de62;
        private String res_private_data_de63;
        private String setl_code;
        private String rec_ins_code;
        private String replacement_amount;
        private String channel_type;
        private String sys_date_time;
        private String setl_flag;
        private String setle_date;
        private String partial_rev_flag;
        private String original_txn_amount;
        private String onus_offus_flag;
        private String dms_sms_flag;
        private String pin_code;
        private String ecom_indicator;
        private String void_rev_indicator;
        private String emv;
        private String service_fee;
        private String cgst;
        private String sgst;
        private String igst;
        
        SwitchExtractEntityBuilder() {
        }
        
        public SwitchExtractEntityBuilder serilaNumber(final Integer serilaNumber) {
            this.serilaNumber = serilaNumber;
            return this;
        }
        
        public SwitchExtractEntityBuilder bank_code(final String bank_code) {
            this.bank_code = bank_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder network(final String network) {
            this.network = network;
            return this;
        }
        
        public SwitchExtractEntityBuilder unique_id(final String unique_id) {
            this.unique_id = unique_id;
            return this;
        }
        
        public SwitchExtractEntityBuilder mti(final String mti) {
            this.mti = mti;
            return this;
        }
        
        public SwitchExtractEntityBuilder pan(final String pan) {
            this.pan = pan;
            return this;
        }
        
        public SwitchExtractEntityBuilder proc_code(final String proc_code) {
            this.proc_code = proc_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder txn_amount(final String txn_amount) {
            this.txn_amount = txn_amount;
            return this;
        }
        
        public SwitchExtractEntityBuilder setl_amount(final String setl_amount) {
            this.setl_amount = setl_amount;
            return this;
        }
        
        public SwitchExtractEntityBuilder txn_date_time(final String txn_date_time) {
            this.txn_date_time = txn_date_time;
            return this;
        }
        
        public SwitchExtractEntityBuilder convert_rate(final String convert_rate) {
            this.convert_rate = convert_rate;
            return this;
        }
        
        public SwitchExtractEntityBuilder stan(final String stan) {
            this.stan = stan;
            return this;
        }
        
        public SwitchExtractEntityBuilder local_txn_time(final String local_txn_time) {
            this.local_txn_time = local_txn_time;
            return this;
        }
        
        public SwitchExtractEntityBuilder local_txn_date(final String local_txn_date) {
            this.local_txn_date = local_txn_date;
            return this;
        }
        
        public SwitchExtractEntityBuilder expiry_date(final String expiry_date) {
            this.expiry_date = expiry_date;
            return this;
        }
        
        public SwitchExtractEntityBuilder setl_date(final String setl_date) {
            this.setl_date = setl_date;
            return this;
        }
        
        public SwitchExtractEntityBuilder merchant_type(final String merchant_type) {
            this.merchant_type = merchant_type;
            return this;
        }
        
        public SwitchExtractEntityBuilder acquiring_inst_con_code(final String acquiring_inst_con_code) {
            this.acquiring_inst_con_code = acquiring_inst_con_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder pos_entry_mode(final String pos_entry_mode) {
            this.pos_entry_mode = pos_entry_mode;
            return this;
        }
        
        public SwitchExtractEntityBuilder pan_seq_number(final String pan_seq_number) {
            this.pan_seq_number = pan_seq_number;
            return this;
        }
        
        public SwitchExtractEntityBuilder pos_condition_code(final String pos_condition_code) {
            this.pos_condition_code = pos_condition_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder txn_fee(final String txn_fee) {
            this.txn_fee = txn_fee;
            return this;
        }
        
        public SwitchExtractEntityBuilder acquiring_inst_id_code(final String acquiring_inst_id_code) {
            this.acquiring_inst_id_code = acquiring_inst_id_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder forward_inst_id_code(final String forward_inst_id_code) {
            this.forward_inst_id_code = forward_inst_id_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder rrn(final String rrn) {
            this.rrn = rrn;
            return this;
        }
        
        public SwitchExtractEntityBuilder approval_code(final String approval_code) {
            this.approval_code = approval_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder resp_code(final String resp_code) {
            this.resp_code = resp_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder srev_rest_code(final String srev_rest_code) {
            this.srev_rest_code = srev_rest_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder terminal_id(final String terminal_id) {
            this.terminal_id = terminal_id;
            return this;
        }
        
        public SwitchExtractEntityBuilder merchant_id(final String merchant_id) {
            this.merchant_id = merchant_id;
            return this;
        }
        
        public SwitchExtractEntityBuilder card_accp_name_loc(final String card_accp_name_loc) {
            this.card_accp_name_loc = card_accp_name_loc;
            return this;
        }
        
        public SwitchExtractEntityBuilder adtl_private_data(final String adtl_private_data) {
            this.adtl_private_data = adtl_private_data;
            return this;
        }
        
        public SwitchExtractEntityBuilder txn_cur_code(final String txn_cur_code) {
            this.txn_cur_code = txn_cur_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder setl_cur_code(final String setl_cur_code) {
            this.setl_cur_code = setl_cur_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder adtl_amounts(final String adtl_amounts) {
            this.adtl_amounts = adtl_amounts;
            return this;
        }
        
        public SwitchExtractEntityBuilder advice_reason_code(final String advice_reason_code) {
            this.advice_reason_code = advice_reason_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder res_private_data_de61(final String res_private_data_de61) {
            this.res_private_data_de61 = res_private_data_de61;
            return this;
        }
        
        public SwitchExtractEntityBuilder res_private_data_de62(final String res_private_data_de62) {
            this.res_private_data_de62 = res_private_data_de62;
            return this;
        }
        
        public SwitchExtractEntityBuilder res_private_data_de63(final String res_private_data_de63) {
            this.res_private_data_de63 = res_private_data_de63;
            return this;
        }
        
        public SwitchExtractEntityBuilder setl_code(final String setl_code) {
            this.setl_code = setl_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder rec_ins_code(final String rec_ins_code) {
            this.rec_ins_code = rec_ins_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder replacement_amount(final String replacement_amount) {
            this.replacement_amount = replacement_amount;
            return this;
        }
        
        public SwitchExtractEntityBuilder channel_type(final String channel_type) {
            this.channel_type = channel_type;
            return this;
        }
        
        public SwitchExtractEntityBuilder sys_date_time(final String sys_date_time) {
            this.sys_date_time = sys_date_time;
            return this;
        }
        
        public SwitchExtractEntityBuilder setl_flag(final String setl_flag) {
            this.setl_flag = setl_flag;
            return this;
        }
        
        public SwitchExtractEntityBuilder setle_date(final String setle_date) {
            this.setle_date = setle_date;
            return this;
        }
        
        public SwitchExtractEntityBuilder partial_rev_flag(final String partial_rev_flag) {
            this.partial_rev_flag = partial_rev_flag;
            return this;
        }
        
        public SwitchExtractEntityBuilder original_txn_amount(final String original_txn_amount) {
            this.original_txn_amount = original_txn_amount;
            return this;
        }
        
        public SwitchExtractEntityBuilder onus_offus_flag(final String onus_offus_flag) {
            this.onus_offus_flag = onus_offus_flag;
            return this;
        }
        
        public SwitchExtractEntityBuilder dms_sms_flag(final String dms_sms_flag) {
            this.dms_sms_flag = dms_sms_flag;
            return this;
        }
        
        public SwitchExtractEntityBuilder pin_code(final String pin_code) {
            this.pin_code = pin_code;
            return this;
        }
        
        public SwitchExtractEntityBuilder ecom_indicator(final String ecom_indicator) {
            this.ecom_indicator = ecom_indicator;
            return this;
        }
        
        public SwitchExtractEntityBuilder void_rev_indicator(final String void_rev_indicator) {
            this.void_rev_indicator = void_rev_indicator;
            return this;
        }
        
        public SwitchExtractEntityBuilder emv(final String emv) {
            this.emv = emv;
            return this;
        }
        
        public SwitchExtractEntityBuilder service_fee(final String service_fee) {
            this.service_fee = service_fee;
            return this;
        }
        
        public SwitchExtractEntityBuilder cgst(final String cgst) {
            this.cgst = cgst;
            return this;
        }
        
        public SwitchExtractEntityBuilder sgst(final String sgst) {
            this.sgst = sgst;
            return this;
        }
        
        public SwitchExtractEntityBuilder igst(final String igst) {
            this.igst = igst;
            return this;
        }
        
        public SwitchExtractEntity build() {
            return new SwitchExtractEntity(this.serilaNumber, this.bank_code, this.network, this.unique_id, this.mti, this.pan, this.proc_code, this.txn_amount, this.setl_amount, this.txn_date_time, this.convert_rate, this.stan, this.local_txn_time, this.local_txn_date, this.expiry_date, this.setl_date, this.merchant_type, this.acquiring_inst_con_code, this.pos_entry_mode, this.pan_seq_number, this.pos_condition_code, this.txn_fee, this.acquiring_inst_id_code, this.forward_inst_id_code, this.rrn, this.approval_code, this.resp_code, this.srev_rest_code, this.terminal_id, this.merchant_id, this.card_accp_name_loc, this.adtl_private_data, this.txn_cur_code, this.setl_cur_code, this.adtl_amounts, this.advice_reason_code, this.res_private_data_de61, this.res_private_data_de62, this.res_private_data_de63, this.setl_code, this.rec_ins_code, this.replacement_amount, this.channel_type, this.sys_date_time, this.setl_flag, this.setle_date, this.partial_rev_flag, this.original_txn_amount, this.onus_offus_flag, this.dms_sms_flag, this.pin_code, this.ecom_indicator, this.void_rev_indicator, this.emv, this.service_fee, this.cgst, this.sgst, this.igst);
        }
        
        @Override
        public String toString() {
            return "SwitchExtractEntity.SwitchExtractEntityBuilder(serilaNumber=" + this.serilaNumber + ", bank_code=" + this.bank_code + ", network=" + this.network + ", unique_id=" + this.unique_id + ", mti=" + this.mti + ", pan=" + this.pan + ", proc_code=" + this.proc_code + ", txn_amount=" + this.txn_amount + ", setl_amount=" + this.setl_amount + ", txn_date_time=" + this.txn_date_time + ", convert_rate=" + this.convert_rate + ", stan=" + this.stan + ", local_txn_time=" + this.local_txn_time + ", local_txn_date=" + this.local_txn_date + ", expiry_date=" + this.expiry_date + ", setl_date=" + this.setl_date + ", merchant_type=" + this.merchant_type + ", acquiring_inst_con_code=" + this.acquiring_inst_con_code + ", pos_entry_mode=" + this.pos_entry_mode + ", pan_seq_number=" + this.pan_seq_number + ", pos_condition_code=" + this.pos_condition_code + ", txn_fee=" + this.txn_fee + ", acquiring_inst_id_code=" + this.acquiring_inst_id_code + ", forward_inst_id_code=" + this.forward_inst_id_code + ", rrn=" + this.rrn + ", approval_code=" + this.approval_code + ", resp_code=" + this.resp_code + ", srev_rest_code=" + this.srev_rest_code + ", terminal_id=" + this.terminal_id + ", merchant_id=" + this.merchant_id + ", card_accp_name_loc=" + this.card_accp_name_loc + ", adtl_private_data=" + this.adtl_private_data + ", txn_cur_code=" + this.txn_cur_code + ", setl_cur_code=" + this.setl_cur_code + ", adtl_amounts=" + this.adtl_amounts + ", advice_reason_code=" + this.advice_reason_code + ", res_private_data_de61=" + this.res_private_data_de61 + ", res_private_data_de62=" + this.res_private_data_de62 + ", res_private_data_de63=" + this.res_private_data_de63 + ", setl_code=" + this.setl_code + ", rec_ins_code=" + this.rec_ins_code + ", replacement_amount=" + this.replacement_amount + ", channel_type=" + this.channel_type + ", sys_date_time=" + this.sys_date_time + ", setl_flag=" + this.setl_flag + ", setle_date=" + this.setle_date + ", partial_rev_flag=" + this.partial_rev_flag + ", original_txn_amount=" + this.original_txn_amount + ", onus_offus_flag=" + this.onus_offus_flag + ", dms_sms_flag=" + this.dms_sms_flag + ", pin_code=" + this.pin_code + ", ecom_indicator=" + this.ecom_indicator + ", void_rev_indicator=" + this.void_rev_indicator + ", emv=" + this.emv + ", service_fee=" + this.service_fee + ", cgst=" + this.cgst + ", sgst=" + this.sgst + ", igst=" + this.igst;
        }
    }
}
