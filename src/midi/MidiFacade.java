package midi;
import model.Parameters;
import javax.sound.midi.*;
import java.io.IOException;

public class MidiFacade {
    private Sequence sequence;

    /**
     * Generates a MIDI music sequence based on the given notes and parameters.
     *
     * @param notes      an array of integers representing the MIDI notes
     * @param parameters the parameters to be used for generating the music sequence
     */
    public void generateMusicSequence(int[] notes, Parameters parameters) {
        try {
            this.sequence = MidiSequencer.createSequence(notes, parameters);
        } catch (InvalidMidiDataException e) {
            System.out.println("There was an issue with generating the music.");
        }
    }

    /**
     * Plays the generated music sequence.
     * If no sequence has been generated, this method does nothing.
     */
    public void playMusicSequence()  {
        try {
            MidiPlayer.playSequence(this.sequence);
        } catch (IllegalStateException e) {
            System.out.println("Sequence has not been generated yet.");
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            System.out.println("There was an issue with playing the music.");
        }
    }

    /**
     * Saves the generated music sequence to a MIDI file at the specified file path.
     *
     * @param filePath the path of the file to save the music sequence to
     */
    public void saveMusicSequence(String filePath)  {
        try {
            MidiSystem.write(sequence, 1, new java.io.File(filePath));
            System.out.println("Fractal music saved.");
        } catch (IOException e) {
            System.out.println("There was an issue with saving the music.");
        }
    }

    public Sequence getSequence() {
        return sequence;
    }
}
