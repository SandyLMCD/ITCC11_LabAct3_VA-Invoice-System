import java.sql.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Connection con = null;
        System.out.println("Hello, World!");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/labact3_vainvoicesystem", "root",
                    "$andyMySQLRootPassword2024");
            System.out.println("Database connected successfully");

            int selectedOption = 0;

            Scanner sc = new Scanner(System.in);
            EventBus eventBus = new EventBus();
            clientManagement clientManagement = new clientManagement(con, eventBus);
            serviceManagement serviceManagement = new serviceManagement(con);
            invoiceManagement invoiceManagement = new invoiceManagement(con, clientManagement, serviceManagement);
            // clientManagement,
            // serviceManagement);

            while (selectedOption != 4) {
                System.out.println("\nWelcome VA Sandy to your Invoice System!");
                System.out.println("1. Client Management");
                System.out.println("2. Service Management");
                System.out.println("3. Invoice Management");
                System.out.println("4. Exit");

                System.out.print("\nPlease select an option: ");
                selectedOption = sc.nextInt();
                try {
                    //
                    switch (selectedOption) {
                        case 1:
                            // Client Management
                            clientManagement.manageClients();
                            break;
                        case 2:
                            // Service Management
                            serviceManagement.manageServices();
                            break;
                        case 3:
                            // Invoice Management
                            System.out.println("Invoice Management");
                            invoiceManagement.manageInvoices();
                            break;
                        case 4:
                            // Exit
                            System.out.println("Thank you for using the Invoice System!");
                            break;
                        default:
                            System.out.println("Invalid option selected. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please try again.");
                    sc.next();
                } catch (NoSuchElementException e) {
                    System.out.println("No input available. Exiting...");
                    break;
                }
            }

            sc.close();

        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: Class Not Found Exception!");
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error: SQL Exception!");
                    ex.printStackTrace();
                }
            }
        }
    }
}