package commands;

import common.Messages;
import data.Task;
import data.TaskList;
import data.exception.StorageOperationException;
import storage.Storage;
import ui.Ui;

public class AddToDoCommand extends Command {
    private Task task;

    public AddToDoCommand(Task task){
        this.task = task;
    }

    public static final String COMMAND_WORD = CommandEnum.TODO.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo task to the TaskList.\n"
            + "Parameters: TODO_DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " TIC2002 Project";

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException {
        try{
            if (taskList.isTaskExist(task)){
                throw new StorageOperationException(Messages.TASK_EXIST);
            }
            taskList.addTask(task);
            storage.saveTask(taskList.getLatestAddedTask());
            ui.printAddSingleTask(task.toString(), taskList.getTotalListCount());

            return Messages.ADDED_TASK + Messages.getTask(task.toString()) + ".\n" + Messages.getTaskCount(taskList.getTotalListCount());
        }catch(StorageOperationException ex){
            ui.showError(Messages.FAIL_TO_ADD_TASK);
            ui.showError(ex.getMessage());
            return Messages.FAIL_TO_ADD_TASK;
        }
    }
}
