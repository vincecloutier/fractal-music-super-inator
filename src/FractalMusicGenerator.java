public class FractalMusicGenerator {

    private MusicGenerationStrategy strategy;

    public FractalMusicGenerator(MusicGenerationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(MusicGenerationStrategy strategy) {
        this.strategy = strategy;
    }

    public int[] generateMusic(Parameters p) {
        return strategy.generateMusic(p);
    }
}