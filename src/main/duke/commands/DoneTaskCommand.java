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
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markAsDoneTask(this.targetMarkAsDoneIndex);
        storage.saveAllTask(taskList.getAllTask());
        ui.showMarkDoneTask(taskList.getTask(targetMarkAsDoneIndex));
    }
}
