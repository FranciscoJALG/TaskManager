public class Main {
    public static void main(String[] args) {
        try {
            TaskDAO dao = new TaskDAO();
            TaskManagerApp app = new TaskManagerApp(dao);
            app.start();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
