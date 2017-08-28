package org.fergonco.bassjam;

import java.util.ArrayList;

import org.fergonco.bassjam.midi.Duration;

public class Rythm {

	private static final double ERROR = 0.0001;

	private static Duration[] supportedDurations = new Duration[] { Duration.QUARTER, Duration.EIGHTH,
			Duration.EIGHTH.dot(), Duration.SIXTEENTH };

	private ArrayList<Duration> durations = new ArrayList<>();

	public boolean isDone() {
		double length = getLength();
		return Math.abs(length - 4) < 0.0001;
	}

	private double getLength() {
		double length = 0;
		for (Duration duration : durations) {
			length += duration.getNumBeats();
		}
		return length;
	}

	public void add(Duration duration) {
		durations.add(duration);
	}

	public static Rythm newRandom() {
		Rythm ret = new Rythm();
		while (!ret.isDone()) {
			Duration duration;
			do {
				duration = supportedDurations[(int) (Math.random() * supportedDurations.length)];
			} while (ret.getLength() + duration.getNumBeats() - 4 > ERROR);
			ret.add(duration);
		}
		return ret;
	}

	public Iterable<Duration> getDurations() {
		return durations;
	}

	public int getDurationCount() {
		return durations.size();
	}

}
