package DataStructures;

import java.util.*;

public class HistoryNode extends TreeNode {
    Stack<Video> history;

    public HistoryNode(String name) {
        super(name);
        history = new Stack<>();
    }

    public void watchVideo(Video video) {
        history.push(video);
    }

    @Override
    public String toString() {
        return name + " ? " + history;
    }
}
