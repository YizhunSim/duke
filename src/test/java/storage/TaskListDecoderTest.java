package storage;

import data.exception.StorageOperationException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListDecoderTest {
    @Test
    public void decodeTaskList_validEncodedTaskList_success() throws StorageOperationException {
        List<String> encodedTaskList = new ArrayList<>();
        encodedTaskList.add("D | 0 | deadline description | 10/10/2021 1010");
        encodedTaskList.add("E | 0 | event description | 10/10/2021 1010");
        encodedTaskList.add("T | 0 | todo description");

        assertEquals(3,TaskListDecoder.decodeTaskList(encodedTaskList).size());
    }

}
