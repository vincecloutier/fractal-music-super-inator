## Fractal-Music-Super-Inator-3000
### Overview
This Java app utilizes algorithmic composition techniques to generate MIDI music.
Users can create music using a variety of algorithms such as Brownian motion, fractal trees, and L-systems.

### User Stories
1. Billy Bob doesn't know much about music, but he wants a tool that can generate unique and interesting sounds without the need for musical composition knowledge.
2. Friar Tuck is a top musician. He loves using randomly generated samples on his pop hits, so he needs to be able to export his sounds as Midi files.

### Use Cases
1. **Generate Music**: Users can generate music by providing a set of parameters.
2. **Play Music**: Users can listen to the generated music sequence within the application.
3. **Save Music**: Users can save the generated music sequence to a MIDI file for further editing in other music software.

### Design Patterns
The application uses the Strategy Pattern in the form of different music generation algorithms (see the `music_generation` package).
This allows for easy extension of the application with new algorithms without modifying the existing codebase. <br>
We also use the Facade pattern to make it easier to control various Midi interactions while respecting the SRP (see the `midi` package). 

### Development Environment
The application was developed using `OpenJDK-18`. <br>
The unit tests for the application were written using the `JUnit 5.8.1` testing framework.

### Code Quality
There are currently no significant code smells and while the application adheres to most of the principles of SOLID and CA, we do have some violations:
- **ISP**: We force `MidiSequencer` and the `MusicGenerator`'s to use parameter's they don't really need.
  - We could probably solve this by creating two separate objects or by increasing coupling (so that we can pass only the parameters needed) 
  but I felt neither was really necessary.  
- **CA**: We don't have an explicit CA structure, and our packages are organized by Use Case not level.
  - Despite this, I feel that the heart of CA is to depend only upwards in level, which is something we do conform to.
  This is why it didn't feel necessary to add the additional complexity of a formal CA structure.



