import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class serviceManagement {
    private Connection con;

    public serviceManagement(Connection con) {
        this.con = con;
    }

    public void manageServices() {
        Scanner sc = new Scanner(System.in);
        int selectedOption = 0;

        while (selectedOption != 5) {
            System.out.println("\n===Service Management===");
            System.out.println("1 | Add Service");
            System.out.println("2 | View All Services");
            System.out.println("3 | Update Service");
            System.out.println("4 | Delete Service");
            System.out.println("5 | Back to Menu");

            System.out.print("\nPlease select an option: ");
            try {
                selectedOption = sc.nextInt();
                sc.nextLine();

                switch (selectedOption) {
                    case 1:
                        addService(sc);
                        break;
                    case 2:
                        viewAllServices(sc);
                        break;
                    case 3:
                        updateService(sc);
                        break;
                    case 4:
                        deleteService(sc);
                        break;
                    case 5:
                        System.out.println("Returning to main menu...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                sc.next();
            }

        }
    }

    private void addService(Scanner sc) {
        System.out.print("Enter service name: ");
        String Service_Name = sc.nextLine();

        System.out.print("Enter per hour rate: ");
        String Per_Hour_Rate = sc.nextLine();

        try {
            String query = "INSERT INTO service (Service_Name, Per_Hour_Rate) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, Service_Name);
            pstmt.setString(2, Per_Hour_Rate);
            pstmt.executeUpdate();
            System.out.println("Service added successfully!");
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
        }
    }

    private void viewAllServices(Scanner sc) {
        try {
            String query = "SELECT * FROM service";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\nService List: ");
            while (rs.next()) {
                int Service_No = rs.getInt("Service_No");
                String Service_Name = rs.getString("Service_Name");
                int Per_Hour_Rate = rs.getInt("Per_Hour_Rate");
                System.out.println(Service_No + " | " + Service_Name + " | " + "$" + Per_Hour_Rate + "/hour");
            }
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
        }
    }

    private void updateService(Scanner sc) {
        /// should update the service name and per hour rate
        System.out.print("\nEnter Service No: ");
        int Service_No = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new Service Name: ");
        String Service_Name = sc.nextLine();

        System.out.print("Enter new per hour rate: ");
        int Per_Hour_Rate = sc.nextInt();
        sc.nextLine();

        try {
            String query = "UPDATE service SET Service_Name = ?, Per_Hour_Rate = ? WHERE Service_No = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, Service_Name);
            pstmt.setInt(2, Per_Hour_Rate);
            pstmt.setInt(3, Service_No);
            pstmt.executeUpdate();
            System.out.println("Service updated successfully!");
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
        }
    }

    private void deleteService(Scanner sc) {
        System.out.print("Enter Service No: ");
        int Service_No = sc.nextInt();
        sc.nextLine();

        try {
            String query = "DELETE FROM service WHERE Service_No = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, Service_No);
            pstmt.executeUpdate();
            System.out.println("Service deleted successfully!");
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
        }
    }

}
