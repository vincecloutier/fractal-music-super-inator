import javax.sound.midi.*;
import java.io.IOException;

public class FractalMusicFacade {
    private Sequence sequence;

    public void generateMusicSequence(Parameters parameters)  {
        try {
            int[] notes = FractalMusicGenerator.generateFractalMusic(parameters);
            this.sequence = MidiSequencer.createSequence(notes, parameters);
        } catch (InvalidMidiDataException e) {
            System.out.println("There was an issue with generating the music.");
        }
    }

    public void playMusicSequence()  {
        try {
            MidiPlayer.playSequence(this.sequence);
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            System.out.println("There was an issue with playing the music.");
        }
    }

    public void saveMusicSequence(String filePath)  {
        try {
            MidiSystem.write(sequence, 1, new java.io.File(filePath));
            System.out.println("Fractal music saved.");
        } catch (IOException e) {
            System.out.println("There was an issue with saving the music.");
        }
    }
}
