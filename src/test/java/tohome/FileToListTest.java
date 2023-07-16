package test.java.tohome;

import main.tohome.files_management.FileToList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileToListTest {

    /**
     * Test the blacklist method
     */
    private List<String> loadedWords;
    private static final String TEST_FILE = "src/test/java/tohome/resources/tst_badnames.csv";

    @BeforeEach
    public void setUp() throws IOException {
        try {
            loadedWords = FileToList.read(new File(TEST_FILE));
        } catch (IOException e) {
            throw new IOException(e.getMessage().concat("ERROR ON TEST SETUP"));
        }
    }

    /*
        Test the method to load the words from the files and compares with the lodaded by Junit.
        Because the application is case insensitive to the words, the comparison occurs on the lowercase.
    */
    @SuppressWarnings("DefaultAnnotationParam") // To be kept as an example
    @ParameterizedTest
    @CsvFileSource(files = TEST_FILE, numLinesToSkip = 0)
    public void testContainsMatchingNameFromFileReturnsTrue(String testName) {
        Assertions.assertTrue(loadedWords.contains(testName.toLowerCase()));
    }
}