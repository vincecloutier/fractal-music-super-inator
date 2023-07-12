import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FractalMusicService fractalMusicService = new FractalMusicService();

        System.out.print("Welcome to the Fractal-Music-Super-Inator-3000. \n");
        System.out.print("While the randomly generated music does not intentionally infringe upon any specific " +
                "copyrights or intellectual property rights, it's essential to be aware of the potential legal and ethical implications. " +
                "If you plan to use the generated music in any public or commercial context, it's advisable to consult a legal professional. \n");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Would you like to use default parameters? (Y/N): ");
            String defaultChoice = scanner.next();

            int rootNote, octaves, iterations;
            double displacement, duration;

            if (defaultChoice.equalsIgnoreCase("Y")) {
                // Define default parameters
                rootNote = 60;
                octaves = 7;
                displacement = 2;
                duration = 2;
                iterations = 2;
            } else {
                System.out.print("Enter the root note (0-127): ");
                rootNote = scanner.nextInt();
                System.out.print("Enter the number of octaves (1-7): ");
                octaves = scanner.nextInt();
                System.out.print("Enter the displacement factor (0-24): ");
                displacement = scanner.nextDouble();
                System.out.print("Enter the duration factor (1-24): ");
                duration = scanner.nextDouble();
                System.out.print("Enter the number of iterations: ");
                iterations = scanner.nextInt();
            }

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
}
