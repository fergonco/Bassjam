package org.fergonco.bassjam;

import org.fergonco.bassjam.midi.Track;

public class WholeNoteStyle implements Style {

	@Override
	public Track generateLine(JamContext jamContext, int instrument) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public InstrumentLine generateLine(int tempo, int voice, Rythm rythm,
	// ChordProgression chordProgression) {
	// StringBuilder ret = new StringBuilder("X[Volume]=10
	// T").append(tempo).append(" V").append(voice)
	// .append(" I[Acoustic_Bass] ");
	// for (int i = 0; i < chordProgression.length(); i++) {
	// Chord notes = chordProgression.getChord(i);
	// String note = notes.getNote((int) (Math.random() *
	// notes.getNoteCount()));
	// ret.append(note).append("2").append("w | ");
	// }
	//
	// return new InstrumentLine(ret.toString(), this, tempo, voice, rythm,
	// chordProgression);
	// }


}
