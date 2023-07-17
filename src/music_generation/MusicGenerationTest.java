package music_generation;
import model.Parameters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MusicGenerationTest {

    @Test
    public void testBrownianMusicGenerator() {
        Parameters p = new Parameters(60, 7, 2.0, 2.0, 1);
        BrownianMusicGenerator generator = new BrownianMusicGenerator();
        int[] music = generator.generateMusic(p);
        assertEquals(p.getTotalNotes(), music.length);
    }

    @Test
    public void testFractalTreeMusicGenerator() {
        Parameters p = new Parameters(60, 7, 2.0, 2.0, 1);
        FractalTreeMusicGenerator generator = new FractalTreeMusicGenerator();
        int[] music = generator.generateMusic(p);
        assertEquals(p.getTotalNotes(), music.length);
    }

    @Test
    public void testLSystemMusicGenerator() {
        Parameters p = new Parameters(60, 7, 2.0, 2.0, 1);
        LSystemMusicGenerator generator = new LSystemMusicGenerator();
        int[] music = generator.generateMusic(p);
        assertEquals(p.getTotalNotes(), music.length);
    }

    @Test
    public void testMusicGenerator() {
        Parameters p = new Parameters(60, 7, 2.0, 2.0, 1);
        MusicGenerator generator = new MusicGenerator();
        generator.setStrategy(new LSystemMusicGenerator());
        int[] music = generator.generateMusic(p);
        assertEquals(p.getTotalNotes(), music.length);
    }
}
