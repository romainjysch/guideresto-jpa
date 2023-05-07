package utils;

public class Utils {

    public static boolean charToJavaBoolean(String value){
        return value.equalsIgnoreCase("T");
    }

    public static String booleanToJavaString(boolean value){
        if(value)
            return "T";
        else
            return "F";
    }

}
