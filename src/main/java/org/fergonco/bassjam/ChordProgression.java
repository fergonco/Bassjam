package org.fergonco.bassjam;

public class ChordProgression {

	private static ChordProgression[] options = new ChordProgression[] {
			new ChordProgression("CEG", "GBD", "ACE", "FAC"), //
			new ChordProgression("CEG", "GBD", "ACE", "FAC"), //
			new ChordProgression("CEG", "GBD", "ACE", "FAC") //
	};
	
	private ChordInfo[] chords;

	public ChordProgression(String... chords) {
		this.chords = new ChordInfo[chords.length];
		for (int i = 0; i < chords.length; i++) {
			this.chords[i] = new ChordInfo(chords[i]);
		}
	}

	public static ChordProgression newRandom() {
		int index = (int) (Math.random() * options.length);
		return options[index];
	}

	public int length() {
		return chords.length;
	}

	public ChordInfo getChord(int i) {
		return chords[i];
	}

}
