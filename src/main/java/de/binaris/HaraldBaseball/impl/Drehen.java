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
		pilot.rotate(90);
	}

	public void dreheRechts() {
		pilot.rotate(-90);
	}
	
}
