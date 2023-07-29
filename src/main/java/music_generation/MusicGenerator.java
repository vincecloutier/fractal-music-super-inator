package music_generation;
import model.Parameters;

/**
 * Class responsible for generating music using a specified music generation strategy.
 */
public class MusicGenerator {

    private MusicGenerationStrategy strategy;

    public MusicGenerator() {
        this.strategy = new BrownianMusicGenerator();
    }

    /**
     * Sets the music generation strategy.
     * @param strategy the new strategy to be used for music generation
     */
    public void setStrategy(MusicGenerationStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Generates music based on the current music generation strategy and provided parameters.
     * @param p the parameters for music generation
     * @return an array of MIDI notes generated
     */
    public int[] generateMusic(Parameters p) {
        return strategy.generateMusic(p);
    }
}
