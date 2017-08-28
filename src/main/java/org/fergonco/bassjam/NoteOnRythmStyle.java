package org.fergonco.bassjam;

import org.fergonco.bassjam.midi.Duration;
import org.fergonco.bassjam.midi.Dynamic;
import org.fergonco.bassjam.midi.Note;
import org.fergonco.bassjam.midi.NoteImpl;
import org.fergonco.bassjam.midi.Track;

public class NoteOnRythmStyle implements Style {

	@Override
	public Track generateLine(JamContext jamContext, int instrument) {
		Track track = new Track();
		track.setInstrument(instrument);
		ChordProgression chordProgression = jamContext.getChordProgression();
		Rythm rythm = jamContext.getRythm();
		for (int i = 0; i < chordProgression.length(); i++) {
			ChordInfo chord = chordProgression.getChord(i);
			Iterable<Duration> durations = rythm.getDurations();
			for (Duration duration : durations) {
				int pitch = chord.getPitch((int) (Math.random() * chord.getPitchCount()));
				Note note = new NoteImpl(pitch, duration, Dynamic.F);
				note.transpose(-2 * 12);
				track.addNote(note);
			}
		}

		return track;
	}

}
