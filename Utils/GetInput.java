package Utils;

import java.util.*;

public class GetInput {
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
