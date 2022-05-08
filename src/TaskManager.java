import java.util.ArrayList;
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

    public int createNewEpic(String name, String description, Status status) {
        var uid = ++taskID;
        Epic epic = new Epic(name, description, uid, status);
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
        epic.addSubtask(subtask);
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
        allTaskHashMaps.putAll(subtaskHashMap);
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

    public Task deleteEpicByID(int id) {
        Task task = epicHashMap.getOrDefault(id, null);
        if (task == null) {
            return null;
        }
        for (int i=1; i<epicHashMap.get(id).getSubtasks().size(); i++){
            epicHashMap.get(id).getSubtasks().remove();
        }
        return epicHashMap.remove(id);
    }

    public Task deleteSubtaskByID(int id) {
        Task task = subtaskHashMap.getOrDefault(id, null);
        if (task == null) {
            return null;
        }
        epicHashMap.get(subtaskHashMap.get(id).getEpicUid()).getSubtasks().remove(id);
        return subtaskHashMap.remove(id);
    }

    public Task upDateTask(Task task) { //TODO  сделать для эпик и субтаск
        if (!(taskHashMap.containsKey(task.getUid()))){
            return null;
        } return taskHashMap.replace(task.getUid(),task);
    }

    public Epic upDateEpic(Epic epic) {
        if (!(epicHashMap.containsKey(epic.getUid()))){
            return null;
        } return epicHashMap.replace(epic.getUid(), epic);
    }

    public Subtask upDateSubtask(Subtask subtask) {
        if (!(subtaskHashMap.containsKey(subtask.getUid()))) {
            return null;
        }
        epicHashMap.get(subtask.getEpicUid()).getSubtasks().set(subtask.getUid(),subtask);
        subtaskHashMap.put(subtask.epicUid, subtask);
        return subtaskHashMap.replace(subtask.getUid(), subtask);
    }

    public ArrayList<Subtask> getSubtaskByEpic (int epicId){ //TODO  проверитьработу
        Epic searchSubtask = epicHashMap.getOrDefault(epicId, null);
        if (searchSubtask == null){
            return null;
        }
        return searchSubtask.getSubtasks();
    }
    }


  /*  1 Возможность хранить задачи всех типов. Для этого вам нужно выбрать подходящую коллекцию.
       2  Методы для каждого из типа задач(Задача/Эпик/Подзадача):
       2.1 Получение списка всех задач. *
       2.2 Удаление всех задач.*
       2.3. Получение по идентификатору. *
       2.4 Создание. Сам объект должен передаваться в качестве параметра. *
       2.5 Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
       2.6 Удаление по идентификатору.
       3. Дополнительные методы:
       3.1 Получение списка всех подзадач определённого эпика.
        4 Управление статусами осуществляется по следующему правилу:
       4.1 Менеджер сам не выбирает статус для задачи. Информация о нём приходит менеджеру вместе с информацией о самой задаче. По этим данным в одних случаях он будет сохранять статус, в других будет рассчитывать.
       4.2 Для эпиков:
        - если у эпика нет подзадач или все они имеют статус NEW, то статус должен быть NEW.
        - если все подзадачи имеют статус DONE, то и эпик считается завершённым — со статусом DONE.
        - во всех остальных случаях статус должен быть IN_PROGRESS.*/