package org.fergonco.bassjam.midi;

import static org.fergonco.bassjam.midi.ByteUtils.bytes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Track {

	private ArrayList<Note> notes = new ArrayList<>();
	private int instrument = 0;

	public void write(int microsecondsPerQuarterNote, int ticksPerQuarterNote, int trackIndex, OutputStream os)
			throws IOException {
		os.write("MTrk".getBytes());

		byte[] trackBytes = getTrackBytes(microsecondsPerQuarterNote, ticksPerQuarterNote, trackIndex);
		os.write(bytes(trackBytes.length));

		os.write(trackBytes);
	}

	private byte[] getTrackBytes(int microsecondsPerQuarterNote, int ticksPerQuarterNote, int trackIndex)
			throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		if (trackIndex == 0) {
			// Tempo metaevent
			os.write(0x00);
			os.write(0xFF);
			os.write(0x51);
			os.write(0x03);
			byte[] MPQ = bytes(microsecondsPerQuarterNote);
			os.write(MPQ, 1, 3);
		}

		if (instrument != 0) {
			os.write(0);
			os.write(0xc0 + trackIndex);
			os.write(instrument);
		}

		// Note sequence
		for (Note note : notes) {
			note.write(trackIndex, ticksPerQuarterNote, os);
		}

		// track out
		os.write(0x00);
		os.write(0xFF);
		os.write(0x2F);
		os.write(0x00);

		return os.toByteArray();
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public void setInstrument(int instrument) {
		this.instrument = instrument;
	}

}
