public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        taskManager.createNewTask("Таск 1","Помыть посуду", Status.NEW);
        taskManager.createNewTask("Таск 2","Сходить в магазин", Status.NEW);

        int epicId = taskManager.createNewEpic("Комплексная задача 1", "Приготовить салат", Status.NEW);
        taskManager.createNewSubtask(epicId, "Задача 1.1", "Помыть овощи", Status.NEW);
        taskManager.createNewSubtask(epicId, "Задача 1.2", "Порорезать овощи", Status.NEW);

        int epicId2 = taskManager.createNewEpic("Комплексная задача 2", "Сделать отчёт", Status.NEW);
        taskManager.createNewSubtask(epicId2,"Задача 2.1", "Проанализировать данные", Status.NEW);

        System.out.println(taskManager.getAllTask());

//        taskManager.deleteAllTask();

        Task taskChecking1 = new Task("Таск 1","Помыть посуду",1,Status.IN_PROGRESS);
        Task taskChecking2 = new Task("Таск 2","Сходить в магазин",2,Status.IN_PROGRESS);
        Subtask subtaskCheching1 = new Subtask("Задача 1.1", "Помыть овощи",4, Status.IN_PROGRESS,3);

        taskManager.upDateSubtask(subtaskCheching1);

//        taskManager.upDateTask(taskChecking1);
//        taskManager.upDateTask(taskChecking2);
//        taskManager.deleteTaskByID(1);
//        taskManager.deleteSubtaskByID(4);
//        taskManager.deleteEpicByID(3);
        System.out.println("сделали апдейт");
        System.out.println(taskManager.getAllTask());
        taskManager.deleteTaskByID(2);

    }
}
