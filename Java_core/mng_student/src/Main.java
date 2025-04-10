import controller.StudentController;

public class Main {
    public static void main(String[] args) {
        StudentController studentController = new StudentController();
        studentController.showMenu();
        System.out.println("Exiting the program. Goodbye!");
    }
}
