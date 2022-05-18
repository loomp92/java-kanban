package Manager;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;
import java.util.Collection;
import java.util.HashMap;


interface TaskManager {

    int createNewTask(String name, String description, Status status);

    int createNewEpic(String name, String description);

    int createNewSubtask(int epicUid, String name, String description, Status status);

    void deleteAllTask();

    HashMap<Integer, Task> getAllTask();

    Task getTaskByID(int id);

    Task deleteTaskByID(int id);

    void deleteEpicByID(int id);

    void deleteSubtaskByID(int id);

    Task upDateTask(Task task);

    Epic upDateEpic(Epic epic);

    void upDateSubtask(Subtask subtask);

    Collection<Subtask> getSubtaskByEpic(int epicId);

    Task getHistory();

        
}
