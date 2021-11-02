package common;

import java.util.List;

public class Messages {
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!";

    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public static final String FAIL_TO_ADD_TASK = "Unable to add task. Please check for conflicts!";

    public static final String FAIL_TO_DELETE_TASK = "Unable to delete task. Please check if task exist";

    public static final String ADDED_TASK = "Got it. I've added this task:";

    public static final String MARK_TASK_DONE = "Nice! I've marked this task as done:";

    public static final String FAIL_TO_MARK_TASK = "Unable to mark task as done. Please check if task exist";

    public static final String FAIL_TO_FIND_TASK_BY_KEYWORD = "Unable to find task by keyword.";

    public static String getTask(String task){
        return task;
    }

    public static String getTaskCount(int totalTask){
        return "Now you have " + totalTask + " tasks in the list.";
    }

    public static String getAllTask(List<String> taskList){
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            result += (i + 1 + ". " + taskList.get(i));
        }
        return result;
    }

    public static String getAllCommands(List<String> commandList){
        String result = "";
        for (int i = 0; i < commandList.size(); i++) {
            result += (i + 1 + ". " + commandList.get(i));
        }
        return result;
    }

}
