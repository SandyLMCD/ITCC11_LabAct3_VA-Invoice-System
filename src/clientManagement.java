import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class clientManagement {
    private Connection con;

    public clientManagement(Connection con) {
        this.con = con;
    }

    public void manageClients() {

        Scanner sc = new Scanner(System.in);
        int selectedOption = 0;

        while (selectedOption != 5) {
            System.out.println("\n===Client Management===");
            System.out.println("1 | Add Client");
            System.out.println("2 | View All Clients");
            System.out.println("3 | Update Client");
            System.out.println("4 | Delete Client");
            System.out.println("5 | Back to Menu");

            System.out.print("\nPlease select an option: ");
            selectedOption = sc.nextInt();
            sc.nextLine();

            switch (selectedOption) {
                case 1:
                    addClient(sc);
                    break;
                case 2:
                    viewAllClients(sc);
                    break;
                case 3:
                    updateClient(sc);
                    break;
                case 4:
                    deleteClient(sc);
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
        System.out.print("\nEnter Client Name: ");
        String Client_Name = sc.nextLine();

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

    private void viewAllClients(Scanner sc) {
        try {
            String query = "SELECT * FROM client";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeQuery();
            Resultset rs = (Resultset) pstmt.getResultSet();
            System.out.println("\nClient list: ");
            while (((java.sql.ResultSet) rs).next()) {
                int Client_No = ((java.sql.ResultSet) rs).getInt("Client_No");
                String Client_Name = ((java.sql.ResultSet) rs).getString("Client_Name");
                System.out.println(Client_No + " | " + Client_Name);
            }
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
        }

    }

    private void updateClient(Scanner sc) {
        System.out.print("\nEnter Client No: ");
        int Client_No = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new Client Name: ");
        String Client_Name = sc.nextLine();

        try {
            String query = "UPDATE client SET Client_Name = ? WHERE Client_No = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, Client_Name);
            pstmt.setInt(2, Client_No);
            pstmt.executeUpdate();
            System.out.println("Client updated successfully!");
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
        }
    }

    private void deleteClient(Scanner sc) {
        System.out.print("Enter Client No: ");
        int Client_No = sc.nextInt();
        sc.nextLine();

        try {
            String query = "DELETE FROM client WHERE Client_No = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, Client_No);
            pstmt.executeUpdate();
            System.out.println("Client deleted successfully!");
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
        }
    }

}
