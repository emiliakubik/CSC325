public class Validation {
    public static boolean validateEvaluationData(String employeeID, String criteria, String comments) {
        return employeeID != null && !employeeID.trim().isEmpty() &&
               criteria != null && !criteria.trim().isEmpty() &&
               comments != null && !comments.trim().isEmpty();
    }
}