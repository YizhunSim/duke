package data;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm a");
    @Test
    public void eventConstructor_withDescriptionAndBy_success(){
        LocalDateTime ldt = LocalDateTime.of(2021,10,10,10,10);
        Event e = new Event("event description", ldt);
        assertEquals("event description", e.getTaskDescription());
        assertEquals(ldt, e.getAt());
    }

    @Test
    public void getDisplayDateTime_validEvent_success(){
        LocalDateTime ldt = LocalDateTime.of(2021,10,10,10,10);
        Event e = new Event("event description", ldt);
        assertEquals("Oct 10 2021 1010 AM", e.getDisplayDateTime());
    }

    @Test
    public void getAt_validEvent_success(){
        LocalDateTime ldt = LocalDateTime.of(2021,10,10,10,10);
        Event e = new Event("event description", ldt);
        assertEquals(LocalDateTime.parse("Oct 10 2021 1010 AM", formatter), e.getAt());
    }

    @Test
    public void toString_validEvent_success(){
        LocalDateTime ldt = LocalDateTime.of(2021,10,10,10,10);
        Event e = new Event("event description", ldt);
        assertEquals("[E][ ] event description (at: Oct 10 2021 1010 AM)", e.toString());
    }
}
