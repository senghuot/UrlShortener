package senghout.github.com.atomizer;

public class AtomizerUtils {
    public static String encodeNumber(int number) {
        String letters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int size = letters.length();
        int count = 6;
        char[] shortURL = new char[7];
        while (count >= 0) {
            shortURL[count--] = letters.charAt(number % size);
            number /= size;
        }
        return new String(shortURL);
    }
}