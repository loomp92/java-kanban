package Manager;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;

import java.util.Collection;
import java.util.HashMap;

public class TaskManager {

    HashMap<Integer, Task> taskHashMap = new HashMap<>();
    HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();
    private static int taskID = 0;

    public int createNewTask(String name, String description, Status status) {
        var uid = ++taskID;
        Task task = new Task(name, description, uid, status);
        taskHashMap.put(uid, task);
        return uid;
    }

    public int createNewEpic(String name, String description) {
        var uid = ++taskID;
        Epic epic = new Epic(name, description, uid);
        epicHashMap.put(uid, epic);
        return uid;
    }

    public int createNewSubtask(int epicUid, String name, String description, Status status) {
        Epic epic = epicHashMap.getOrDefault(epicUid, null);
        if (epic == null) {
            return 0;
        }

        var uid = ++taskID;
        Subtask subtask = new Subtask(name, description, uid, status, epicUid);
        epic.addOrUpdateSubtask(subtask);
        subtaskHashMap.put(uid, subtask);

        return uid;
    }

    public void deleteAllTask() {
        taskHashMap.clear();
        epicHashMap.clear();
        subtaskHashMap.clear();
    }

    public HashMap<Integer, Task> getAllTask() {
        HashMap<Integer, Task> allTaskHashMaps = new HashMap<>();
        allTaskHashMaps.putAll(taskHashMap);
        allTaskHashMaps.putAll(epicHashMap);
        return allTaskHashMaps;
    }

    public Task getTaskByID(int id) {
        return taskHashMap.getOrDefault(id, null);
    }

    public Task deleteTaskByID(int id) { //TODO сделать еще для 2х мап.
        Task task = taskHashMap.getOrDefault(id, null);
        if (task == null) {
            return null;
        }
        return taskHashMap.remove(id);
    }

    public void deleteEpicByID(int id) {
        Task task = epicHashMap.getOrDefault(id, null);
        if (task == null) {
            return;
        }
        Epic epic = epicHashMap.get(id);
        subtaskHashMap.keySet().removeAll(epic.getSubtaskKeys());
        epicHashMap.remove(id);
    }

    public void deleteSubtaskByID(int id) {

        epicHashMap.get(subtaskHashMap.get(id).getEpicUid()).deleteSubtask(id);
        subtaskHashMap.remove(id);
    }

    public Task upDateTask(Task task) {
        if (!(taskHashMap.containsKey(task.getUid()))) {
            return null;
        }
        return taskHashMap.replace(task.getUid(), task);
    }

    public Epic upDateEpic(Epic epic) {
        if (!(epicHashMap.containsKey(epic.getUid()))) {
            return null;
        }
        return epicHashMap.replace(epic.getUid(), epic);
    }

    public void upDateSubtask(Subtask subtask) {
        if (!(subtaskHashMap.containsKey(subtask.getUid()))) {
            System.out.println("Сабтаск не сцществует");
            return;
        }
        epicHashMap.get(subtask.getEpicUid()).addOrUpdateSubtask(subtask);
        subtaskHashMap.put(subtask.getEpicUid(), subtask);
    }

    public Collection<Subtask> getSubtaskByEpic(int epicId) {
        Epic searchSubtask = epicHashMap.getOrDefault(epicId, null);
        if (searchSubtask == null) {
            return null;
        }
        return epicHashMap.get(epicId).getSubtasks();
    }
}
