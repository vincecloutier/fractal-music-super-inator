package music_generation;
import model.Parameters;

public class BrownianMusicGenerator implements MusicGenerationStrategy {
    /**
     * Generates a sequence of notes based on Brownian motion.
     * The initial note is defined by the root note in the parameters. 
     * Subsequent notes are calculated by adding a random displacement to the previous note.
     * The displacement is limited to a maximum and minimum defined by the parameters.
     *
     * @param p the parameters for music generation, including root note, total notes, and displacement
     * @return an array of MIDI notes generated
     */
    @Override
    public int[] generateMusic(Parameters p) {
        int[] notes = new int[p.getTotalNotes()];
        notes[0] = p.getRootNote();

        // Generate the fractal pattern
        for (int i = 1; i < p.getTotalNotes(); i++) {
            int nextNote = (int) (notes[i - 1] + Math.random() * p.getDisplacement() * 2 - p.getDisplacement());
            nextNote = Math.max(0, Math.min(127, nextNote)); // ensure note is within valid MIDI range
            notes[i] = nextNote;
        }
        return notes;
    }
}