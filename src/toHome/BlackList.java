package toHome;

import java.util.ArrayList;
import java.util.List;

public final class BlackList {
    private static List<String> badNames = new ArrayList();

    static {
        badNames.add("agreement");
        badNames.add("manual");
        badNames.add("uninstall");
        badNames.add("license");
        badNames.add("(");
    }

    public static boolean contains(String fileName){
        for(String badName : badNames){
            if(fileName.toLowerCase().contains(badName))
                return true;
        }

        return false;
    }
}
