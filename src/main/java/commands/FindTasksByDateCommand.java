package commands;

import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.Ui;

import java.time.LocalDate;

public class FindTasksByDateCommand extends Command{
    protected LocalDate dt ;
    public FindTasksByDateCommand(LocalDate datetime){
        this.dt = datetime;
    }

    public static final String COMMAND_WORD = CommandEnum.FIND.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all deadline/events task occurring on a specific date. "
            + "deadline/event date to be specified in the following date format [d/MM/yyyy]\n"
            + "Parameters: d/MM/yyyy \n"
            + "Example: " + COMMAND_WORD + " 01/11/2021";
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showTasksOnSpecificDate(taskList.retrieveTasksByDate(dt));
    }
}
