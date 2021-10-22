import java.io.IOException;

import commands.Command;
import parser.Parser;
import storage.Storage;
import ui.Ui;
import data.TaskList;
import data.exception.DukeException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            taskList = new TaskList(storage.load());
        }catch(DukeException e){
            ui.showLoadingError();
            taskList = new TaskList();
            ui.showError(e.getMessage());
        }
    }

    public void run(){
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("______")
                Command c = new Parser().parseCommand(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e){
                ui.showError(e.getMessage());
            }
            finally {
                ui.showLine();
            }
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
