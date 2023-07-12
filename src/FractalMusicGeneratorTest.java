import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javax.sound.midi.*;

class FractalMusicGeneratorTest {

    @Test
    void generateFractalMusic_ValidParameters() {
        FractalMusicGenerator fractalMusicGenerator = new FractalMusicGenerator();
        try {
            int rootNote = 60;
            int octaves = 3;
            double displacement = 1;
            double duration = 2;
            int iterations = 5;

            Sequence sequence = fractalMusicGenerator.generateFractalMusic(rootNote, octaves, displacement, duration, iterations);

            Assertions.assertNotNull(sequence);
            Assertions.assertEquals(Sequence.PPQ, sequence.getDivisionType());
            Assertions.assertEquals(24, sequence.getResolution());

            Track[] tracks = sequence.getTracks();
            Assertions.assertEquals(1, tracks.length);

            Track track = tracks[0];
            Assertions.assertEquals(72, track.size());

            MidiEvent event = track.get(0);
            Assertions.assertEquals(ShortMessage.PROGRAM_CHANGE, event.getMessage().getStatus());
        } catch (InvalidMidiDataException e) {
            Assertions.fail("Invalid MIDI data exception occurred.");
        }
    }

    @Test
    void generateFractalMusic_InvalidParameters_DoesNotThrowException() {
        FractalMusicGenerator fractalMusicGenerator = new FractalMusicGenerator();
        int rootNote = 200; // Invalid note value
        int octaves = -3; // Invalid octaves value
        double displacement = -0.5; // Invalid displacement value
        double duration = 1.5; // Invalid duration value
        int iterations = 0; // Invalid iterations value

        Assertions.assertDoesNotThrow(() ->
                fractalMusicGenerator.generateFractalMusic(rootNote, octaves, displacement, duration, iterations)
        );
    }
}
