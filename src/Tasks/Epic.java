package Tasks;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

public class Epic extends Task {

    private final HashMap<Integer, Subtask> subtasks;

    public Epic(String name, String description, int uid) {
        super(name, description, uid, Status.NEW);
        this.subtasks = new HashMap<>();
    }

    @Override
    public Status getStatus() {
        if (subtasks.size() == 0) {
            return Status.NEW;
        }

        boolean newTaskExists = subtasks.values().stream().anyMatch(subtask -> subtask.status == Status.NEW);
        boolean doneTaskExists = subtasks.values().stream().anyMatch(subtask -> subtask.status == Status.DONE);
        boolean inProgressTaskExists = subtasks.values().stream().anyMatch(subtask -> subtask.status == Status.IN_PROGRESS);

        if (!doneTaskExists && !inProgressTaskExists) {
            return Status.NEW;
        }
        if (!newTaskExists && !inProgressTaskExists) {
            return Status.DONE;
        }
        return Status.IN_PROGRESS;
    }

    public void addOrUpdateSubtask(Subtask subtask) {
        subtasks.put(subtask.getUid(), subtask);
    }

    public Collection<Integer> getSubtaskKeys() {
        return subtasks.keySet();
    }

    public Collection<Subtask> getSubtasks() {
        return subtasks.values();
    }

    public void deleteSubtask(int subtaskId) {
        subtasks.remove(subtaskId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtasks, epic.subtasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subtasks);
    }

    @Override
    public String toString() {
        return "Tasks.Epic{" +
                "subtasks=" + subtasks +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uid=" + uid +
                ", status=" + getStatus() +
                '}';
    }
}
