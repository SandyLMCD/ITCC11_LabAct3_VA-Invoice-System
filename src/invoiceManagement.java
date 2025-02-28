import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class invoiceManagement {
    private Connection con;

    public invoiceManagement(Connection con) {
        this.con = con;
    }

    public void manageInvoices() {

        Scanner sc = new Scanner(System.in);
        int selectedOption = 0;

        while (selectedOption != 5) {
            System.out.println("\n===Invoice Management===");
            System.out.println("1 | Create New Invoice");
            System.out.println("2 | View All Invoices");
            System.out.println("3 | Update Invoice Status");
            System.out.println("4 | Delete Invoice");
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
                        deleteInvoice(sc);
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
    }

    private void viewAllInvoices(Scanner sc) {
        System.out.println("Viewing all invoices...");
    }

    private void updateInvoiceStatus(Scanner sc) {
        System.out.println("Updating invoice status...");
    }

    private void deleteInvoice(Scanner sc) {
        System.out.println("Deleting invoice...");
    }

}
