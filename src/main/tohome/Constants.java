package main.tohome;

import java.util.Arrays;
import java.util.List;

/**
 * LIST OF CONSTANTS
 */
final class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String USER_DIR_PREFIX = "C:\\Users";
    public static final String USER_DIR_SUFIX ="\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs";
    public static final String SYSTEM_START_MENU ="C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs";
    public static final List<String> SYSTEM_FOLDERS = Arrays.asList("All Users", "Default", "Default User", "Public");
}
