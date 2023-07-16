import javax.sound.midi.*;

public class MidiPlayer {
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
