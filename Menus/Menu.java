package Menus;

import java.time.LocalDateTime;
import java.util.*;

import Utils.*;
import Database.*;
import DataStructures.*;
import Menus.*;

public class Menu {
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
        if (CurrentUser.getUsername() == null) return;

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
