package commands;

import common.Messages;
import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.Ui;

public class InvalidCommand extends Command{
    private String errorMessage;

    public InvalidCommand(String errorMsg){
        this.errorMessage = errorMsg;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        try{
            return errorMessage + "\n" + Messages.INVALID_INPUT_USER;
        }finally {
            return ui.showInvalidInput(errorMessage);
        }
    }
}
