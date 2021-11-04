package storage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.Deadline;
import data.Event;
import data.Todo;

import data.Task;
import data.exception.StorageOperationException;
import parser.Parser;

/**
 * Decodes the storage file into an {@code TaskList} object.
 */
public class TaskListDecoder {
    public static final Pattern TASK_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("((^[DE]) \\| ([0-1]) \\| (.*\\|) (.*))|((^[T]) \\| ([0-1]) \\| (.*))");

    /**
     * Decodes {@code encodedTaskList} into an {@code List<Task>} containing the decoded tasks.
     *
     * @throws StorageOperationException if the {@code encodedAddressBook} is in an invalid format.
     */
    public static List<Task> decodeTaskList(List<String> encodedTaskList) throws StorageOperationException {
        final List<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList){
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return decodedTasks;
    }

    /**
     * Decodes {@code encodedTask} into an {@code Task} containing the a single decoded tasks.
     *
     * @throws StorageOperationException if the {@code encodedTask} is in an invalid format.
     */
    private static Task decodeTaskFromString(String encodedTask) throws StorageOperationException {
        final Matcher matcher = TASK_DATA_ARGS_FORMAT.matcher(encodedTask);
        if (!matcher.matches()) {
            throw new StorageOperationException("Encoded task in invalid format. Unable to decode");
        }
        Task newTask = null;
        String[] data = encodedTask.split("[|]");
        String typeOfTask = data[0].trim();
        String taskStatus = data[1].trim();
        String taskDescription = data[2].trim();
        // Can be put in a separate method
        if (typeOfTask.equals(TaskListEnum.T.toString())) { // Todo Task
            newTask = new Todo(taskDescription);
            if (taskStatus.equals("1")) {
                newTask.markAsDone();
            }
        } else if (typeOfTask.equals(TaskListEnum.D.toString())) { //Deadline Task
            String deadlineDate = data[3].trim();
            newTask = new Deadline(taskDescription, Parser.parseStringDateTimeFromText(deadlineDate));
            if (taskStatus.equals("1")) {
                newTask.markAsDone();
            }
        } else if (typeOfTask.equals(TaskListEnum.E.toString())) { //Event Task
            String eventDate = data[3].trim();
            newTask = new Event(taskDescription, Parser.parseStringDateTimeFromText(eventDate));
            if (taskStatus.equals("1")) {
                newTask.markAsDone();
            }
        }
        return newTask;
    }
}
