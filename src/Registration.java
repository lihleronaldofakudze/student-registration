import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Registration extends MenuCreateReadUpdateDelete {
    int id;
    int course_id;
    int student_id;
    Scanner scanner = new Scanner(System.in);
    Connection connection = DatabaseConnection.getConnection();

    public Registration() {
    }

    public Registration(int id, int course_id, int student_id) {
        this.id = id;
        this.course_id = course_id;
        this.student_id = student_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    @Override
    public void menu() {
        System.out.println("Registration Menu");
        System.out.println("1. Add Registration");
        System.out.println("2. Read Registration");
        System.out.println("3. Update Registration");
        System.out.println("4. Delete Registration");
        System.out.println("5. Back to Main Menu");
        System.out.println("6. Exit Program");
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
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    @Override
    public void create() {
        System.out.println("Add Registration");
        System.out.println("----------------");

        System.out.println("Enter Course ID: ");
        setCourse_id(Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter Student ID: ");
        setCourse_id(Integer.parseInt(scanner.nextLine()));

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO registrations (course_id, student_id) VALUES (" + getCourse_id() + ", "
                    + getStudent_id() + ")");
            System.out.println("Registration Created Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        System.out.println("Update Registration");
        System.out.println("----------------");

        System.out.println("Enter Registration ID: ");
        setId(Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter Course ID: ");
        setCourse_id(Integer.parseInt(scanner.nextLine()));

        System.out.println("Enter Student ID: ");
        setStudent_id(Integer.parseInt(scanner.nextLine()));

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE registrations SET course_id = " + getCourse_id() + ", student_id = "
                    + getStudent_id() + " WHERE id = " + getId());
            System.out.println("Registration Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        System.out.println("Delete Registration");
        System.out.println("----------------");

        System.out.println("Enter Registration ID: ");
        setId(Integer.parseInt(scanner.nextLine()));

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM registrations WHERE id = " + getId());
            System.out.println("Registration Deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {
        System.out.println("Read Registration");
        System.out.println("----------------");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM registrations");
            while (resultSet.next()) {
                System.out.println("Registration ID: " + resultSet.getInt("id"));
                System.out.println("Course ID: " + resultSet.getInt("course_id"));
                System.out.println("Student ID: " + resultSet.getInt("student_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
