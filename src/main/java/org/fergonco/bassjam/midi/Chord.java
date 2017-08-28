package org.fergonco.bassjam.midi;

import java.io.IOException;
import java.io.OutputStream;

public class Chord extends AbstractNote implements Note {

	private int[] pitches;
	private Duration duration;
	private int volume;

	public Chord(Duration duration, int volume, int... pitches) {
		this.duration = duration;
		this.volume = volume;
		this.pitches = pitches;
	}

	@Override
	public void write(int channel, int ticksPerQuarterNote, OutputStream os) throws IOException {
		for (int pitch : pitches) {
			// timestamp
			write(os, 0);
			// event type
			os.write(NOTE_ON * 16 + channel);
			// note
			os.write(pitch);
			// volume
			os.write(volume);
		}
		int noteLength = duration.getTicks(ticksPerQuarterNote);
		for (int pitch : pitches) {
			// length
			write(os, noteLength);
			noteLength = 0;
			// note
			os.write(pitch);
			// volume
			os.write(0);
		}

	}

	@Override
	public void transpose(int distance) {
		for (int i = 0; i < pitches.length; i++) {
			pitches[i] += distance;
		}
	}

}
