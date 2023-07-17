import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FractalMusicFacade fractalMusicFacade = new FractalMusicFacade();

        System.out.println("Welcome to the Fractal-Music-Super-Inator-3000.");
        System.out.println("Please note the randomly generated music does not intentionally infringe upon any specific copyrights.");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean keepRunning = true;
            while (keepRunning) {
                Parameters parameters = new Parameters();

                System.out.print("Would you like to enter custom parameters? (Y/N): ");
                if (scanner.next().equalsIgnoreCase("Y")) {
                    UserInputReader userInputReader = new UserInputReader(scanner);
                    parameters = userInputReader.readCustomParameters();
                }

                fractalMusicFacade.generateMusicSequence(parameters);
                fractalMusicFacade.playMusicSequence();

                System.out.print("Do you want to save the generated music? (Y/N): ");
                if (scanner.next().equalsIgnoreCase("Y")) {
                    System.out.print("Enter the file path to save the music: ");
                    fractalMusicFacade.saveMusicSequence(scanner.next());
                }

                System.out.print("Do you want to generate another song? (Y/N): ");
                keepRunning = scanner.next().equalsIgnoreCase("Y");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}