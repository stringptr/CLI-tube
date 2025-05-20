import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

class FormattedPrint {
    public static final int DEFAULT_PADDING = 64;

    public static void center(String content, String border, int outerPadding) {
        int totalPadding = DEFAULT_PADDING - ( content.length() + (border.length()*2)+ (outerPadding*2) );
        if (totalPadding < 0) totalPadding = 0;
        int innerPaddingLength = totalPadding / 2;
        int otherinnerPaddingLength = totalPadding - innerPaddingLength;

        String innerPad = (" ").repeat(innerPaddingLength);
        String otherinnerPad = (" ").repeat(otherinnerPaddingLength);
        String outerPad = (" ").repeat(outerPadding);

        System.out.println(outerPad + border + innerPad + content + otherinnerPad + border + outerPad);
    }
}

class GetInput {
    public static final Scanner input = new Scanner(System.in);

    public static int integer(String prompt, String errortext, int padding) {
        int number;
        String beforePad = (" ").repeat(padding);
        while (true) {
            try {
                System.out.print(beforePad + prompt);
                number = input.nextInt();
                input.nextLine();
                return number;
            } catch (InputMismatchException e) {
                System.out.println(beforePad + errortext);
                input.nextLine();
            }
        }
    }

    public static int integerZeroPositiveCenter(String prompt, String errortext, int inputLength) {
        int leftErrorPadLength = (FormattedPrint.DEFAULT_PADDING - errortext.length() - inputLength)/2;
        String leftErrorPad = (" ").repeat(leftErrorPadLength);

        int number;
        while (true) {
            number = integerCenter(prompt, errortext, inputLength);
            if (number >= 0) return number;
            System.out.println(leftErrorPad + errortext);
        }
    }

    public static int integerPositiveCenter(String prompt, String errortext, int inputLength) {
        int leftErrorPadLength = (FormattedPrint.DEFAULT_PADDING - errortext.length() - inputLength)/2;
        String leftErrorPad = (" ").repeat(leftErrorPadLength);

        int number;
        while (true) {
            number = integerCenter(prompt, errortext, inputLength);
            if (number > 0) return number;
            System.out.println(leftErrorPad + errortext);
        }
    }

    public static int integerBoolCenter(String prompt, String errortext, int inputLength) {
        int leftErrorPadLength = (FormattedPrint.DEFAULT_PADDING - errortext.length() - inputLength)/2;
        String leftErrorPad = (" ").repeat(leftErrorPadLength);

        int number;
        while (true) {
            number = integerCenter(prompt, errortext, inputLength);
            if (number == 1 || number == 0) return number;
            System.out.println(leftErrorPad + errortext);
        }
    }

    public static int integerCenter(String prompt, String errortext, int inputLength) {
        int leftPromptPadLength = (FormattedPrint.DEFAULT_PADDING - prompt.length() - inputLength)/2;
        int leftErrorPadLength = (FormattedPrint.DEFAULT_PADDING - errortext.length() - inputLength)/2;
        String leftPromptPad = (" ").repeat(leftPromptPadLength);
        String leftErrorPad = (" ").repeat(leftErrorPadLength);

        int number;
        while (true) {
            try {
                System.out.print(leftPromptPad + prompt);
                number = input.nextInt();
                input.nextLine();
                return number;
            } catch (InputMismatchException e) {
                System.out.println(leftErrorPad + errortext + "\n");
                input.nextLine();
            }
        }
    }

    public static int integerPositive(String prompt, String errortext, int padding) {
        int number;
        String beforePad = (" ").repeat(padding);
        while (true) {
            number = integer(prompt, errortext, padding);
            if (number > 0) return number;
            System.out.println(beforePad + errortext);
        }
    }

    public static String stringLimited(String prompt, String errortext, int limit, int padding) {
        String sentences;
        String beforePad = (" ").repeat(padding);
        while (true) {
            System.out.print(beforePad + prompt);
            sentences = input.nextLine();

            if (sentences.length() <= limit) return sentences;
            System.out.println(beforePad + errortext);
        }
    }



