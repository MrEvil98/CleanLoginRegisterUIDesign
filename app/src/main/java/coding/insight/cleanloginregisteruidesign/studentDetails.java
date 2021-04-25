package coding.insight.cleanloginregisteruidesign;

public class studentDetails {

    private String Enroll;
    private String Pass;

    public studentDetails(){

    }

    public studentDetails(String Enroll,String Pass) {
        this.Enroll = Enroll;
        this.Pass=Pass;
    }

    public String getEnroll() {
        return Enroll;
    }

    public String getPass() {
        return Pass;
    }

    public void setEnroll(String enroll) {
        Enroll = enroll;
    }


    public void setPass(String pass) {
        Pass = pass;
    }
}
