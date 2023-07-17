package model;

public class Parameters {
    private final int rootNote;
    private final int octaves;
    private final double displacement;
    private final double duration;
    private final int iterations;
    private final int totalNotes;


    /**
     * Constructs Parameters with specific values for music generation.
     * @param rootNote the root note from which the music starts
     * @param octaves the number of octaves used in the music
     * @param displacement the displacement value for music generation algorithms
     * @param duration the duration of each note in the music
     * @param iterations the number of iterations for music generation algorithms
     */
    public Parameters(int rootNote, int octaves, double displacement, double duration, int iterations) {
        this.rootNote = rootNote;
        this.octaves = octaves;
        this.displacement = displacement;
        this.duration = duration;
        this.iterations = iterations;
        int tn = (int) Math.pow(2, octaves);  // Calculate real totalNotes
        this.totalNotes = Math.max(1, tn); // Ensure total notes is at least 1
    }

    /**
     * Constructs Parameters with default values.
     */
    public Parameters() {
        this.rootNote = 60;
        this.octaves = 7;
        this.displacement = 2.0;
        this.duration = 2.0;
        this.iterations = 1;
        this.totalNotes = 128;
    }

    public int getRootNote() {
        return rootNote;
    }

    public int getOctaves() {
        return octaves;
    }

    public double getDisplacement() {
        return displacement;
    }

    public double getDuration() {
        return duration;
    }

    public int getIterations() {
        return iterations;
    }

    public int getTotalNotes() {return totalNotes;}
}