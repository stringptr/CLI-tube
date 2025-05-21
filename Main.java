import Utils.*;
import Menus.*;

public class Main{
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
