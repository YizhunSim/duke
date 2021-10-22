package data.exception;

public class TaskNotFoundException extends DukeException{
    public TaskNotFoundException(int taskId) {
        super("Task id provided: " + taskId + " does not exist.");
    }
}
