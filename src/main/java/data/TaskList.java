package data;

import data.exception.TaskNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the entire Duke's Task List. Contains the data of the Task list.
 */
public class TaskList {
    private final List<Task> allTasks;

    /**
     * Creates an empty TaskList
     */
    public TaskList(){
        allTasks = new ArrayList<>();
    }

    /**
     * Constructs an TaskList with the given data.
     *
     * @param Tasks external changes to this will not affect this Task List
     */
    public TaskList(List<Task> Tasks){
        this.allTasks = new ArrayList<>(Tasks);
    }

    /**
     * Returns a new TaskList of all tasks in the Duke's TaskList at the time of the call.
     */
    public List<Task> getAllTask(){
        return allTasks;
    }

    /**
     * Adds a Task to the Task List.
     *
     * @param newTask external changes to this will not affect this Task List
     */
    public void addTask(Task newTask){
        this.allTasks.add(newTask);
    }

    /**
     * Returns the total count of the entire TaskList
     */
    public int getTotalListCount(){
        return allTasks.size();
    }

    /**
     * Removes the equivalent person from the address book.
     *
     * @throws TaskNotFoundException if no such Task could be found.
     */
    public String deleteTask(int toBeDeletedTaskIndex) throws TaskNotFoundException {
        if (toBeDeletedTaskIndex > allTasks.size() || toBeDeletedTaskIndex < 0){
            throw new TaskNotFoundException(toBeDeletedTaskIndex);
        }
        else{
            String taskToBeDeleted = allTasks.get(toBeDeletedTaskIndex).getTaskDescription();
            allTasks.remove(toBeDeletedTaskIndex);
            return taskToBeDeleted;
        }
    }

    /**
     * Returns the latest task that was added to the Task List
     */
    public Task getLatestAddedTask(){
        return allTasks.get(getTotalListCount()-1);
    }

    /**
     * Marks a particular task as done (Might have duplicate)
     */
    public void markAsDoneTask(int taskToMarkDoneIndex) throws TaskNotFoundException{
        if (taskToMarkDoneIndex > allTasks.size() || taskToMarkDoneIndex < 0){
            throw new TaskNotFoundException(taskToMarkDoneIndex);
        }else{
            getTask(taskToMarkDoneIndex).markAsDone();
        }
    }

    /**
     * Returns the task based on the index
     *
     *  @param taskIndex external changes to this will not affect this Task List
     */
    public Task getTask(int taskIndex) throws TaskNotFoundException{
        if (taskIndex > allTasks.size() || taskIndex < 1){
            throw new TaskNotFoundException(taskIndex);
        }else{
            return allTasks.get(taskIndex);
        }
    }

    /**
     * Returns the all tasks based on a specified date
     *
     *  @param dateTime external changes to this will not affect this address book
     */
    public List<Task> retrieveTasksByDate (LocalDate dateTime){
        List<Task> tasksGroupBySpecificDate = new ArrayList<>();
        for (Task singleTask : allTasks){
            if (singleTask instanceof Deadline ){ // Only Deadline and Events have date attributes
                Deadline d = (Deadline)singleTask;
                if (d.getBy().toLocalDate().equals(dateTime)){
                    tasksGroupBySpecificDate.add(singleTask);
                }
            }
            else if (singleTask instanceof Event){ // Only Deadline and Events have date attributes
                Event e = (Event)singleTask;
                if (e.getAt().toLocalDate().equals(dateTime)){
                    tasksGroupBySpecificDate.add(singleTask);
                }
            }
        }
        return tasksGroupBySpecificDate;
    }
}
