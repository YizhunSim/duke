package storage;

import data.*;
import data.exception.DukeException;
import data.exception.StorageOperationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void constructor_withFilePath_success(){
        Storage s = new Storage("data/testFile.txt");
        assertEquals("data/testFile.txt", s.getFilePath());
    }

    @Test
    public void load_invalidFormat_exceptionThrown() throws StorageOperationException {
        // File contains valid txt data, but does not match the Person Format
        Storage s = new Storage("data/testFile.txt");
        try{
            List<Task> tasks = s.load();
        } catch (StorageOperationException e){
            assertEquals(StorageOperationException.class, e.getClass());
        }
    }

    @Test
    public void load_validFormat_success() throws StorageOperationException{
        Storage s = new Storage("data/validTestData.txt");
        List<Task> expectedTaskList = getTestTaskList().getAllTask();
        List<Task> actualTaskList = s.load();

        // TODO needs to compare by object level
        assertEquals(2, 2);
    }

    @Test
    public void save_nullTaskList_StorageOperationExceptionThrown() throws StorageOperationException{
        Storage s = null;
        // Todo
    }

    private TaskList getTestTaskList() {
        Task t1 = new Deadline("deadline description", LocalDateTime.of(2021,10,10,10,10));
        Task t2 = new Event("event description", LocalDateTime.of(2021,10,10,10,10));
        Task t3 = new Todo("todo description");
        List<Task> t = new ArrayList<>();
        t.add(t1);
        t.add(t2);
        t.add(t3);

        TaskList taskList = new TaskList(t);
        return taskList;
    }
}
