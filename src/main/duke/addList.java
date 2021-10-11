import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Integer;

import data.*;
import data.exception.DukeException;


public class addList extends Task{
    static String validateToDoTask (String task) throws DukeException{
        String[] toDoTask = task.split(" ");
        StringBuilder result = new StringBuilder();
        if (toDoTask.length < 2){
            System.out.println("____________________________________________________________\n");
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        for (String word : toDoTask){
            result.append(word).append(" ");
        }
        return result.substring(5).trim();
    }
    static int validateTaskAction (String action, List<Task> taskList) throws DukeException {
       int taskIndex = -1;

        if (action.startsWith(TaskAction.DONE.toString().toLowerCase()) || action.startsWith(TaskAction.DONE.toString())) {
            taskIndex = Integer.parseInt(action.substring(5));
        } else if (action.startsWith(TaskAction.DELETE.toString().toLowerCase()) || action.startsWith(TaskAction.DELETE.toString())) {
            taskIndex = Integer.parseInt(action.substring(7));
        }
       for (int i = 0; i < taskList.size(); i++){
           if (taskIndex == i) {
               return taskIndex;
           }
       }
       throw new DukeException("Invalid Task Action. Task "+ taskIndex +" does not exist in Task List!");

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

        System.out.println(greeting);
            do {
                try {
                    input = sc.nextLine();
                    System.out.println(input);
                    if (input.startsWith(TaskAction.BYE.toString()) || input.startsWith(TaskAction.BYE.toString().toLowerCase())){
                        break;
                    }

                    if (input.startsWith(TaskAction.LIST.toString()) || input.startsWith(TaskAction.LIST.toString().toLowerCase())) {
                        System.out.println("____________________________________________________________\n");
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println(i + 1 + ". " + taskList.get(i));
                        }
                        System.out.println("____________________________________________________________\n");
                    } else if (input.startsWith(TaskAction.DONE.toString()) || input.startsWith(TaskAction.DONE.toString().toLowerCase())) {
                        int taskListIndex = validateTaskAction(input, taskList);
                        taskList.get(taskListIndex - 1).markAsDone();
                        System.out.println("____________________________________________________________\n");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   [" + taskList.get(taskListIndex - 1).getStatusIcon() + "] " + taskList.get(taskListIndex - 1).getTaskDescription());
                        System.out.println("____________________________________________________________\n");
                    } else if (input.startsWith(TaskAction.TODO.toString()) || input.startsWith(TaskAction.TODO.toString().toLowerCase())) {
                        String task = validateToDoTask(input);
                        taskList.add(new Todo(task));
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + taskList.get(taskList.size() - 1)); // Get the latest added task from the taskList
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________\n");
                    } else if (input.startsWith(TaskAction.DEADLINE.toString()) || input.startsWith(TaskAction.DEADLINE.toString().toLowerCase())) {
                        String[] deadline = input.substring(9).split("/");
                        String deadlineDesc = deadline[0];
                        String deadlineDate = deadline[1].replaceAll("by", "").trim();

                        taskList.add(new Deadline(deadlineDesc, deadlineDate));
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + taskList.get(taskList.size() - 1)); // Get the latest added task from the taskList
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________\n");
                    } else if (input.startsWith(TaskAction.EVENT.toString()) || input.startsWith(TaskAction.EVENT.toString().toLowerCase())) {
                        String[] event = input.substring(6).split("/");
                        String eventDesc = event[0];
                        String eventDate = event[1].replaceAll("at", "").trim();

                        taskList.add(new Event(eventDesc, eventDate));
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + taskList.get(taskList.size() - 1)); // Get the latest added task from the taskList
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________\n");
                    }
                    else if (input.startsWith(TaskAction.DELETE.toString()) || input.startsWith(TaskAction.DELETE.toString().toLowerCase())){
                        int taskListIndex = validateTaskAction(input, taskList);

                        System.out.println("____________________________________________________________\n");
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println("   " + taskList.get(taskListIndex - 1));
                        //System.out.println("   [" + taskList.get(taskListIndex - 1).getStatusIcon() + "] " + taskList.get(taskListIndex - 1).getTaskDescription());
                        taskList.remove(taskListIndex -1);

                        System.out.println(" Now you have " + taskList.size() +" tasks in the list.");
                        System.out.println("____________________________________________________________\n");
                    }
                    else {
                        System.out.println("____________________________________________________________");
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException ex) {
                    System.out.println("Caught Duke Exception!");
                    System.out.println("Exception occurred: " + ex);
                }
            }while(!input.equals(TaskAction.BYE.toString()) || !input.equals(TaskAction.BYE.toString().toUpperCase()));

        System.out.println(byeGreeting);
    }

}
