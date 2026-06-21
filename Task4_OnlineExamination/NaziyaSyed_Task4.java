package Task4_OnlineExamination;

public class NaziyaSyed_Task4 {

    public static void main(String[] args) {
        InputReader.start();

        UserAccountManager accountManager = new UserAccountManager();
        accountManager.addUser("naziya", "pass123");

        System.out.println("===================================");
        System.out.println("      ONLINE EXAMINATION SYSTEM");
        System.out.println("===================================");

        System.out.print("\nEnter Username: ");
        String username = InputReader.readLine().trim();
        System.out.print("Enter Password: ");
        String password = InputReader.readLine().trim();

        if (!accountManager.authenticate(username, password)) {
            System.out.println("Invalid credentials. Exiting...");
            return;
        }

        System.out.println("\nLogin successful! Welcome, " + username + ".");

        boolean running = true;
        boolean examTaken = false;

        while (running) {
            printMenu();
            int choice = readChoice();

            switch (choice) {
                case 1:
                    accountManager.updateProfile(username);
                    break;
                case 2:
                    if (examTaken) {
                        System.out.println("\nYou have already submitted the exam.");
                    } else {
                        ExamEngine exam = new ExamEngine();
                        exam.startExam();
                        examTaken = true;
                    }
                    break;
                case 3:
                    System.out.println("\nClosing session. Logged out successfully. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n----------- MAIN MENU -----------");
        System.out.println("1. Update Profile / Password");
        System.out.println("2. Start Exam");
        System.out.println("3. Logout");
        System.out.print("Choose an option: ");
    }

    private static int readChoice() {
        String input = InputReader.readLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}