import java.util.*;

import javax.xml.crypto.Data;

import java.time.*;

class FormattedPrint {
    public static void center (String content, String border, int outerPadding) {
        int totalPadding = 64 - ( content.length() + border.length() + (outerPadding*2) );
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
        int errorPadLength = errorTotalPad / 2;

        int inputTotalPad = 64 - inputWidth;
        if (inputTotalPad < 0) inputTotalPad = 0;
        int inputPadLength = inputTotalPad / 2;

        String promtPad = (" ").repeat(promptPadLength);
        String errorPad = (" ").repeat(errorPadLength);
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
        return current.toString();
    }

    public static String getName() {
        return current.display_name;
    }

    public static User get() {
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
        return CurrentUser.get().channel;
    }

    public static String getUsername() {
        return CurrentUser.get().channel.toString();
    }

    public static String getName() {
        return CurrentUser.get().channel.display_name;
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
        this.library = new ChannelLibrary(display_name);
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

    public TreeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
}

class PlaylistNode extends TreeNode {
    List<Video> playlist;

    public PlaylistNode(String name) {
        super(name);
        this.playlist = new ArrayList<>();
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
        TreeNode playlistParent = getTreePlaylistParenNode();
        PlaylistNode playlistNode = new PlaylistNode(name);
        playlistParent.addChild(playlistNode);
        return playlistNode;
    }

    public TreeNode getTreePlaylistParenNode() {
        for (TreeNode child : root.children) {
            if (child.name.equals("Playlists")) return child;
        }
        return null;
    }

    public PlaylistNode getTreePlaylistParentChild() {
        for (TreeNode child : getTreePlaylistParenNode().children) {
            if (child.name.equals("Playlists")) return (PlaylistNode) child;
        }
        return null;
    }

    public PlaylistNode getPlaylistParentChild() {
        return ((PlaylistNode) getTreePlaylistParentChild());
    }

    public List<TreeNode> getCurrentPlaylistParent() {
        return Database.Users.get(CurrentUser.getName()).channel.library.getPlaylistParentChild().children;
    }

    public TreeNode createPlaylist(TreeNode playlistParent, String name) {
        TreeNode playlist = new TreeNode(name);
        playlistParent.addChild(playlist);
        return playlist;
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


    public LinkedList<TreeNode> getCurrentChannelPlaylistRecent(int count) {
        LinkedList<TreeNode> recent = new LinkedList<>();

        for (int i = 0; i <= count - 1 && i < getCurrentPlaylistParent().size(); i++) {
            int inverseIndex = getCurrentPlaylistParent().size() - 1 - i;
            TreeNode playlist = getCurrentPlaylistParent().get(inverseIndex);
            recent.add(playlist);
        }

        return recent;
    }
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
    public ChannelLibrary(String username) {
        super(username);
        TreeNode videosNode = new TreeNode("Videos");
        root.addChild(videosNode);
    }

    private TreeNode getTreeVideoNode() {
        for (TreeNode child : root.children) {
            if (child.name.equals("Videos")) return child;
        }
        return null;
    }

    public VideoNode getVideosNode() {
        return ((VideoNode) getTreeVideoNode());
    }

    public static LinkedList<Video> getVideos(String username) {
        User user = Database.Users.get(username);
        if (user == null || user.channel == null) return new LinkedList<>();

        VideoNode node = user.channel.library.getVideosNode();
        if (node == null) return new LinkedList<>();
        return node.videos;
    }

    public static LinkedList<Video> getCurrentChannelVideos() {
    if (CurrentUser.get() == null) return new LinkedList<>();
        return getVideos(CurrentUser.getName());
    }

    public void uploadVideo(Video video) {
        getVideosNode().upload(video);
    }

    public void deleteVideo(Video video) {
        getVideosNode().delete(video);
    }

    public static LinkedList<Video> getCurrentChannelVideosRecent(int count) {
        LinkedList<Video> recent = new LinkedList<>();

        for (int i = 0; i <= count - 1 && i < getCurrentChannelVideos().size(); i++) {
            int inverseIndex = getCurrentChannelVideos().size() - 1 - i;
            Video video = getCurrentChannelVideos().get(inverseIndex);
            recent.add(video);
        }

        return recent;
    }

    public static LinkedList<Video> getCurrentChannelVideosRange(int from, int to) {
        LinkedList<Video> recent = new LinkedList<>();

        for (int i = 0;i >= from - 1 && i <= to - 1; i++) {
            Video video = getCurrentChannelVideos().get(i);
            recent.add(video);
        }

        return recent;
    }

    public static LinkedList<Video> getCurrentChannelVideosRecentRange(int from, int to) {
        LinkedList<Video> recent = new LinkedList<>();

        for (int i = 0;i >= from - 1 && i <= to - 1; i++) {
            int inverseIndex = getCurrentChannelVideos().size() - 1 - i;
            Video video = getCurrentChannelVideos().get(inverseIndex);
            recent.add(video);
        }

        return recent;
    }
}

class Menu {
    static Queue<Video> QueuedVideos = new LinkedList<>();

