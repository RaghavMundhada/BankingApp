package bankingApp.utils;

public class Util {

    public static String parseData(String str) {
        return str != null && str.trim().length() != 0 && !str.trim().equalsIgnoreCase("null") ? str.trim() : null;
    }

}
