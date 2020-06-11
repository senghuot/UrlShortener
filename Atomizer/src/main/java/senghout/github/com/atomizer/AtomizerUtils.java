package senghout.github.com.atomizer;

import org.springframework.stereotype.Component;

@Component
public class AtomizerUtils {

    // 62^POWER yields the possible domain
    private static final int POWER = 8;

    public String encodeNumber(long number) {
        String letters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int size = letters.length();
        char[] shortURL = new char[POWER];
        for (int i = shortURL.length-1; i >= 0; i--) {
            shortURL[i] = letters.charAt((int) (number % size));
            number /= size;
        }
        return new String(shortURL);
    }
}
