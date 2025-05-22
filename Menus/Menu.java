package Menus;

import java.time.LocalDateTime;

import Utils.*;
import Database.*;
import DataStructures.*;

public class Menu {
    public static void login() {
        

        int outerPad = 21;

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
