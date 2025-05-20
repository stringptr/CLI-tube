package Database;
import DataStructures.*;

public class CurrentUser {
    public static String currentUsername;

    public static String getUsername() {
        return currentUsername;
    }

    public static void set(String username) {
        currentUsername = username;
    }

    public static User getUser() {
        return QueryUser.getUser(currentUsername);
    }

    public static String getName() {
        return QueryUser.getName(currentUsername);
    }

    public static UserLibrary getUserLibrary() {
        return QueryUser.getUserLibrary(currentUsername);
    }
}
