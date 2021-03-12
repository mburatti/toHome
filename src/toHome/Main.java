//this a comment
package toHome;

import java.io.File;

public class Main {

    private static final String[] starts = {"C:\\Users\\mbura\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs",
            "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs"};

    private static String currentRoot;
    private static String source;
    private static String destiny;

    public static void main(String[] args) {
        JavaElevator.elevate(args);
        System.out.println("toHome");
        System.out.println("toHome VER 1.2");

        for (String start : starts) {
            currentRoot = start;
            verifyFolder(start);
        }


        System.out.println("Execution Terminated");
    }

    /**
     * verifyFolder.
     *
     * @param path
     * @return true if the folder is empty
     */
    private static boolean verifyFolder(String path) {
        File folder = new File(path);

        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles)
            if (file.isFile()) {
                if (BlackList.contains(file.getName())) {
                    deleteFile(file);
                } else {
                    source = file.getAbsolutePath();
                    destiny = getDestinyFileName(file);

                    if (!source.equalsIgnoreCase(destiny)) {
                        System.out.println(file.getAbsolutePath() + " MOVE to: " + getDestinyFileName(file));
                        file.renameTo(new File(getDestinyFileName(file)));
                    }
                }
            }

        for (File file : listOfFiles)
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getAbsolutePath());
                if (verifyFolder(file.getAbsolutePath())) {
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
        if (folder.listFiles().length == 0) {
            deleteFile(folder);
            return true;
        }
        return false;
    }
}
