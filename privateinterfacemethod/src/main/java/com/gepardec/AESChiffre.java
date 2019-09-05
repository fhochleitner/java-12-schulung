package com.gepardec;

import com.gepardec.interfaces.Chiffre;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESChiffre implements Chiffre {
    private Cipher cipher;
    private IvParameterSpec ivParameterSpec;
    private SecretKeySpec secretKeySpec;

    public AESChiffre(String secretKey) {
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            secretKey = secretKey.repeat((int) Math.ceil(16.0 / secretKey.length())).substring(0, 16);

            var salt = new byte[16];
            var secureRandom = new SecureRandom();
            secureRandom.nextBytes(salt);

            ivParameterSpec = new IvParameterSpec(salt);
            secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        }
        catch (Exception e) {
            throw new RuntimeException("can't construct AES chiffre: " + e.getMessage(), e);
        }
    }

    @Override
    public synchronized String encrypt(String plainText) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            return Base64.getEncoder().encodeToString(
                    cipher.doFinal(
                            plainText.getBytes()
                    )
            );
        }
        catch (Exception e) {
            throw new RuntimeException("can't encrypt plain text: " + e.getMessage(), e);
        }
    }

    @Override
    public synchronized String decrypt(String cipherText) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            return new String(
                    cipher.doFinal(
                            Base64.getDecoder().decode(cipherText)
                    )
            );
        }
        catch (Exception e) {
            throw new RuntimeException("can't decrypt cipher text: " + e.getMessage(), e);
        }
    }
    /* ????????????????????????????????? */
}