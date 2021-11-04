package commands;

import common.Messages;
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

    public static final String COMMAND_WORD = CommandEnum.DEADLINE.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Deadline task to the TaskList. "
            + "DEADLINE_DATE to be specified in the following date format -> [d/MM/yyyy HHmm]\n"
            + "Parameters: [DEADLINE_DESCRIPTION] /by [DEADLINE_DATE]\n"
            + "Example: "
            + COMMAND_WORD + " TIC2002 Project /by 01/11/2021 2359";
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException {
        try {
            taskList.addTask(task);
            storage.saveTask(taskList.getLatestAddedTask());
            ui.printAddSingleTask(task.toString(), taskList.getTotalListCount()); // Message will still be printed in console

            return Messages.ADDED_TASK + Messages.getTask(task.toString()) + Messages.getTaskCount(taskList.getTotalListCount());
        } catch (StorageOperationException ex) {
            ui.showError(Messages.FAIL_TO_ADD_TASK);
            ui.showError(ex.getMessage());
            return Messages.FAIL_TO_ADD_TASK;
        }
    }
}
