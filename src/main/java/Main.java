import midi.MidiFacade;
import model.Parameters;
import music_generation.*;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MidiFacade midiFacade = new MidiFacade();
    private static final MusicGenerator generator = new MusicGenerator();

    public static void main(String[] args) {
        printWelcomeMessage();

        do {
            MusicGenerationStrategy strategy = readStrategyChoice();
            Parameters parameters = readParameters();

            generator.setStrategy(strategy);
            int[] notes = generator.generateMusic(parameters);

            midiFacade.generateMusicSequence(notes, parameters);
            midiFacade.playMusicSequence();

            if (confirmUserChoice("Do you want to save the generated music? (Y/N): ")) {
                System.out.print("Enter the file path to save the music: ");
                midiFacade.saveMusicSequence(scanner.next());
            }

        } while (confirmUserChoice("Do you want to generate another song? (Y/N): "));
    }

    private static void printWelcomeMessage() {
        System.out.println("Welcome to the Fractal-Music-Super-Inator-3000.");
        System.out.println("Please note the randomly generated music does not intentionally infringe upon any specific copyrights.");
    }

    private static MusicGenerationStrategy readStrategyChoice() {
        System.out.println("(1) Brownian \n(2) Fractal Tree \n(3) L-System eg: F=F+F-");
        int strategyInt = validateInput("Enter your desired generation method (1-3): ", 1, 3);

        switch (strategyInt) {
            case 1: return new BrownianMusicGenerator();
            case 2: return new FractalTreeMusicGenerator();
            case 3: default: return new LSystemMusicGenerator();
        }
    }

    private static Parameters readParameters() {
        if (confirmUserChoice("Would you like to enter custom parameters? (Y/N): ")) {
            return new Parameters(
                    validateInput("Enter the root note (0-127): ", 0, 127),
                    validateInput("Enter the number of octaves (1-7): ", 1, 7),
                    validateInput("Enter the displacement factor (0-24): ", 0, 24),
                    validateInput("Enter the duration factor (1-24): ", 1, 24),
                    validateInput("Enter the number of iterations (0-20): ", 0, 20)
            );
        } else {
            return new Parameters();
        }
    }

    private static int validateInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            int input = scanner.nextInt();
            if (input >= min && input <= max) {
                return input;
            }
            System.out.printf("Invalid input! Please enter a value between %d and %d.%n", min, max);
        }
    }

    private static boolean confirmUserChoice(String prompt) {
        System.out.print(prompt);
        return scanner.next().equalsIgnoreCase("Y");
    }
}
