public class Event_Service {
    private String action;
    private int Service_No;
    private String Service_Name;

    public Event_Service(String action, int Service_No, String Service_Name) {
        this.action = action;
        this.Service_No = Service_No;
        this.Service_Name = Service_Name;
    }

    public String getAction() {
        return action;
    }

    public int getService_No() {
        return Service_No;
    }

    public String getService_Name() {
        return Service_Name;
    }
}
