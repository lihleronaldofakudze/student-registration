import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Payments extends MenuCreateReadUpdateDelete {
    int id;
    String type;
    int amount;
    String date;
    int student_id;
    Scanner scanner = new Scanner(System.in);
    Connection connection = DatabaseConnection.getConnection();

    public Payments() {
    }

    public Payments(int id, int amount, String type, String date, int student_id) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.student_id = student_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    @Override
    public void menu() {
        System.out.println("Payments Menu");
        System.out.println("1. Create Payment");
        System.out.println("2. Read Payment");
        System.out.println("3. Update Payment");
        System.out.println("4. Delete Payment");
        System.out.println("5. Back to Main Menu");
        System.out.println("6. Exit Program");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                create();
                break;
            case 2:
                read();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }

    @Override
    public void create() {
        System.out.println("Add Payment");
        System.out.println("----------------");

        System.out.println("Enter Payment Type: ");
        setType(scanner.nextLine());

        System.out.println("Enter Payment Amount: ");
        setAmount(Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter Payment Date: ");
        setDate(scanner.nextLine());

        System.out.println("Enter Student ID: ");
        setStudent_id(Integer.parseInt(scanner.nextLine()));

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO payments (type, amount, date, student_id) VALUES ('" + getType()
                    + "', '" + getAmount() + "', '" + getDate() + "', '" + getStudent_id() + "')");
            System.out.println("Payment Created Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        System.out.println("Update Payment");
        System.out.println("----------------");

        System.out.println("Enter Payment ID: ");
        setId(Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter Payment Type: ");
        setType(scanner.nextLine());

        System.out.println("Enter Payment Amount: ");
        setAmount(Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter Payment Date: ");
        setDate(scanner.nextLine());

        System.out.println("Enter Student ID: ");
        setStudent_id(Integer.parseInt(scanner.nextLine()));

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "UPDATE payments SET type = '" + getType() + "', amount = '" + getAmount() + "', date = '"
                            + getDate() + "', student_id = '" + getStudent_id() + "' WHERE id = '" + getId() + "'");
            System.out.println("Payment Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        System.out.println("Delete Payment");
        System.out.println("----------------");

        System.out.println("Enter Payment ID: ");
        setId(Integer.parseInt(scanner.nextLine()));

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM payments WHERE id = " + getId());
            System.out.println("Payment Deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        System.out.println("Read Payment");
        System.out.println("----------------");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM payments ");
            while (resultSet.next()) {
                System.out.println("Payment ID: " + resultSet.getInt("id"));
                System.out.println("Payment Type: " + resultSet.getString("type"));
                System.out.println("Payment Date: " + resultSet.getString("date"));
                System.out.println("Student ID: " + resultSet.getInt("student_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}