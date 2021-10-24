package commands;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        // Get task description before deletion!
        String taskToBeDeleted = taskList.deleteTask(targetDeleteIndex);
        storage.saveAllTask(taskList.getAllTask());
        ui.showDeletedTask(taskToBeDeleted);
        ui.printTaskCount(taskList.getTotalListCount());
    }
}
