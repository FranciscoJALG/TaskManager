import java.util.List;
import java.util.Scanner;

public class TaskManagerApp {
    private TaskDAO dao;
    private Scanner scanner;

    public TaskManagerApp(TaskDAO dao) {
        this.dao = dao;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Gestor de Tareas ---");
            System.out.println("1. Ver tareas");
            System.out.println("2. Agregar tarea");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Eliminar tarea");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            String option = scanner.nextLine();
    
            try {
                switch (option) {
                    case "1":
                        listTasks();
                        break;
                    case "2":
                        addTask();
                        break;
                    case "3":
                        markTaskCompleted();
                        break;
                    case "4":
                        deleteTask();
                        break;
                    case "0":
                        System.out.println("Adiós.");
                        return;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    

    private void listTasks() throws Exception {
        List<Task> tasks = dao.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas registradas.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    private void addTask() throws Exception {
        System.out.print("Descripción de la tarea: ");
        String description = scanner.nextLine();
        dao.addTask(description);
        System.out.println("Tarea agregada.");
    }

    private void markTaskCompleted() throws Exception {
        System.out.print("ID de la tarea a marcar como completada: ");
        int id = Integer.parseInt(scanner.nextLine());
        dao.markAsCompleted(id);
        System.out.println("Tarea marcada como completada.");
    }
    
    private void deleteTask() throws Exception {
        System.out.print("ID de la tarea a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        dao.deleteTask(id);
        System.out.println("Tarea eliminada.");
    }
}
