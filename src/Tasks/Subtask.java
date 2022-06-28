package Tasks;

import java.util.Objects;

public class Subtask extends Task {
    protected int epicUid;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subtask subtask = (Subtask) o;
        return epicUid == subtask.epicUid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicUid);
    }

    @Override
    public String toString() {
        return "Tasks.Subtask{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uid=" + uid +
                ", status=" + status +
                '}';
    }
}
