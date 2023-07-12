import javax.sound.midi.*;

public class MidiPlayer {
    private final Sequencer sequencer;

    public MidiPlayer() throws MidiUnavailableException {
        sequencer = MidiSystem.getSequencer();
    }

    public void playSequence(Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException {
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
