package Database;
import DataStructures.*;

public class CurrentChannel extends CurrentUser {
    public static Channel getChannel() {
        return QueryChannel.getChannel(currentUsername);
    }

    public static String getName() {
        return QueryChannel.getName(currentUsername);
    }

    public static String getDesc() {
        return QueryChannel.getDesc(currentUsername);
    }

    public static void delete() {
        CurrentUser.getUser().channel = null;
    }

    public static ChannelLibrary getChannelLibrary() {
        return QueryChannel.getChannelLibrary(currentUsername);
    }
}
