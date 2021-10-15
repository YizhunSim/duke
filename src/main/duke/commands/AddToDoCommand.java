package commands;

import data.Task;
import data.TaskList;
import storage.Storage;
import ui.Ui;

public class AddToDoCommand extends Command {
    private Task task;

    public AddToDoCommand(Task task){
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        taskList.addTask(task);
        storage.saveTask(taskList.getLatestAddedTask());
        ui.printAddSingleTask(task, taskList.getTotalListCount());
    }
}
