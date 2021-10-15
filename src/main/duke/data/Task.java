package data;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task (){

    }

    public Task (String description){
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getTaskDescription(){
        return this.description;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    @Override
    public String toString() { return  "["+ getStatusIcon() + "] " + getTaskDescription(); }
}
