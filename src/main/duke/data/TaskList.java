package data;

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

    public List<Task> getTask(){
        return allTasks;
    }

    public void addTask(Task newTask){
        this.allTasks.add(newTask);
    }

    public int getTotalListCount(){
        return allTasks.size();
    }

    public String deleteTask(int toBeDeletedTask){
        return allTasks.get(toBeDeletedTask).getTaskDescription();
    }
}
