package DataStructures;

import java.util.*;

public class PlaylistNode extends TreeNode {
    public LinkedList<Video> playlist;
    String description;

    public PlaylistNode(String name, String description) {
        super(name);
        this.playlist = new LinkedList<>();
        this.description = description;
    }

    public void addVideo(Video video) {
        playlist.add(video);
    }

    @Override
    public String toString() {
        return name + " ? " + playlist;
    }
}
