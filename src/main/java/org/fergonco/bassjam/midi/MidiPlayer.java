package org.fergonco.bassjam.midi;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class MidiPlayer {

	public static void main(String[] args) throws Exception {
//		play(new File("/tmp/twotracks.mid"));
		 play(new File("/tmp/a.mid"));
	}

	public static void play(File file) throws MidiUnavailableException, IOException, InvalidMidiDataException {
		Sequencer sequencer = MidiSystem.getSequencer();
		sequencer.open();
		InputStream is = new BufferedInputStream(new FileInputStream(file));
		sequencer.setSequence(is);
		sequencer.start();
	}
}
