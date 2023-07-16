import javax.sound.midi.*;

public class FractalMusicGenerator {
    public Sequence generateFractalMusic(Parameters p) throws InvalidMidiDataException {

        int[] notes = new int[p.getTotalNotes()];
        notes[0] = p.getRootNote();

        // Generate the fractal pattern
        for (int i = 1; i < p.getTotalNotes(); i++) {
            int nextNote = (int) (notes[i - 1] + Math.random() * p.getDisplacement() * 2 - p.getDisplacement());
            nextNote = Math.max(0, Math.min(127, nextNote)); // ensure note is within valid MIDI range
            notes[i] = nextNote;
        }

        // Create a new sequence and a track
        Sequence sequence = new Sequence(Sequence.PPQ, 24);
        Track track = sequence.createTrack();

        // Set the instrument to piano
        int channel = 0;
        track.add(createMidiEvent(ShortMessage.PROGRAM_CHANGE, channel, 0, 0));

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

    private MidiEvent createNoteOnEvent(int channel, int note, long tick) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.NOTE_ON, channel, note, 100);
        return new MidiEvent(message, tick);
    }

    private MidiEvent createNoteOffEvent(int channel, int note, long tick) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.NOTE_OFF, channel, note, 100);
        return new MidiEvent(message, tick);
    }

    private MidiEvent createMidiEvent(int command, int channel, int data1, int data2) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(command, channel, data1, data2);
        return new MidiEvent(message, 0);
    }
}
