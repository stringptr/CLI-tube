import java.util.*;
import java.time.*;

class FormattedPrint {
    public static void center (String content, String border, int outerPadding) {
        int totalPadding = 64 - ( content.length() + border.length() + (outerPadding*2) );
        if (totalPadding < 0) totalPadding = 0;
        int innerPaddingLength = totalPadding / 2;

        String innerPad = (" ").repeat(innerPaddingLength);
        String outerPad = (" ").repeat(outerPadding);

        System.out.println(outerPad + border + innerPad + content + innerPad + border + outerPad);
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
    List<Video> videos;

    public PlaylistNode(String name) {
        super(name);
        this.videos = new ArrayList<>();
    }

    public void addVideo(Video video) {
        videos.add(video);
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

    public TreeNode createHistoryNode() {
        TreeNode historyNode = new TreeNode("History");
        root.addChild(historyNode);
        return historyNode;
    }

    public TreeNode createPlaylistParent() {
        TreeNode playlistNode = new TreeNode("Playlists");
        root.addChild(playlistNode);
        return playlistNode;
    }

    public TreeNode getPlaylistParent() {
        for (TreeNode child : root.children) {
            if (child.name.equals("Playlists")) return child;
        }
        return null;
    }

    public PlaylistNode createPlaylist(TreeNode playlistParent, String name) {
        PlaylistNode pl = new PlaylistNode(name);
        playlistParent.addChild(pl);
        return pl;
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
    Stack<Video> history;

    public UserLibrary(String username) {
        super(username);
        history = new Stack<>();
        TreeNode historyNode = new TreeNode("History");
        root.addChild(historyNode);
    }

    public TreeNode getHistoryNode() {
        for (TreeNode child : root.children) {
            if (child.name.equals("History")) return child;
        }
        return null;
    }

    public void watchVideo(Video video) {
        history.push(video);
        System.out.println("Watching: " + video);
    }

    public void printHistory() {
        System.out.println("History: ");
        for (Video v : history) {
            System.out.println("- " + v);
        }
    }
}

class ChannelLibrary extends Library {
    List<Video> videos;

    public ChannelLibrary(String username) {
        super(username);
        videos = new LinkedList<>();
        TreeNode videosNode = new TreeNode("Videos");
        root.addChild(videosNode);
    }
}

class Menu {
    static HashMap<String, User> Users = new HashMap<>();
    static Queue<Video> QueuedVideos = new LinkedList<>();

    public static void login() {
        Users.put("user", new User("a",LocalDateTime.now(),new Channel("channel", null, LocalDateTime.now())));
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
            } else if (!Users.containsKey(inputtedUsername)) {
                FormattedPrint.center("Username isn't exist",   "", 0);
                System.out.println("");
            } else {
                System.out.print("\033[H\033[2J");
                mainMenu(inputtedUsername);
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
            if (Users.containsKey(username)) System.out.println("Username already used."); else break;
        }

        System.out.println("");
        display_name = GetInput.stringLimitedCenter("Name","Input isn't valid.", 24, 16);
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("Account successfully created. Now, you can login.", "", 0);
        System.out.println("");
        return;
    }

    private static void mainMenu(String loggedAs) {
        int choice;
        while (true) {
            FormattedPrint.center("======= CLI-tube =======", "###", 17);
            FormattedPrint.center("", "||", 17);
            if (Users.get(loggedAs).display_name.length() % 2 != 1) {
                FormattedPrint.center("Welcome, " + Users.get(loggedAs).display_name + " ", "||", 17);
            } else {
                FormattedPrint.center("Welcome, " + Users.get(loggedAs).display_name, "||", 17);
            }
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
                    userMain(loggedAs);
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    if (Users.get(loggedAs).channel != null) {
                        System.out.print("\033[H\033[2J");
                        channelMain(loggedAs);
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

    private static void userMain(String loggedAs) {
        int choice;
        while (true) {
            FormattedPrint.center("======== CLI-tube ========", "###", 15);
            FormattedPrint.center("", "||", 15);
            FormattedPrint.center(" What are we doing today? ", "##", 15);
            if (Users.get(loggedAs).display_name.length() % 2 != 0) {
                FormattedPrint.center(Users.get(loggedAs).display_name + " ", "##", 15);
            } else {
                FormattedPrint.center(Users.get(loggedAs).display_name, "##", 15);
            }
            FormattedPrint.center("", "||", 15);
            FormattedPrint.center("1. Watch Video & Playlist ", "||", 15);
            FormattedPrint.center("2. Playlist Collection", "||", 15);
            FormattedPrint.center("3. Accout Setting ", "||", 15);
            if (Users.get(loggedAs).channel != null) {
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
                    userWatch(loggedAs);
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    if (Users.get(loggedAs).channel != null) {
                        System.out.print("\033[H\033[2J");
                        channelMain(loggedAs);
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

    private static void channelMain(String loggedAs) {
        int choice;
        while (true) {
            FormattedPrint.center("======== CLI-tube ========", "###", 15);
            FormattedPrint.center("", "||", 15);
            FormattedPrint.center(" Now's your time to shine !!", "##", 15);
            if (Users.get(loggedAs).display_name.length() % 2 != 0) {
                FormattedPrint.center(Users.get(loggedAs).channel.display_name + " ", "##", 15);
            } else {
                FormattedPrint.center(Users.get(loggedAs).channel.display_name, "##", 15);
            }
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
                    channelVideo(loggedAs);
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    System.out.print("\033[H\033[2J");
                    channelPlaylist(loggedAs);
                    System.out.print("\033[H\033[2J");
                    break;
                case 3:
                    System.out.print("\033[H\033[2J");
                    channelSetting(loggedAs);
                    System.out.print("\033[H\033[2J");
                    break;
                default:
                    return;
            }
        }
    }

    private static void userWatch(String loggedAs) {
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

    private static void channelVideo(String loggedAs) {
        int choice;

        FormattedPrint.center("========== CLI-tube ==========", "###", 13);
        FormattedPrint.center("", "||", 13);
        FormattedPrint.center(" Now is your time to shine !! ", "##", 13);
        if (Users.get(loggedAs).display_name.length() % 2 != 0) {
            FormattedPrint.center(Users.get(loggedAs).channel.display_name + " ", "##", 13);
        } else {
            FormattedPrint.center(Users.get(loggedAs).channel.display_name, "##", 13);
        }

        if (!Users.get(loggedAs).channel.library.videos.isEmpty()) {
            FormattedPrint.center("", "||", 13);
            FormattedPrint.center("Your recent videos: ", "||", 13);

            for (int i = 0; i <= 9 && i < Users.get(loggedAs).channel.library.videos.size(); i++) {
                int inverseIndex = Users.get(loggedAs).channel.library.videos.size() - 1 - i;
                String title = Users.get(loggedAs).channel.library.videos.get(inverseIndex).title;
                if (title.length() % 2 != 0) {
                    FormattedPrint.center(title + " ", "||", 13);
                } else {
                    FormattedPrint.center(title, "||", 13);
                }
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

                Users.get(loggedAs).channel.library.videos.add(new Video(title, description, LocalDateTime.now()));
                FormattedPrint.center("Video uploaded successfully.", "", 0);
                break;
            case 2:
                System.out.print("\033[H\033[2J");
                //channelVideoDelete(loggedAs);
                break;
            case 3:
                System.out.print("\033[H\033[2J");
                //channelVideoEdit(loggedAs);
                break;
            default:
                return;
        }
    }

    private static void channelPlaylist(String loggedAs) {
        int choice;
    }

    private static void channelSetting(String loggedAs) {
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
                    break;
            }
        }
    }
}
