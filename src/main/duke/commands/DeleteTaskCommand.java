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
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        // Get task description before deletion!
        String taskToBeDeleted = taskList.deleteTask(targetDeleteIndex);
        //storage.saveTask(taskList);
        ui.showDeletedTask(taskToBeDeleted);
        ui.printTaskCount(taskList.getTotalListCount());
    }
}
