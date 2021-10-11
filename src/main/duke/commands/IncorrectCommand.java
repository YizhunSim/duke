package commands;

import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.Ui;

public class IncorrectCommand extends Command{

   private String erroMessage;

   public IncorrectCommand(String errorMsg){
       this.erroMessage = errorMsg;
   }

   @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
       throw new DukeException(erroMessage);
    }
}
