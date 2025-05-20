package DataStructures;

import java.time.LocalDateTime;

public class User {
    public String display_name;
    public LocalDateTime date_created;
    public UserLibrary library;
    public Channel channel; //null if user doesn't have channel

    public User(String display_name, LocalDateTime date_created, Channel channel) {
        this.display_name = display_name;
        this.date_created = date_created;
        this.channel = channel;
        this.library = new UserLibrary();
    }
}
