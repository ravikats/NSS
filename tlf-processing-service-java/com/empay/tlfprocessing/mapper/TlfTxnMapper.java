package com.empay.tlfprocessing.mapper;

import com.empay.common.irf.IrfTxnData;
import com.empay.common.irf.IrfTxnDataMapper;
import com.empay.entities.PosTransactionEntity;
import org.springframework.stereotype.Component;

/**
 * Adapter from TLF's {@link PosTransactionEntity} to the shared {@link IrfTxnData}
 * DTO consumed by irf-service. Field names are matched against the getters actually
 * present on {@code PosTransactionEntity} in this tree.
 *
 * <p>Post-migration TLF no longer holds calculators; this mapper (plus the
 * {@code HttpIrfCalculator} bean) is the whole IRF surface of the service.
 */
@Component
public class TlfTxnMapper implements IrfTxnDataMapper<PosTransactionEntity> {

    @Override
    public IrfTxnData toIrfData(PosTransactionEntity txn) {
        IrfTxnData data = new IrfTxnData()
                .setSerialNumber(txn.getSerialNumber())
                .setInsCode(txn.getInsCode())
                .setNetwork(txn.getNetwork())
                .setScheme(txn.getScheme())
                .setMcc(txn.getMcc())
                .setTxnCode(txn.getTxnCode())
                .setResponseCode(txn.getResponseCode())
                .setApprovalCode(txn.getApprovalCode())
                .setRrn(txn.getRrn())
                .setPosEntryMode(txn.getPosEntryMode())
                .setPosConditionCode(txn.getPosConditionCode())
                .setServiceCode(txn.getServiceCode())
                .setCardSeqNumber(txn.getCardSeqNumber())
                .setTerminalType(txn.getTerminalType())
                .setTxnAmount(txn.getTxnAmount())
                .setSetlAmount(txn.getSetlAmount())
                .setCashBackAmount(txn.getCashBackAmount())
                .setNetAmount(txn.getNetAmount())
                .setTxnCurCode(txn.getTxnCurCode())
                .setSetlCurCode(txn.getSetlCurCode())
                .setFeePgmIndicator(txn.getFeePgmIndicator())
                .setReImbursementAttribute(txn.getReImbursementAttribute())
                .setMotoEcomIndicator(txn.getMotoEcomIndicator())
                .setMvv(txn.getMvv())
                .setNetworkData(txn.getNetworkData())
                .setMaid(txn.getMaid())
                .setTxnId(txn.getTxnId())
                .setMeCategoryType(txn.getMeCategoryType())
                .setChAuthAbility(txn.getChAuthAbility())
                .setCardInputAbility(txn.getCardInputAbility())
                .setCardCaptureAbility(txn.getCardCaptureAbility())
                .setCardInputMode(txn.getCardInputMode())
                .setChPresent(txn.getChPresent())
                .setCardPresent(txn.getCardPresent())
                .setTxnDateTime(txn.getTxnDateTime())
                .setMsgTypeId(txn.getMsgTypeId())
                .setTxnUniqueId(txn.getTxnUniqueId())
                .setCardNumber(txn.getCardNumber())
                .setEncCardNumber(txn.getEncCardNumber())
                .setAcqInstConCode(txn.getAcqInstConCode());
        // getTrlCapabilities() is a String on the entity but IrfTxnData carries it
        // as Character, so set both (char preferred, String as fallback) to match the
        // calculator's getTrlCapabilities()!=null ? String.valueOf(..) : getTerminalCapability().
        // oprtEnvironment is the first char of the ISO operating-environment code (the
        // MPGS UAEMcIRFCalculation semantics the MC calculator expects).
        data.setTrlCapabilities(firstChar(txn.getTrlCapabilities()));
        data.setTerminalCapability(txn.getTrlCapabilities());
        data.setOprtEnvironment(firstChar(txn.getOprtEnvironment()));
        return data;
    }

    private static Character firstChar(String s) {
        if (s == null || s.length() != 1) {
            return null;
        }
        return s.charAt(0);
    }
}
