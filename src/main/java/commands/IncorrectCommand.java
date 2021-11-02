package commands;

import common.Messages;
import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.Ui;

public class IncorrectCommand extends Command{

   private String errorMessage;

   public IncorrectCommand(String errorMsg){
       this.errorMessage = errorMsg;
   }

   @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeException {
       return Messages.MESSAGE_INVALID_COMMAND_FORMAT;

   }
}
