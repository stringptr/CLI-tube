package Database;

import DataStructures.*;

public class QueryUser {
    public static User getUser(String username) {
        return Database.Users.get(username);
    }

    public static String getName(String username) {
        return getUser(username).display_name;
    }

    public static UserLibrary getUserLibrary(String username) {
        return getUser(username).library;
    }
}
