package music_generation;
import model.Parameters;

public class LSystemMusicGenerator implements MusicGenerationStrategy {
    /**
     * Generates a sequence of notes based on an L-system rule.
     * The initial note is defined by the root note in the parameters.
     * Subsequent notes are calculated by applying an L-system rule (F -> FF+).
     * Every second note repeats the previous note and the others increment by one.
     * This ensures the notes are within the valid MIDI range.
     *
     * @param p the parameters for music generation, including root note and total notes
     * @return an array of MIDI notes generated
     */
    @Override
    public int[] generateMusic(Parameters p) {
        int[] notes = new int[p.getTotalNotes()];
        notes[0] = p.getRootNote();

        // L-system rule: F -> FF+
        for (int i = 1; i < p.getTotalNotes(); i++) {
            if(i % 2 == 0) {
                notes[i] = notes[i-1] + 1; // F -> FF+
            } else {
                notes[i] = notes[i-1]; // F -> FF+
            }
            notes[i] = Math.max(0, Math.min(127, notes[i])); // ensure note is within valid MIDI range
        }
        return notes;
    }
}