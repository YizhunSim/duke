package data;

import data.exception.TaskNotFoundException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void taskListConstructor_newEmptyTaskList_success(){
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getTotalListCount());
    }

    @Test
    public void taskListConstructor_existingTaskList_success(){
        Task t1 = new Deadline("deadline description", LocalDateTime.of(2021,10,10,10,10));
        Task t2 = new Event("event description", LocalDateTime.of(2021,10,10,10,10));
        Task t3 = new Todo("todo description");
        List<Task> t = new ArrayList<>();
        t.add(t1);
        t.add(t2);
        t.add(t3);

        TaskList taskList = new TaskList(t);
        assertEquals(3, taskList.getTotalListCount());
    }

    @Test
    public void getAllTask_validTaskList_success(){
        Task t1 = new Deadline("deadline description", LocalDateTime.of(2021,10,10,10,10));
        Task t2 = new Event("event description", LocalDateTime.of(2021,10,10,10,10));
        Task t3 = new Todo("todo description");
        List<Task> t = new ArrayList<>();
        TaskList taskList = new TaskList();

        assertEquals(t, taskList.getAllTask());

        t.add(t1);
        t.add(t2);
        t.add(t3);

        taskList.addTask(t1);
        taskList.addTask(t2);
        taskList.addTask(t3);

        assertEquals(t, taskList.getAllTask());
    }

    @Test
    public void addTask_validTask_success(){
        Task t1 = new Deadline("deadline description", LocalDateTime.of(2021,10,10,10,10));
        Task t2 = new Event("event description", LocalDateTime.of(2021,10,10,10,10));
        Task t3 = new Todo("todo description");

        TaskList taskList = new TaskList();

        assertEquals(0, taskList.getTotalListCount());

        taskList.addTask(t1);
        taskList.addTask(t2);
        taskList.addTask(t3);

        assertEquals(3, taskList.getTotalListCount());
    }

    @Test
    public void getTotalListCount_validTaskList_success(){
        Task t1 = new Deadline("deadline description", LocalDateTime.of(2021,10,10,10,10));
        Task t2 = new Event("event description", LocalDateTime.of(2021,10,10,10,10));
        Task t3 = new Todo("todo description");

        TaskList taskList = new TaskList();

        assertEquals(0, taskList.getTotalListCount());

        taskList.addTask(t1);
        taskList.addTask(t2);
        taskList.addTask(t3);

        assertEquals(3, taskList.getTotalListCount());
    }

    @Test
    public void deleteTask_validTaskId_success() throws TaskNotFoundException{
        Task t1 = new Deadline("deadline description", LocalDateTime.of(2021,10,10,10,10));
        Task t2 = new Event("event description", LocalDateTime.of(2021,10,10,10,10));
        Task t3 = new Todo("todo description");
        TaskList taskList = new TaskList();
        taskList.addTask(t1);
        taskList.addTask(t2);
        taskList.addTask(t3);
        taskList.deleteTask(1);
        assertEquals(2, taskList.getTotalListCount());
    }

    @Test
    public void deleteTask_invalidTaskId_taskNotFoundExceptionThrown() throws TaskNotFoundException{
        Task t1 = new Deadline("deadline description", LocalDateTime.of(2021,10,10,10,10));
        Task t2 = new Event("event description", LocalDateTime.of(2021,10,10,10,10));
        Task t3 = new Todo("todo description");
        TaskList taskList = new TaskList();
        taskList.addTask(t1);
        taskList.addTask(t2);
        taskList.addTask(t3);
        int taskId = 4;

        try{
            taskList.deleteTask(taskId);
        } catch (TaskNotFoundException e){
            assertEquals("Task id provided: " + taskId + " does not exist.", e.getMessage());
        }
    }

    @Test
    public void getLatestAddedTask_validTaskList_success(){
        Task t1 = new Deadline("deadline description", LocalDateTime.of(2021,10,10,10,10));
        Task t2 = new Event("event description", LocalDateTime.of(2021,10,10,10,10));
        Task t3 = new Todo("todo description");
        TaskList taskList = new TaskList();
        taskList.addTask(t1);
        taskList.addTask(t2);
        taskList.addTask(t3);

        assertEquals(t3, taskList.getLatestAddedTask());
    }

    @Test
    public void markAsDoneTask_validTaskId_success() throws TaskNotFoundException {
        int taskId = 1;
        Task t1 = new Deadline("deadline description", LocalDateTime.of(2021,10,10,10,10));
        Task t2 = new Event("event description", LocalDateTime.of(2021,10,10,10,10));
        Task t3 = new Todo("todo description");
        TaskList taskList = new TaskList();
        taskList.addTask(t1);
        taskList.addTask(t2);
        taskList.addTask(t3);

        taskList.markAsDoneTask(taskId);
        assertEquals(true, taskList.getTask(taskId).getIsDone());
    }

    @Test
    public void markAsDoneTask_invalidTaskId_taskNotFoundExceptionThrown() throws TaskNotFoundException{
        int taskId = 4;
        Task t1 = new Deadline("deadline description", LocalDateTime.of(2021,10,10,10,10));
        Task t2 = new Event("event description", LocalDateTime.of(2021,10,10,10,10));
        Task t3 = new Todo("todo description");
        TaskList taskList = new TaskList();
        taskList.addTask(t1);
        taskList.addTask(t2);
        taskList.addTask(t3);

        try{
            taskList.markAsDoneTask(taskId);
        } catch (TaskNotFoundException e){
            assertEquals("Task id provided: " + taskId + " does not exist.", e.getMessage());
        }
    }

    @Test
    public void getTask_validTaskId_success() throws TaskNotFoundException {
        int validTaskId = 2;
        Task t1 = new Deadline("deadline description", LocalDateTime.of(2021,10,10,10,10));
        Task t2 = new Event("event description", LocalDateTime.of(2021,10,10,10,10));
        Task t3 = new Todo("todo description");
        TaskList taskList = new TaskList();
        taskList.addTask(t1);
        taskList.addTask(t2);
        taskList.addTask(t3);

        assertEquals(t2, taskList.getTask(validTaskId));
    }

    @Test
    public void getTask_invalidTaskId_taskNotFoundExceptionThrown() throws TaskNotFoundException{
        int invalidId = 5;
        Task t1 = new Deadline("deadline description", LocalDateTime.of(2021,10,10,10,10));
        Task t2 = new Event("event description", LocalDateTime.of(2021,10,10,10,10));
        Task t3 = new Todo("todo description");
        TaskList taskList = new TaskList();
        taskList.addTask(t1);
        taskList.addTask(t2);
        taskList.addTask(t3);

        try{
            taskList.getTask(invalidId);
        }catch(TaskNotFoundException e){
            assertEquals("Task id provided: " + invalidId + " does not exist.", e.getMessage());
        }
    }

    @Test
    public void retrieveTaskByDate_validDateTime_success(){
        Task t1 = new Deadline("deadline description", LocalDateTime.of(2021,10,10,10,10));
        Task t2 = new Event("event description", LocalDateTime.of(2021,10,10,10,10));

        TaskList taskList = new TaskList();
        taskList.addTask(t1);
        taskList.addTask(t2);

        LocalDate date1 = LocalDate.of(2021,05,05);
        assertEquals(new TaskList().getAllTask(), taskList.retrieveTasksByDate(date1));

        LocalDate date2 = LocalDate.of(2021,10,10);
        assertEquals(taskList.getAllTask(), taskList.retrieveTasksByDate(date2));
    }
}
