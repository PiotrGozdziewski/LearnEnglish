package com.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    private MessageDigest digest;
    private String encodedPass;

    public SHA256(String toEncode) {
        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.update(toEncode.getBytes());
            byte[] hash = digest.digest();
            encodedPass = byteToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getEncoded() {
        return encodedPass;
    }

    private String byteToHex(byte[] tab) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tab.length; i++) {
            sb.append(Integer.toString((tab[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
