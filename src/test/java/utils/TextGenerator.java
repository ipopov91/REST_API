package utils;

import java.security.SecureRandom;

public class TextGenerator {

    private final static String LETTERS = PropertyManager.getProperty("symbols");
    static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(LETTERS.charAt(rnd.nextInt(LETTERS.length())));
        return sb.toString();
    }
}
