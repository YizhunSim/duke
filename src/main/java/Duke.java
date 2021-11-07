import commands.Command;
import data.exception.StorageOperationException;
import parser.Parser;
import storage.Storage;
import ui.Ui;
import data.TaskList;
import data.exception.DukeException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private boolean isExit = false;

    public Duke(String filePath){
        try{
            ui = new Ui();
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        }catch(StorageOperationException e){
            ui.showLoadingError();
            taskList = new TaskList();
            ui.showError(e.getMessage());
            isExit = true;
        }
    }

    public void run(){
        while (!isExit) {
            try {
                ui.showWelcome();
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getDukeResponse(String input) {
        try {
            Command c = new Parser().parseCommand(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e){
            return e.getMessage();
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
