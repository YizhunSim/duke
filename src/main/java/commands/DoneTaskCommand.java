package commands;

import common.Messages;
import data.TaskList;
import data.exception.StorageOperationException;
import data.exception.TaskNotFoundException;
import storage.Storage;
import ui.Ui;

public class DoneTaskCommand extends Command{
    private int targetMarkAsDoneIndex;
    public DoneTaskCommand(int targetMarkAsDoneIndex){
        this.targetMarkAsDoneIndex = targetMarkAsDoneIndex;
    }

    public static final String COMMAND_WORD = CommandEnum.DONE.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task as done identified by the index number used in the last task listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, StorageOperationException {
        try{
            taskList.markAsDoneTask(this.targetMarkAsDoneIndex);
            storage.saveAllTask(taskList.getAllTask());
            ui.showMarkDoneTask(taskList.getTask(targetMarkAsDoneIndex).toString());

            return Messages.MARK_TASK_DONE + Messages.getTask(taskList.getTask(targetMarkAsDoneIndex).toString());
        }catch (TaskNotFoundException | StorageOperationException ex){
            ui.showError(Messages.FAIL_TO_MARK_TASK);
            ui.showError(ex.getMessage());
            return Messages.FAIL_TO_MARK_TASK;
        }

    }
}
