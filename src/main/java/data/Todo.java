package data;

public class Todo extends Task {
    protected String description;

    public Todo (String description) {
      super(description);
      this.description = description;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