    public static void login() {
        Database.Users.put("user", new User("a",LocalDateTime.now(),new Channel("channel", null, LocalDateTime.now())));
        String inputtedUsername;

        FormattedPrint.center("=== CLI-tube ===", "###", 21);
        FormattedPrint.center("", "||", 21);
        FormattedPrint.center("Login ", "||", 21);
        FormattedPrint.center("", "||", 21);
        FormattedPrint.center("================", "###", 21);
        FormattedPrint.center("Tip: fill ~ to go back", "", 21);

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
            if (Database.Users.containsKey(username)) System.out.println("Username already used."); else break;
        }

        System.out.println("");
        display_name = GetInput.stringLimitedCenter("Name","Input isn't valid.", 24, 16);
        Database.Users.put(username, new User(display_name, LocalDateTime.now(), null));
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("Account successfully created. Now, you can login.", "", 0);
        System.out.println("");
        return;
    }

    private static void mainMenu() {
        int choice;
        while (true) {
            FormattedPrint.center("======= CLI-tube =======", "###", 17);
            FormattedPrint.center("", "||", 17);
            FormattedPrint.center("Welcome, " + CurrentUser.getName() + " ", "||", 17);
            FormattedPrint.center("", "||", 17);
            FormattedPrint.center("======= ChooseMenu =======", "##", 17);
            FormattedPrint.center("", "||", 17);
            FormattedPrint.center("1. User Menu", "||", 17);
            FormattedPrint.center(" 2. Channel Menu", "||", 17);
            FormattedPrint.center("~. Exit ", "||", 17);
            FormattedPrint.center("", "||", 17);
            FormattedPrint.center("========================", "###", 17);
            System.out.println("");

            choice = GetInput.integerPositive("Choice: ", "Input isn't valid", 28);

            switch (choice) {
                case 1:
                    System.out.print("\033[H\033[2J");
                    userMain();
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    if (CurrentUser.get().channel != null) {
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
                    return;
            }
        }
    }

    private static void userMain() {
        int choice;
        while (true) {
            FormattedPrint.center("======== CLI-tube ========", "###", 15);
            FormattedPrint.center("", "||", 15);
            FormattedPrint.center(" What are we doing today? ", "##", 15);
            FormattedPrint.center(CurrentUser.get().display_name, "##", 15);
            FormattedPrint.center("", "||", 15);
            FormattedPrint.center("1. Watch Video & Playlist ", "||", 15);
            FormattedPrint.center("2. Playlist Collection", "||", 15);
            FormattedPrint.center("3. Accout Setting ", "||", 15);
            if (CurrentUser.get().channel != null) {
                FormattedPrint.center("6. Switch to Channel Mode ", "||", 15);
            };
            FormattedPrint.center("~. Exit ", "||", 15);
            FormattedPrint.center("", "||", 15);
            FormattedPrint.center("============================", "###", 15);
            System.out.println("");

            choice = GetInput.integerPositive("Choice: ", "Input isn't valid", 28);

            switch (choice) {
                case 1:
                    System.out.print("\033[H\033[2J");
                    userWatch();
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    if (CurrentUser.get().channel != null) {
                        System.out.print("\033[H\033[2J");
                        channelMain();
                        System.out.print("\033[H\033[2J");
                        break;
                    };
                    System.out.print("\033[H\033[2J");
                    break;
                default:
                    return;
            }
        }
    }

    private static void channelMain() {
        int choice;
        while (true) {
            FormattedPrint.center("======== CLI-tube ========", "###", 15);
            FormattedPrint.center("", "||", 15);
            FormattedPrint.center(" Now's your time to shine !!", "##", 15);
            FormattedPrint.center(CurrentChannel.getName() + " ", "##", 15);
            FormattedPrint.center("", "||", 15);
            FormattedPrint.center("1. Manage Video ", "||", 15);
            FormattedPrint.center("2. Manage Playlist", "||", 15);
            FormattedPrint.center("3. Channel Setting", "||", 15);
            FormattedPrint.center("4. Switch to User Mode", "||", 15);
            FormattedPrint.center("~. Exit ", "||", 15);
            FormattedPrint.center("", "||", 15);
            FormattedPrint.center("============================", "###", 15);
            System.out.println("");

            choice = GetInput.integerPositive("Choice: ", "Input isn't valid", 28);

            switch (choice) {
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
                    return;
            }
        }
    }

    private static void userWatch() {
        FormattedPrint.center("======== CLI-tube ========", "###", 15);
        FormattedPrint.center("", "||", 15);
        FormattedPrint.center(" Enjoy your watching time!", "##", 15);
        FormattedPrint.center("", "||", 15);
        FormattedPrint.center("1. Watch queued video", "||", 15);
        FormattedPrint.center("2. Watch all queued videos ", "||", 15);
        FormattedPrint.center("3. Queue a video", "||", 15);
        FormattedPrint.center("4. Queue a playlist ", "||", 15);
        FormattedPrint.center("5. Remove a queued video", "||", 15);
        FormattedPrint.center("6. Remove all queued videos ", "||", 15);
        FormattedPrint.center("~. Back ", "||", 15);
        FormattedPrint.center("", "||", 15);
        FormattedPrint.center("============================", "###", 15);
        System.out.println("");

        int choice = GetInput.integerPositive("Choice: ", "Input isn't valid", 28);
        switch (choice) {
            case 1:
        }
    }

    private static void channelVideo() {
        int choice;

        FormattedPrint.center("========== CLI-tube ==========", "###", 13);
        FormattedPrint.center("", "||", 13);
        FormattedPrint.center(" Now is your time to shine !! ", "##", 13);
        FormattedPrint.center(CurrentUser.getName() + " ", "##", 13);

        if (!ChannelLibrary.getCurrentChannelVideos().isEmpty()) {
            FormattedPrint.center("", "||", 13);
            FormattedPrint.center("Your recent videos: ", "||", 13);

            LinkedList<Video> recentVideos = ChannelLibrary.getCurrentChannelVideosRecent(10);
            for (int i = 1; i <= recentVideos.size(); i++) {
                FormattedPrint.center(recentVideos.get(i - 1).title, "||", 13);
            }
        }

        FormattedPrint.center("", "||", 13);
        FormattedPrint.center("1. Upload Video ", "||", 13);
        FormattedPrint.center("2. Delete Video ", "||", 13);
        FormattedPrint.center("3. Edit Video ", "||", 13);
        FormattedPrint.center("~. Back ", "||", 13);
        FormattedPrint.center("", "||", 13);
        FormattedPrint.center("================================", "###", 13);
        System.out.println("");

        choice = GetInput.integerPositive("Choice: ", "Input isn't valid", 28);

        switch (choice) {
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

                CurrentUser.get().channel.library.uploadVideo(new Video(title, description, LocalDateTime.now()));
                FormattedPrint.center("Video uploaded successfully.", "", 0);
                break;
            case 2:
                while (true) {
                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("=== CLI-tube ===", "###", 21);
                    FormattedPrint.center("", "||", 21);
                    FormattedPrint.center("Delete Video", "||", 21);
                    FormattedPrint.center("", "||", 21);
                    FormattedPrint.center("================", "###", 21);
                    FormattedPrint.center("", "||", 21);

                    LinkedList<Video> recentVideosRange = ChannelLibrary.getCurrentChannelVideosRecentRange(1, 10);
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
                return;
        }
    }

    private static void channelPlaylist() {
        int choice;
        FormattedPrint.center("========== CLI-tube ==========", "###", 13);
        FormattedPrint.center("", "||", 13);
        FormattedPrint.center(" Let's create a fun list !! ", "##", 13);
        FormattedPrint.center(CurrentChannel.getName() + " ", "##", 13);

        if (!ChannelLibrary.getCurrentChannelVideos().isEmpty()) {
            FormattedPrint.center("", "||", 13);
            FormattedPrint.center("Your recent playlist: ", "||", 13);

            LinkedList<Video> recentVideos = ChannelLibrary.getCurrentChannelVideosRecent(10);
            for (int i = 1; i <= recentVideos.size(); i++) {
                FormattedPrint.center(recentVideos.get(i - 1).title, "||", 13);
            }
        } else {
            FormattedPrint.center("You don't have any playlist.", "", 0);
        }

        FormattedPrint.center("", "||", 13);
        FormattedPrint.center("1. Upload Video ", "||", 13);
        FormattedPrint.center("2. Delete Video ", "||", 13);
        FormattedPrint.center("3. Edit Video ", "||", 13);
        FormattedPrint.center("~. Back ", "||", 13);
        FormattedPrint.center("", "||", 13);
        FormattedPrint.center("================================", "###", 13);
        System.out.println("");

        choice = GetInput.integerPositive("Choice: ", "Input isn't valid", 28);

        switch (choice) {
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

                ChannelLibrary.getCurrentChannelVideos().add(new Video(title, description, LocalDateTime.now()));
                FormattedPrint.center("Video uploaded successfully.", "", 0);
                break;
            case 2:
                while (true) {
                    System.out.print("\033[H\033[2J");
                    FormattedPrint.center("=== CLI-tube ===", "###", 21);
                    FormattedPrint.center("", "||", 21);
                    FormattedPrint.center("Delete Video", "||", 21);
                    FormattedPrint.center("", "||", 21);
                    FormattedPrint.center("================", "###", 21);
                    FormattedPrint.center("", "||", 21);

                    LinkedList<Video> recentVideosRange = ChannelLibrary.getCurrentChannelVideosRecentRange(1, 10);
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
                return;
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
        while (true) {
            FormattedPrint.center("=== CLI-tube ===", "###", 21);
            FormattedPrint.center("", "||", 21);
            FormattedPrint.center("1. Login", "||", 21);
            FormattedPrint.center(" 2. Register", "||", 21);
            FormattedPrint.center("~. Exit ", "||", 21);
            FormattedPrint.center("", "||", 21);
            FormattedPrint.center("================", "###", 21);
            System.out.println("");

            choice = GetInput.integerPositive("Choice: ", "Input isn't valid", 28);

            switch (choice) {
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
                    FormattedPrint.center("Are you sure (1 to yes) : .", "", 0);
                    int exit = GetInput.integer("","", 32);
                    if (exit == 1) return;
                    break;
            }
        }
    }
}
