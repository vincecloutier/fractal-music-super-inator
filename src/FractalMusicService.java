import javax.sound.midi.*;

public class FractalMusicService {
    private final MidiPlayer midiPlayer;
    private final FractalMusicGenerator fractalMusicGenerator;
    private Sequence sequence;

    public FractalMusicService() {
        try {
            midiPlayer = new MidiPlayer();
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
        fractalMusicGenerator = new FractalMusicGenerator();
    }

    public void generateAndPlayFractalMusic(int rootNote, int octaves, double displacement, double duration, int iterations) throws InvalidMidiDataException, MidiUnavailableException {
        sequence = fractalMusicGenerator.generateFractalMusic(rootNote, octaves, displacement, duration, iterations);
        midiPlayer.playSequence(sequence);
    }

    public void saveFractalMusic() throws Exception {
        MidiSystem.write(sequence, 1, new java.io.File("fractal_music.mid"));
    }
}
