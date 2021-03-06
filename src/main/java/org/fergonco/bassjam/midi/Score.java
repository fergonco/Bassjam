package org.fergonco.bassjam.midi;

import static org.fergonco.bassjam.midi.ByteUtils.bytes;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Score {

	private OutputStream os;
	private ArrayList<Track> tracks = new ArrayList<>();
	private int ticksPerQuarterNote = 128;
	private int microsecondsPerQuarterNote;

	public Score(File file) throws FileNotFoundException {
		// Write a type 1 midi
		// write a channel for each instrument
		os = new BufferedOutputStream(new FileOutputStream(file));
		setTempo(80);
	}

	public void write() throws IOException {
		try {
			os.write("MThd".getBytes());
			// standard midi file
			os.write(new byte[] { 0, 0, 0, 6 });
			// type
			os.write(new byte[] { 0, 1 });
			// number of tracks
			os.write(bytes((short) tracks.size()));
			// speed of music: ticks per quarter note
			os.write(bytes((short) ticksPerQuarterNote));

			for (int i = 0; i < tracks.size(); i++) {
				tracks.get(i).write(microsecondsPerQuarterNote, ticksPerQuarterNote, i, os);
			}
		} finally {
			os.close();
		}
	}

	public void addTracks(Track... tracks) {
		for (Track track : tracks) {
			this.tracks.add(track);
		}
	}

	public void setTempo(int tempo) {
		microsecondsPerQuarterNote = (int) (60000000.0 / tempo);
	}

	private int sixteenth() {
		return ticksPerQuarterNote / 4;
	}

	private int eighth() {
		return ticksPerQuarterNote / 2;
	}

	private int quarter() {
		return ticksPerQuarterNote;
	}

	private int half() {
		return ticksPerQuarterNote * 2;
	}

	private int whole() {
		return ticksPerQuarterNote * 4;
	}

	public static void main(String[] args) throws Exception {
		File file = new File("/tmp/a.mid");
		Score score = new Score(file);
		score.setTempo(60);
		Track track = new Track();
		track.setInstrument(Instrument.PIANO);
		for (int i = 0; i < 10; i++) {
			track.addNote(new NoteImpl(Note.C4, Duration.QUARTER, 127));
			track.addNote(new NoteImpl(Note.E4, Duration.EIGHTH, 127));
			track.addNote(new NoteImpl(Note.G4, Duration.EIGHTH.dot(), 127));
			track.addNote(new Chord(Duration.QUARTER, 127, Note.C4, Note.E4, Note.G4));
		}
		score.addTracks(track);
		track = new Track();
		track.setInstrument(Instrument.ACOUSTIC_GUITAR);
		track.addNote(new NoteImpl(Note.C4, Duration.SIXTEENTH, 0));
		for (int i = 0; i < 10; i++) {
			track.addNote(new NoteImpl(Note.C4, Duration.QUARTER, 127));
			track.addNote(new NoteImpl(Note.E4, Duration.EIGHTH, 127));
			track.addNote(new NoteImpl(Note.G4, Duration.EIGHTH.dot(), 127));
			track.addNote(new Chord(Duration.QUARTER, 127, Note.C4, Note.E4, Note.G4));
		}
		score.addTracks(track);
		score.write();
		MidiPlayer.play(file);
	}

}
