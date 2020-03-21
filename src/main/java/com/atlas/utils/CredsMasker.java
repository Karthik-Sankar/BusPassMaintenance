package com.atlas.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class CredsMasker {
    private static SecretKeySpec secretKey;
    private static final String skey = "ItsSherlocked";
    private static byte[] k;

    public static String encrypt(String str)
    {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA-1");
            k = skey.getBytes("UTF-8");
            k = sha.digest(k);
            k = Arrays.copyOf(k, 16);
            secretKey = new SecretKeySpec(k, "AES");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Encryption Error : " + e.toString());
        }
        return null;
    }

    public static String decrypt(String str)
    {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA-1");
            k = skey.getBytes("UTF-8");
            k = sha.digest(k);
            k = Arrays.copyOf(k, 16);
            secretKey = new SecretKeySpec(k, "AES");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(str)));
        }
        catch (Exception e)
        {
            System.out.println("Decryption Error : " + e.toString());
        }
        return null;
    }
}
