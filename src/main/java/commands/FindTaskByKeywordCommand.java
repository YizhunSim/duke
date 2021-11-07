package commands;

import common.Messages;
import data.Task;
import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.Ui;

import java.util.*;

public class FindTaskByKeywordCommand extends Command{
    protected Set<String> keywords;
    public static final String COMMAND_WORD = CommandEnum.FIND.toString().toLowerCase();

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all deadline/events task based on a specific keyword. \n"
            + "Parameters: [KEYWORD] \n"
            + "Example: " + COMMAND_WORD + " proposal";

    public FindTaskByKeywordCommand(Set<String> keywords){
        this.keywords = keywords;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<Task> tasksFound = getTaskWithDescriptionContainingAnyKeyword(keywords, taskList);
        ui.foundTasksWithKeyword(taskList.getAllTaskListString(tasksFound), keywords);

        return Messages.foundTasksWithKeyword(taskList.getAllTaskListString(tasksFound), keywords);

    }

    private List<Task> getTaskWithDescriptionContainingAnyKeyword(Set<String> keywords, TaskList taskList){
        List<Task> tl = new ArrayList<>();
        for(Task task : taskList.getAllTask()){
            Set<String> wordsInTask = new HashSet<>(getWordsInTask(task));
            /*  Disjoint used to check whether two specified collections are disjoint.
                It returns true if the two specified collections have no elements in common.
                It returns false if the two specified collections **have elements in common**   */
            if(!Collections.disjoint(wordsInTask, keywords)){
                tl.add(task);
            }
        }
        return tl;
    }

    /**
     * Retrieves a listing of every word in the task, in order.
     */
    private List<String> getWordsInTask(Task task){
        return Arrays.asList(task.getTaskDescription().split(" "));
    }
}
