package commands;

import data.Task;
import data.TaskList;
import storage.Storage;
import ui.Ui;

public class AddEventCommand extends Command {
    private Task task;

    public AddEventCommand(Task task){
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        taskList.addTask(task);
        //storage.saveTask(taskList.);
        ui.printAddTodo(task.getTaskDescription(), taskList.getTotalListCount());
    }
}
