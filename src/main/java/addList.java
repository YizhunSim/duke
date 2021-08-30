import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Integer;

public class addList extends Task{
    public static void main(String[] args) {
        String input = "";
        List<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String greeting = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
        String byeGreeting = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        String list = "list";
        String done = "done";
        String end = "bye";
        System.out.println(greeting);
        do{
            input = sc.nextLine();
            System.out.println(input);
            if (input.equals(end)) break;
            if (input.equals(list)){
                System.out.println("____________________________________________________________\n");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 1; i <= taskList.size(); i++){
                    System.out.println(i + ". [" + taskList.get(i-1).getStatusIcon() + "] " + taskList.get(i-1).getTaskDescription());
                }
                System.out.println("____________________________________________________________\n");
            }else if (input.substring(0,4).equals(done) && Integer.parseInt(input.substring(5)) <= taskList.size()){
               int taskListIndex = Integer.parseInt(input.substring(5));
               taskList.get(taskListIndex-1).markAsDone();
               System.out.println("____________________________________________________________\n");
               System.out.println(" Nice! I've marked this task as done:");
               System.out.println("   [" + taskList.get(taskListIndex-1).getStatusIcon() + "] " + taskList.get(taskListIndex-1).getTaskDescription());
               System.out.println("____________________________________________________________\n");
            }else{
                Task t = new Task(input);
                taskList.add(t);
            }
            //System.out.println("____________________________________________________________\n");
        }while(!input.equals(end));
        System.out.println(byeGreeting);
    }
}
