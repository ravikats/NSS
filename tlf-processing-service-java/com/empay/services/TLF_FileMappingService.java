// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import org.apache.logging.log4j.LogManager;
import java.util.Collections;
import java.util.Objects;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import com.empay.tlfprocessing.vo.SwitchExtractVo;
import java.util.List;
import org.springframework.core.env.Environment;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class TLF_FileMappingService
{
    private static final Logger log;
    private final Environment env;
    
    public List<SwitchExtractVo> doTlfFileMapping(final String fileName) {
        List<SwitchExtractVo> valueObjects = new ArrayList<SwitchExtractVo>();
        try (final BufferedReader br = new BufferedReader(new FileReader(this.env.getProperty("switch.file.inptPath") + fileName))) {
            String line = "";
            while (Objects.nonNull(line = br.readLine())) {
                final String[] fields = line.split("\\|");
                final SwitchExtractVo valueObject = new SwitchExtractVo();
                int index = 0;
                for (final String field : fields) {
                    switch (index) {
                        case 0: {
                            valueObject.setBankCode(field);
                            break;
                        }
                        case 1: {
                            valueObject.setNetwork(field);
                            break;
                        }
                        case 2: {
                            valueObject.setUniqueId(field);
                            break;
                        }
                        case 3: {
                            valueObject.setMti(field);
                            break;
                        }
                        case 4: {
                            valueObject.setCardNumber(field);
                            break;
                        }
                        case 5: {
                            valueObject.setProcessCode(field);
                            break;
                        }
                        case 6: {
                            valueObject.setAmountTransaction(field);
                            break;
                        }
                        case 7: {
                            valueObject.setAmountSettlement(field);
                            break;
                        }
                        case 8: {
                            valueObject.setTxnDateTime(field);
                            break;
                        }
                        case 9: {
                            valueObject.setStan(field);
                            break;
                        }
                        case 10: {
                            valueObject.setLocalTxnTime(field);
                            break;
                        }
                        case 11: {
                            valueObject.setLocalTxnDate(field);
                            break;
                        }
                        case 12: {
                            valueObject.setExpiryDate(field);
                            break;
                        }
                        case 13: {
                            valueObject.setSettlementDate(field);
                            break;
                        }
                        case 14: {
                            valueObject.setMcc(field);
                            break;
                        }
                        case 15: {
                            valueObject.setAcqInsConCode(field);
                            break;
                        }
                        case 16: {
                            valueObject.setPosEntryMode(field);
                            break;
                        }
                        case 17: {
                            valueObject.setPanSequence(field);
                            break;
                        }
                        case 18: {
                            valueObject.setPosCode(field);
                            break;
                        }
                        case 19: {
                            valueObject.setTxnFeeAmount(field);
                            break;
                        }
                        case 20: {
                            valueObject.setAcqInsIdCode(field);
                            break;
                        }
                        case 21: {
                            valueObject.setRetRefNumber(field);
                            break;
                        }
                        case 22: {
                            valueObject.setAuthIdResponse(field);
                            break;
                        }
                        case 23: {
                            valueObject.setResponseCode(field);
                            break;
                        }
                        case 24: {
                            valueObject.setServiceRestrictionCode(field);
                            break;
                        }
                        case 25: {
                            valueObject.setCardAcceptorTid(field);
                            break;
                        }
                        case 26: {
                            valueObject.setCardAcceptorId(field);
                            break;
                        }
                        case 27: {
                            valueObject.setCardAcceptorName(field);
                            break;
                        }
                        case 28: {
                            valueObject.setCardAcceptorStreetAddress(field);
                            break;
                        }
                        case 29: {
                            valueObject.setCardAcceptorCity(field);
                            break;
                        }
                        case 30: {
                            valueObject.setCardAcceptorStateCode(field);
                            break;
                        }
                        case 31: {
                            valueObject.setCardAcceptorCountryCode(field);
                            break;
                        }
                        case 40: {
                            valueObject.setTxnSource(field);
                            break;
                        }
                        case 41: {
                            valueObject.setServerDateTime(field);
                            break;
                        }
                        case 42: {
                            valueObject.setSettlementIndicator((Objects.nonNull(field) && !field.equals("")) ? Character.valueOf(field.charAt(0)) : null);
                            break;
                        }
                        case 43: {
                            valueObject.setOnusOffusIndicator(field);
                            break;
                        }
                        case 44: {
                            valueObject.setSmsDmsIndicator(field);
                            break;
                        }
                    }
                    ++index;
                }
                valueObjects.add(valueObject);
            }
        }
        catch (final Exception e) {
            valueObjects = Collections.emptyList();
            TLF_FileMappingService.log.error("ERROR:doTlfFileMapping():", (Throwable)e);
        }
        return valueObjects;
    }
    
    public TLF_FileMappingService(final Environment env) {
        this.env = env;
    }
    
    static {
        log = LogManager.getLogger((Class)TLF_FileMappingService.class);
    }
}
