import java.util.Scanner;

public class UserInputReader {
    private final Scanner scanner;

    public UserInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Parameters readUserInput() {
        System.out.print("Would you like to use default parameters? (Y/N): ");
        String defaultChoice = scanner.next();

        if (defaultChoice.equalsIgnoreCase("Y")) {
            return new Parameters();
        }

        int rootNote = validateInput("Enter the root note (0-127): ", 0, 127);
        int octaves = validateInput("Enter the number of octaves (1-7): ", 1, 7);
        double displacement = validateInput("Enter the displacement factor (0-24): ", 0, 24);
        double duration = validateInput("Enter the duration factor (1-24): ", 1, 24);
        int iterations = validateInput("Enter the number of iterations (0-100) (keep it low to start): ", 0, 100);

        return new Parameters(rootNote, octaves, displacement, duration, iterations);
    }

    private int validateInput(String prompt, int min, int max) {
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