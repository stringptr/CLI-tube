import java.util.*;
import java.time.*;

class FormattedPrint {
    public static final int DEFAULT_PADDING = 64;
    public static void center (String content, String border, int outerPadding) {
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

class GetInput extends FormattedPrint {
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

    public static int integerCenter(String prompt, String errortext, int inputLength) {
        int leftPromptPadLength = (DEFAULT_PADDING - prompt.length() - inputLength)/2;
        int leftErrorPadLength = (DEFAULT_PADDING - errortext.length() - inputLength)/2;
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

class CurrentUser extends Database {
    private static User current;

    public static String getUsername() {
        if (current != null) {
            return current.toString();
        }
        return null;
    }

    public static String getName() {
        if (current != null) {
            return current.display_name;
        }
        return null;
    }

    public static User getU() {
        return current;
    }

    public static void set(User user) {
        current = user;
    }

    public static void clear() {
        current = null;
    }
}

class CurrentChannel extends CurrentUser {
    public static Channel getC() {
        return CurrentUser.getU().channel;
    }

    public static String getUsername() {
        return CurrentUser.getU().channel.toString();
    }

    public static String getName() {
        return CurrentUser.getU().channel.display_name;
    }
}

class Channel {
    String display_name;
    String description;
    ChannelLibrary library;
    LocalDateTime date_created;

    public Channel(String display_name, String description, LocalDateTime date_created, String username) {
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
        this.library = new UserLibrary(display_name);
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

    public TreeNode() {
        this.name = "user";
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
}

class PlaylistNode extends TreeNode {
    LinkedList<Video> playlist;

    public PlaylistNode(String name) {
        super(name);
        this.playlist = new LinkedList<>();
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

    public PlaylistNode addPlaylistNode(String name) {
        TreeNode playlistParent = getPlaylistsParentNode();
        PlaylistNode playlistNode = new PlaylistNode(name);
        playlistParent.addChild(playlistNode);
        return playlistNode;
    }

    private TreeNode getPlaylistsParentNode() {
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

    /*
    public LinkedList<PlaylistNode> getPlaylistsChildren() {
        PlaylistNode playlist = getAllPlaylistNode();
        return playlist.playlist;
    }
    */

    public TreeNode createPlaylist(TreeNode playlistParent, String name) {
        TreeNode playlist = new TreeNode(name);
        playlistParent.addChild(playlist);
        return playlist;
    }

    /*
    private LinkedList<TreeNode> getPlaylistParent() {
        return getPlaylistParentNode().children.;
    }
    */

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


    /*
    public LinkedList<TreeNode> getCurrentChannelPlaylistRecent(int count) {
        LinkedList<TreeNode> recent = new LinkedList<>();

        /*
        for (int i = 0; i <= count - 1 && i < getCurrentPlaylistParent().size() && !getCurrentPlaylistParent().isEmpty() ; i++) {
            int inverseIndex = getCurrentPlaylistParent().size() - 1 - i;
            TreeNode playlist = getCurrentPlaylistParent().get(inverseIndex);
            recent.add(playlist);
        }

        return recent;

    }*/



}

class UserLibrary extends Library{
    public UserLibrary(String username) {
        super(username);
        HistoryNode historyNode = new HistoryNode("History");
        root.addChild(historyNode);
    }

    public HistoryNode getHistoryNode() {
        for (TreeNode child : root.children) {
            if (child.name.equals("History")) return (HistoryNode) child;
        }
        return null;
    }

    public void watchVideo(Video video) {
        getHistoryNode().watchVideo(video);
        System.out.println("Watching: " + video);
    }


    public void printHistory() {
        System.out.println("History: ");
        for (Video v : ((HistoryNode) getHistoryNode()).history) {
            System.out.println("- " + v);
        }
    }
}

class ChannelLibrary extends Library {
    public ChannelLibrary() {
        super("channel");
        VideoNode videosNode = new VideoNode("Videos");
        root.addChild(videosNode);
    }

    private TreeNode getChannelTreeVideoNode(String username) {
        for (TreeNode child : Database.Users.get(username).channel.library.root.children) {
            if (child.name.equals("Videos")) return child;
        }
        return null;
    }

    public VideoNode getChannelVideosNode(String username) {
        return ((VideoNode) getChannelTreeVideoNode(username));
    }

    public LinkedList<Video> getChannelVideos(String username) {
        User user = Database.Users.get(username);
        if (user == null || user.channel == null) return new LinkedList<>();

        VideoNode node = getChannelVideosNode(username);
        if (node == null) return new LinkedList<>();
        return node.videos;
    }

    public LinkedList<Video> getCurrentChannelVideos(String username) {
    if (getChannelVideos(username) == null) return new LinkedList<>();
        return getChannelVideos(username);
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

    public LinkedList<Video> getCurrentChannelVideosRange(String username, int from, int to) {
        LinkedList<Video> recent = new LinkedList<>();
        LinkedList<Video>  videos = new LinkedList<>(getChannelVideos(username));

        for (int i = 0;i >= from - 1 && i <= to - 1 && i < videos.size() && !videos.isEmpty(); i++) {
            Video video = videos.get(i);
            recent.add(video);
        }

        return recent;
    }

    public LinkedList<Video> getChannelVideosRecent(String username, int from, int to) {
        LinkedList<Video> recent = new LinkedList<>();
        LinkedList<Video> videos = new LinkedList<>(getChannelVideos(username));

        for (int i = 0;i >= from - 1 && i <= to - 1 && i < videos.size() && !videos.isEmpty(); i++) {
            int inverseIndex = videos.size() - 1 - i;
            Video video = videos.get(inverseIndex);
            recent.add(video);
        }

        return recent;
    }
}

class Menu {
    static Queue<Video> QueuedVideos = new LinkedList<>();

    public static void login() {
        int outerPad = 21;
        Database.Users.put("user", new User("a",LocalDateTime.now(),new Channel("channel", null, LocalDateTime.now(), "user")));
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
                CurrentUser.set(Database.Users.get(inputtedUsername));
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
        int createChannel = GetInput.integerCenter("Do you want to create a channel: ", "Input isn't valid", 1);

        if (createChannel == 0) {
            Database.Users.put(username, new User(display_name, LocalDateTime.now(), null));
            System.out.print("\033[H\033[2J");
            FormattedPrint.center("Account successfully created. Now, you can login.", "", 0);
            System.out.println("");
            return;
        }

        String channel_name = GetInput.stringLimitedCenter("Channel Name:","Too many characters.", 16, 10);
        Database.Users.put(username, new User(display_name, LocalDateTime.now(), new Channel(channel_name, null, LocalDateTime.now(), username)));
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
            FormattedPrint.center(" 2. Channel Menu", "||", outerPad);
            FormattedPrint.center("0. Exit ", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("========================", "###", outerPad);
            System.out.println("");

            choice = GetInput.integerCenter("Choice: ", "Input isn't valid", 1);

            switch (choice) {
                case 0:
                    return;
                case 1:
                    System.out.print("\033[H\033[2J");
                    userMain();
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    if (CurrentUser.getU().channel != null) {
                        System.out.print("\033[H\033[2J");
                        channelMain();
                        System.out.print("\033[H\033[2J");
                        break;
                    };
                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("You don't have a channel", "", 0);
                    FormattedPrint.center("Tip: You can easily create one from User Menu.","",0);
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
            FormattedPrint.center(CurrentUser.getU().display_name, "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("1. Watch Video & Playlist", "||", outerPad);
            FormattedPrint.center("2. Playlist Collection", "||", outerPad);
            FormattedPrint.center("3. Accout Setting", "||", outerPad);
            if (CurrentUser.getU().channel != null) {
                FormattedPrint.center("4. Switch to Channel Mode ", "||", outerPad);
            } else {
                FormattedPrint.center("4. Create Channel ", "||", outerPad);
            }
            FormattedPrint.center("0. Exit ", "||", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("====================================", "###", outerPad);
            System.out.println("");

            choice = GetInput.integerCenter("Choice: ", "Input isn't valid", 1);

            switch (choice) {
                case 0:
                    return;
                case 1:
                    System.out.print("\033[H\033[2J");
                    userWatch();
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    if (CurrentUser.getU().channel != null) {
                        System.out.print("\033[H\033[2J");
                        channelMain();
                        System.out.print("\033[H\033[2J");
                        break;
                    };
                    System.out.print("\033[H\033[2J");
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
            FormattedPrint.center(CurrentChannel.getUsername().toString(), "||", outerPad);
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

            choice = GetInput.integerCenter("Choice: ", "Input isn't valid", 1);

            switch (choice) {
                case 0:
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
                default:
                    System.out.print("\033[H\033[2J");
                    break;
            }
        }
    }

    private static void userWatch() {
        int outerPad = 15;
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

        switch (GetInput.integerCenter("Choice: ", "Input isn't valid", 1)) {
            case 0:
                return;
            case 1:
                break;
            default:
                    System.out.print("\033[H\033[2J");
                    break;
        }
    }

    private static void channelVideo() {
        int choice;
        int outerPad = 11;

        FormattedPrint.center("============ CLI-tube ============", "###", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center(" Now is your time to shine !! ", "##", outerPad);
        FormattedPrint.center(CurrentUser.getName() + " ", "##", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("====================================", "##", outerPad);

        LinkedList<Video> recentVideos = CurrentChannel.getC().library.getChannelVideosRecent(CurrentUser.getUsername(),10);
        if (!recentVideos.isEmpty()) {
            FormattedPrint.center("==================================", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("Your recent videos: ", "||", outerPad);
            for (int i = 1; i <= recentVideos.size(); i++) {
                FormattedPrint.center(i + ". " + recentVideos.get(i - 1).title, "||", outerPad);
           }

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
        FormattedPrint.center("=====================================", "###", outerPad);
        System.out.println("");

        choice = GetInput.integerCenter("Choice: ", "Input isn't valid", 1);

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
                String description = GetInput.stringLimitedCenter("Title:", "Input isn't valid.", 36, 8);

                CurrentChannel.getC().library.uploadChannelVideo(CurrentUser.getUsername(), new Video(title, description, LocalDateTime.now()));
                FormattedPrint.center("Video uploaded successfully.", "", 0);
                break;
            case 2:
                while (true) {
                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("=== CLI-tube ===", "###", 10);
                    FormattedPrint.center("", "||", 10);
                    FormattedPrint.center("Delete Video", "||", 10);
                    FormattedPrint.center("", "||", 10);
                    FormattedPrint.center("================", "###", 10);
                    FormattedPrint.center("", "||", 10);

                    LinkedList<Video> recentVideosRange = CurrentChannel.getC().library.getChannelVideosRecent(CurrentUser.getUsername(),1, 10);
                    for (int i = 1; i <= recentVideosRange.size(); i++) {
                        FormattedPrint.center(recentVideosRange.get(i - 1).title, "||", 13);
                    }

                    String toDelete = GetInput.stringLimited("Which to delete", "", 5, 20);
                    if (toDelete.equals("~")) {

                    } //else (toDelete.to) {

                    System.out.print("\033[H\033[2J");
                    break;
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

    private static void channelPlaylist() {
        int choice;
        int outerPad = 11;

        FormattedPrint.center("============ CLI-tube ============", "###", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center(" Let's create a fun list !! ", "##", outerPad);
        FormattedPrint.center(CurrentChannel.getName() + " ", "##", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("==================================", "###", outerPad);

        // Check for at least one playlist
        /*
        if (recentPlaylists.isEmpty()) {
            // The channel has no playlists.
            FormattedPrint.center("This channel has no playlists yet.", "", 0);
        } else {
            // The channel has playlists.
            // Display the playlists (see below).
        }
        */

        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("1. Upload Video ", "||", outerPad);
        FormattedPrint.center("2. Delete Video ", "||", outerPad);
        FormattedPrint.center("3. Edit Video ", "||", outerPad);
        FormattedPrint.center("0. Back ", "||", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("==================================", "###", outerPad);
        System.out.println("");

        choice = GetInput.integerCenter("Choice: ", "Input isn't valid", 1);

        switch (choice) {
            case 0:
                return;
            case 1:
                System.out.print("\033[H\033[2J");

                FormattedPrint.center("=== CLI-tube ===", "###", 21);
                FormattedPrint.center("", "||", 21);
                FormattedPrint.center("Upload Video", "||", 21);
                FormattedPrint.center("", "||", 21);
                FormattedPrint.center("================", "###", 21);
                System.out.println("");

                String title = GetInput.stringLimitedCenter("Title:", "Input isn't valid.", 36, 8);
                String description = GetInput.stringLimitedCenter("Title:", "Input isn't valid.", 36, 8);

                CurrentChannel.getC().library.uploadChannelVideo(CurrentChannel.getUsername(), new Video(title, description, LocalDateTime.now()));
                FormattedPrint.center("Video uploaded successfully.", "", 0);
                break;
            case 2:
                int outerPad2 = 21;
                while (true) {
                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("=== CLI-tube ===", "###", outerPad2);
                    FormattedPrint.center("", "||", outerPad2);
                    FormattedPrint.center("Delete Video", "||", outerPad2);
                    FormattedPrint.center("", "||", outerPad2);
                    FormattedPrint.center("================", "###", outerPad2);
                    FormattedPrint.center("", "||", outerPad2);

                    LinkedList<Video> recentVideosRange = CurrentChannel.getC().library.getChannelVideosRecent(CurrentUser.getUsername(),1, 10);
                    for (int i = 1; i <= recentVideosRange.size(); i++) {
                        FormattedPrint.center(recentVideosRange.get(i - 1).title, "||", 13);
                    }

                    String toDelete = GetInput.stringLimited("Which to delete", "", 5, 20);
                    if (toDelete.equals("~")) {

                    } // else (toDelete.to) {

                    System.out.print("\033[H\033[2J");
                    break;
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

            choice = GetInput.integerCenter("Choice: ", "Input isn't valid", 1);

            switch (choice) {
                case 0:
                    int exit;
                    exit = GetInput.integerCenter("Are you sure (1 to yes):",  "Input isn't valid", 1);
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
