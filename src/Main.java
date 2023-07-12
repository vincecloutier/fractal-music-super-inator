import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FractalMusicService fractalMusicService = new FractalMusicService();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the root note (0-127): ");
            int rootNote = scanner.nextInt();
            System.out.print("Enter the number of octaves (1-7): ");
            int octaves = scanner.nextInt();
            System.out.print("Enter the displacement factor (0-24): ");
            int displacement = scanner.nextInt();
            System.out.print("Enter the duration factor (1-24): ");
            int duration = scanner.nextInt();
            System.out.print("Enter the number of iterations: ");
            int iterations = scanner.nextInt();

            fractalMusicService.generateAndPlayFractalMusic(rootNote, octaves, displacement, duration, iterations);

            System.out.print("Do you want to save the generated music? (Y/N): ");
            String saveChoice = scanner.next();
            if (saveChoice.equalsIgnoreCase("Y")) {
                fractalMusicService.saveFractalMusic();
                System.out.println("Fractal music saved.");
            } else {
                System.out.println("Fractal music not saved.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
