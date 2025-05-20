package DataStructures;

import java.util.*;

public class VideoNode extends TreeNode {
    LinkedList<Video> videos;

    public VideoNode(String name) {
        super(name);
        videos = new LinkedList<>();
    }

    public void upload(Video video) {
        videos.push(video);
    }

    public void delete(Video video) {
        videos.remove(video);
    }

    @Override
    public String toString() {
        return name + " ? " + videos;
    }
}
