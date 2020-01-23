//this a comment
package toHome;

import java.io.File;

public class Main {

    private static String[] starts = {	"C:\\Users\\mbura\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs",
    									"C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs"	};

    public static void main(String[] args) {
        JavaElevator.elevate(args);
    	System.out.println("toHome");
        System.out.println("toHome VER 1.2");
        
        for(String start : starts)
        	folder(start);
       
        System.out.println("Execution Terminated");
    }

    private static int folder(String path) {
        File folder = new File(path);

        System.out.println("Openning Directory: " + path);
        File[] listOfFiles = folder.listFiles(); 
        
        folder.listFiles();
        
        for (File file : listOfFiles)
            if (file.isFile()) {
            	if (!file.getName().equals(path + "\\" + file.getName())) {
            		System.out.println(file.getAbsolutePath() + " MOVE to: " + path + "\\" + file.getName());
            		file.renameTo(new File(path + "\\" + file.getName()));
            	}
            } else if (file.isDirectory()) {
                System.out.println("Directory: " + file.getAbsolutePath());
                if (folder(file.getAbsolutePath()) == 1) {
                	file.delete();
                }
            }

        return FolderIsVoid(folder);
    }
    
    static int FolderIsVoid(File folder) {        
        if (folder.listFiles().length == 0) {
            folder.delete();
            return 1;
        }
        return 0;
    }
}
