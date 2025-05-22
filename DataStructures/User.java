package DataStructures;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class User {
    public String display_name;
    public LocalDateTime date_created;
    public UserLibrary library;
    public Channel channel; //null if user doesn't have channel
    private String hashedPassword; // Store the hashed password
    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;


    public User(String display_name, LocalDateTime date_created, Channel channel, String password) {
        this.display_name = display_name;
        this.date_created = date_created;
        this.channel = channel;
        this.library = new UserLibrary();
        this.hashedPassword = hashPassword(password);
    }

    public void setPassword(String password) {
        this.hashedPassword = hashPassword(password);
    }

    // Method to verify a password against the stored hash
    public boolean verifyPassword(String password) {
        return verifyPassword(password, this.hashedPassword);
    }

    private String hashPassword(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        byte[] hashedPassword = hash(password, salt);
        // Store the salt with the password hash
        return Base64.getEncoder().encodeToString(salt) + "$" + Base64.getEncoder().encodeToString(hashedPassword);
    }

    private boolean verifyPassword(String password, String storedHash) {
        if (storedHash == null) {
            return false; // No password set
        }
        String[] parts = storedHash.split("\\$");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hashedPassword = Base64.getDecoder().decode(parts[1]);

        byte[] computedHash = hash(password, salt);

        return Arrays.equals(hashedPassword, computedHash);
    }

    private byte[] hash(String password, byte[] salt) {
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

     //Getter for hashedPassword (Careful use)
    public String getHashedPassword() {
        return hashedPassword;
    }
}
