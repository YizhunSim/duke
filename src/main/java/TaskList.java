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
}
