package DataStructures;

import java.time.LocalDateTime;

public class Channel {
    public String display_name;
    public String description;
    public ChannelLibrary library;
    public LocalDateTime date_created;

    public Channel(String display_name, String description, LocalDateTime date_created) {
        this.display_name = display_name;
        this.description = description;
        this.date_created = date_created;
        this.library = new ChannelLibrary();
    }
}
