package Utils;

public class FormattedPrint {
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