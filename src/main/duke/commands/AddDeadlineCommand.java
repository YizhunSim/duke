package commands;

import data.Task;
import data.TaskList;
import data.exception.StorageOperationException;
import storage.Storage;
import ui.Ui;

public class AddDeadlineCommand extends Command{
    private Task task;

    public AddDeadlineCommand(Task task){
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException {
        taskList.addTask(task);
        storage.saveTask(taskList.getLatestAddedTask());
        ui.printAddSingleTask(task, taskList.getTotalListCount());
    }
}
