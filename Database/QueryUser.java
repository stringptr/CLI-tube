package Database;

import java.util.*;

import DataStructures.*;

public class QueryUser {
    public static User getUser(String username) {
        return Database.Users.get(username);
    }

    public static Set<String> getAllUsername(){
        return new HashSet<>(Database.Users.keySet());
    }

    public static Collection<User> getAllUser() {
        return (Database.Users.values());
    }

    public static String getName(String username) {
        return getUser(username).display_name;
    }

    public static UserLibrary getUserLibrary(String username) {
        return getUser(username).library;
    }
}
