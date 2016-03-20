package com.mforn.data.backend.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Static util class to handle MD5 hash
 */
public class HashUtils {

    /**
     * Convert an input string to a MD5 Hash
     *
     * @param string input String
     * @return hash input String
     */
    public static String md5(final String string) {
        String result = "";

        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(string.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }

            result = hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }

}
