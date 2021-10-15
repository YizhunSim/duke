package data;

import parser.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime by;
    protected String displayDateTime;

    public Event(String description, LocalDateTime by) {
        super(description);
        this.by = by;

    }

    public String getDisplayDateTime() {
        return this.displayDateTime = Parser.parseDateForDisplay(by);
    }

    public LocalDateTime getBy(){
        return this.by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +" (at: " + getDisplayDateTime() + ")";
    }
}
