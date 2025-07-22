package main.tohome.files_management;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages undesired files from badnames.csv file.
 */
public final class BlackList implements ListableBlackList {
    private static final Logger LOGGER = Logger.getLogger(BlackList.class.getName());
    private List<String> badNames;
    
    public BlackList(){
        try {
            this.badNames = FileToList.read(new File("badnames.csv"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    /**
     * Returns is the given names is in the undesired file list.
     */
    @Override
    public boolean contains(String fileName){
        if (null != fileName)
            for(String badName : badNames)
                if(fileName.toLowerCase().contains(badName))
                    return true;

        return false;
    }
}
