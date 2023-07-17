import java.util.Scanner;

public class UserInputReader {
    private final Scanner scanner;

    public UserInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Parameters readCustomParameters() {
        int rootNote = validateParameters("Enter the root note (0-127): ", 0, 127);
        int octaves = validateParameters("Enter the number of octaves (1-7): ", 1, 7);
        double displacement = validateParameters("Enter the displacement factor (0-24): ", 0, 24);
        double duration = validateParameters("Enter the duration factor (1-24): ", 1, 24);
        int iterations = validateParameters("Enter the number of iterations (0-100) (keep it low to start): ", 0, 100);
        return new Parameters(rootNote, octaves, displacement, duration, iterations);
    }

    private int validateParameters(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            int input = scanner.nextInt();
            if (input >= min && input <= max) {
                return input;
            }
            System.out.printf("Invalid input! Please enter a value between %d and %d.%n", min, max);
        }
    }
}