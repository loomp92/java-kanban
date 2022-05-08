public class Subtask extends Task{
    int epicUid;

    public Subtask(String name, String description, int uid, Status status, int epicUid) {
        super(name, description, uid, status);
        this.epicUid = epicUid;
    }

    public int getEpicUid() {
        return epicUid;
    }

    public void setEpicUid(int epicUid) {
        this.epicUid = epicUid;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uid=" + uid +
                ", status=" + status +
                '}';
    }
}
