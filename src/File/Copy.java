package File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by Денис on 10/11/16.
 */
public class Copy {

    public static void main(String[] args) {
        if(args.length > 1) {
            Path source = Paths.get(args[0]);
            Path destination = Paths.get(args[1]);
            if(args.length == 3){
                copyAndDecode(source, destination, "UTF8");
            }
            copy(source, destination);
        }
    }

    private static void copy(Path source, Path destination){
        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyAndDecode(Path source, Path destination, String code){

    }
}
