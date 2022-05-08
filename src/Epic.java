import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Subtask> subtasks;

    public Epic(String name, String description, int uid, Status status) {
        super(name, description, uid, status);
        this.subtasks = new ArrayList<>();
    }

    @Override
    public Status getStatus() {
        if (subtasks.size() == 0) {
            return Status.NEW;
        }

        boolean newTaskExists = subtasks.stream().anyMatch(subtask -> subtask.status == Status.NEW);
        boolean doneTaskExists = subtasks.stream().anyMatch(subtask -> subtask.status == Status.DONE);
        boolean inProgressTaskExists = subtasks.stream().anyMatch(subtask -> subtask.status == Status.IN_PROGRESS);

        if (!doneTaskExists && !inProgressTaskExists) {
            return Status.NEW;
        }
        if (!newTaskExists && !inProgressTaskExists) {
            return Status.DONE;
        }
        return Status.IN_PROGRESS;
    }
    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

//    public Subtask revomeSubtask(Epic epic){
//        return epic.subtasks.get(epic.uid);
//    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }


    @Override
    public String toString() {
        return "Epic{" +
                "subtasks=" + subtasks +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uid=" + uid +
                ", status=" + status +
                '}';
    }



    //    public void addSubTask(String name, String description, int uid, Status status) {
//        Subtask subtask = new Subtask(name, description, uid, status);
//        subtasks.add(subtask);
//    }
}
