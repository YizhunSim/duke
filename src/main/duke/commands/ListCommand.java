package commands;

import data.TaskList;
import storage.Storage;
import ui.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.printAllTasks(taskList.getTask());
    }
}
