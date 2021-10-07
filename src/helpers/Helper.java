package helpers;

public class Helper {

    private static final String TAIP = "taip";
    private static final String NE = "ne";

    public static boolean arTaip(String string) {
        return string.equalsIgnoreCase(TAIP);
    }

    public static String getTaipArbaNe(boolean bool) {
        return bool ? TAIP : NE;
    }
}
