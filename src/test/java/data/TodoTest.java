package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoConstructor_withDescription_success(){
        Todo t = new Todo("todo description");
        assertEquals("todo description", t.getTaskDescription());
    }

    @Test
    public void toString_validToDo_success(){
        Todo t = new Todo("write junit testcase");
        assertEquals("[T][ ] write junit testcase", t.toString());
    }
}
