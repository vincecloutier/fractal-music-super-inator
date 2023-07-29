package music_generation;
import model.Parameters;

public interface MusicGenerationStrategy {
    int[] generateMusic(Parameters p);
}
