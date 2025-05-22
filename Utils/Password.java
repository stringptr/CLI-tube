import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public class Password {

    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    public static String hashPassword(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        byte[] hashedPassword = hash(password, salt);
        return Base64.getEncoder().encodeToString(salt) + "$" + Base64.getEncoder().encodeToString(hashedPassword);
    }

    public static boolean verifyPassword(String password, String storedHash) {
        String[] parts = storedHash.split("\\$");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hashedPassword = Base64.getDecoder().decode(parts[1]);

        byte[] computedHash = hash(password, salt);

        return Arrays.equals(hashedPassword, computedHash);
    }

    private static byte[] hash(String password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public static void main(String[] args) {
        String password = "mySecretPassword";
        String hashedPassword = hashPassword(password);
        System.out.println("Hashed password: " + hashedPassword);

        boolean passwordMatches = verifyPassword("mySecretPassword", hashedPassword);
        System.out.println("Password matches: " + passwordMatches);

        passwordMatches = verifyPassword("wrongPassword", hashedPassword);
        System.out.println("Password matches: " + passwordMatches);
    }
}
