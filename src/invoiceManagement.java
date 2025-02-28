import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class invoiceManagement {
    private Connection con;
    private clientManagement clientManagement;
    private serviceManagement serviceManagement;

    public invoiceManagement(Connection con, clientManagement clientManagement, serviceManagement serviceManagement) {
        this.con = con;
        this.clientManagement = clientManagement;
        this.serviceManagement = serviceManagement;
    }

    public void manageInvoices() {

        Scanner sc = new Scanner(System.in);
        int selectedOption = 0;

        while (selectedOption != 5) {
            System.out.println("\n===Invoice Management===");
            System.out.println("1 | Create New Invoice");
            System.out.println("2 | View All Invoices");
            System.out.println("3 | Update Invoice Status");
            System.out.println("4 | View Invoice By Client");
            System.out.println("5 | Back to Menu");

            System.out.print("\nPlease select an option: ");
            try {
                selectedOption = sc.nextInt();
                sc.nextLine();

                switch (selectedOption) {
                    case 1:
                        createNewInvoice(sc);
                        break;
                    case 2:
                        viewAllInvoices(sc);
                        break;
                    case 3:
                        // ask user for invoice id
                        // update the invoice status (unpaid/paid)
                        updateInvoiceStatus(sc);
                        break;
                    case 4:
                        viewInvoiceByClient(sc);
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

    private void createNewInvoice(Scanner sc) {
        System.out.println("Creating new invoice...");

        Random rand = new Random();
        int Invoice_No = rand.nextInt(9000);

        // user inputs the invoice date (when the invoice was created)
        System.out.print("Enter Invoice Date (Format: YYYY/MM/DD): ");
        String Invoice_Date = sc.nextLine();

        // print client list here
        clientManagement.viewAllClients(sc);

        // user selects which among the clients to create an invoice for
        System.out.print("\nSelect Client No: ");
        int Client_No = sc.nextInt();
        sc.nextLine();

        try {
            String query = "INSERT INTO invoice (Invoice_No, Invoice_Date, Client_No) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, Invoice_No);
            pstmt.setString(2, Invoice_Date);
            pstmt.setInt(3, Client_No);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
            return; // Exit the method if the invoice insertion fails
        }

        // print service list here
        serviceManagement.viewAllServices(sc);

        // user selects what services to include in the invoice
        // user can select multiple services
        while (true) {
            System.out.print("\nEnter Service No (Enter 0 if you want to finish the invoice): ");
            int Service_No = sc.nextInt();
            sc.nextLine();
            if (Service_No == 0) {
                break;
            }

            System.out.print("Enter Total Hours Rendered: ");
            int Total_Hours_Rendered = sc.nextInt();
            sc.nextLine();

            // Retrive the per_hour_rate from services table
            int Per_Hour_Rate = 0;
            try {
                String query = "SELECT Per_Hour_Rate FROM service WHERE Service_No = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, Service_No);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Per_Hour_Rate = rs.getInt("Per_Hour_Rate");
                }
            } catch (SQLException ex) {
                System.out.println("Error: SQL Exception");
                ex.printStackTrace();
            }

            int Service_Subtotal = Per_Hour_Rate * Total_Hours_Rendered;

            try {
                String query = "INSERT INTO invoice_line_service(Invoice_No, Service_No, Total_Hours_Rendered, Service_Subtotal) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, Invoice_No);
                ps.setInt(2, Service_No);
                ps.setInt(3, Total_Hours_Rendered);
                ps.setInt(4, Service_Subtotal);
                ps.executeUpdate();
                System.out.println("Service added to invoice successfully!");
            } catch (SQLException ex) {
                System.out.println("Error: SQL Exception");
                ex.printStackTrace();
            }
        }

        // retrieve client name from the database

        // invoice gets generated and saved in the database

        String Client_Name = "";
        try {
            String query = "SELECT Client_Name FROM client WHERE Client_No = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, Client_No);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Client_Name = rs.getString("Client_Name");
            }
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
        }

        // invoice will be shown to the user and user would have to input 0 to go back
        // to the Invoice Management Menu
        System.out.println("\n===INVOICE DETAILS===");
        System.out.println("Invoice No: " + Invoice_No + " (" + Invoice_Date + ")");
        System.out.println("Client: " + Client_Name);
        int Total_Amount = 0;

        try {
            String query = "SELECT ils.Service_No, s.Service_Name, ils.Total_Hours_Rendered, ils.Service_Subtotal FROM invoice_line_service ils JOIN service s ON ils.Service_No = s.Service_No WHERE ils.Invoice_No = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, Invoice_No);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\nServices Included:");
            while (rs.next()) {
                int Service_No = rs.getInt("Service_No");
                String Service_Name = rs.getString("Service_Name");
                int Total_Hours_Rendered = rs.getInt("Total_Hours_Rendered");
                int Service_Subtotal = rs.getInt("Service_Subtotal");
                Total_Amount += Service_Subtotal;
                System.out.println("Service No: " + Service_No + " | Service Name: " + Service_Name + " | Total Hours: "
                        + Total_Hours_Rendered + " | Subtotal: " + Service_Subtotal);
            }
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
        }

        System.out.println("\nTotal Amount: " + Total_Amount);

        System.out.println("\n0 | Back to Invoice Management Menu");
        int backToMenu = sc.nextInt();
        sc.nextLine();
        if (backToMenu == 0) {
            return;
        }
    }

    private void viewAllInvoices(Scanner sc) {
        try {
            String query = "SELECT i.Invoice_No, i.Invoice_Date, c.Client_Name, i.Invoice_Status_Paid " +
                    "FROM invoice i " +
                    "JOIN client c on i.Client_No = c.Client_No";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\nInvoice List: ");
            while (rs.next()) {
                int Invoice_No = rs.getInt("Invoice_No");
                String Invoice_Date = rs.getString("Invoice_Date");
                String Client_Name = rs.getString("Client_Name");
                boolean Invoice_Status_Paid = rs.getBoolean("Invoice_Status_Paid");
                String Invoice_Status = Invoice_Status_Paid ? "Paid" : "Unpaid";
                System.out
                        .println(Invoice_No + " | " + Invoice_Date + " | " + Client_Name + " | " + Invoice_Status);
            }
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception");
            ex.printStackTrace();
        }
    }

    private void updateInvoiceStatus(Scanner sc) {
        System.out.println("Updating invoice status...");
        viewAllInvoices(sc);

        System.out.print("\nEnter Invoice Number: ");
        int Invoice_Number = sc.nextInt();
        sc.nextLine();

        System.out.print("\nEnter 1 if client has paid. 0 if client still hasn't paid: ");
        int status = sc.nextInt();
        sc.nextLine();

        boolean Invoice_Status_Paid = (status == 1);

        try {
            String query = "UPDATE invoice SET Invoice_Status_Paid = ? WHERE Invoice_No = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setBoolean(1, Invoice_Status_Paid);
            pstmt.setInt(2, Invoice_Number);
            pstmt.executeUpdate();
            System.out.println("Invoice status updated successfully!");
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception");
            ex.printStackTrace();
        }
    }

    private void viewInvoiceByClient(Scanner sc) {
        System.out.println("Deleting invoice...");
    }

}
