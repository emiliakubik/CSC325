public class SprintEvaluation {
    private String fullName;
    private String evalTitle;
    private String evalQuestion1;
    private String evalQuestion2;
    private String evalQuestion3;
    private String evalDay;
    private String evalMonth;
    private String evalYear;

    public SprintEvaluation(String name, String title, String response1, String response2, String response3, String day, String month, String year){
        fullName = name;
        evalTitle = title;
        evalQuestion1 = response1;
        evalQuestion2 = response2;
        evalQuestion3 = response3;
        evalDay = day;
        evalMonth = month;
        evalYear = year;
    }

    public String getName(){
        return fullName;
    }

    public String getTitle(){
        return evalTitle;
    }

    public void setTitle(String title){
        evalTitle = title;
    }

    public String getEvalQuestion1(){
        return evalQuestion1;
    }

    public void setEvalQuestion1(String response){
        evalQuestion1 = response;
    }

    public String getEvalQuestion2(){
        return evalQuestion2;
    }

    public void setEvalQuestion2(String response){
        evalQuestion2 = response;
    }

    public String getEvalQuestion3(){
        return evalQuestion3;
    }

    public void setEvalQuestion3(String response){
        evalQuestion3 = response;
    }

    public String getEvalDay(){
        return evalDay;
    }

    public void setEvalDay(String day){
        evalDay = day;
    }

    public String getEvalMonth(){
        return evalMonth;
    }

    public void setEvalMonth(String month){
        evalMonth = month;
    }

    public String getEvalYear(){
        return evalYear;
    }

    public void setEvalYear(String year){
        evalYear = year;
    }
}
