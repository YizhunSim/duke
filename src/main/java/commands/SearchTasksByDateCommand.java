package commands;

import common.Messages;
import data.Task;
import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.Ui;

import java.time.LocalDate;
import java.util.List;

public class SearchTasksByDateCommand extends Command{
    protected LocalDate dt ;
    public SearchTasksByDateCommand(LocalDate datetime){
        this.dt = datetime;
    }

    public static final String COMMAND_WORD = CommandEnum.FIND.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all deadline/events task occurring on a specific date. "
            + "deadline/event date to be specified in the following date format [d/MM/yyyy]\n"
            + "Parameters: d/MM/yyyy \n"
            + "Example: " + COMMAND_WORD + " 01/11/2021";
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<Task> foundTasks = taskList.retrieveTasksByDate(dt);
        ui.showTasksOnSpecificDate(taskList.getAllTaskListString(foundTasks));

        return Messages.getAllTask(taskList.getAllTaskListString(foundTasks));
    }
}
