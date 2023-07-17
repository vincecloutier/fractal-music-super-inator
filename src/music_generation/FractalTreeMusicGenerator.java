package music_generation;
import model.Parameters;

public class FractalTreeMusicGenerator implements MusicGenerationStrategy {
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
