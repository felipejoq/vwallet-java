package com.uncodigo.strings;

public class StringUtils {
    /**
     * This method generates a random string with the given length
     *
     * @param length the length of the string
     * @return the random string
     */
    public static String getRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (characters.length() * Math.random());
            if (i % 3 == 0 && i != 0)
                stringBuilder.append("-");
            stringBuilder.append(characters.charAt(index));
        }

        return stringBuilder.toString();
    }
}
