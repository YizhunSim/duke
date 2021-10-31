package commands;

import data.Deadline;
import data.Event;
import data.Task;
import data.TaskList;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortTaskCommand extends Command{
    public static final String EVENT = CommandEnum.EVENT.toString().toLowerCase();
    public static final String DEADLINE = CommandEnum.DEADLINE.toString().toLowerCase();
    private String taskType;
    private String sortType;

    public SortTaskCommand(String taskType, String sortType) {
        this.taskType = taskType;
        this.sortType = sortType;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        List<Task> tl = getTaskListByType(taskList, taskType);
        ui.showMessage("Before Sort:");
        ui.printAllTasks(tl);
        ui.showMessage("After Sort:");
        ui.printAllTasks((sortListChronologically(tl, taskType)));
    }

    private List<Task> getTaskListByType (TaskList taskList, String taskType){
        List<Task> tl = new ArrayList<>();
        if (taskType.equals(EVENT)){
            tl = taskList.getAllTaskEvents();
        }
        if (taskType.equals(DEADLINE)){
            tl = taskList.getAllTaskDeadlines();
        }
        return tl;
    }

    private List<Task> sortListChronologically (List<Task> listTask, String taskType){
        List<Task> sortedList = new ArrayList<>();
        if (taskType.equals(DEADLINE)){
            sortedList = listTask.stream()
                    .sorted(Comparator.comparing(t -> ((Deadline) t).getBy()))
                    .collect(Collectors.toList());
        }
        if (taskType.equals(EVENT)){
            sortedList = listTask.stream()
                    .sorted(Comparator.comparing(t -> ((Event) t).getAt()))
                    .collect(Collectors.toList());
        }
        return sortedList;
    }
}

