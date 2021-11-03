package commands;

import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.Ui;

public abstract class Command {
    private boolean isExit;

    public Command(){
    }

    // Results String will be thrown to GUI as duke response
    public abstract String execute(TaskList task, Ui ui, Storage storage) throws DukeException;

    public boolean isExit(){
        return this.isExit;
    }

    public void setExit(boolean exit){
        this.isExit = exit;
    }

}
