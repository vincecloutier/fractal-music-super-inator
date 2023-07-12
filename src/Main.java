import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FractalMusicService fractalMusicService = new FractalMusicService();

        System.out.println("Welcome to the Fractal-Music-Super-Inator-3000.");
        System.out.println("Please note the randomly generated music does not intentionally infringe upon any specific copyrights.");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Would you like to use default parameters? (Y/N): ");
            String defaultChoice = scanner.next();

            int rootNote = defaultChoice.equalsIgnoreCase("Y") ? 60 :
                    validateInput(scanner, "Enter the root note (0-127): ", 0, 127);
            int octaves = defaultChoice.equalsIgnoreCase("Y") ? 6 :
                    validateInput(scanner, "Enter the number of octaves (1-7): ", 1, 7);
            double displacement = defaultChoice.equalsIgnoreCase("Y") ? 2 :
                    validateInput(scanner, "Enter the displacement factor (0-24): ", 0, 24);
            double duration = defaultChoice.equalsIgnoreCase("Y") ? 2 :
                    validateInput(scanner, "Enter the duration factor (1-24): ", 1, 24);
            int iterations = defaultChoice.equalsIgnoreCase("Y") ? 1 :
                    validateInput(scanner, "Enter the number of iterations (0-100) (but keep it low to start): ", 0, 100);

            fractalMusicService.generateAndPlayFractalMusic(rootNote, octaves, displacement, duration, iterations);

            System.out.print("Do you want to save the generated music? (Y/N): ");
            String saveChoice = scanner.next();
            if (saveChoice.equalsIgnoreCase("Y")) {
                System.out.print("Enter the file path to save the music: ");
                String filePath = scanner.next();
                fractalMusicService.saveFractalMusic(filePath);
                System.out.println("Fractal music saved.");
            } else {
                System.out.println("Fractal music not saved.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int validateInput(Scanner scanner, String msg, int start, int end) {
        while (true) {
            System.out.print(msg);
            int input = scanner.nextInt();
            if (input >= start && input <= end) {
                return input;
            } else {
                System.out.println("Invalid input!");
            }
        }
    }
}