package DataStructures;

import java.util.*;

import Database.*;

public class ChannelLibrary extends Library {
    public ChannelLibrary() {
        super("channel");
        VideoNode videosNode = new VideoNode("Videos");
        root.addChild(videosNode);
    }

    private VideoNode getChannelVideosNode() {
        for (TreeNode child : root.children) {
            if (child.name.equals("Videos")) return (VideoNode) child;
        }
        return null;
    }

    public LinkedList<Video> getChannelVideos() {
        VideoNode node = getChannelVideosNode();

        if (node.videos == null || node.videos.isEmpty()) return new LinkedList<>();
        return node.videos;
    }

    public void uploadChannelVideo(Video video) {
        getChannelVideosNode().upload(video);
    }

    public void deleteChannelVideo(Video video) {
        getChannelVideosNode().delete(video);
    }

    public LinkedList<Video> getChannelVideosRecent(int count) {
        LinkedList<Video> recent = new LinkedList<>();
        LinkedList<Video> videos = getChannelVideos();

        for (int i = 0; i <= count - 1 && i < videos.size() && !videos.isEmpty(); i++) {
            Video video = videos.get(videos.size() - 1 - i);
            recent.add(video);
        }

        return recent;
    }

    public LinkedList<Video> getChannelVideosRange(int from, int to) {
        LinkedList<Video> recent = new LinkedList<>();
        LinkedList<Video>  videos = getChannelVideos();

        for (int i = 0;i >= from - 1 && i <= to - 1 && i < videos.size() && !videos.isEmpty(); i++) {
            Video video = videos.get(i);
            recent.add(video);
        }

        return recent;
    }

    public LinkedList<Video> getChannelVideosRecentRange(int from, int to) {
        LinkedList<Video> recent = new LinkedList<>();
        LinkedList<Video> videos = new LinkedList<>(getChannelVideos());

        for (int i = 0;i >= from - 1 && i <= to - 1 && i < videos.size() && !videos.isEmpty(); i++) {
            int inverseIndex = videos.size() - 1 - i;
            Video video = videos.get(inverseIndex);
            recent.add(video);
        }

        return recent;
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

    public PlaylistNode getPlaylist(int index) {
        if (!getAllPlaylistNode().isEmpty());
        return getAllPlaylistNode().get(index);
    }

    public List<PlaylistNode> getEveryPlaylistNode() {
        List<PlaylistNode> playlists = new LinkedList<>();
        for (ChannelLibrary channel : QueryChannel.getAllChannelLibrary()) {
            LinkedList<PlaylistNode> playlist = channel.getAllPlaylistNode();
            playlists.addAll(playlist);
        }
        return playlists;
    }

    public LinkedList<PlaylistNode> getPlaylistNodeRecent(int count) {
        LinkedList<PlaylistNode> playlistsChild = new LinkedList<>();
        LinkedList <PlaylistNode> playlists = getAllPlaylistNode();


        for (int i=0; i < count && i < playlists.size() && !playlists.isEmpty(); i++) {
            playlistsChild.add(playlists.get(i));
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
            QueryChannel.getChannelLibrary(root.name).getPlaylistsParentNode().children.remove(playlist);
    }

    public List<Video> getAllChannelVideos() {
        List<Video> videos = new LinkedList<>();
        for (ChannelLibrary channel : QueryChannel.getAllChannelLibrary()) {
            videos.addAll(channel.getChannelVideos());
        }
        return videos;
    }
}
