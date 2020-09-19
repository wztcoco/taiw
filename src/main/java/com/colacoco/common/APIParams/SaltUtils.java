package com.colacoco.common.APIParams;

import java.util.Random;

public class SaltUtils {
    public static String getSalt(int n){
        StringBuilder sb = new StringBuilder();
        char[] chars = "ABCDEFGIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()".toCharArray();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }
    public static void main(String[] args){
        String salt = getSalt(8);
    }
}
