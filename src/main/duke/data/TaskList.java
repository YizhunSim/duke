package data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> allTasks;

    public TaskList(){
        allTasks = new ArrayList<>();
    }

    public TaskList(List<Task> Tasks){
        this.allTasks = new ArrayList<>(Tasks);
    }

    public List<Task> getAllTask(){
        return allTasks;
    }

    public void addTask(Task newTask){
        this.allTasks.add(newTask);
    }

    public int getTotalListCount(){
        return allTasks.size();
    }

    public String deleteTask(int toBeDeletedTask){
        String taskToBeDeleted = allTasks.get(toBeDeletedTask).getTaskDescription();
        allTasks.remove(toBeDeletedTask);
        return taskToBeDeleted;
    }

    public Task getLatestAddedTask(){
        return allTasks.get(getTotalListCount()-1);
    }

    public void markAsDoneTask(int taskToMarkDone){
        allTasks.get(taskToMarkDone).markAsDone();
    }

    public Task getTask(int taskIndex){
        return allTasks.get(taskIndex);
    }

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
                if (e.getBy().toLocalDate().equals(dateTime)){
                    tasksGroupBySpecificDate.add(singleTask);
                }
            }
        }
        return tasksGroupBySpecificDate;
    }
}
