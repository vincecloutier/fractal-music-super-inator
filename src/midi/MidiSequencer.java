package midi;

import music_generation.Parameters;
import javax.sound.midi.*;

public class MidiSequencer {
    private static final int SEQUENCE_RESOLUTION = 24;
    private static final int PIANO_INSTRUMENT = 0;
    private static final int VELOCITY = 100;
    private static final int CHANNEL = 0;

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

    private static MidiEvent createMidiEvent(int command, int note, int velocity, long tick) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(command, MidiSequencer.CHANNEL, note, velocity);
        return new MidiEvent(message, tick);
    }
}
