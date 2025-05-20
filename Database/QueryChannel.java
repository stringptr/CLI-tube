package Database;

import DataStructures.*;

public class QueryChannel extends QueryUser {
    public static Channel getChannel(String username) {
        return getUser(username).channel;
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
}
