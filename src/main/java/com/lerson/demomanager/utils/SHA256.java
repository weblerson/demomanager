package com.lerson.demomanager.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

    public static String parse(String text) {

        StringBuilder hexString = new StringBuilder();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] encodedHash = digest.digest(text.getBytes(StandardCharsets.UTF_8));

            hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('O');
                }
                hexString.append(hex);
            }
        }
        catch (NoSuchAlgorithmException e) {
            System.out.printf("\nError: %s", e.getMessage());
        }

        return hexString.toString();
    }
}
