import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Course extends MenuCreateReadUpdateDelete {
    int id;
    String name;
    Scanner scanner = new Scanner(System.in);
    Connection connection = DatabaseConnection.getConnection();

    public Course() {
    }

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public void menu() {
        System.out.println("Course Menu");
        System.out.println("1. Create Course");
        System.out.println("2. Read Course");
        System.out.println("3. Update Course");
        System.out.println("4. Delete Course");
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
        System.out.println("Add Course");
        System.out.println("----------------");

        System.out.println("Enter Course Name: ");
        setName(scanner.nextLine());

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO courses (name) VALUES ('" + getName() + "')");
            System.out.println("Course Created Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        System.out.println("Update Course");
        System.out.println("----------------");

        System.out.println("Enter Course ID: ");
        setId(Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter Course Name: ");
        setName(scanner.nextLine());

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE courses SET name = '" + getName() + "' WHERE id = " + getId());
            System.out.println("Course Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        System.out.println("Delete Course");
        System.out.println("----------------");

        System.out.println("Enter Course ID: ");
        setId(Integer.parseInt(scanner.nextLine()));

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM courses WHERE id = " + getId());
            System.out.println("Course Deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        System.out.println("Read Course");
        System.out.println("----------------");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses");
            while (resultSet.next()) {
                System.out.println("Course ID: " + resultSet.getInt("id"));
                System.out.println("Course Name: " + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
