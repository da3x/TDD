package de.binaris.HaraldBaseball.impl;

import de.binaris.HaraldBaseball.errors.NoPilotException;
import lejos.remote.ev3.RemoteRequestPilot;

public class Drehen {

	private RemoteRequestPilot pilot;

	public Drehen(RemoteRequestPilot pilot) throws NoPilotException {
		if (pilot == null) throw new NoPilotException("Der Pilot darf nicht null sein!");
		this.pilot = pilot;
	}

	public void dreheLinks() {
		System.out.println("Drehen.dreheLinks()");
		pilot.rotate(-86); // 4° weniger...
	}

	public void dreheRechts() {
		System.out.println("Drehen.dreheRechts()");
		pilot.rotate(90);
	}
	
}
