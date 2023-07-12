public class Parameters {
    private final int rootNote;
    private final int octaves;
    private final double displacement;
    private final double duration;
    private final int iterations;

    public Parameters(int rootNote, int octaves, double displacement, double duration, int iterations) {
        this.rootNote = rootNote;
        this.octaves = octaves;
        this.displacement = displacement;
        this.duration = duration;
        this.iterations = iterations;
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
}