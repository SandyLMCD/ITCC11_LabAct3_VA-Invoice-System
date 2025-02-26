import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class clientManagement {
    private Connection con;

    public clientManagement(Connection con) {
        this.con = con;
    }

    public void manageClients() {

        Scanner sc = new Scanner(System.in);
        int selectedOption = 0;

        while (selectedOption != 5) {
            System.out.println("===Client Management===");
            System.out.println("1 | Add Client");
            System.out.println("2 | View All Clients");
            System.out.println("3 | Update Client");
            System.out.println("4 | Delete Client");
            System.out.println("5 | Back to Menu");

            System.out.println("Please select an option: ");
            selectedOption = sc.nextInt();

            switch (selectedOption) {
                case 1:
                    addClient(sc);
                    break;
                case 2:
                    // viewAllClients();
                    break;
                case 3:
                    // updateClient();
                    break;
                case 4:
                    // deleteClient();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    // returnToMenu(); // is it possible to make this an interface?
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

        }
        sc.close();
    }

    private void addClient(Scanner sc) {
        System.out.println("Enter Client Name: ");
        String Client_Name = sc.next();

        // Client client = new Client(Client_No, Client_Name);
        // clientDAO.addClient(client);
        try {
            String query = "INSERT INTO client (Client_Name) VALUES (?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, Client_Name);
            pstmt.executeUpdate();
            System.out.println("Client added successfully!");
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
        }
    }

}
