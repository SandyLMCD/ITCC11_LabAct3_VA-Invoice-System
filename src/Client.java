public class Client {
    private int Client_No;
    private String Client_Name;

    public Client(int Client_No, String Client_Name) {
        this.Client_No = Client_No;
        this.Client_Name = Client_Name;
    }

    public int getClient_No() {
        return Client_No;
    }

    public void setClient_No(int Client_No) {
        this.Client_No = Client_No;
    }

    public String getClient_Name() {
        return Client_Name;
    }

    public void setClient_Name(String Client_Name) {
        this.Client_Name = Client_Name;
    }
}
