package commands;

import common.Messages;
import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.Ui;

public class DeleteTaskCommand extends Command{
    private int targetDeleteIndex;
    public DeleteTaskCommand(int targetDeleteIndex){
        this.targetDeleteIndex = targetDeleteIndex;
    }

    public static final String COMMAND_WORD = CommandEnum.DELETE.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the last task listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try{
            String taskToBeDeleted = taskList.deleteTask(targetDeleteIndex);
            storage.saveAllTask(taskList.getAllTask());
            ui.showDeletedTask(taskToBeDeleted);
            ui.printTaskCount(taskList.getTotalListCount());

            return Messages.DELETED_TASK + " " + Messages.getTask(taskToBeDeleted) + "\n"+ Messages.getTaskCount(taskList.getTotalListCount());
        } catch(DukeException ex){
            ui.showError(Messages.FAIL_TO_DELETE_TASK);
            ui.showError(ex.getMessage());
            return Messages.FAIL_TO_DELETE_TASK;
        }

    }
}
