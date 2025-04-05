import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            TaskDAO dao = new TaskDAO();

            System.out.println("1. Agregar tarea");
            System.out.println("2. Ver tareas");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                System.out.println("Descripción:");
                String desc = scanner.nextLine();
                dao.addTask(new Task(desc));
                System.out.println("Tarea agregada.");
            } else if (opcion == 2) {
                for (Task t : dao.getAllTasks()) {
                    System.out.println(t.getId() + ": " + t.getDescription() + " - Completado: " + t.isCompleted());
                }
            }
        }
    }
}