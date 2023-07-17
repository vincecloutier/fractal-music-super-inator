package music_generation;

public class MusicGenerator {

    private MusicGenerationStrategy strategy;

    public MusicGenerator() {
        this.strategy = new BrownianMusicGenerator();
    }

    public void setStrategy(MusicGenerationStrategy strategy) {
        this.strategy = strategy;
    }

    public int[] generateMusic(Parameters p) {
        return strategy.generateMusic(p);
    }
}
