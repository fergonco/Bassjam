package org.fergonco.bassjam;

public class JamContext {
	private int tempo;
	private Rythm rythm;
	private ChordProgression chordProgression;

	public JamContext(int tempo, Rythm rythm, ChordProgression chordProgression) {
		this.tempo = tempo;
		this.rythm = rythm;
		this.chordProgression = chordProgression;
	}

	public int getTempo() {
		return tempo;
	}

	public Rythm getRythm() {
		return rythm;
	}

	public ChordProgression getChordProgression() {
		return chordProgression;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public void setRythm(Rythm rythm) {
		this.rythm = rythm;
	}

	public void setChordProgression(ChordProgression chordProgression) {
		this.chordProgression = chordProgression;
	}

}
