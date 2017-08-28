package org.fergonco.bassjam;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.fergonco.bassjam.midi.Instrument;
import org.fergonco.bassjam.midi.MidiPlayer;
import org.fergonco.bassjam.midi.Score;
import org.fergonco.bassjam.midi.Track;

public class Bassjam2 extends JFrame {
	private static final long serialVersionUID = 1L;
	private JamContext context;

	public Bassjam2() {
		super("Bass jam");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(new JLabel("BassJam"), BorderLayout.CENTER);
		this.pack();
		// this.addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent e) {
		// System.out.println(e.getKeyCode());
		// String code;
		// switch (e.getKeyCode()) {
		// case 10:
		// code = txtCode.getText();
		// Score score = parse(code);
		// Play.stopMidi();
		// Play.midi(score);
		// break;
		// case 40:
		// code = txtCode.getText();
		// tempo -= 5;
		// txtCode.setText(txtCode.getText().replaceAll("T\\d+", "T" + tempo));
		// Play.stopMidi();
		// Play.midi(parse(txtCode.getText()));
		// break;
		// case 38:
		// code = txtCode.getText();
		// tempo += 5;
		// txtCode.setText(txtCode.getText().replaceAll("T\\d+", "T" + tempo));
		// Play.stopMidi();
		// Play.midi(parse(txtCode.getText()));
		// break;
		// case 32:
		// if (Play.cycleIsPlaying()) {
		// Play.pauseAudio();
		// } else {
		// Play.unPauseAudio();
		// }
		// break;
		// case 83:
		// File file;
		// int index = 0;
		// do {
		// file = new File("save" + index++);
		// } while (file.exists());
		// FileOutputStream fos = null;
		// try {
		// fos = new FileOutputStream(file);
		// fos.write(txtCode.getText().getBytes());
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// } finally {
		// if (fos != null) {
		// try {
		// fos.close();
		// } catch (IOException e1) {
		// }
		// }
		// }
		//
		// break;
		// }
		// }
		//
		// });
	}

	public void start() throws MidiUnavailableException, IOException, InvalidMidiDataException {
		Rythm rythm = Rythm.newRandom();
		int tempo = 80;// TODO (int) (50 + Math.random() * 120);
		ChordProgression chordProgression = ChordProgression.newRandom();
		context = new JamContext(tempo, rythm, chordProgression);
		Style style = newRandomStyle();
		Track bassTrack = style.generateLine(context, Instrument.ACOUSTIC_BASS);
		Track chordsTrack = new ChordsStyle().generateLine(context, Instrument.VIOLIN);
		play(bassTrack, chordsTrack);
		super.setVisible(true);
	}

	private void play(Track... tracks) throws MidiUnavailableException, IOException, InvalidMidiDataException {
		File file = new File("/tmp/a.mid");
		Score score = new Score(file);
		score.addTracks(tracks);
		score.setTempo(context.getTempo());
		score.write();
		MidiPlayer.play(file);
	}

	private Style newRandomStyle() {
		Style[] options = new Style[] { //
				new NoteOnRythmStyle(), //
				// new WholeNoteStyle()//
		};
		return options[(int) (Math.random() * options.length)];

	}

	public static void main(String[] args) throws Exception {
		Bassjam2 frame = new Bassjam2();
		frame.start();
	}

}