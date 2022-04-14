import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Student Registration System");
        System.out.println("--------------------------------------");

    }

    public static void menu() {
        Administrator administrator = new Administrator();
        Student student = new Student();
        Payments payments = new Payments();
        Course course = new Course();
        Registration registration = new Registration();

        System.out.println("Main Menu");
        System.out.println("1. Administrator");
        System.out.println("2. Student");
        System.out.println("3. Payment");
        System.out.println("4. Course");
        System.out.println("4. Registration");
        System.out.println("6. Exit");

        try (Scanner scanner = new Scanner(System.in)) {
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    administrator.menu();
                    break;
                case 2:
                    student.menu();
                    break;
                case 3:
                    payments.menu();
                    break;
                case 4:
                    course.menu();
                    break;
                case 5:
                    registration.menu();
                    break;
                case 6:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();

            System.exit(0);
        }
    }
}
