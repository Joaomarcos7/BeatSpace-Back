package com.beatspace.beatspace.util;
import java.security.SecureRandom;
public class Util {

    private static final String LETRAS_NUMEROS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    public static String generateRandomString(int length){
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETRAS_NUMEROS.length());
            sb.append(LETRAS_NUMEROS.charAt(index));
        }
        return sb.toString();
    }

}
