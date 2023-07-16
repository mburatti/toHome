package toHome;

import java.io.File;
import java.util.Objects;

import toHome.filesManagement.BlackList;
import toHome.filesManagement.ListableBlackList;
import toHome.systemOperations.JavaElevator;
public class Main {

    //Find correct dir for Start
    private static final String[] STARTS = {
        "C:\\Users\\mbura\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs",
        "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs"};

    private static String currentRoot;

    public static void main(String[] args) {
        JavaElevator.elevate(args);
        System.out.println("toHome");
        System.out.println("toHome VER 1.3");

        for (String start : STARTS) {
            currentRoot = start;
            verifyFolder(start);
        }

        System.out.println("Execution Terminated");
    }

    /**
     * verifyFolder.
     *
     * @param path is the folder to be analysed.
     * @return true if the folder is empty
     */
    private static boolean verifyFolder(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        ListableBlackList blackList = new BlackList();

        assert listOfFiles != null;
        for (File file : listOfFiles)
            if (file.isFile()) {
                if (blackList.contains(file.getName())) {
                    deleteFile(file);
                } else {
                    String source = file.getAbsolutePath();
                    String destiny = getDestinyFileName(file);

                    if (!source.equalsIgnoreCase(destiny)) {
                        System.out.println(file.getAbsolutePath() + " MOVE to: " + getDestinyFileName(file));
                        //noinspection ResultOfMethodCallIgnored
                        file.renameTo(new File(getDestinyFileName(file)));
                    }
                }
            }

        for (File file : listOfFiles)
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getAbsolutePath());
                if (verifyFolder(file.getAbsolutePath())) {
                    //noinspection ResultOfMethodCallIgnored
                    file.delete();
                }
            }

        return isFolderVoid(folder);
    }

    private static void deleteFile(File file) {
        System.out.println("DELETE " + file.getName());
        file.deleteOnExit();
    }

    private static String getDestinyFileName(File file) {
        return currentRoot + "\\" + file.getName();
    }

    static boolean isFolderVoid(File folder) {
        if (Objects.requireNonNull(folder.listFiles()).length == 0) {
            deleteFile(folder);
            return true;
        }
        return false;
    }
}
