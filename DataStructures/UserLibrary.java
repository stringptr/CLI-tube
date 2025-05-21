package DataStructures;

import java.util.*;

import Database.*;
import Utils.FormattedPrint;

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

    public QueueNode getQueueNode(String username) {
        for (TreeNode child : QueryUser.getUserLibrary(username).root.children) {
            if (child.name.equals("Queue")) return (QueueNode) child;
        }
        return null;
    }

    public Queue<Video> getQueue(String username) {
        return getQueueNode(username).queue;
    }

    public void addQueue(String username, Video video) {
        QueueNode queueNode = getQueueNode(username);
        if (queueNode != null) {
            getQueue(username).add(video);
        }
    }

    public void removeQueue(String username, Video video) {
        QueueNode queueNode = getQueueNode(username);
        if (queueNode != null) {
            getQueue(username).remove(video);
        }
    }

    public HistoryNode getHistoryNode(String username) {
        for (TreeNode child : QueryUser.getUserLibrary(username).root.children) {
            if (child.name.equals("History")) return (HistoryNode) child;
        }
        return null;
    }

    public WatcHashSetNode getWatchHashSetNode(String username) {
        for (TreeNode child : QueryUser.getUserLibrary(username).root.children) {
            if (child.name.equals("WatchHashSetNode")) return (WatcHashSetNode) child;
        }
        return new WatcHashSetNode(username);
    }

    public LinkedHashSet<Video> getWatchHashSetVideos(String username) {
        if (getWatchHashSetNode(username).watchashset.isEmpty()) return new LinkedHashSet<>();
        return getWatchHashSetNode(username).watchashset;
    }

    public void addWatchHashSetVideos(String username, Video video) {
        getWatchHashSetNode(username).watchashset.add(video);
    }

    public void removeWatchHashSetVideos(String username, Video video) {
        getWatchHashSetNode(username).watchashset.remove(video);
    }

    public void addHistory(String username, Video video) {
        getHistoryNode(username).watchVideo(video);
    }


    public Stack<Video> getHistory(String username) {
        return getHistoryNode(username).history;
    }

    public TreeNode getPlaylistsParentNode(String username) {
        for (TreeNode child : QueryUser.getUserLibrary(username).root.children) {
            if (child.name.equals("Playlists")) return child;
        }
        return null;
    }

    public LinkedList<PlaylistNode> getAllPlaylistNode(String username) {
        LinkedList<PlaylistNode> playlistsChild = new LinkedList<>();

        if (getPlaylistsParentNode(username) == null) return playlistsChild;
        for (TreeNode child : getPlaylistsParentNode(username).children) {
            playlistsChild.add((PlaylistNode) child);
        }

        return playlistsChild;
    }

    public PlaylistNode getPlaylist(String username, int index) {
        if (!getAllPlaylistNode(username).isEmpty());
        return getAllPlaylistNode(username).get(index);
    }

    public void addPlaylistVideo(PlaylistNode playlist, Video video) {
        playlist.addVideo(video);
    }

    public LinkedList<PlaylistNode> getPlaylistNodeRecent(String username, int count) {
        LinkedList<PlaylistNode> playlistsChild = new LinkedList<>();
        LinkedList <PlaylistNode> playlists = getAllPlaylistNode(username);


        for (int i=0; i < count && i < playlists.size() && !playlists.isEmpty(); i++) {
            int inverseIndex = playlists.size() - 1 - i;
            playlistsChild.add(playlists.get(inverseIndex));
        }

        return playlistsChild;
    }

    public LinkedList<PlaylistNode> getPlaylistNodeRecentRange(String username, int from, int to) {
        LinkedList<PlaylistNode> playlistsChild = new LinkedList<>();
        LinkedList <PlaylistNode> playlists = getAllPlaylistNode(username);

        for (int i=0; i >= from - 1 && i < to && i < playlists.size() && !playlists.isEmpty(); i++) {
            int inverseIndex = playlists.size() - 1 - i;
            playlistsChild.add(playlists.get(inverseIndex));
        }

        return playlistsChild;
    }

    public LinkedList<PlaylistNode> getPlaylistNodeRange(String username, int from, int to) {
        LinkedList<PlaylistNode> playlistsChild = new LinkedList<>();
        LinkedList <PlaylistNode> playlists = getAllPlaylistNode(username);

        for (int i=0; i >= from - 1 && i < to && i < playlists.size() && !playlists.isEmpty(); i++) {
            playlistsChild.add(playlists.get(i));
        }

        return playlistsChild;
    }

    public PlaylistNode createPlaylist(TreeNode playlistParent, String name, String description) {
        PlaylistNode playlist = new PlaylistNode(name, description);
        playlistParent.addChild(playlist);
        return playlist;
    }

    public void deletePlaylist(String username, PlaylistNode playlist) {
        QueryUser.getUserLibrary(username).getPlaylistsParentNode(username).children.remove(playlist);
    }
}
