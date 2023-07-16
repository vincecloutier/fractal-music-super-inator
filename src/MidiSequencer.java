import javax.sound.midi.*;

public class MidiSequencer {

    public static Sequence createSequence(int[] notes, Parameters p) throws InvalidMidiDataException {
        // Create a new sequence and a track
        Sequence sequence = new Sequence(Sequence.PPQ, 24);
        Track track = sequence.createTrack();

        // Set the instrument to piano
        int channel = 0;
        track.add(setPianoMidiEvent(channel));

        // Add the notes to the MIDI track
        int tick = 0;
        for (int i = 0; i < p.getIterations(); i++) {
            for (int j = 0; j < p.getTotalNotes() - 1; j++) {
                int note = notes[j];
                int nextNote = notes[j + 1];

                // Calculate the duration based on the displacement
                long noteDuration = (long) (p.getDuration() * Math.abs(nextNote - note));

                // Add the note to the MIDI track
                track.add(createNoteOnEvent(channel, note, tick));
                track.add(createNoteOffEvent(channel, note, tick + noteDuration));

                tick += noteDuration;
            }
        }

        return sequence;
    }

    private static MidiEvent createNoteOnEvent(int channel, int note, long tick) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.NOTE_ON, channel, note, 100);
        return new MidiEvent(message, tick);
    }

    private static MidiEvent createNoteOffEvent(int channel, int note, long tick) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.NOTE_OFF, channel, note, 100);
        return new MidiEvent(message, tick);
    }

    private static MidiEvent setPianoMidiEvent(int channel) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.PROGRAM_CHANGE, channel, 0, 0);
        return new MidiEvent(message, 0);
    }
}
