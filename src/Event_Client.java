public class Event_Client {
    private String action;
    private int Client_No;
    private String Client_Name;

    public Event_Client(String action, int Client_No, String Client_Name) {
        this.action = action;
        this.Client_No = Client_No;
        this.Client_Name = Client_Name;
    }

    public String getAction() {
        return action;
    }

    public int getClient_No() {
        return Client_No;
    }

    public String getClientName() {
        return Client_Name;
    }

}
