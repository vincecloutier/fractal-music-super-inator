package midi;

import model.Parameters;
import music_generation.BrownianMusicGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import javax.sound.midi.Sequence;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class MidiFacadeTest {
    private static final String TEST_FILE_PATH = "test.mid";

    @AfterEach
    public void cleanup() {
        // Deletes the test file after each test, if it exists
        try {
            Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
        } catch (Exception e) {
            System.out.println("An error occurred during cleanup: " + e.getMessage());
        }
    }

    @Test
    public void testGenerateMusicSequence() {
        MidiFacade facade = new MidiFacade();
        Parameters parameters = new Parameters();
        BrownianMusicGenerator generator = new BrownianMusicGenerator();
        int[] notes = generator.generateMusic(parameters);
        facade.generateMusicSequence(notes, parameters);
        Sequence sequence = facade.getSequence();
        assertNotNull(sequence, "Sequence should not be null after generation");
    }

    @Test
    public void testSaveMusicSequence() {
        MidiFacade facade = new MidiFacade();
        Parameters parameters = new Parameters();
        BrownianMusicGenerator generator = new BrownianMusicGenerator();
        int[] notes = generator.generateMusic(parameters);
        facade.generateMusicSequence(notes, parameters);
        facade.saveMusicSequence(TEST_FILE_PATH);
        assertTrue(Files.exists(Paths.get(TEST_FILE_PATH)), "File should exist after saving");
    }

    @Test
    public void testPlayMusicSequence() {
        MidiFacade facade = new MidiFacade();
        assertDoesNotThrow(facade::playMusicSequence,
                "No exception should be thrown when playing music without having generated a sequence");
    }
}
