// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.services;

import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPPrivateKey;
import java.util.Iterator;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.operator.jcajce.JcePublicKeyDataDecryptorFactoryBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.operator.KeyFingerPrintCalculator;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.PGPUtil;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.io.InputStream;
import org.springframework.stereotype.Service;

@Service
public class PGPFileDecryptor
{
    public static InputStream decryptPGPFile(final String encryptedFilePath, final String privateKeyPath, final String passphrase) throws Exception {
        Security.addProvider((Provider)new BouncyCastleProvider());
        final InputStream encryptedData = new BufferedInputStream(new FileInputStream(encryptedFilePath));
        try {
            final InputStream keyIn = new BufferedInputStream(new FileInputStream(privateKeyPath));
            try {
                final PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(keyIn), (KeyFingerPrintCalculator)new JcaKeyFingerprintCalculator());
                final PGPObjectFactory pgpF = new PGPObjectFactory(PGPUtil.getDecoderStream(encryptedData), (KeyFingerPrintCalculator)new JcaKeyFingerprintCalculator());
                final Object o = pgpF.nextObject();
                PGPEncryptedDataList enc;
                if (o instanceof final PGPEncryptedDataList list) {
                    enc = list;
                }
                else {
                    enc = (PGPEncryptedDataList)pgpF.nextObject();
                }
                final Iterator<?> it = enc.getEncryptedDataObjects();
                PGPPrivateKey sKey = null;
                PGPPublicKeyEncryptedData pbe = null;
                while (sKey == null && it.hasNext()) {
                    pbe = (PGPPublicKeyEncryptedData)it.next();
                    final PGPSecretKey pgpSecKey = pgpSec.getSecretKey(pbe.getKeyID());
                    if (pgpSecKey != null) {
                        sKey = pgpSecKey.extractPrivateKey(new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(passphrase.toCharArray()));
                    }
                }
                if (sKey == null) {
                    throw new IllegalArgumentException("Secret key for message not found.");
                }
                final InputStream clear = pbe.getDataStream(new JcePublicKeyDataDecryptorFactoryBuilder().setProvider("BC").build(sKey));
                final PGPObjectFactory plainFact = new PGPObjectFactory(clear, (KeyFingerPrintCalculator)new JcaKeyFingerprintCalculator());
                final Object message = plainFact.nextObject();
                if (message instanceof final PGPLiteralData literalData) {
                    final InputStream inputStream = literalData.getInputStream();
                    keyIn.close();
                    encryptedData.close();
                    return inputStream;
                }
                throw new PGPException("Invalid PGP encrypted content.");
            }
            catch (final Throwable t) {
                try {
                    keyIn.close();
                }
                catch (final Throwable exception) {
                    t.addSuppressed(exception);
                }
                throw t;
            }
        }
        catch (final Throwable t2) {
            try {
                encryptedData.close();
            }
            catch (final Throwable exception2) {
                t2.addSuppressed(exception2);
            }
            throw t2;
        }
    }
}
