import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

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

    public void showLine(){
        out.println(DIVIDER);
    }

    public String readCommand(){
        return in.nextLine();
    }

}
