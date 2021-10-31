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
     * Retrieve Task Status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Method to mark task as Done for a single Task object
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
