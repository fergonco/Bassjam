package org.fergonco.bassjam.midi;

public class Duration {
	public static final Duration WHOLE = new Duration(4);
	public static final Duration HALF = new Duration(2);
	public static final Duration QUARTER = new Duration(1);
	public static final Duration EIGHTH = new Duration(1.0 / 2);
	public static final Duration SIXTEENTH = new Duration(1.0 / 4);

	private double multiplier;
	private boolean divide;

	Duration(double multiplier) {
		this(multiplier, false);
	}

	Duration(double multiplier, boolean divide) {
		this.multiplier = multiplier;
		this.divide = divide;
	}

	public int getTicks(int ticksPerQuarterNote) {
		return (int) (ticksPerQuarterNote * multiplier);
	}

	public Duration dot() {
		return new Duration(multiplier + multiplier / 2, divide);
	}

	public double getNumBeats() {
		return multiplier;
	}

}
