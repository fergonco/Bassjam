package org.fergonco.bassjam;

public class Duration {

	private String duration;

	public Duration(String duration) {
		this.duration = duration;
	}

	public int getSixteenths() {
		switch (duration) {
		case "q":
			return 4;
		case "i.":
			return 3;
		case "i":
			return 2;
		case "s":
			return 1;
		}
		throw new RuntimeException();
	}

	public String getDuration() {
		return duration;
	}

}
