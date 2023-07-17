import java.util.Scanner;

public class UserInputReader {
    private final Scanner scanner;

    public UserInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Parameters readCustomParameters() {
        int rootNote = validateInput("Enter the root note (0-127): ", 0, 127);
        int octaves = validateInput("Enter the number of octaves (1-7): ", 1, 7);
        double displacement = validateInput("Enter the displacement factor (0-24): ", 0, 24);
        double duration = validateInput("Enter the duration factor (1-24): ", 1, 24);
        int iterations = validateInput("Enter the number of iterations (0-100) (keep it low to start): ", 0, 100);
        return new Parameters(rootNote, octaves, displacement, duration, iterations);
    }

    public MusicGenerationStrategy readStrategyChoice() {
        System.out.println("(1) Brownian \n(2) Fractal Tree \n(3) L-System eg: F=F+F-");
        int strategyInt = validateInput("Enter your desired generation method (1-3): ", 1, 3);
        if (strategyInt == 1) {
            return new BrownianMusicGenerator();
        } else if (strategyInt == 2) {
            return new FractalTreeMusicGenerator();
        } else {
            return new LSystemMusicGenerator();
        }
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