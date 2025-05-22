package DataStructures;

import java.util.*;

import Database.*;

public class UserLibrary extends Library{
    public UserLibrary() {
        super("user");
        HistoryNode historyNode = new HistoryNode("History");
        root.addChild(historyNode);
        QueueNode queueNode = new QueueNode("Queue");
        root.addChild(queueNode);
        WatcHashSetNode watchashsetNode = new WatcHashSetNode("WatchHashSet");
        root.addChild(watchashsetNode);
    }

    public QueueNode getQueueNode() {
        for (TreeNode child : root.children) {
            if (child.name.equals("Queue")) return (QueueNode) child;
        }
        return null;
    }

    public Queue<Video> getQueue() {
        return getQueueNode().queue;
    }

    public void addQueue(Video video) {
        QueueNode queueNode = getQueueNode();
        if (queueNode != null) {
            getQueue().add(video);
        }
    }

    public void removeQueue(Video video) {
        QueueNode queueNode = getQueueNode();
        if (queueNode != null) {
            getQueue().remove(video);
        }
    }

    public HistoryNode getHistoryNode() {
        for (TreeNode child : root.children) {
            if (child.name.equals("History")) return (HistoryNode) child;
        }
        return null;
    }

    public WatcHashSetNode getWatchHashSetNode() {
        for (TreeNode child : root.children) {
            if (child.name.equals("WatchHashSet")) return (WatcHashSetNode) child;
        }
        return new WatcHashSetNode("");
    }

    public LinkedHashSet<Video> getWatchHashSetVideos(String username) {
        if (getWatchHashSetNode().watchashset.isEmpty()) return new LinkedHashSet<>();
        return getWatchHashSetNode().watchashset;
    }

    public void addWatchHashSetVideos(Video video) {
        getWatchHashSetNode().watchashset.add(video);
    }

    public void removeWatchHashSetVideos(Video video) {
        getWatchHashSetNode().watchashset.remove(video);
    }

    public void addHistory(Video video) {
        getHistoryNode().watchVideo(video);
    }


    public Stack<Video> getHistory() {
        return getHistoryNode().history;
    }

    public TreeNode getPlaylistsParentNode() {
        for (TreeNode child : root.children) {
            if (child.name.equals("Playlists")) return child;
        }
        return null;
    }

    public LinkedList<PlaylistNode> getAllPlaylistNode() {
        LinkedList<PlaylistNode> playlistsChild = new LinkedList<>();

        if (getPlaylistsParentNode() == null) return playlistsChild;
        for (TreeNode child : getPlaylistsParentNode().children) {
            playlistsChild.add((PlaylistNode) child);
        }

        return playlistsChild;
    }

    public void addPlaylist(PlaylistNode playlist) {
        getPlaylistsParentNode().addChild(playlist);
    }

    public PlaylistNode getPlaylist(int index) {
        if (!getAllPlaylistNode().isEmpty());
        return getAllPlaylistNode().get(index);
    }

    public void addPlaylistVideo(PlaylistNode playlist, Video video) {
        playlist.addVideo(video);
    }

    public LinkedList<PlaylistNode> getPlaylistNodeRecent(int count) {
        LinkedList<PlaylistNode> playlistsChild = new LinkedList<>();
        LinkedList <PlaylistNode> playlists = getAllPlaylistNode();


        for (int i=0; i < count && i < playlists.size() && !playlists.isEmpty(); i++) {
            int inverseIndex = playlists.size() - 1 - i;
            playlistsChild.add(playlists.get(inverseIndex));
        }

        return playlistsChild;
    }

    public LinkedList<PlaylistNode> getPlaylistNodeRecentRange(int from, int to) {
        LinkedList<PlaylistNode> playlistsChild = new LinkedList<>();
        LinkedList <PlaylistNode> playlists = getAllPlaylistNode();

        for (int i=0; i >= from - 1 && i < to && i < playlists.size() && !playlists.isEmpty(); i++) {
            int inverseIndex = playlists.size() - 1 - i;
            playlistsChild.add(playlists.get(inverseIndex));
        }

        return playlistsChild;
    }

    public LinkedList<PlaylistNode> getPlaylistNodeRange(int from, int to) {
        LinkedList<PlaylistNode> playlistsChild = new LinkedList<>();
        LinkedList <PlaylistNode> playlists = getAllPlaylistNode();

        for (int i=0; i >= from - 1 && i < to && i < playlists.size() && !playlists.isEmpty(); i++) {
            playlistsChild.add(playlists.get(i));
        }

        return playlistsChild;
    }

    public PlaylistNode createPlaylist(String name, String description) {
        PlaylistNode playlist = new PlaylistNode(name, description);
        getPlaylistsParentNode().addChild(playlist);
        return playlist;
    }

    public void deletePlaylist(PlaylistNode playlist) {
        getPlaylistsParentNode().children.remove(playlist);
    }
}
