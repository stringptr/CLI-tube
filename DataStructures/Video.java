package DataStructures;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Video {
    public String title;
    public String description;
    public LocalDateTime date_uploaded;
    public LocalTime duration;

    public Video(String title, String description, LocalDateTime date_uploaded) {
        this.title = title;
        this.description = description;
        this.date_uploaded = date_uploaded;
    }

    public String toString() {
        return title;
    }
}
