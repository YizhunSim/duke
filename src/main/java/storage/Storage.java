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
import data.exception.StorageOperationException;

/**
 * Represents the file used to store Duke's Chat Bot Task List data.
 */
public class Storage {
    public final String home = System.getProperty(("user.home"));
    private String filePath;
    private String dataAbsoluteFilePath;
    private File f;
    private Path path;

    /**
     * Constructor for Storage Class
     *
     *  @param filePath passed in from Duke Main
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath(){
        return this.filePath;
    }

    public void Init() throws StorageOperationException{
        try{
            path = Paths.get(home, "duke", filePath);
            dataAbsoluteFilePath = Paths.get(home, "duke", filePath).toString();

            boolean directoryExists = Files.exists(path);
            if (directoryExists){
                // Read file
                System.out.println("Storage File Constructor: Read file");
                f = new File(dataAbsoluteFilePath);
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
        }  catch (IOException ex) {
            throw new StorageOperationException((ex.getMessage()));
        }
    }


    /**
     * Loads the {@code TaskList} data from this storage file, and then returns it.
     * Returns an empty {@code TaskList} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException, if there were errors reading and/or converting data from file.
     */
    public List<Task> load() throws StorageOperationException {
        Init();
        try{
            return TaskListDecoder.decodeTaskList(Files.readAllLines(path));
        } catch (IOException | DukeException e){
            throw new StorageOperationException("Error writing to file upon load: "+ dataAbsoluteFilePath);
        }

    }

    /**
     * Saves the {@code Task} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void saveTask(Task taskToBeSave) throws StorageOperationException{
        try {
            TaskListEncoder.encodeTask(taskToBeSave, dataAbsoluteFilePath);
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file upon saveTask: "+ dataAbsoluteFilePath);
        }
    }

    /**
     * Saves the {@code TaskList} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void saveAllTask(List<Task> allTask) throws StorageOperationException{
        try {
            TaskListEncoder.encodeTaskList(allTask, dataAbsoluteFilePath);
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file upon saveAllTask: "+ dataAbsoluteFilePath);
        }
    }
}
