package commands;

import data.TaskList;
import data.exception.DukeException;
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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markAsDoneTask(this.targetMarkAsDoneIndex);
        storage.saveAllTask(taskList.getAllTask());
        ui.showMarkDoneTask(taskList.getTask(targetMarkAsDoneIndex));
    }
}
