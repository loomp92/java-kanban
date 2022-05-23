package Manager;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface TaskManager {

    int createNewTask(String name, String description, Status status);

    int createNewEpic(String name, String description);

    int createNewSubtask(int epicUid, String name, String description, Status status);

    void deleteAllTask();

    Map<Integer, Task> getAllTask();

    Task getTaskByID(int id);

    Epic getEpicByID(int id);

    Subtask getSubTaskByID(int id);

    Task deleteTaskByID(int id);

    void deleteEpicByID(int id);

    void deleteSubtaskByID(int id);

    Task upDateTask(Task task);

    Epic upDateEpic(Epic epic);

    void upDateSubtask(Subtask subtask);

    Collection<Subtask> getSubtaskByEpic(int epicId);

    List<Task> getHistory();

}
