package com.example.calendarapp.crypto;

import org.apache.commons.codec.binary.Hex;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.security.SecureRandom;

public class PBKDF2 {

    private static final int ITERATIONS = 10000;
    private static final int KEY_SIZE = 256;
    private static final int BYTE_SIZE = 16;

    public static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[BYTE_SIZE];
        sr.nextBytes(salt);
        return salt;
    }

    public static byte[] generateHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] chars = password.toCharArray();
        PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, KEY_SIZE);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return skf.generateSecret(spec).getEncoded();
    }

    public static boolean validatePassword(String password, byte[] salt, byte[] passwordHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] chars = password.toCharArray();
        PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, KEY_SIZE);

        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = passwordHash.length ^ testHash.length;
        for(int i = 0; i < passwordHash.length && i < testHash.length; i++)
        {
            diff |= passwordHash[i] ^ testHash[i];
            if(diff == 1) break;
        }
        return diff == 0;
    }


}
