public class Duke {
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath){
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.load());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){

    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        new Duke("data/tasks.txt").run();
    }

}
