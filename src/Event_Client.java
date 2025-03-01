public class Event_Client extends Event {
    private String action;
    private int Client_No;
    private String Client_Name;

    public Event_Client(String action, int Client_No, String Client_Name) {
        super(Client_Name, Client_Name); // Call to the superclass constructor
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
