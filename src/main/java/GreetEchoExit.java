import java.util.Scanner;

public class GreetEchoExit {
    public static void main(String[] args) {
        String input = "";
        Scanner sc = new Scanner(System.in);
        String greeting = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
        String byeGreeting = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        String end = "bye";
        System.out.println(greeting);
        do{
            input = sc.nextLine();
            System.out.println("____________________________________________________________\n"
                    + " " + input + "\n"
                    + "____________________________________________________________\n");
        }while(!input.equals(end));
        System.out.println(byeGreeting);
    }
}
