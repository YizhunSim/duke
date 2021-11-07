package commands;

import common.Messages;
import data.TaskList;
import data.exception.DukeException;
import data.exception.StorageOperationException;
import data.exception.TaskNotFoundException;
import storage.Storage;
import ui.Ui;

public class UndoTaskCommand extends Command{
    private int targetUndoTaskIndex;
    public UndoTaskCommand(int targetUndoTaskIndex){
        this.targetUndoTaskIndex = targetUndoTaskIndex;
    }

    public static final String COMMAND_WORD = CommandEnum.UNDO.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmark the task as done identified by the index number used in the last task listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try{
            taskList.markAsUndoTask(this.targetUndoTaskIndex);
            storage.saveAllTask(taskList.getAllTask());
            ui.showUndoneTask(taskList.getTask(targetUndoTaskIndex - 1).toString());

            return Messages.UNMARK_TASK_DONE + Messages.getTask(taskList.getTask(targetUndoTaskIndex - 1).toString());
        }catch (TaskNotFoundException | StorageOperationException ex){
            ui.showError(Messages.FAIL_TO_UMMARK_TASK);
            ui.showError(ex.getMessage());
            return Messages.FAIL_TO_UMMARK_TASK;
        }

    }
}
