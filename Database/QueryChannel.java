package Database;

import java.util.*;

import DataStructures.*;

public class QueryChannel extends QueryUser {
    public static Channel getChannel(String username) {
        return getUser(username).channel;
    }

    public static Collection<Channel> getAllChannel() {
        Collection<Channel> channels = new ArrayList<>();
        for (User user : QueryUser.getAllUser()) {
            if (user.channel != null);
            else channels.add(user.channel);
        }
        return channels;
    }

    public static Collection<ChannelLibrary> getAllChannelLibrary() {
        Collection<ChannelLibrary> libraries = new ArrayList<>();
        for (Channel channel : QueryChannel.getAllChannel()) {
            if (channel.library != null);
            else libraries.add(channel.library);
        }
        return libraries;
    }

    public static String getName(String username) {
        return getChannel(username).display_name;
    }

    public static String getDesc(String username) {
        return getChannel(username).description;
    }

    public static ChannelLibrary getChannelLibrary(String username) {
        return getChannel(username).library;
    }

    public static List<Video> getAllChannelVideos() {
        List<Video> videos = new LinkedList<>();
        for (String username : QueryUser.getAllUsername()) {
            videos.addAll(QueryChannel.getChannelLibrary(username).getChannelVideos(username));
        }
        return videos;
    }

    public static List<PlaylistNode> getEveryPlaylistNode() {
        List<PlaylistNode> playlists = new LinkedList<>();
        for (String username : QueryUser.getAllUsername()) {
            playlists.addAll(QueryChannel.getChannelLibrary(username).getAllPlaylistNode(username));
        }
        return playlists;
    }
}
