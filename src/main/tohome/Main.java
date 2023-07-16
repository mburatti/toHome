package main.tohome;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import main.tohome.files_management.BlackList;
import main.tohome.files_management.ListableBlackList;
import main.tohome.system_operations.JavaElevator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
import static java.nio.file.Files.delete;

@SuppressFBWarnings("DMI_HARDCODED_ABSOLUTE_FILENAME")
public class Main {

    //Find correct dir for Start
    private static final List<String> starts = listOfDirsWithStart();

    private static String currentRoot;

    public static void main(String[] args) {
        JavaElevator.elevate();
        //noinspection SpellCheckingInspection
        out.println("tohome");
        out.println("toHome VER 1.3");

        starts.forEach(start -> {
            currentRoot = start;
            verifyFolder(start);
        });

        out.println("Execution Terminated");
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
        analiseFiles(listOfFiles, blackList);
        try {
            deleteEmptyFolders(listOfFiles);
        } catch (IOException e) {
            out.println("Error deleting folder");
        }

        return isFolderVoid(folder);
    }

    private static void deleteEmptyFolders(File[] listOfFiles) throws IOException {
        for (File file : listOfFiles)
            if (file.isDirectory()) {
                out.println("Directory: " + file.getAbsolutePath());
                if (verifyFolder(file.getAbsolutePath())) {
                    delete(file.toPath());
                }
            }
    }

    private static void analiseFiles(File[] listOfFiles, ListableBlackList blackList) {
        assert listOfFiles!= null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                if (blackList.contains(file.getName())) {
                    processFile(file, "delete");
                } else {
                    processFile(file, "rename");
                }
            }
        }
    }

    private static void processFile(File file, String action) {
        String source = file.getAbsolutePath();
        String destiny = getDestinyFileName(file);

        switch (action) {
            case "delete":
                out.println(file.getAbsolutePath() + " DELETE");
                deleteFile(file);
                break;
            case "rename":
                out.println(file.getAbsolutePath() + " MOVE to: " + getDestinyFileName(file));
                if (!source.equalsIgnoreCase(destiny) && file.renameTo(new File(getDestinyFileName(file)))) {
                        System.out.println(file.getAbsolutePath() + " ERROR Renaming file");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid action: " + action);
        }
    }

    private static void deleteFile(File file) {
        out.println("DELETE " + file.getName());
        file.deleteOnExit();
    }

    private static String getDestinyFileName(File file) {
        return currentRoot + "\\" + file.getName();
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
    static boolean isFolderVoid(File folder) {
        assert folder != null;
        if(folder.listFiles().length == 0) {
            deleteFile(folder);
            return true;
        }
        return false;
    }

    private static List<String> listOfDirsWithStart(){
        File file = new File(Constants.USER_DIR_PREFIX);

        String[] userFolders = file.list((current, name) -> new File(current, name).isDirectory());
        assert userFolders != null;

        List<String> dirs = new ArrayList<>();
        dirs.add(Constants.SYSTEM_START_MENU);

        for(String userFolder : userFolders)
            if(!Constants.SYSTEM_FOLDERS.contains(userFolder))
                dirs.add(Constants.USER_DIR_PREFIX + userFolder + Constants.USER_DIR_SUFIX);

        return dirs;
    }

}
