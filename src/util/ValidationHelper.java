package util;

public class ValidationHelper {
    private ValidationHelper() {
    }

    public static boolean isInteger(String value) {
        return value != null && value.matches("-?\\d+");
    }

    public static boolean isPositiveInteger(String value) {
        return isInteger(value) && Integer.valueOf(value) > 0;
    }
}
