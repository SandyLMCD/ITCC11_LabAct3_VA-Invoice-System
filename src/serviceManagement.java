import java.sql.Connection;
import java.sql.PreparedStatement;
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
        ///
        System.out.println("Enter service name: ");
        String Service_Name = sc.nextLine();

        try {
            String query = "INSERT INTO services (Service_Name) VALUES (?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, Service_Name);
            pstmt.executeUpdate();
            System.out.println("Service added successfully!");
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
        }
    }

    private void viewAllServices(Scanner sc) {
        ///
    }

    private void updateService(Scanner sc) {
        ///
    }

    private void deleteService(Scanner sc) {
        ///
    }

}
