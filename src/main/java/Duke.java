import java.io.IOException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.load());
        }catch(DukeException | IOException e){
            ui.showLoadingError();
            tasks = new TaskList();
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

//            } catch (DukeException e){
//                ui.showError(e.getMessage());
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
