public class Parameters {
    private final int rootNote;
    private final int octaves;
    private final double displacement;
    private final double duration;
    private final int iterations;
    private final int totalNotes;

    public Parameters(int rootNote, int octaves, double displacement, double duration, int iterations) {
        this.rootNote = rootNote;
        this.octaves = octaves;
        this.displacement = displacement;
        this.duration = duration;
        this.iterations = iterations;
        int tn = (int) Math.pow(2, octaves);  // Calculate real totalNotes
        this.totalNotes = Math.max(1, tn); // Ensure total notes is at least 1
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