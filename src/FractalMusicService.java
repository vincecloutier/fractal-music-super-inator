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

    public void generateAndPlayFractalMusic(Parameters parameters) throws InvalidMidiDataException, MidiUnavailableException {
        sequence = fractalMusicGenerator.generateFractalMusic(parameters);
        midiPlayer.playSequence(sequence);
    }

    public void saveFractalMusic(String filePath) throws Exception {
        MidiSystem.write(sequence, 1, new java.io.File(filePath));
    }
}
