package DataStructures;

import java.util.*;

import Database.*;

public class ChannelLibrary extends Library {
    public ChannelLibrary() {
        super("channel");
        VideoNode videosNode = new VideoNode("Videos");
        root.addChild(videosNode);
    }

    private VideoNode getChannelVideosNode(String username) {
        for (TreeNode child : QueryChannel.getChannelLibrary(username).root.children) {
            if (child.name.equals("Videos")) return (VideoNode) child;
        }
        return null;
    }

    public LinkedList<Video> getChannelVideos(String username) {
        Channel channel = QueryChannel.getChannel(username);
        VideoNode node = getChannelVideosNode(username);

        if (channel == null || node.videos == null) return new LinkedList<>();
        return node.videos;
    }

    public void uploadChannelVideo(String username, Video video) {
        getChannelVideosNode(username).upload(video);
    }

    public void deleteChannelVideo(String username, Video video) {
        getChannelVideosNode(username).delete(video);
    }

    public LinkedList<Video> getChannelVideosRecent(String username, int count) {
        LinkedList<Video> recent = new LinkedList<>();
        LinkedList<Video> videos = new LinkedList<>(getChannelVideos(username));

        for (int i = 0; i <= count - 1 && i < videos.size() && !videos.isEmpty(); i++) {
            Video video = videos.get(i);
            recent.add(video);
        }

        return recent;
    }

    public LinkedList<Video> getChannelVideosRange(String username, int from, int to) {
        LinkedList<Video> recent = new LinkedList<>();
        LinkedList<Video>  videos = new LinkedList<>(getChannelVideos(username));

        for (int i = 0;i >= from - 1 && i <= to - 1 && i < videos.size() && !videos.isEmpty(); i++) {
            Video video = videos.get(i);
            recent.add(video);
        }

        return recent;
    }

    public LinkedList<Video> getChannelVideosRecentRange(String username, int from, int to) {
        LinkedList<Video> recent = new LinkedList<>();
        LinkedList<Video> videos = new LinkedList<>(getChannelVideos(username));

        for (int i = 0;i >= from - 1 && i <= to - 1 && i < videos.size() && !videos.isEmpty(); i++) {
            int inverseIndex = videos.size() - 1 - i;
            Video video = videos.get(inverseIndex);
            recent.add(video);
        }

        return recent;
    }

    public TreeNode getPlaylistsParentNode(String username) {
        for (TreeNode child : Database.Users.get(username).channel.library.root.children) {
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

    public List<PlaylistNode> getEveryPlaylistNode() {
        List<PlaylistNode> playlists = new LinkedList<>();
        for (String username : QueryUser.getAllUsername()) {
            playlists.addAll(getAllPlaylistNode(username));
        }
        return playlists;
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
            QueryChannel.getChannelLibrary(username).getPlaylistsParentNode(username).children.remove(playlist);
    }

    public List<Video> getAllChannelVideos() {
        List<Video> videos = new LinkedList<>();
        for (String username : QueryUser.getAllUsername()) {
            videos.addAll(getChannelVideos(username));
        }
        return videos;
    }
}
