package music_generation;
import model.Parameters;

public class BrownianMusicGenerator implements MusicGenerationStrategy {
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