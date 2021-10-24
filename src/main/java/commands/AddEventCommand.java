package commands;

import data.Task;
import data.TaskList;
import data.exception.StorageOperationException;
import storage.Storage;
import ui.Ui;

public class AddEventCommand extends Command {
    private Task task;

    public AddEventCommand(Task task){
        this.task = task;
    }

    public static final String COMMAND_WORD = CommandEnum.EVENT.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a event task to the TaskList. "
            + "EVENT_DATE to be specified in the following date format -> [d/MM/yyyy HHmm]\n"
            + "Parameters: EVENT_DESCRIPTION /by EVENT_DATE]\n"
            + "Example: "
            + COMMAND_WORD + " TIC2002 Project /at 01/11/2021 2359";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException {
        taskList.addTask(task);
        storage.saveTask(taskList.getLatestAddedTask());
        ui.printAddSingleTask(task, taskList.getTotalListCount());
    }
}