    public static String stringLimitedCenter(String prompt, String errortext, int limit, int inputWidth) {
        int proomptTotalPad = 64 - prompt.length();
        if (proomptTotalPad < 0) proomptTotalPad = 0;
        int promptPadLength = proomptTotalPad / 2   ;

        int errorTotalPad = 64 - errortext.length();
        if (errorTotalPad < 0) errorTotalPad = 0;

        int inputTotalPad = 64 - inputWidth;
        if (inputTotalPad < 0) inputTotalPad = 0;
        int inputPadLength = inputTotalPad / 2;

        String promtPad = (" ").repeat(promptPadLength);
        String inputPad = (" ").repeat(inputPadLength);

        String sentences;

        while (true) {
            System.out.println(promtPad + prompt);
            System.out.print(inputPad);
            sentences = input.nextLine();

            if (sentences.length() <= limit) return sentences;

            FormattedPrint.center(errortext, "", 0);
            System.out.println("");
        }

    }
}

class Database {
    public static HashMap<String, User> Users = new HashMap<>();
}

class QueryUser {
    public static User getUser(String username) {
        return Database.Users.get(username);
    }

    public static String getName(String username) {
        return getUser(username).display_name;
    }

    public static UserLibrary getUserLibrary(String username) {
        return getUser(username).library;
    }
}

class QueryChannel extends QueryUser {
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

class CurrentUser {
    public static String currentUsername;

    public static String getUsername() {
        return currentUsername;
    }

    public static void set(String username) {
        currentUsername = username;
    }

    public static User getUser() {
        return QueryUser.getUser(currentUsername);
    }

    public static String getName() {
        return QueryUser.getName(currentUsername);
    }

    public static UserLibrary getUserLibrary() {
        return QueryUser.getUserLibrary(currentUsername);
    }
}

class CurrentChannel extends CurrentUser {
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

class Channel {
    String display_name;
    String description;
    ChannelLibrary library;
    LocalDateTime date_created;

    public Channel(String display_name, String description, LocalDateTime date_created) {
        this.display_name = display_name;
        this.description = description;
        this.date_created = date_created;
        this.library = new ChannelLibrary();
    }
}

class User {
    String display_name;
    LocalDateTime date_created;
    UserLibrary library;
    Channel channel; //null if user doesn't have channel

    public User(String display_name, LocalDateTime date_created, Channel channel) {
        this.display_name = display_name;
        this.date_created = date_created;
        this.channel = channel;
        this.library = new UserLibrary();
    }
}

class Video {
    String title;
    String description;
    LocalDateTime date_uploaded;
    LocalTime duration;

    public Video(String title, String description, LocalDateTime date_uploaded) {
        this.title = title;
        this.description = description;
        this.date_uploaded = date_uploaded;
    }

    public String toString() {
        return title;
    }
}

class TreeNode {
    String name;
    List<TreeNode> children;

    public TreeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
}

class WatcHashSetNode extends TreeNode {
    LinkedHashSet<Video> watchashset;

    public WatcHashSetNode(String name) {
        super(name);
        this.watchashset = new LinkedHashSet<>();
    }

    public void add(Video video) {
        watchashset.add(video);
    }

    public void remove(Video video) {
        watchashset.remove(video);
    }

    @Override
    public String toString() {
        return name + " ➔ " + watchashset;
    }
}

class PlaylistNode extends TreeNode {
    LinkedList<Video> playlist;
    String description;

    public PlaylistNode(String name, String description) {
        super(name);
        this.playlist = new LinkedList<>();
        this.description = description;
    }

    public void addVideo(Video video) {
        playlist.add(video);
    }

    @Override
    public String toString() {
        return name + " ➔ " + playlist;
    }
}

class HistoryNode extends TreeNode {
    Stack<Video> history;

    public HistoryNode(String name) {
        super(name);
        history = new Stack<>();
    }

    public void watchVideo(Video video) {
        history.push(video);
    }

    @Override
    public String toString() {
        return name + " ➔ " + history;
    }
}

class QueueNode extends TreeNode {
    Queue<Video> queue;

    public QueueNode(String name) {
        super(name);
        queue = new LinkedList<>();
    }

    public void watchVideo(Video video) {
        queue.add(video);
    }

    @Override
    public String toString() {
        return name + " ➔ " + queue;
    }
}

class VideoNode extends TreeNode {
    LinkedList<Video> videos;

