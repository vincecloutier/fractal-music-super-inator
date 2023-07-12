import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FractalMusicService fractalMusicService = new FractalMusicService();

        System.out.println("Welcome to the Fractal-Music-Super-Inator-3000.");
        System.out.println("Please note the randomly generated music does not intentionally infringe upon any specific copyrights.");
        System.out.println("Just leave any prompt blank for a default value.");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean keepRunning = true;
            while (keepRunning) {
                UserInputReader userInputReader = new UserInputReader(scanner);
                Parameters parameters = userInputReader.readUserInput();

                fractalMusicService.generateAndPlayFractalMusic(
                        parameters.getRootNote(),
                        parameters.getOctaves(),
                        parameters.getDisplacement(),
                        parameters.getDuration(),
                        parameters.getIterations()
                );

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

                System.out.print("Do you want to generate another song? (Y/N): ");
                String continueChoice = scanner.next();
                keepRunning = continueChoice.equalsIgnoreCase("Y");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}