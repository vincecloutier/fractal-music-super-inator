package midi;
import model.Parameters;
import javax.sound.midi.*;

public class MidiSequencer {
    private static final int SEQUENCE_RESOLUTION = 24;
    private static final int PIANO_INSTRUMENT = 0;
    private static final int VELOCITY = 100;
    private static final int CHANNEL = 0;

    /**
     * Creates a MIDI sequence based on the given notes and parameters.
     *
     * @param notes      an array of integers representing the MIDI notes
     * @param p the parameters to be used for creating the MIDI sequence
     * @return the created MIDI sequence
     * @throws InvalidMidiDataException if the MIDI data is invalid
     */
    public static Sequence createSequence(int[] notes, Parameters p) throws InvalidMidiDataException {
        Sequence sequence = new Sequence(Sequence.PPQ, SEQUENCE_RESOLUTION);
        Track track = sequence.createTrack();

        // set instrument to piano
        track.add(createMidiEvent(ShortMessage.PROGRAM_CHANGE, PIANO_INSTRUMENT, 0, 0));

        int tick = 0;
        for (int i = 0; i < p.getIterations(); i++) {
            for (int j = 0; j < p.getTotalNotes() - 1; j++) {
                int note = notes[j];
                int nextNote = notes[j + 1];
                long noteDuration = (long) (p.getDuration() * Math.abs(nextNote - note));
                track.add(createMidiEvent(ShortMessage.NOTE_ON, note, VELOCITY, tick)); // note on
                track.add(createMidiEvent(ShortMessage.NOTE_OFF, note, VELOCITY, tick + noteDuration)); // note off
                tick += noteDuration;
            }
        }

        return sequence;
    }

    /**
     * Creates a MIDI event with the specified parameters.
     *
     * @param command  the MIDI command for the event (e.g., ShortMessage.NOTE_ON)
     * @param note     the MIDI note for the event
     * @param velocity the velocity of the event
     * @param tick     the tick at which the event occurs in the sequence
     * @return the created MIDI event
     * @throws InvalidMidiDataException if the MIDI data is invalid
     */
    private static MidiEvent createMidiEvent(int command, int note, int velocity, long tick) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(command, MidiSequencer.CHANNEL, note, velocity);
        return new MidiEvent(message, tick);
    }
}
