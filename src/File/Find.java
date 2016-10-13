package File;

import java.io.File;
import java.io.IOException;

/**
 * Created by Денис on 10/10/16.
 */
public class Find {

    public static void main(String[] args) {
        findFile(args[0], args[1]);
    }

    private static void findFile(String startPath, String fileName){
        File startCatalog = new File(startPath);

//        FilenameFilter filenameFilter = new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                if(name.startsWith(fileName)){
//                    return true;
//                }else{
//                    return false;
//                }
//            }
//        };
        File[] listOfFiles = startCatalog.listFiles();
        for(File file : listOfFiles){
            if(file.isDirectory()){
                findFile(file.getAbsolutePath(), fileName);
            }else if(file.getName().startsWith(fileName)){
                try {
                    System.out.println(file.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
