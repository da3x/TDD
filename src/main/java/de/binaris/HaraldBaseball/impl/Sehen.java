package de.binaris.HaraldBaseball.impl;

import lejos.remote.ev3.RemoteRequestSampleProvider;
import lejos.robotics.Color;

public class Sehen {

	private RemoteRequestSampleProvider sample;

	public Sehen(RemoteRequestSampleProvider sample) {
		this.sample = sample;
	}

	public int sehen() {
		System.out.println("Sehen.sehen()");
		float[] data = new float[10];
		sample.fetchSample(data, 0);
		return (int) data[0];
	}

	public void augenZu() {
		System.out.println("Sehen.augenZu()");
		sample.close();
	}

}
