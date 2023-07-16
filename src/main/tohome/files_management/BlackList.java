package main.tohome.files_management;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Manages undesired files from badnames.csv file.
 */
public final class BlackList implements ListableBlackList {
    private List<String> badNames;
    
    public BlackList(){
        try {
            this.badNames = FileToList.read(new File("badnames.csv"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
