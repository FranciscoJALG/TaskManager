import java.sql.*;
import java.util.*;

public class TaskDAO {
    private Connection conn;

    public TaskDAO() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:database/tasks.db");
        createTable();
    }

    private void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS tasks (id INTEGER PRIMARY KEY AUTOINCREMENT, description TEXT, completed BOOLEAN)";
        conn.createStatement().execute(sql);
    }

    public void addTask(String description) throws SQLException {
        String sql = "INSERT INTO tasks (description, completed) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, description);
        stmt.setBoolean(2, false);
        stmt.executeUpdate();
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()) {
            tasks.add(new Task(rs.getInt("id"), rs.getString("description"), rs.getBoolean("completed")));
        }
        return tasks;
    }

    public void markAsCompleted(int id) throws SQLException {
    String sql = "UPDATE tasks SET completed = 1 WHERE id = ?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, id);
    stmt.executeUpdate();
}

public void deleteTask(int id) throws SQLException {
    String sql = "DELETE FROM tasks WHERE id = ?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, id);
    stmt.executeUpdate();
}
}
