package parser;

import commands.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseDoListCommand_validListCommand_success(){
        Parser p = new Parser();
        Command c = p.parseCommand("list");
        assertEquals(ListCommand.class, c.getClass());
    }

    @Test
    public void parseDoAddToDoCommand_validAddToDoCommand_success(){
        Parser p = new Parser();
        Command c = p.parseCommand("todo run 12km");
        assertEquals(AddToDoCommand.class, c.getClass());
    }

    @Test
    public void parseDoAddDeadlineCommand_validAddDeadlineCommand_success(){
        Parser p = new Parser();
        Command c = p.parseCommand("deadline run 130km /by 31/10/2021 2359");
        assertEquals(AddDeadlineCommand.class, c.getClass());
    }

    @Test
    public void parseDoAddEventCommand_validAddEventCommand_success(){
        Parser p = new Parser();
        Command c = p.parseCommand("event Investment Summit /at 10/11/2021 1000");
        assertEquals(AddEventCommand.class, c.getClass());
    }

    @Test
    public void parseDoDoneTaskCommand_validDoneTaskCommand_success(){
        Parser p = new Parser();
        Command c = p.parseCommand("done 1");
        assertEquals(DoneTaskCommand.class, c.getClass());
    }

    @Test
    public void parseDoDeleteTaskCommand_validDeleteTaskCommand_success(){
        Parser p = new Parser();
        Command c = p.parseCommand("delete 1");
        assertEquals(DeleteTaskCommand.class, c.getClass());
    }

    @Test
    public void parseDoFindTaskBySpecificDateCommand_validFindTaskBySpecificDateCommand_success(){
        Parser p = new Parser();
        Command c = p.parseCommand("search 10/10/2021");
        assertEquals(SearchTasksByDateCommand.class, c.getClass());
    }

    @Test
    public void parseStringFindDateFromText_validStringDateParameter_success(){
        LocalDate ld = LocalDate.of(2021,10,20);
        String dateInString = "20/10/2021";
        assertEquals(ld, Parser.parseStringFindDateFromText(dateInString));
    }

    @Test
    public void parseStringDateFromText_validStringDateTimeParameter_success(){
        LocalDateTime ldt = LocalDateTime.of(2021, 10, 21, 8, 0);
        String dateInString = "21/10/2021 0800";
        assertEquals(ldt, Parser.parseStringDateTimeFromText(dateInString));
    }

    @Test
    public void parseDateForDisplay_validLocalDateTimeParameter_success(){
        LocalDateTime ldt = LocalDateTime.of(2021, 10, 21, 8, 0);
        assertEquals("Oct 21 2021 0800 AM", Parser.parseDateForDisplay(ldt));
    }

    @Test
    public void parseDateForStorage_validLocalDateTimeParameter_success(){
        LocalDateTime ldt = LocalDateTime.of(2021, 10, 15, 6, 0);
        assertEquals("15/10/2021 0600", Parser.parseDateForStorage(ldt));
    }
}
