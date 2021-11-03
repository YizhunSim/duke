package commands;

import common.Messages;
import data.TaskList;
import storage.Storage;
import ui.Ui;

public class ListCommand extends Command{

    public static final String COMMAND_WORD = CommandEnum.LIST.name().toLowerCase();
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all the tasks in the task list as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage){
        ui.printAllTasks(taskList.getAllTaskListString(taskList.getAllTask()));
        return Messages.getAllTask(taskList.getAllTaskListString(taskList.getAllTask()));
    }
}
