package Tasks;

public class Task {
    protected String name;
    protected String description;
    protected int uid;
    protected Status status;


    public Task(String name, String description, int uid, Status status) {
        this.name = name;
        this.description = description;
        this.uid = uid;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tasks.Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uid=" + uid +
                ", status=" + status +
                '}';
    }
}
