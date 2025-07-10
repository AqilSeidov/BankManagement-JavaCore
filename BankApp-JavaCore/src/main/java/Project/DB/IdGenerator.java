package Project.DB;

import java.util.Random;

public class IdGenerator {
    final Random rand = new Random();
    final String charSet = "abXYZ0cdefgKLMNOPhijkCDITlmJQRSnopqrs234567tuvEFGHwxyzABUVW189";
    public String generateId() {

        final StringBuilder id = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            id.append(charSet.charAt(rand.nextInt(charSet.length())));
        }
        return id.toString();
    }
}
