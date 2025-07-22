package main.tohome;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    // Analyze files in a folder using threads
    public static void analyzeFilesWithThreads(File folder) {
        File[] files = folder.listFiles();

        // Check if the folder is accessible
        if (files == null) {
            System.err.println("Unable to access directory: " + folder.getAbsolutePath());
            return;
        }

        // Create a thread pool
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // Submit file processing tasks to the thread pool
        for (File file : files) {
            executor.submit(() -> processFile(file));
        }

        // Shutdown the executor service
        executor.shutdown();
    }

    // Process a single file
    private static void processFile(File file) {
        if (file.isFile()) {
            System.out.println("File: " + file.getName());
        } else if (file.isDirectory()) {
            System.out.println("Directory: " + file.getName());
        }
    }

    // Verify folder and analyze files
    public static void verifyFolder(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Invalid folder path: " + folderPath);
            return;
        }

        analyzeFilesWithThreads(folder);
    }

    // Main method
    public static void main(String[] args) {
        // Example folder paths
        String[] folderPaths = {"path1", "path2", "path3"};

        // Verify and analyze each folder
        for (String folderPath : folderPaths) {
            verifyFolder(folderPath);
        }
    }
}