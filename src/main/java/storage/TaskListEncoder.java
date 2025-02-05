package storage;

import data.Task;
import data.Event;
import data.Deadline;
import data.Todo;
import parser.Parser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Encodes the {@code TaskList} object into a data file [tasks.txt] for storage.
 */
public class TaskListEncoder {

    /**
     * Encodes all the {@code Task} in the {@code toSave} into a list of decodable and readable string presentation
     * for storage.
     */
    public static void encodeTaskList(List<Task> toSave, String pathOfFileToSave) throws IOException{
        final List<String> encodedTaskList = new ArrayList<>();
        FileWriter fw = new FileWriter(pathOfFileToSave, false);
        BufferedWriter bw = new BufferedWriter(fw);
        for(Task individualTask : toSave){
            encodedTaskList.add(encodeTaskToString(individualTask));
        }
        for(String singleTask : encodedTaskList){
            bw.write(singleTask);
            bw.newLine();
        }
        bw.close();
    }
    /**
     * Encodes a single {@code Task} in the {@code toSave} into a decodable and readable string presentation
     * for storage.
     */
    public static void encodeTask(Task toSave, String pathOfFileToSave) throws IOException {
        try {
            String encodedTask = encodeTaskToString(toSave);
            FileWriter fw = new FileWriter(pathOfFileToSave, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(  encodedTask);
            bw.newLine();
            bw.close();
        } catch (Exception e){
            e.getMessage();
        }

    }

    /**
     * Encodes the {@code task} into a decodable and readable string representation
     * @param task
     * @return the encoded task in String to be store back into the text file
     */
    private static String encodeTaskToString(Task task){
        assert task != null : "[encodeTaskToString]: Task should not be null";
        StringBuilder encodedTaskBuilder = new StringBuilder();
        if (task instanceof Todo){
            encodedTaskBuilder.append(TaskListEnum.T);
            encodedTaskBuilder = appendEncodedTask(encodedTaskBuilder, task);
        }
        else if (task instanceof Deadline){
            encodedTaskBuilder.append(TaskListEnum.D);
            encodedTaskBuilder = appendEncodedTask(encodedTaskBuilder, task);
            Deadline d = (Deadline)task;
            encodedTaskBuilder.append(" | ");
            encodedTaskBuilder.append(Parser.parseDateForStorage(d.getBy()));
        }
        else if (task instanceof Event){
            encodedTaskBuilder.append(TaskListEnum.E);
            encodedTaskBuilder = appendEncodedTask(encodedTaskBuilder, task);
            Event e = (Event)task;
            encodedTaskBuilder.append(" | ");
            encodedTaskBuilder.append(Parser.parseDateForStorage(e.getAt()));
        }
        return encodedTaskBuilder.toString();
    }

    /**
     * Helper method that construct the String for the encoded Task in the text field
     * @param sb StringBuilder
     * @param task Task
     * @return Partial Construction of the task in the specified txt file
     */
    private static StringBuilder appendEncodedTask(StringBuilder sb, Task task){
        assert task != null : "[appendEncodedTask] Task should not be null";
        sb.append(" | ");
        sb.append(task.getIsDone() ? "1" : 0);
        sb.append(" | ");
        sb.append(task.getTaskDescription());

        return sb;
    }


}
