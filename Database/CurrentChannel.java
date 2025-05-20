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

    public static ChannelLibrary getChannelLibrary() {
        return QueryChannel.getChannelLibrary(currentUsername);
    }
}
