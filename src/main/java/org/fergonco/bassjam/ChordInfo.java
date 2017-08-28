package org.fergonco.bassjam;

import org.fergonco.bassjam.midi.Note;

public class ChordInfo {

	private int[] pitches;

	public ChordInfo(String chordString) {
		pitches = new int[chordString.length()];
		for (int i = 0; i < pitches.length; i++) {
			pitches[i] = getNote(Character.toString(chordString.charAt(i)));
		}
	}

	private int getNote(String code) {
		switch (code) {
		case "C":
			return Note.C4;
		case "D":
			return Note.D4;
		case "E":
			return Note.E4;
		case "F":
			return Note.F4;
		case "G":
			return Note.G4;
		case "A":
			return Note.A4;
		case "B":
			return Note.B4;
		default:
			throw new RuntimeException("Unsupported note");
		}
	}

	public int getPitch(int i) {
		return pitches[i];
	}

	public int getPitchCount() {
		return pitches.length;
	}

}
