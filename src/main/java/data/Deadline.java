package data;

import parser.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected String displayDateTime;

    public Deadline(String description, LocalDateTime by) {
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
        return "[D]" + super.toString() +" (by: " + getDisplayDateTime() + ")";
    }
}
