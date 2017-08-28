package org.fergonco.bassjam;

import org.fergonco.bassjam.midi.Chord;
import org.fergonco.bassjam.midi.Duration;
import org.fergonco.bassjam.midi.Dynamic;
import org.fergonco.bassjam.midi.Track;

public class ChordsStyle implements Style {

	@Override
	public Track generateLine(JamContext jamContext, int instrument) {
		Track track = new Track();
		track.setInstrument(instrument);
		ChordProgression chordProgression = jamContext.getChordProgression();
		for (int i = 0; i < chordProgression.length(); i++) {
			ChordInfo chord = chordProgression.getChord(i);
			int[] pitchArray = new int[chord.getPitchCount()];
			for (int j = 0; j < chord.getPitchCount(); j++) {
				pitchArray[j] = chord.getPitch(j);
			}
			track.addNote(new Chord(Duration.WHOLE, Dynamic.F, pitchArray));
		}

		return track;

	}

}
