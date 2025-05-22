package Menus;

import java.time.LocalDateTime;

import Utils.*;
import Database.*;
import DataStructures.*;

public class Menu {
    public static void login() {
        int outerPad = 21;
        Database.Users.put("user", new User("a",LocalDateTime.now(),new Channel("channel", null, LocalDateTime.now()),"user"));
        Database.Users.put("username", new User("name",LocalDateTime.now(),new Channel("channel", null, LocalDateTime.now()),"password"));
        Database.Users.put("onething", new User("onething",LocalDateTime.now(),new Channel("onething", null, LocalDateTime.now()),"onething"));
        Database.Users.put("queen", new User("queen",LocalDateTime.now(),new Channel("queen", null, LocalDateTime.now()),"queen"));

        QueryChannel.getChannelLibrary("username").uploadChannelVideo("username", new Video("Code a Java Program Overnight", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("user").uploadChannelVideo("user", new Video("Code Slow and Steady", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("username").uploadChannelVideo("username", new Video("One Night Thousand Lines", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("user").uploadChannelVideo("user", new Video("Don't Code a Java Program Overnight", null, LocalDateTime.now()));

        QueryChannel.getChannelLibrary("onething").uploadChannelVideo("onething", new Video("One Thing I Don't Know Why", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo("onething", new Video("It Doesn't Matter", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo("onething", new Video("How Hard You Code", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo("onething", new Video("Keep That In Mind", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo("onething", new Video("I Design This Program", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo("onething", new Video("To Remind Myself", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo("onething", new Video("All I Know", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("onething").uploadChannelVideo("onething", new Video("Time is a Valuable Thing", null, LocalDateTime.now()));

        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo("songbykeane", new Video("I came across A VL tree", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo("songbykeane", new Video("I  felt the branches of it looking at me", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo("songbykeane", new Video("Is this the we use to love?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo("songbykeane", new Video("Is this the place that i've been dreaming of?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo("songbykeane", new Video("Oh, simple thing, where have you gone?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo("songbykeane", new Video("I'm getting old, and i need something to rely on", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo("songbykeane", new Video("So, tell me when you're gonne let me in?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo("songbykeane", new Video("I'm getting tired, and i need somewhere to begin", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo("songbykeane", new Video("And if you have a minute why don't we go?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo("songbykeane", new Video("Talk about it somewhere only we know", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo("songbykeane", new Video("This could be the end of everything", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo("songbykeane", new Video("So why don't we go?", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("songbykeane").uploadChannelVideo("songbykeane", new Video("Somewhere only we know", null, LocalDateTime.now()));

        QueryChannel.getChannelLibrary("queen").uploadChannelVideo("queen", new Video("Too late, my time has come", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo("queen", new Video("Sends shivers down my spine, body's aching all the time", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo("queen", new Video("Goodbye, everybody, I've got to go", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo("queen", new Video("Gotta leave you all behind and face the truth", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo("queen", new Video("Mamaaaa..... Uuuuuuu......", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo("queen", new Video("I Don't Wanna Die", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo("queen", new Video("Sometime I Wish I Never Born At All", null, LocalDateTime.now()));
        QueryChannel.getChannelLibrary("queen").uploadChannelVideo("queen", new Video("(Insert Brian May Solo Here)", null, LocalDateTime.now()));

        QueryChannel.getChannelLibrary("queen").createPlaylist(QueryChannel.getChannelLibrary("username").getPlaylistsParentNode("queen"), "Rhapsody","a song");
        QueryChannel.getChannelLibrary("songbykeane").createPlaylist(QueryChannel.getChannelLibrary("username").getPlaylistsParentNode("songbykeane"), "Somewhere","a song");
        QueryChannel.getChannelLibrary("username").createPlaylist(QueryChannel.getChannelLibrary("username").getPlaylistsParentNode("username"), "Java Speedrun","Faster, Better");

        for (Video video : QueryChannel.getChannelLibrary("queen").getChannelVideos("queen")) {
            QueryChannel.getChannelLibrary("queen").getPlaylist("queen",0).playlist.add(video);
        }
        for (Video video : QueryChannel.getChannelLibrary("songbykeane").getChannelVideos("songbykeane")) {
            QueryChannel.getChannelLibrary("songbykeane").getPlaylist("songbykeane",0).playlist.add(video);
        }

        String inputtedUsername;

        FormattedPrint.center("=== CLI-tube ===", "###", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("Login ", "||", outerPad);
        FormattedPrint.center("", "||", outerPad);
        FormattedPrint.center("================", "###", outerPad);
        FormattedPrint.center("Tip: fill ~ to go back", "", outerPad);

        System.out.println("");

        while (true) {
            inputtedUsername = GetInput.stringLimitedCenter("Login As","Input isn't valid.", 16, 8);
            if (inputtedUsername.equals("~")) {
                return;
            } else if (!Database.Users.containsKey(inputtedUsername)) {
                FormattedPrint.center("Username isn't exist",   "", 0);
                System.out.println("");
            } else {
                System.out.println("");
                String password;
                while(true){
                    password = GetInput.stringLimitedCenter("Password","Input isn't valid.", 24, 8);
                    if (password.equals("~")) {
                        break;
                    } else if (QueryUser.getUser(inputtedUsername).verifyPassword(password)) {
                        System.out.print("\033[H\033[2J");
                        CurrentUser.set(inputtedUsername);
                        System.out.print("\033[H\033[2J");
                        mainMenu();
                        break;
                    } else {
                        FormattedPrint.center("Password incorrect.", "", 0);
                        System.out.println("");
                    }
                }
                break;
            }
        }
    }

    public static void register() {
        String username = "";
        String password = "";
        String display_name = "";

        FormattedPrint.center("=== CLI-tube ===", "###", 21);
        FormattedPrint.center("", "||", 21);
        FormattedPrint.center("Register", "||", 21);
        FormattedPrint.center("", "||", 21);
        FormattedPrint.center("================", "###", 21);
        FormattedPrint.center("Tip: fill ~ to go back", "", 21);

        while (username.length() < 6) {
            System.out.println("");
            username = GetInput.stringLimitedCenter("Username (min 6 characters)","Input isn't valid.", 16, 8);
            if (username.equals("~")) return;
            if (Database.Users.containsKey(username)) {
                System.out.println("Username already used.");
                FormattedPrint.center("", "", 0);
            };
        }

        System.out.println("");
        while (password.length() < 8) {
            password = GetInput.stringLimitedCenter("Password (min 8 characters)","Input isn't valid.", 24, 8);
            if (password.equals("~")) return;
            if (password.length() < 8) {
                System.out.println("Password must be at least 8 characters.");
                FormattedPrint.center("", "", 0);
            }
        }
        System.out.println("");

        display_name = GetInput.stringLimitedCenter("Name","Input isn't valid.", 24, 8);
        if (display_name.equals("~")) return;
        System.out.println("");
        int createChannel = GetInput.integerBoolCenter("Do you want to create a channel (0/1): ", "Input isn't valid", 1);
        System.out.println("");

        if (createChannel == 0) {
            User newUser = new User(display_name, LocalDateTime.now(), null, password);
            Database.Users.put(username, newUser);
            System.out.print("\033[H\033[2J");
            FormattedPrint.center("Account successfully created. Now, you can login.", "", 0);
            System.out.println("");
            return;
        }

        String channel_name = GetInput.stringLimitedCenter("Channel Name:","Too many characters.", 16, 8);
        User newUser = new User(display_name, LocalDateTime.now(), new Channel(channel_name, "", LocalDateTime.now()), password);
        Database.Users.put(username, newUser);
        System.out.print("\033[H\033[2J");
        FormattedPrint.center("Account with Channel successfully created. Now, you can login.", "", 0);
        System.out.println("");
        return;
    }

    private static void mainMenu() {
        if (CurrentUser.getUsername() == null) return;

        int choice;
        int outerPad = 17;

        while (true) {
            FormattedPrint.center("======= CLI-tube =======", "###", outerPad);
            FormattedPrint.center("", "||", outerPad);
            FormattedPrint.center("Welcome,", "||", outerPad);
            FormattedPrint.center(CurrentUser.getName(), "||", outerPad);
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
                    userMenu.main();
                    System.out.print("\033[H\033[2J");
                    break;
                case 2:
                    if (CurrentChannel.getChannel() != null) {
                        System.out.print("\033[H\033[2J");
                        channelMenu.main();
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
}
