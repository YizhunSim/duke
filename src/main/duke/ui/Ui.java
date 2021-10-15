package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import data.Task;

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

    public void showWelcome(){
        out.println(DUKE_SPLASHSCREEN);
    }

    public void showLoadingError(){
        out.println(MESSAGE_INIT_FAILED);
    }

    public void showError(String errorMessage){
        out.println(errorMessage);
    }

    public void showInvalidInput(){
        out.println(INVALID_INPUT_USER);
    }

    public void showLine(){
        out.println(DIVIDER);
    }

    public String readCommand(){
        return in.nextLine();
    }

    public void printAllTasks(List<Task> taskList){
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    public void printTaskCount(int taskCount){
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    // Combines addToDo Print into one function
    public void printAddSingleTask(Task task, int taskListCount){
        showLine();
        out.println(" Got it. I've added this task:");
        out.println("   " + task);
        printTaskCount(taskListCount);
    }

    public void showGoodByeMessage(){
        out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n");
    }

    public void showDeletedTask(String deletedTask){
        out.println(deletedTask);
    }

    public void showMarkDoneTask(Task task){
        showLine();
        out.println(" Nice! I've marked this task as done:");
        out.println(task.toString());
    }

}
