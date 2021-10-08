import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Pattern;

public class Storage {
    private File f;
    String dukeTextFilePath;
    public final String home = System.getProperty(("user.home"));
    public Path path;

    /*Can be converted to enums*/
    final static String TODO = "T";
    final static String DEADLINE = "D";
    final static String EVENT = "E";

    public Storage(String filePath){

        try{
            path = Paths.get(home, "duke", filePath);
            boolean directoryExists = Files.exists(path);
            dukeTextFilePath = Paths.get(home, "duke", filePath).toString();

            if (directoryExists){
                // Read file
                System.out.println("Storage File Constructor: Read file");
                f = new File(dukeTextFilePath);
                Scanner s = new Scanner(f);
                while (s.hasNext()){
                    System.out.println(s.nextLine());
                }
            }else{
                // Create File
                System.out.println("Storage File Constructor: Create file");
                Files.createDirectory(path.getParent());
                Files.createFile(path);
            }
        } catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> load() throws IOException, DukeException {
        return TaskListDecoder.decodeTaskList(Files.readAllLines(path));
    }
}
