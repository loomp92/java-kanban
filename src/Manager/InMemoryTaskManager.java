package Manager;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

class InMemoryTaskManager implements TaskManager {

    private final HashMap<Integer, Task> taskHashMap;
    private final HashMap<Integer, Epic> epicHashMap;
    private final HashMap<Integer, Subtask> subtaskHashMap;
    private final HistoryManager historyManager;
    private static int taskID = 0;

    public InMemoryTaskManager() {
        this.taskHashMap = new HashMap<>();
        this.subtaskHashMap = new HashMap<>();
        this.epicHashMap = new HashMap<>();
        this.historyManager = Managers.getDefaultHistory();
    }

    @Override
    public int createNewTask(String name, String description, Status status) {
        var uid = ++taskID;
        Task task = new Task(name, description, uid, status);
        taskHashMap.put(uid, task);
        return uid;
    }

    @Override
    public int createNewEpic(String name, String description) {
        var uid = ++taskID;
        Epic epic = new Epic(name, description, uid);
        epicHashMap.put(uid, epic);
        return uid;
    }

    @Override
    public int createNewSubtask(int epicUid, String name, String description, Status status) {
        Epic epic = epicHashMap.getOrDefault(epicUid, null);
//        Epic epic1 = epicHashMap.get(epicUid);
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

    @Override
    public HashMap<Integer, Task> getAllTask() {
        HashMap<Integer, Task> allTaskHashMaps = new HashMap<>();
        allTaskHashMaps.putAll(taskHashMap);
        allTaskHashMaps.putAll(epicHashMap);
        return allTaskHashMaps;
    }

    @Override
    public Task getTaskByID(int id) {
        Task task = taskHashMap.getOrDefault(id, null);
        if (task != null) {
            historyManager.add(task);
        }

        return task;
    }

    @Override
    public Epic getEpicByID(int id) {
        Epic epic = epicHashMap.getOrDefault(id, null);
        if (epic != null) {
            historyManager.add(epic);
        }

        return epic;
    }

    @Override
    public Subtask getSubTaskByID(int id) {
        Subtask subtask = subtaskHashMap.getOrDefault(id, null);
        if (subtask != null) {
            historyManager.add(subtask);
        }

        return subtask;
    }

    @Override
    public Task deleteTaskByID(int id) {
        Task task = taskHashMap.getOrDefault(id, null);
        if (task == null) {
            return null;
        }
        return taskHashMap.remove(id);
    }

    @Override
    public void deleteEpicByID(int id) {
        Task task = epicHashMap.getOrDefault(id, null);
        if (task == null) {
            return;
        }
        Epic epic = epicHashMap.get(id);
        subtaskHashMap.keySet().removeAll(epic.getSubtaskKeys());
        epicHashMap.remove(id);
    }

    @Override
    public void deleteSubtaskByID(int id) {

        epicHashMap.get(subtaskHashMap.get(id).getEpicUid()).deleteSubtask(id);
        subtaskHashMap.remove(id);
    }

    @Override
    public Task upDateTask(Task task) {
        if (!(taskHashMap.containsKey(task.getUid()))) {
            return null;
        }
        return taskHashMap.replace(task.getUid(), task);
    }

    @Override
    public Epic upDateEpic(Epic epic) {
        if (!(epicHashMap.containsKey(epic.getUid()))) {
            return null;
        }
        return epicHashMap.replace(epic.getUid(), epic);
    }

    @Override
    public void upDateSubtask(Subtask subtask) {
        if (!(subtaskHashMap.containsKey(subtask.getUid()))) {
            System.out.println("Сабтаск не существует");
            return;
        }
        epicHashMap.get(subtask.getEpicUid()).addOrUpdateSubtask(subtask);
        subtaskHashMap.put(subtask.getEpicUid(), subtask);
    }

    @Override
    public Collection<Subtask> getSubtaskByEpic(int epicId) {
        Epic searchSubtask = epicHashMap.getOrDefault(epicId, null);
        if (searchSubtask == null) {
            return null;
        }
        return epicHashMap.get(epicId).getSubtasks();
    }

    @Override
    public List<Task> getHistoryForMenu() {
        return historyManager.getHistory();
    }

}