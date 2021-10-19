package commands;

import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.Ui;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        ui.showGoodByeMessage();
        setExit(true);
    }
}
