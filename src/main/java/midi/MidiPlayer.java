package midi;
import javax.sound.midi.*;

public class MidiPlayer {
    /**
     * Plays the specified MIDI sequence.
     *
     * @param sequence the MIDI sequence to be played
     * @throws MidiUnavailableException if a MIDI sequencer is not available
     * @throws InvalidMidiDataException if the MIDI sequence is invalid
     */
    public static void playSequence(Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException {
        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.open();
        sequencer.setSequence(sequence);
        sequencer.start();

        while (sequencer.isRunning()) {
            // Do nothing...
        }
        sequencer.stop();
        sequencer.close();
    }
}
