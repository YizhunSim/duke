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
    private File f;
    private String dukeTextFilePath;
    public final String home = System.getProperty(("user.home"));
    private Path path;

    /**
     * Constructor for Storage Class
     *
     *  @param filePath passed in from Duke Main
     */
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

    /**
     * Loads the {@code TaskList} data from this storage file, and then returns it.
     * Returns an empty {@code TaskList} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException, if there were errors reading and/or converting data from file.
     */
    public List<Task> load() throws StorageOperationException {
        try{
            return TaskListDecoder.decodeTaskList(Files.readAllLines(path));
        } catch (IOException | DukeException e){
            throw new StorageOperationException("Error writing to file upon load: "+ dukeTextFilePath);
        }

    }

    /**
     * Saves the {@code Task} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void saveTask(Task taskToBeSave) throws StorageOperationException{
        try {
            TaskListEncoder.encodeTask(taskToBeSave, dukeTextFilePath);
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file upon saveTask: "+ dukeTextFilePath);
        }
    }

    /**
     * Saves the {@code TaskList} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void saveAllTask(List<Task> allTask) throws StorageOperationException{
        try {
            TaskListEncoder.encodeTaskList(allTask, dukeTextFilePath);
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file upon saveAllTask: "+ dukeTextFilePath);
        }
    }
}
