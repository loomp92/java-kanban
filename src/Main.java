import Manager.TaskManager;
import Manager.Managers;
import Tasks.Status;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();

        taskManager.createNewTask("Таск 1", "Помыть посуду", Status.NEW);
        taskManager.createNewTask("Таск 2", "Сходить в магазин", Status.NEW);

        int epicId = taskManager.createNewEpic("Комплексная задача 1", "Приготовить салат");
        taskManager.createNewSubtask(epicId, "Задача 1.1", "Помыть овощи", Status.NEW);
        taskManager.createNewSubtask(epicId, "Задача 1.2", "Порорезать овощи", Status.NEW);
        taskManager.createNewSubtask(epicId, "Задача 1.3", "Сложить овощи в миску", Status.NEW);

        int epicId2 = taskManager.createNewEpic("Комплексная задача 2", "Сделать отчёт");
        taskManager.createNewSubtask(epicId2, "Задача 2.1", "Проанализировать данные", Status.NEW);

        System.out.println(taskManager.getAllTask());

        taskManager.getTaskByID(1);
        taskManager.getTaskByID(1);
        taskManager.getTaskByID(3);
        taskManager.getTaskByID(3);
        taskManager.getTaskByID(1);
        taskManager.getTaskByID(1);
        taskManager.getTaskByID(1);
        taskManager.getTaskByID(4);
        taskManager.getTaskByID(3);
        taskManager.getTaskByID(5);
        taskManager.getTaskByID(3);
        taskManager.getTaskByID(1);

        printHistory(taskManager);


        taskManager.deleteTaskByID(1);

        taskManager.deleteEpicByID(4);

        System.out.println(taskManager.getAllTask());

        printHistory(taskManager);
    }


    public static void printHistory(TaskManager taskManager) {
        String[] list = taskManager.getHistoryForMenu().toString().split("},");
        StringBuilder result = new StringBuilder("История обращени к задачам: \n");
        for (String line : list) {
            result.append(line).append(".\n");
        }
        System.out.println(result);
    }
}

