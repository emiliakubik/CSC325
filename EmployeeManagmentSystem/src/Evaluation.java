import java.util.Date;
import java.util.Objects;

public class Evaluation {
    private String employeeID;
    private Date evaluationDate;
    private String criteria;
    private String comments;

    public Evaluation(String employeeID, Date evaluationDate, String criteria, String comments) {
        this.employeeID = employeeID;
        this.evaluationDate = evaluationDate;
        this.criteria = criteria;
        this.comments = comments;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluation that = (Evaluation) o;
        return Objects.equals(employeeID, that.employeeID) &&
               Objects.equals(evaluationDate, that.evaluationDate) &&
               Objects.equals(criteria, that.criteria) &&
               Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, evaluationDate, criteria, comments);
    }
}