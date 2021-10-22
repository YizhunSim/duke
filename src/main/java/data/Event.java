package data;

import parser.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime at;
    protected String displayDateTime;

    public Event(String description, LocalDateTime by) {
        super(description);
        this.at = by;

    }

    public String getDisplayDateTime() {
        return this.displayDateTime = Parser.parseDateForDisplay(at);
    }

    public LocalDateTime getAt(){
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +" (at: " + getDisplayDateTime() + ")";
    }
}
