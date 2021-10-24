package commands;

import data.Task;
import data.TaskList;
import data.exception.StorageOperationException;
import storage.Storage;
import ui.Ui;

public class AddToDoCommand extends Command {
    private Task task;

    public AddToDoCommand(Task task){
        this.task = task;
    }

    public static final String COMMAND_WORD = CommandEnum.TODO.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo task to the TaskList.\n"
            + "Parameters: TODO_DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " TIC2002 Project";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException {
        taskList.addTask(task);
        storage.saveTask(taskList.getLatestAddedTask());
        ui.printAddSingleTask(task, taskList.getTotalListCount());
    }
}
