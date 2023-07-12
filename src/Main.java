import javax.sound.midi.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Create a new sequence and a track
            Sequence sequence = new Sequence(Sequence.PPQ, 24);
            Track track = sequence.createTrack();

            // Set the instrument to piano
            int channel = 0;
            track.add(createMidiEvent(ShortMessage.PROGRAM_CHANGE, channel, 0, 0));

            // Get user input for fractal parameters
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the root note (0-127): ");
            int rootNote = scanner.nextInt();
            System.out.print("Enter the number of octaves: ");
            int octaves = scanner.nextInt();
            System.out.print("Enter the displacement factor (0-24): ");
            int displacement = scanner.nextInt();
            System.out.print("Enter the duration factor (1-24): ");
            int duration = scanner.nextInt();
            System.out.print("Enter the number of iterations: ");
            int iterations = scanner.nextInt();

            // Generate fractal music
            generateFractalMusic(track, channel, rootNote, octaves, displacement, duration, iterations);

            // Play the generated music
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.setSequence(sequence);
            sequencer.open();
            sequencer.start();

            // Ask the user if they want to save the music
            System.out.print("Do you want to save the generated music? (Y/N): ");
            String saveChoice = scanner.next();
            if (saveChoice.equalsIgnoreCase("Y")) {
                // Save the sequence to a MIDI file
                MidiSystem.write(sequence, 1, new java.io.File("fractal_music.mid"));
                System.out.println("Fractal music saved.");
            } else {
                System.out.println("Fractal music not saved.");
            }

            // Stop and close the sequencer
            sequencer.stop();
            sequencer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateFractalMusic(Track track, int channel, int rootNote, int octaves, int displacement, int duration, int iterations) throws InvalidMidiDataException {
        int totalNotes = (int) Math.pow(2, octaves);

        int[] notes = new int[totalNotes];
        notes[0] = rootNote;

        // Generate the fractal pattern
        for (int i = 1; i < totalNotes; i++) {
            notes[i] = (int) (notes[i - 1] + Math.random() * displacement * 2 - displacement);
        }

        // Add the notes to the MIDI track
        int tick = 0;
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < totalNotes - 1; j++) {
                int note = notes[j];

                // Add the note to the MIDI track
                track.add(createNoteOnEvent(channel, note, tick));
                track.add(createNoteOffEvent(channel, note, tick + duration));

                tick += duration;
            }
        }
    }

    private static MidiEvent createNoteOnEvent(int channel, int note, long tick) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.NOTE_ON, channel, note, 100);
        return new MidiEvent(message, tick);
    }

    private static MidiEvent createNoteOffEvent(int channel, int note, long tick) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.NOTE_OFF, channel, note, 0);
        return new MidiEvent(message, tick);
    }

    private static MidiEvent createMidiEvent(int command, int channel, int data1, int data2) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(command, channel, data1, data2);
        return new MidiEvent(message, 0);
    }
}
