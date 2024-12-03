import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_management";
    private static final String USER = "root";
    private static final String PASS = "password";
    private static final Logger LOGGER = Logger.getLogger(DatabaseHandler.class.getName());

    public static void insertEvaluation(Evaluation evaluation) throws SQLException {
        String sql = "INSERT INTO evaluations (employeeID, evaluationDate, criteria, comments) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, evaluation.getEmployeeID());
            pstmt.setDate(2, new java.sql.Date(evaluation.getEvaluationDate().getTime()));
            pstmt.setString(3, evaluation.getCriteria());
            pstmt.setString(4, evaluation.getComments());

            pstmt.executeUpdate();
            LOGGER.log(Level.INFO, "Evaluation inserted successfully for employee ID: " + evaluation.getEmployeeID());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting evaluation for employee ID: " + evaluation.getEmployeeID(), e);
            throw e;
        }
    }

    public static List<Evaluation> getEvaluations() {
        List<Evaluation> evaluations = new ArrayList<>();
        String sql = "SELECT * FROM evaluations";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Evaluation evaluation = new Evaluation(
                    rs.getString("employeeID"),
                    rs.getDate("evaluationDate"),
                    rs.getString("criteria"),
                    rs.getString("comments")
                );
                evaluations.add(evaluation);
            }
            LOGGER.log(Level.INFO, "Evaluations retrieved successfully");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving evaluations", e);
        }

        return evaluations;
    }
}