    public VideoNode(String name) {
        super(name);
        videos = new LinkedList<>();
    }

    public void upload(Video video) {
        videos.push(video);
    }

    public void delete(Video video) {
        videos.remove(video);
    }

    @Override
    public String toString() {
        return name + " ➔ " + videos;
    }
}

// Tree untuk menimpan data setiap user dan channel
class Library {
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

class UserLibrary extends Library{
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

    public void removeWatchHashSetVideos(String username, Video video) {
        getWatchHashSetNode(username).watchashset.remove(video);
    }

    public void watchVideo(String username, Video video) {
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

class ChannelLibrary extends Library {
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
}

class Menu {
    public static void login() {
        int outerPad = 21;
        Database.Users.put("user", new User("a",LocalDateTime.now(),new Channel("channel", null, LocalDateTime.now())));
        String inputtedUsername;

        FormattedPrint.center("=== CLI-tube ===", "###", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("Login ", "||", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("================", "###", outerPad);
        FormattedPrint.center("Tip: fill ~ to go back", "", outerPad);

        System.out.println("");

        while (true) {
            inputtedUsername = GetInput.stringLimitedCenter("Login As","Input isn't valid.", 16, 16);
            if (inputtedUsername.equals("~")) {
                return;
            } else if (!Database.Users.containsKey(inputtedUsername)) {
                FormattedPrint.center("Username isn't exist",   "", 0);
                System.out.println("");
            } else {
                System.out.print("\033[H\033[2J");
                CurrentUser.set(inputtedUsername);
                System.out.print("\033[H\033[2J");
                mainMenu();
                break;
            }
        }
    }

    public static void register() {
        String username;
        String display_name;

        FormattedPrint.center("=== CLI-tube ===", "###", 21);
        FormattedPrint.center("", "||", 21);
        FormattedPrint.center("Register", "||", 21);
        FormattedPrint.center("", "||", 21);
        FormattedPrint.center("================", "###", 21);
        FormattedPrint.center("Tip: fill ~ to go back", "", 21);

        while (true) {
            System.out.println("");
            username = GetInput.stringLimitedCenter("Username","Input isn't valid.", 16, 16);
            if (username.equals("~")) return;
            if (Database.Users.containsKey(username)) System.out.println("Username already used."); else break;
        }

        System.out.println("");
        display_name = GetInput.stringLimitedCenter("Name","Input isn't valid.", 24, 16);
        if (display_name.equals("~")) return;
        int createChannel = GetInput.integerBoolCenter("Do you want to create a channel (0/1): ", "Input isn't valid", 1);
        System.out.println("");

        if (createChannel == 0) {
            User newUser = new User(display_name, LocalDateTime.now(), null);
            Database.Users.put(username, newUser);
            System.out.print("\033[H\033[2J");
            FormattedPrint.center("Account successfully created. Now, you can login.", "", 0);
            System.out.println("");
            return;
        }

        String channel_name = GetInput.stringLimitedCenter("Channel Name:","Too many characters.", 16, 10);
        User newUser = new User(display_name, LocalDateTime.now(), new Channel(channel_name, "", LocalDateTime.now()));
        Database.Users.put(username, newUser);
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("Account with Channel successfully created. Now, you can login.", "", 0);
        System.out.println("");
        return;
    }

    private static void mainMenu() {
        int choice;
        int outerPad = 17;

        while (true) {
            FormattedPrint.center("======= CLI-tube =======", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("Welcome, " + CurrentUser.getName() + " ", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("======= ChooseMenu =======", "##", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. User Menu", "||", outerPad);
            if (CurrentChannel.getChannel() != null) {
                FormattedPrint.center(" 2. Channel Menu", "||", outerPad);
            } else {
                FormattedPrint.center(" 2. Create a Channel", "||", outerPad);
            }
            FormattedPrint.center("0. Exit ", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("========================", "###", outerPad);
            System.out.println("");

            choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);

            switch (choice) {
                case 0:
                    System.out.print("\033[H\033[2J");
                    return;
                case 1:
                    System.out.print("\033[H\033[2J");
                    userMain();
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    if (CurrentChannel.getChannel() != null) {
                        System.out.print("\033[H\033[2J");
                        channelMain();
                        System.out.print("\033[H\033[2J");
                        break;
                    } else {
                        String channel_name = GetInput.stringLimitedCenter("Channel Name:","Too many characters.", 16, 10);
                        CurrentUser.getUser().channel = new Channel(channel_name, null, LocalDateTime.now());
                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("Account with Channel successfully created.", "", 0);
                        System.out.println("");
                    }
                    break;
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void userMain() {
        int choice;
        int outerPad = 11;
        while (true) {
            FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("What are we doing today?", "||", outerPad);
            FormattedPrint.center(CurrentUser.getName(), "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. Watch Video & Playlist", "||", outerPad);
            FormattedPrint.center("2. Playlist Collection", "||", outerPad);
            FormattedPrint.center("3. Check Watch Later", "||", outerPad);
            if (CurrentChannel.getChannel() != null) {
                FormattedPrint.center("4. Switch to Channel Mode ", "||", outerPad);
            } else {
                FormattedPrint.center("4. Create Channel ", "||", outerPad);
            }
            FormattedPrint.center("5. Accout Setting", "||", outerPad);
            FormattedPrint.center("0. Exit ", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            System.out.println("");

            choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);

            switch (choice) {
                case 0:
                    System.out.print("\033[H\033[2J");
                    return;
                case 1:
                    System.out.print("\033[H\033[2J");
                    userWatch();
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    //userPlaylist();
                    System.out.print("\033[H\033[2J");
                    break;
                case 3:
                    System.out.print("\033[H\033[2J");
                    userWatcHashSet();
                    System.out.print("\033[H\033[2J");
                    break;
                case 4:
                    System.out.print("\033[H\033[2J");
                    if (CurrentChannel.getChannel() != null) {
                        System.out.print("\033[H\033[2J");
                        channelMain();
                        System.out.print("\033[H\033[2J");
                    } else {
                        String channel_name = GetInput.stringLimitedCenter("Channel Name:","Too many characters.", 16, 10);
                        CurrentUser.getUser().channel = new Channel(channel_name, null, LocalDateTime.now());
                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("Account with Channel successfully created.", "", 0);
                        System.out.println("");
                    }
                    break;
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void channelMain() {
        int choice;
        int outerPad = 11;

        while (true) {
            FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center(" Now's your time to shine !!", "||", outerPad);
            FormattedPrint.center(CurrentUser.getUsername(), "||", outerPad);
            FormattedPrint.center(CurrentChannel.getName() + " ", "||", outerPad);;
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);;
            FormattedPrint.center("1. Manage Video ", "||", outerPad);
            FormattedPrint.center("2. Manage Playlist", "||", outerPad);
            FormattedPrint.center("3. Channel Setting", "||", outerPad);
            FormattedPrint.center("4. Switch to User Mode", "||", outerPad);
            FormattedPrint.center("0. Exit ", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            System.out.println("");

            choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);

            switch (choice) {
                case 0:
                    System.out.print("\033[H\033[2J");
                    return;
                case 1:
                    System.out.print("\033[H\033[2J");
                    channelVideo();
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    channelPlaylist();
                    System.out.print("\033[H\033[2J");
                    break;
                case 3:
                    System.out.print("\033[H\033[2J");
                    channelSetting();
                    System.out.print("\033[H\033[2J");
                    break;
                case 4:
                    System.out.print("\033[H\033[2J");
                    userMain();
                    System.out.print("\033[H\033[2J");
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void userWatch() {
        int outerPad = 15;

        while (true) {
            FormattedPrint.center("======== CLI-tube ========", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center(" Enjoy your watching time!", "##", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. Watch queued video", "||", outerPad);
            FormattedPrint.center("2. Watch all queued videos ", "||", outerPad);
            FormattedPrint.center("3. Queue a video", "||", outerPad);
            FormattedPrint.center("4. Queue a playlist ", "||", outerPad);
            FormattedPrint.center("5. Remove a queued video", "||", outerPad);
            FormattedPrint.center("6. Remove all queued videos ", "||", outerPad);
            FormattedPrint.center("0. Back ", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("============================", "###", outerPad);
            System.out.println("");

            switch (GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1)) {
                case 0:
                    System.out.print("\033[H\033[2J");
                    return;
                case 1:
                    break;
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void userPlaylist() {
        int choice;
        int outerPad = 10;

        while (true) {
            FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center(" Watch and create a fun list !! ", "##", outerPad);
            FormattedPrint.center(CurrentUser.getName() + " ", "##", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);

            LinkedList<PlaylistNode> recentPlaylists = CurrentUser.getUserLibrary().getPlaylistNodeRecent(CurrentUser.getUsername(), 10);
            if (recentPlaylists.isEmpty()) {
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("You don't have any playlists yet.", "||", outerPad);
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            } else {
                FormattedPrint.center("", "||", outerPad);
                for (int i = 1; i <= recentPlaylists.size(); i++) {
                    FormattedPrint.center(i + ". " + recentPlaylists.get(i - 1).name, "||", outerPad);
                }
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            }

            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. Create a Playlist ", "||", outerPad);
            FormattedPrint.center("2. Delete a Playlist ", "||", outerPad);
            FormattedPrint.center("3. Delete All Playlist ", "||", outerPad);
            FormattedPrint.center("4. Edit Playlist ", "||", outerPad);
            FormattedPrint.center("0. Back ", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            System.out.println("");

            choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);

            switch (choice) {
                case 0:
                    return;
                case 1:
                    int outerPad1 = 16;
                    System.out.print("\033[H\033[2J");

                    FormattedPrint.center("======== CLI-tube ========", "###", outerPad1);
                    FormattedPrint.center("", "||", outerPad1);
                    FormattedPrint.center("Create a Playlist", "||", outerPad1);
                    FormattedPrint.center("", "||", outerPad1);
                    FormattedPrint.center("==========================", "###", outerPad1);
                    System.out.println("");

                    String title = GetInput.stringLimitedCenter("Title:", "Input isn't valid.", 36, 8);
                    String description = GetInput.stringLimitedCenter("Description:", "Input isn't valid.", 36, 8);

                    CurrentUser.getUserLibrary().createPlaylist(CurrentUser.getUserLibrary().getPlaylistsParentNode(CurrentUser.getUsername()), title, description);
                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("Playlist created successfully.", "", 0);
                    break;
                case 2:
                    if (recentPlaylists.isEmpty()) {
                        break;
                    }
                    int outerPad2 = 10;
                    while (true) {
                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("============= CLI-tube =============", "###", outerPad2);
                        FormattedPrint.center("", "||", outerPad2);
                        FormattedPrint.center("Delete Playlist", "||", outerPad2);
                        FormattedPrint.center("", "||", outerPad2);
                        FormattedPrint.center("====================================", "###", outerPad2);
                        FormattedPrint.center("", "||", outerPad2);

                        LinkedList<PlaylistNode> playlist = CurrentUser.getUserLibrary().getAllPlaylistNode(CurrentUser.getUsername());
                        for (int i = 1; i <= playlist.size(); i++) {
                            FormattedPrint.center(i + ". " + playlist.get(i - 1).name, "||", outerPad2);
                        }
                        FormattedPrint.center("", "||", outerPad2);
                        FormattedPrint.center("====================================", "###", outerPad2);
                        FormattedPrint.center("Tip: 0 to back.", "", 0);
                        System.out.println("");

                        int toDelete = GetInput.integerZeroPositiveCenter("Which to delete: ", "Input isn't valid", 2);
                        if (toDelete == 0) {
                            break;
                        } else {
                            CurrentUser.getUserLibrary().deletePlaylist(CurrentUser.getUsername(), playlist.get(toDelete - 1));
                        }

                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("Playlist deleted succesfully.", "", 0);
                        System.out.println("");
                        break;
                    }
                case 3:
                    if (recentPlaylists.isEmpty()) {
                        break;
                    }

                    for (PlaylistNode node : CurrentUser.getUserLibrary().getAllPlaylistNode(CurrentUser.getUsername())) {
                        CurrentUser.getUserLibrary().deletePlaylist(CurrentChannel.getUsername(), node);
                    }

                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("All playlist deleted succesfully.", "", 0);
                    System.out.println("");
                    break;
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void userWatcHashSet() {
        int choice;
        int outerPad = 11;

        while (true) {
        FormattedPrint.center("====================================", "##", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center(" Watch the Watch Later ", "##", outerPad);
        FormattedPrint.center(CurrentUser.getName() + " ", "##", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("====================================", "##", outerPad);

        LinkedHashSet<Video> videos = CurrentUser.getUserLibrary().getWatchHashSetVideos(CurrentUser.getUsername());
        if (!videos.isEmpty()) {
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("Your watch later list: ", "||", outerPad);
            int counter = 1;
            for (Video video : videos) {
                FormattedPrint.center(counter + ". " + video.title, "||", outerPad);
                counter++;
            }
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("==================================", "###", outerPad);
        } else {
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("You didn't save any video yet.", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("==================================", "###", outerPad);
        }
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("1. Watch", "##", outerPad);
        FormattedPrint.center("2. Watch all", "##", outerPad);
        FormattedPrint.center("3. Remove", "##", outerPad);
        FormattedPrint.center("4. Remove all", "##", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("====================================", "##", outerPad);

        System.out.println("");

        int toDO;
        choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid.", 1);
        switch (choice) {
            case 0:
                System.out.print("\033[H\033[2J");
                return;
            case 1:
                while (!videos.isEmpty()) {
                    toDO = GetInput.integerZeroPositiveCenter("Which to Watch: ", "Input isn't valid", 2);

                    if (toDO <= videos.size()){
                        int count = 1;
                        for (Video video : videos) {
                            if (count == toDO) {
                                CurrentUser.getUserLibrary().removeWatchHashSetVideos(CurrentUser.currentUsername, video);
                                CurrentUser.getUserLibrary().watchVideo(CurrentUser.currentUsername, video);
                            }
                            count++;
                        }
                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("Video watched.", "", 0);
                        System.out.println("");
                    } else {
                        break;
                    }
                }
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("All videos watched.", "", 0);
                System.out.println("");
            case 2:
                for (Video video : videos) {
                    CurrentUser.getUserLibrary().watchVideo(CurrentUser.getUsername(), video);
                    CurrentUser.getUserLibrary().removeWatchHashSetVideos(CurrentUser.getUsername(), video);
                }
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("All videos watched.", "", 0);
                System.out.println("");
                break;
            case 3:
                while (!videos.isEmpty()) {
                    toDO = GetInput.integerZeroPositiveCenter("Which video to remove: ", "Input isn't valid", 2);

                    if (toDO <= videos.size()){
                        int count = 1;
                        for (Video video : videos) {
                            if (count == toDO) {
                                CurrentUser.getUserLibrary().removeWatchHashSetVideos(CurrentUser.getUsername(), video);
                            }
                            count++;
                        }
                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("Video removed.", "", 0);
                        System.out.println("");
                        break;
                    } else {
                        break;
                    }
                }
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("All videos removed.", "", 0);
                System.out.println("");
            case 4:
                for (Video video : videos) {
                    CurrentUser.getUserLibrary().removeWatchHashSetVideos(CurrentUser.getUsername(), video);
                }
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("All videos removed.", "", 0);
                System.out.println("");
                break;
            default:
                break;
            }
        }
    }

    private static void channelVideo() {
        int choice;
        int outerPad = 11;

        while (true) {
        FormattedPrint.center("============ CLI-tube ============", "###", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center(" Now is your time to shine !! ", "##", outerPad);
        FormattedPrint.center(CurrentChannel.getName() + " ", "##", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("==================================", "###", outerPad);

        LinkedList<Video> recentVideos = CurrentChannel.getChannelLibrary().getChannelVideosRecent(CurrentChannel.getUsername(),10);
        if (!recentVideos.isEmpty()) {
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("Your recent videos: ", "||", outerPad);
            for (int i = 1; i <= recentVideos.size(); i++) {
                FormattedPrint.center(i + ". " + recentVideos.get(i - 1).title, "||", outerPad);
           }
           FormattedPrint.center("", "||", outerPad);
           FormattedPrint.center("==================================", "###", outerPad);
        } else {
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("You don't have any video.", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("==================================", "###", outerPad);
        }

        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("1. Upload Video ", "||", outerPad);
        FormattedPrint.center("2. Delete Video ", "||", outerPad);
        FormattedPrint.center("3. Edit Video ", "||", outerPad);
        FormattedPrint.center("0. Back ", "||", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("==================================", "###", outerPad);
        System.out.println("");

        choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);

        switch (choice) {
            case 0:
                return;
            case 1:
                int outerPad1 = 15;

                System.out.print("\033[H\033[2J");

                FormattedPrint.center("=== CLI-tube ===", "###", outerPad1);
                FormattedPrint.center("", "||", outerPad1);
                FormattedPrint.center("Upload Video", "||", outerPad1);
                FormattedPrint.center("", "||", outerPad1);
                FormattedPrint.center("==========================", "###", outerPad1);
                System.out.println("");

                String title = GetInput.stringLimitedCenter("Title:", "Input isn't valid.", 36, 8);
                System.out.println("");
                String description = GetInput.stringLimitedCenter("Description:", "Input isn't valid.", 36, 8);

                CurrentChannel.getChannelLibrary().uploadChannelVideo(CurrentChannel.getUsername(), new Video(title, description, LocalDateTime.now()));
                System.out.print("\033[H\033[2J");
                FormattedPrint.center("Video uploaded successfully.", "", 0);
                System.out.println("");
                break;
            case 2:
                int outerPad2 = 11;
                while (!recentVideos.isEmpty()) {
                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("============ CLI-tube ============", "###", outerPad2);
                    FormattedPrint.center("", "||", outerPad2);
                    FormattedPrint.center("Delete Video", "||", outerPad2);
                    FormattedPrint.center("", "||", outerPad2);
                    FormattedPrint.center("==================================", "###", outerPad2);
                    FormattedPrint.center("", "||", outerPad2);

                    LinkedList<Video> allChannelVideos = CurrentChannel.getChannelLibrary().getChannelVideos(CurrentChannel.getUsername());
                    for (int i = 1; i <= allChannelVideos.size(); i++) {
                        FormattedPrint.center(i + ". " + allChannelVideos.get(i - 1).title, "||", outerPad2);
                    }

                    FormattedPrint.center("", "||", outerPad2);
                    FormattedPrint.center("==================================", "###", outerPad2);
                    FormattedPrint.center("Tip: 0 to back.", "", 0);
                    System.out.println("");

                    int toDelete = GetInput.integerZeroPositiveCenter("Which to delete: ", "Input isn't valid", 2);
                    if (toDelete == 0) {
                        break;
                    } else if (toDelete > 0 && toDelete <= allChannelVideos.size()) {
                        CurrentChannel.getChannelLibrary().deleteChannelVideo(CurrentChannel.getUsername(), allChannelVideos.get(toDelete - 1));
                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("Video deleted successfully.", "", 0);
                        System.out.println("");
                    } else {
                        break;
                    }

                    System.out.print("\033[H\033[2J");
                }
            case 3:
                System.out.print("\033[H\033[2J");
                //channelVideoEdit();
                break;
            default:
                System.out.print("\033[H\033[2J");
                break;
            }
        }
    }

    private static void channelPlaylist() {
        int choice;
        int outerPad = 10;

        while (true) {
            FormattedPrint.center("============= CLI-tube =============", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center(" Let's create a fun list !! ", "##", outerPad);
            FormattedPrint.center(CurrentChannel.getName() + " ", "##", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);

            LinkedList<PlaylistNode> recentPlaylists = CurrentChannel.getChannelLibrary().getPlaylistNodeRecent(CurrentChannel.getUsername(), 10);
            if (recentPlaylists.isEmpty()) {
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("This channel has no playlists yet.", "||", outerPad);
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            } else {
                FormattedPrint.center("", "||", outerPad);
                for (int i = 1; i <= recentPlaylists.size(); i++) {
                    FormattedPrint.center(i + ". " + recentPlaylists.get(i - 1).name, "||", outerPad);
                }
                FormattedPrint.center("", "||", outerPad);
                FormattedPrint.center("====================================", "###", outerPad);
            }

            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. Create a Playlist ", "||", outerPad);
            FormattedPrint.center("2. Delete a Playlist ", "||", outerPad);
            FormattedPrint.center("3. Delete All Playlist ", "||", outerPad);
            FormattedPrint.center("4. Edit Playlist ", "||", outerPad);
            FormattedPrint.center("0. Back ", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            System.out.println("");

            choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);

            switch (choice) {
                case 0:
                    System.out.print("\033[H\033[2J");
                    return;
                case 1:
                    int outerPad1 = 16;
                    System.out.print("\033[H\033[2J");

                    FormattedPrint.center("======== CLI-tube ========", "###", outerPad1);
                    FormattedPrint.center("", "||", outerPad1);
                    FormattedPrint.center("Create a Playlist", "||", outerPad1);
                    FormattedPrint.center("", "||", outerPad1);
                    FormattedPrint.center("==========================", "###", outerPad1);
                    System.out.println("");

                    String title = GetInput.stringLimitedCenter("Title:", "Input isn't valid.", 36, 8);
                    String description = GetInput.stringLimitedCenter("Description:", "Input isn't valid.", 36, 8);

                    CurrentChannel.getChannel().library.createPlaylist(CurrentChannel.getChannel().library.getPlaylistsParentNode(CurrentChannel.getUsername()), title, description);
                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("Video uploaded successfully.", "", 0);
                    break;
                case 2:
                    if (recentPlaylists.isEmpty()) {
                        break;
                    }
                    int outerPad2 = 10;
                    while (true) {
                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("============= CLI-tube =============", "###", outerPad2);
                        FormattedPrint.center("", "||", outerPad2);
                        FormattedPrint.center("Delete Playlist", "||", outerPad2);
                        FormattedPrint.center("", "||", outerPad2);
                        FormattedPrint.center("====================================", "###", outerPad2);
                        FormattedPrint.center("", "||", outerPad2);

                        LinkedList<PlaylistNode> allChannelPlaList = CurrentChannel.getChannelLibrary().getAllPlaylistNode(CurrentChannel.getUsername());
                        for (int i = 1; i <= allChannelPlaList.size(); i++) {
                            FormattedPrint.center(i + ". " + allChannelPlaList.get(i - 1).name, "||", outerPad2);
                        }
                        FormattedPrint.center("", "||", outerPad2);
                        FormattedPrint.center("====================================", "###", outerPad2);
                        FormattedPrint.center("Tip: 0 to back.", "", 0);
                        System.out.println("");

                        int toDelete = GetInput.integerZeroPositiveCenter("Which to delete: ", "Input isn't valid", 2);
                        if (toDelete == 0) {
                            break;
                        } else {
                            CurrentChannel.getChannel().library.deletePlaylist(CurrentChannel.getUsername(),allChannelPlaList.get(toDelete - 1));
                        }

                        System.out.print("\033[H\033[2J");
                        FormattedPrint.center("Playlist deleted succesfully.", "", 0);
                        System.out.println("");
                        break;
                    }
                case 3:
                    if (recentPlaylists.isEmpty()) {
                        break;
                    }

                    for (PlaylistNode node : CurrentChannel.getChannelLibrary().getAllPlaylistNode(CurrentChannel.getUsername())) {
                        CurrentChannel.getChannelLibrary().deletePlaylist(CurrentChannel.getUsername(), node);
                    }

                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("All playlist deleted succesfully.", "", 0);
                    System.out.println("");
                    break;
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void channelSetting() {
        int choice;
    }
}

public class ResponsiPSDA1_A_L0224024_SatriaManggalaPutraPratama{
    public static void main(String[] Args) {
        System.out.print("\033[H\033[2J");

        int choice;
        int outerPad = 21;

        while (true) {
            FormattedPrint.center("=== CLI-tube ===", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. Login", "||", outerPad);
            FormattedPrint.center(" 2. Register", "||", outerPad);
            FormattedPrint.center("0. Exit ", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("================", "###", outerPad);
            System.out.println("");

            choice = GetInput.integerZeroPositiveCenter("Choice: ", "Input isn't valid", 1);

            switch (choice) {
                case 0:
                    int exit;
                    exit = GetInput.integerBoolCenter("Are you sure (1 to yes, 0 to no):",  "Input isn't valid", 1);
                    if (exit == 0) return;
                    break;
                case 1:
                    System.out.print("\033[H\033[2J");
                    Menu.login();
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    Menu.register();
                    System.out.print("\033[H\033[2J");
                    break;
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }
}
