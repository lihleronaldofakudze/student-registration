import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Student extends MenuCreateReadUpdateDelete {
    int id;
    String name;
    String username;
    String password;
    int cell;
    Scanner scanner = new Scanner(System.in);
    Connection connection = DatabaseConnection.getConnection();

    public Student() {
    }

    public Student(int id, String name, String username, String password, int cell) {
        this.id = id;
        this.name = name;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        System.out.println("Student Menu");
        System.out.println("----------------");
        System.out.println("1. Add Student");
        System.out.println("2. Read Student");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Back to Main Menu");
        System.out.println("----------------");
        System.out.println("Enter your choice: ");
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
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }

    @Override
    public void create() {
        System.out.println("Add Student");
        System.out.println("----------------");

        System.out.println("Enter Student Name: ");
        setName(scanner.nextLine());

        System.out.println("Enter Student Username: ");
        setUsername(scanner.nextLine());

        System.out.println("Enter Student Password: ");
        setPassword(scanner.nextLine());

        System.out.println("Enter Student Cell: ");
        setCell(Integer.parseInt(scanner.nextLine()));

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO students (name, username, password, cell) VALUES ('" + getName()
                    + "', '" + getUsername() + "', '" + getPassword() + "', '" + getCell() + "')");
            System.out.println("Student Created Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        System.out.println("Update Student");
        System.out.println("----------------");

        System.out.println("Enter Student ID: ");
        setId(Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter Student Name: ");
        setName(scanner.nextLine());

        System.out.println("Enter Student Username: ");
        setUsername(scanner.nextLine());

        System.out.println("Enter Student Password: ");
        setPassword(scanner.nextLine());

        System.out.println("Enter Student Cell: ");
        setCell(Integer.parseInt(scanner.nextLine()));

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE students SET name = '" + getName() + "', username = '" + getUsername()
                    + "', password = '" + getPassword() + "', cell = '" + getCell() + "' WHERE id = '" + getId() + "'");
            System.out.println("Student Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        System.out.println("Delete Student");
        System.out.println("----------------");

        System.out.println("Enter Student ID: ");
        setId(Integer.parseInt(scanner.nextLine()));

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM students WHERE id = '" + getId() + "'");
            System.out.println("Student Deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        System.out.println("Read Student");
        System.out.println("----------------");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Username: " + resultSet.getString("username"));
                System.out.println("Password: " + resultSet.getString("password"));
                System.out.println("Cell: " + resultSet.getInt("cell"));
                System.out.println("----------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
