package org.fergonco.bassjam;

import org.fergonco.bassjam.midi.Track;

public interface Style {

	Track generateLine(JamContext jamContext, int instrument);

}
