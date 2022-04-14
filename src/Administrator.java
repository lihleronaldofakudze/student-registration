import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Administrator extends MenuCreateReadUpdateDelete {
    int id;
    String name;
    String email;
    String password;
    int cell;
    Scanner scanner = new Scanner(System.in);
    Connection connection = DatabaseConnection.getConnection();

    public Administrator() {
    }

    public Administrator(int id, String name, String email, String password, int cell) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cell = cell;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    @Override
    public void menu() {
        System.out.println("Administrator Menu");
        System.out.println("1. Create Administrator");
        System.out.println("2. Read Administrator");
        System.out.println("3. Update Administrator");
        System.out.println("4. Delete Administrator");
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
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    @Override
    public void create() {
        System.out.println("Add Administrator");
        System.out.println("----------------------------------");

        System.out.print("Enter the name of the administrator");
        setName(scanner.nextLine());

        System.out.print("Enter the email of the administrator");
        setEmail(scanner.nextLine());

        System.out.println("Enter the password of the administrator");
        setPassword(scanner.nextLine());

        System.out.println("Enter the cell of the administrator");
        setCell(Integer.parseInt(scanner.nextLine()));

        String query = "INSERT INTO administrator(name, email, password, cell) VALUES('" + getName() + "', '"
                + getEmail() + "', '" + getPassword() + "', '" + getCell() + "')";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Administrator created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {
        System.out.println("Update Administrator");
        System.out.println("----------------------------------");

        System.out.print("Enter the id of the administrator");
        setId(Integer.parseInt(scanner.nextLine()));

        System.out.print("Enter the name of the administrator");
        setName(scanner.nextLine());

        System.out.print("Enter the email of the administrator");
        setEmail(scanner.nextLine());

        System.out.println("Enter the password of the administrator");
        setPassword(scanner.nextLine());

        System.out.println("Enter the cell of the administrator");
        setCell(Integer.parseInt(scanner.nextLine()));

        String query = "UPDATE administrator SET name = '" + getName() + "', email = '" + getEmail() + "', password = '"
                + getPassword() + "', cell = '" + getCell() + "' WHERE id = '" + getId() + "'";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Administrator updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete() {
        System.out.println("Delete Administrator");
        System.out.println("----------------------------------");

        System.out.print("Enter the id of the administrator");
        setId(Integer.parseInt(scanner.nextLine()));

        String query = "DELETE FROM administrator WHERE id = '" + getId() + "'";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Administrator deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void read() {
        System.out.println("Read Administrator");
        System.out.println("----------------------------------");

        String query = "SELECT * FROM administrator";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Password: " + resultSet.getString("password"));
                System.out.println("Cell: " + resultSet.getString("cell"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
