package toHome.filesManagement;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class FileToList {

    static public List<String> read(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine().toLowerCase());
        }

        scanner.close();
        return lines;
    }

}
