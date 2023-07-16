package main.tohome.files_management;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileToList {
    private FileToList(){}

    public static List<String> read(File file) throws IOException {
        Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);
        List<String> lines = new ArrayList<>();
        
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine().toLowerCase());
        }

        scanner.close();
        return lines;
    }

}
