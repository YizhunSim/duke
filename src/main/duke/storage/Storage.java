package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;

import data.Task;
import data.TaskList;
import data.exception.DukeException;

public class Storage {
    private File f;
    private String dukeTextFilePath;
    public final String home = System.getProperty(("user.home"));
    private Path path;

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

    public void saveTask(Task taskToBeSave) {
        try {
            TaskListEncoder.encodeTaskList(taskToBeSave, dukeTextFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
