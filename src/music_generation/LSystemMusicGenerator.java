package music_generation;

public class LSystemMusicGenerator implements MusicGenerationStrategy {
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