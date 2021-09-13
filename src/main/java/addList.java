import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Integer;

public class addList extends Task{
    static String validateToDoTask (String task) throws DukeException{
        String[] toDoTask = task.split(" ");
        String result = "";
        if (toDoTask.length < 2){
            System.out.println("____________________________________________________________\n");
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        for (String word : toDoTask){
            result += word + " ";
        }
        return result.trim();
    }
    static boolean validateDoneTask (String doneInput, List<Task> taskList) throws DukeException {
       int taskIndex = Integer.parseInt(doneInput.substring(5));
       if(taskIndex <= taskList.size()){
           return true;
       }else{
           throw new DukeException("Unable to mark task as 'Done'. As task "+ taskIndex +" does not exist in Task List!");
       }
    }
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
        final String list = "list";
        final String done = "done";
        final String end = "bye";
        System.out.println(greeting);
            do {
                try {
                    input = sc.nextLine();
                    System.out.println(input);
                    if (input.equals(end)) break;

                    if (input.equals(list)) {
                        System.out.println("____________________________________________________________\n");
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println(i + 1 + ". " + taskList.get(i));
                        }
                        System.out.println("____________________________________________________________\n");
                    } else if (input.substring(0, 4).equals(done)) {
                        if (validateDoneTask(input, taskList)) {
                            int taskListIndex = Integer.parseInt(input.substring(5));
                            taskList.get(taskListIndex - 1).markAsDone();
                            System.out.println("____________________________________________________________\n");
                            System.out.println(" Nice! I've marked this task as done:");
                            System.out.println("   [" + taskList.get(taskListIndex - 1).getStatusIcon() + "] " + taskList.get(taskListIndex - 1).getTaskDescription());
                            System.out.println("____________________________________________________________\n");
                        }
                    } else if (input.startsWith("todo")) {
                        String task = validateToDoTask(input);
                        taskList.add(new Todo(task));
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + taskList.get(taskList.size() - 1)); // Get the latest added task from the taskList
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________\n");
                    } else if (input.startsWith("deadline")) {
                        String[] deadline = input.substring(9).split("/");
                        String deadlineDesc = deadline[0];
                        String deadlineDate = deadline[1].replaceAll("by", "").trim();

                        taskList.add(new Deadline(deadlineDesc, deadlineDate));
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + taskList.get(taskList.size() - 1)); // Get the latest added task from the taskList
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________\n");
                    } else if (input.startsWith("event")) {
                        String[] event = input.substring(6).split("/");
                        String eventDesc = event[0];
                        String eventDate = event[1].replaceAll("at", "").trim();

                        taskList.add(new Event(eventDesc, eventDate));
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + taskList.get(taskList.size() - 1)); // Get the latest added task from the taskList
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________\n");
                    } else {
                        System.out.println("____________________________________________________________");
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException ex) {
                    System.out.println("Caught Duke Exception!");
                    System.out.println("Exception occured: " + ex);
                }
            }while(!input.equals(end));

        System.out.println(byeGreeting);
    }

}
