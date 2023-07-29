package music_generation;
import model.Parameters;

public class FractalTreeMusicGenerator implements MusicGenerationStrategy {
    /**
     * Generates a sequence of notes based on a fractal tree pattern.
     * The initial note is defined by the root note in the parameters.
     * Subsequent notes are calculated by using a random branching factor and adding a random displacement
     * to the parent note. The displacement is limited to a maximum and minimum defined by the parameters.
     *
     * @param p the parameters for music generation, including root note, total notes, and displacement
     * @return an array of MIDI notes generated
     */
    @Override
    public int[] generateMusic(Parameters p) {
        int[] notes = new int[p.getTotalNotes()];
        notes[0] = p.getRootNote();

        // Generate the fractal tree pattern
        for (int i = 1; i < p.getTotalNotes(); i++) {
            int branchingFactor = 1 + (int)(Math.random() * 3);  // Random branching factor between 1 and 3
            int parentIndex = (i - 1) / branchingFactor;
            int nextNote = (int) (notes[parentIndex] + (Math.random() * p.getDisplacement() * 2 - p.getDisplacement()));
            nextNote = Math.max(0, Math.min(127, nextNote)); // ensure note is within valid MIDI range
            notes[i] = nextNote;
        }
        return notes;
    }
}
