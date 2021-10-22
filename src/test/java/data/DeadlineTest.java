package data;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm a");
    @Test
    public void deadlineConstructor_withDescription_success(){
        LocalDateTime ldt = LocalDateTime.of(2021,10,10,10,10);
        Deadline d = new Deadline("deadline description", ldt);
        assertEquals("deadline description", d.getTaskDescription());
        assertEquals(ldt, d.getBy());
    }

    @Test
    public void getDisplayDateTime_validDeadline_success(){
        LocalDateTime ldt = LocalDateTime.of(2021,10,10,10,10);
        Deadline e = new Deadline("deadline description", ldt);

        assertEquals("Oct 10 2021 1010 AM", e.getDisplayDateTime());
    }

    @Test
    public void getBy_validDeadline_success(){
        LocalDateTime ldt = LocalDateTime.of(2021,10,10,10,10);
        Deadline e = new Deadline("deadline description", ldt);
        assertEquals(LocalDateTime.parse("Oct 10 2021 1010 AM", formatter), e.getBy());
    }

    @Test
    public void toString_validDeadline_success(){
        LocalDateTime ldt = LocalDateTime.of(2021,10,10,10,10);
        Deadline e = new Deadline("deadline description", ldt);
        assertEquals("[D][ ] deadline description (by: Oct 10 2021 1010 AM)", e.toString());
    }
}
