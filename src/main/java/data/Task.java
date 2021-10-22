package data;

/**
 * Represents a Task in the Duke Chat Box.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task (){

    }

    public Task (String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Used for display of Task Status Icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Used to mark isDone attribute for a single Task object
     */
    public void markAsDone(){
        this.isDone = true;
    }

    public String getTaskDescription(){
        return this.description;
    }

    /**
     * Getter method to release task isDone status
     */
    public boolean getIsDone(){
        return this.isDone;
    }

    @Override
    public String toString() { return  "["+ getStatusIcon() + "] " + getTaskDescription(); }
}
