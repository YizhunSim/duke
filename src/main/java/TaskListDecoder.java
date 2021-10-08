import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskListDecoder {
    public static final Pattern TASK_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("((^[DE]) \\| ([0-1]) \\| (.*\\|) (.*))|((^[T]) \\| ([0-1]) \\| (.*))"); // variable number of tags

    public static List<Task> decodeTaskList(List<String> encodedTaskList) throws DukeException {
        final List<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList){
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return decodedTasks;
    }

    private static Task decodeTaskFromString(String encodedTask) throws DukeException {
        final Matcher matcher = TASK_DATA_ARGS_FORMAT.matcher(encodedTask);
        if (!matcher.matches()) {
            throw new DukeException("Encoded task in invalid format. Unable to decode");
        }
        Task newTask = null;
        String[] data = encodedTask.split("[|]");
        String typeOfTask = data[0].trim();
        String taskStatus = data[1].trim();
        String taskDescription = data[2].trim();
        // Can be put in a separate method
        if (typeOfTask.equals("T")) { // Todo Task
            newTask = new Todo(taskDescription);
            if (taskStatus.equals("1")) {
                newTask.markAsDone();
            }
        } else if (typeOfTask.equals("D")) {
            String deadlineDate = data[3].trim();
            newTask = new Deadline(taskDescription, deadlineDate);
            if (taskStatus.equals("1")) {
                newTask.markAsDone();
            }
        } else if (typeOfTask.equals("E")) {
            String eventDate = data[3].trim();
            newTask = new Deadline(taskDescription, eventDate);
            if (taskStatus.equals("1")) {
                newTask.markAsDone();
            }
        }
        return newTask;
    }
}
