package test.java.tohome;

import main.tohome.files_management.BlackList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class BlackListTest {

    /**
     * Test the blacklist method
     */
    private BlackList blackList;

    @BeforeEach
    public void setUp() {
        blackList = new BlackList();
    }

    /*
    Test the blacklist method with a valid word, without loading a file.
     */
    @Test
    public void testContainsMatchingNameReturnsTrue() {
        Assertions.assertTrue(blackList.contains("Manual"));
    }

    /*
    Test the blacklist method with an invalid word, without loading a file.
     */
    @Test
    public void testContainsNonMatchingNameReturnsFalse() {
        Assertions.assertFalse(blackList.contains("Explorer"));
    }

    /*
    Test the blacklist method loading words from a file, as it happens on the actual execution.
    The method is case-insensitive, so it runs the blacklist thrice.
    */
    @SuppressWarnings("DefaultAnnotationParam") // To be kept as an example
    @ParameterizedTest
    @CsvFileSource(files = "src/test/java/tohome/resources/tst_badnames.csv", numLinesToSkip = 0)
    public void testContainsMatchingNameFromFileReturnsTrue(String testName) {
        Assertions.assertTrue(blackList.contains(testName));
        Assertions.assertTrue(blackList.contains(testName.toLowerCase()));
        Assertions.assertTrue(blackList.contains(testName.toUpperCase()));
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void testContainsNullAndEmptyReturnsFalse(String word) {
        Assertions.assertFalse(blackList.contains(word));
    }
}
//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme