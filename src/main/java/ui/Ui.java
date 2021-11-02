package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import data.Task;

/**
 * Text UI of the application.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String DUKE_SPLASHSCREEN =
                    DIVIDER
                    + "\n" +
                      " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n\n"
                    + "Hello! I'm Duke\n"
                    + "What can I do for you?\n"
                    + DIVIDER;
    private final Scanner in;
    private final PrintStream out;
    public static final String MESSAGE_INIT_FAILED = "Failed to initialise duke application. Exiting...";
    public static final String INVALID_INPUT_USER = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Generates and prints the welcome duke message upon the start of the application.
     *
     */
    public void showWelcome(){
        out.println(DUKE_SPLASHSCREEN);
    }

    /**
     * Shows error message upon failure to load Duke Application
     *
     */
    public void showLoadingError(){
        out.println(MESSAGE_INIT_FAILED);
    }

    /**
     * Shows error message fed in
     *
     * @param errorMessage Error Message to be shown to the user
     */
    public void showError(String errorMessage){
        out.println(errorMessage);
    }

    public void showInvalidInput(){
        out.println(INVALID_INPUT_USER);
    }

    /**
     * Shows Divider Line
     *
     */
    public void showLine(){
        out.println(DIVIDER);
    }

    public String readCommand(){
        out.print("Enter command: ");
        return in.nextLine();
    }

    /**
     * Shows all the Task in the TaskList
     *
     */
    public void printAllTasks(List<String> taskList){
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    /**
     * Shows all the Total Task Count in the TaskList
     *
     */
    public void printTaskCount(int taskCount){
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Shows the Task, together with the taskList total count once it is added
     *
     */
    public void printAddSingleTask(String task, int taskListCount){
        showLine();
        out.println(" Got it. I've added this task:");
        out.println("   " + task);
        printTaskCount(taskListCount);
    }


    /**
     * Shows goodbye message
     *
     */
    public void showGoodByeMessage(){
        out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n");
    }

    /**
     * Shows Deleted Task
     *
     * @param deletedTask Task that will be deleted
     */
    public void showDeletedTask(String deletedTask){
        out.println(deletedTask);
    }

    /**
     * Shows Tasks once it is mark done
     *
     * @param task Task that will be mark done
     */
    public void showMarkDoneTask(String task){
        showLine();
        out.println(" Nice! I've marked this task as done:");
        out.println(task);
    }

    /**
     * Shows All the Tasks group by the same date
     *
     * @param TasksOfSameDates TaskList With similar date
     */
    public void showTasksOnSpecificDate(List<String> TasksOfSameDates){
        showLine();
        for(String t: TasksOfSameDates){
            out.println(t);
        }
    }

    /**
     * Shows all available help commands with its message_usage
     *
     */
    public void showCommands(List<String> commandList){
        for (int i = 0; i < commandList.size(); i++) {
            System.out.println(i + 1 + ". " + commandList.get(i));
        }
    }

    /**
     * Shows Message
     *
     * @param message to be shown to user
     */
    public void showMessage(String message){
        out.println(message);
    }

}
