package DataStructures;

import java.util.*;

public class QueueNode extends TreeNode {
    Queue<Video> queue;

    public QueueNode(String name) {
        super(name);
        queue = new LinkedList<>();
    }

    public void watchVideo(Video video) {
        queue.add(video);
    }

    @Override
    public String toString() {
        return name + " ? " + queue;
    }
}
