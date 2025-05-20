package DataStructures;

import java.util.*;

public class WatcHashSetNode extends TreeNode {
    LinkedHashSet<Video> watchashset;

    public WatcHashSetNode(String name) {
        super(name);
        this.watchashset = new LinkedHashSet<>();
    }

    public void add(Video video) {
        watchashset.add(video);
    }

    public void remove(Video video) {
        watchashset.remove(video);
    }

    @Override
    public String toString() {
        return name + " ? " + watchashset;
    }
}
