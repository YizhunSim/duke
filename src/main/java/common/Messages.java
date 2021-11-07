package common;

import java.util.List;

public class Messages {
    public static final String DIVIDER = "_______________________________________________________";
    public static final String DUKE_SPLASHSCREEN =
                    DIVIDER
                    + "\n"
                    + "\t ____        _        \n"
                    + "\t|  _ \\ _   _| | _____ \n"
                    + "\t| | | | | | | |/ / _ \\\n"
                    + "\t| |_| | |_| |   <  __/\n"
                    + "\t|____/ \\__,_|_|\\_\\___|\n\n"
                    + "\tGreetings... I'm Duke\n"
                    + "\tWhat can I do for you?\n"
                    + DIVIDER;

    public static final String INVALID_INPUT_USER = "Speak in Duke minion! For I am unable to comprehend what that means.";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!";

    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public static final String FAIL_TO_ADD_TASK = "Unable to add task. Please check for duplicate/conflicts!";

    public static final String FAIL_TO_DELETE_TASK = "Unable to delete task. Please check if task exist";

    public static final String ADDED_TASK = "Got it. I've added this task:";

    public static final String DELETED_TASK = "Got it. I've removed this task:";

    public static final String MARK_TASK_DONE = "Nice! I've marked this task as done:";

    public static final String TASK_DONE_EXIST = "Task is already marked as completed!";

    public static final String UNMARK_TASK_DONE = "Dohhh! I am not done with this task:";

    public static final String TASK_UNDONE_EXIST = "Task is already marked as incomplete!";

    public static final String UNSPECIFIED_TASK_TO_MARK_DONE_UNDONE = "Unspecified Task! Please include in the task index you wish to mark as done/undone";

    public static final String FAIL_TO_MARK_TASK = "Unable to mark task as done. Please check if task exist";

    public static final String FAIL_TO_UMMARK_TASK = "Unable to mark task as undone. Please check if task exist";

    public static final String TASK_EXIST = "The same Task already exist. [Duplicates]";

    public static final String UNABLE_TO_FIND_TASK_BY_KEYWORD = "Unable to find task by keyword.";

    public static String getTask(String task){
        return task;
    }

    public static String getTaskCount(int totalTask){
        return "Now you have " + totalTask + " tasks in the list.";
    }

    public static String getAllTask(List<String> taskList){
        String resultTaskCount = "You have " + taskList.size() + " tasks in the list.\n";
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            result += i + 1 + ". " + taskList.get(i) +"\n";
        }
        return resultTaskCount + result.trim();
    }

    public static String getAllCommands(List<String> commandList){
        String result = "";
        for (int i = 0; i < commandList.size(); i++) {
            result += (i + 1 + ". " + commandList.get(i) +"\n");
        }
        return result.trim();
    }

}
