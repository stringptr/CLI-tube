package DataStructures;

import java.util.LinkedList;

public class Library {
    TreeNode root;

    public Library(String username) {
        root = new TreeNode(username);
        TreeNode playlistParent = new TreeNode("Playlists");
        root.addChild(playlistParent);
    }

    public void printTree() {
        printNode(root, 0);
    }

    private void printNode(TreeNode node, int level) {
        System.out.println("  ".repeat(level) + "- " + node.name);
        for (TreeNode child : node.children) {
            if (child instanceof PlaylistNode) {
                System.out.println("  ".repeat(level + 1) + "* " + child); // show videos
            } else {
                printNode(child, level + 1);
            }
        }
    }
}
