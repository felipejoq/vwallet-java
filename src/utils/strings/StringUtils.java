package utils.strings;

public class StringUtils {
    public static String getRandomString(int length) {
        // Generate a random string of the given length and separate with dash 3 characters
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (characters.length() * Math.random());
            if(i % 3 == 0 && i != 0)
                stringBuilder.append("-");
            stringBuilder.append(characters.charAt(index));
        }

        return stringBuilder.toString();
    }
}
