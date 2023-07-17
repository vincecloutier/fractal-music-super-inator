package music_generation;
import model.Parameters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MusicGenerationTest {

    @Test
    public void testBrownianMusicGenerator() {
        testMusicGenerator(new BrownianMusicGenerator());
    }

    @Test
    public void testFractalTreeMusicGenerator() {
        testMusicGenerator(new FractalTreeMusicGenerator());
    }

    @Test
    public void testLSystemMusicGenerator() {
        testMusicGenerator(new LSystemMusicGenerator());
    }

    private void testMusicGenerator(MusicGenerationStrategy strategy) {
        Parameters p = new Parameters(60, 7, 2.0, 2.0, 1);
        MusicGenerator generator = new MusicGenerator();
        generator.setStrategy(strategy);
        int[] music = generator.generateMusic(p);
        assertEquals(p.getTotalNotes(), music.length);
    }
}
