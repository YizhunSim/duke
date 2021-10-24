package commands;

import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.Ui;

public class ExitCommand extends Command{
    public static final String COMMAND_WORD = CommandEnum.BYE.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        ui.showGoodByeMessage();
        setExit(true);
    }
}
