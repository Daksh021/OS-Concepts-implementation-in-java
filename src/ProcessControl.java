import java.io.IOException;
import java.util.Scanner;

public class ProcessControl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("=== Process Control Simulation ===");
        System.out.println("1. Zombie Process Simulation");
        System.out.println("2. Orphan Process Simulation");
        System.out.print("Enter your choice (1 or 2): ");
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                simulateZombie();
                break;
            case 2:
                simulateOrphan();
                break;
            default:
                System.out.println("Invalid choice. Please enter 1 or 2.");
        }


    }

    // ======================
    // Zombie Process Simulation
    // ======================
    public static void simulateZombie() {
        try {
            System.out.println("\n--- Zombie Process Simulation ---");
            ProcessBuilder builder = new ProcessBuilder("notepad.exe");
            Process process = builder.start();
            builder.start();



            System.out.println("Child Process (Notepad) started.");
            System.out.println("Parent is now sleeping for 5 seconds (simulating delay)...");
            Thread.sleep(5000);

            System.out.println("Parent now waits for child to terminate...");
            process.waitFor(); // Waits for Notepad to be closed manually

            System.out.println("Child process terminated. Zombie cleaned up.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ======================
    // Orphan Process Simulation
    // ======================
    public static void simulateOrphan() {
        try {
            System.out.println("\n--- Orphan Process Simulation ---");
            ProcessBuilder builder = new ProcessBuilder("notepad.exe");
            builder.start();


            System.out.println("Child Process (Notepad) started.");
            System.out.println("Parent will now exit. Child will continue running (orphan).");

            Thread.sleep(3000); // Give child time to detach
            System.out.println("Parent exiting...");
            // Parent method ends, leaving child alive
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
