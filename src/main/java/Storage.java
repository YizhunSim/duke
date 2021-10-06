import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class Storage {
    public Storage(String filePath){
        try{
            String home = System.getProperty(("user.home"));
            Path path = Paths.get(home, "duke", filePath);
            boolean directoryExists = Files.exists(path);
            String dukeTextFilePath = Paths.get(home, "duke", filePath).toString();
            File f = new File(dukeTextFilePath);
            System.out.println("full path: " + f.getAbsolutePath());
            System.out.println("file exists?: " + f.exists());
            System.out.println("is Directory?: " + f.isDirectory());
            if (directoryExists){
                // Read file
                Scanner s = new Scanner(f);
                while (s.hasNext()){
                    System.out.println(s.nextLine());
                }
            }else{
                // Create File
                Files.createDirectory(path.getParent());
                Files.createFile(path);
            }
        } catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
