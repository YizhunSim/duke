package storage;

import data.*;
import data.exception.DukeException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Encodes the {@code TaskList} object into a data file for storage.
 */
public class TaskListEncoder {

    /**
     * Encodes all the {@code Task} in the {@code toSave} into a list of decodable and readable string presentation
     * for storage.
     */
    public static void encodeTaskList(Task toSave, String pathOfFileToSave) throws IOException {
        try {
            String encodedTask = encodeTaskToString(toSave);
            FileWriter fw = new FileWriter(pathOfFileToSave, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(encodedTask);
            bw.newLine();
            bw.close();
        } catch (Exception e){
            e.getMessage();
        }

    }

    /**
     * Encodes the {@code task} into a decodable and readable string representation
     * @param task
     * @return
     */
    private static String encodeTaskToString(Task task){
        StringBuilder encodedTaskBuilder = new StringBuilder();
        if (task instanceof Todo){
            encodedTaskBuilder.append(TaskListEnum.T);
            encodedTaskBuilder = appendEncodedTask(encodedTaskBuilder, task);
        }
        else if (task instanceof Deadline){
            encodedTaskBuilder.append(TaskListEnum.D);
            encodedTaskBuilder = appendEncodedTask(encodedTaskBuilder, task);
        }
        else if (task instanceof Event){
            encodedTaskBuilder.append(TaskListEnum.E);
            encodedTaskBuilder = appendEncodedTask(encodedTaskBuilder, task);
        }
        return encodedTaskBuilder.toString();
    }

    private static StringBuilder appendEncodedTask(StringBuilder sb, Task task){
        sb.append(" | ");
        sb.append(task.getStatusIcon().equals("x") ? "1" : 0);
        sb.append(" | ");
        sb.append(task.getTaskDescription());
        return sb;
    }

//    private static String encodeTaskStatusToString(String taskStatus){
//        if (taskStatus.equals("X")){
//            return "1";
//        }else{
//            return "0";
//        }
//    }

}
