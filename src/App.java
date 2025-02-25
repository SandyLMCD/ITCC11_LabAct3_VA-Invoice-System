import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/labact3_vainvoicesystem", "root",
                    "$andyMySQLRootPassword2024");
            System.out.println("Database connected successfully");

            Statement stmt = con.createStatement();

            String strSelect = "select * from client";
            System.out.println("The SQL query is: " + strSelect);
            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println("The records selected are:");
            int rowCount = 0;
            while (rset.next()) {
                int clientNo = rset.getInt("Client_No");
                String clientName = rset.getString("Client_Name");
                System.out.println(clientNo + ", " + clientName);

                ++rowCount;
            }
            System.out.println("Total number of records = " + rowCount);
        } catch (SQLException ex) {
            System.out.println("Error: SQL Exception!");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: Class Not Found Exception!");
            ex.printStackTrace();
        }
    }

}