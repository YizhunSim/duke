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
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showTasksOnSpecificDate(taskList.retrieveTasksByDate(dt));
    }
}
