package DataStructures;

import java.util.*;

public class TreeNode {
    public String name;
    public List<TreeNode> children;

    public TreeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
}
