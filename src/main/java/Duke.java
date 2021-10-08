public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.load());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        ui.showWelcome();

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
