package de.binaris.HaraldBaseball.impl;

import de.binaris.HaraldBaseball.errors.NoPilotException;
import lejos.remote.ev3.RemoteRequestPilot;

public class Laufen {

	private RemoteRequestPilot pilot;

	public Laufen(RemoteRequestPilot pilot) throws NoPilotException {
		if (pilot == null) throw new NoPilotException("Der Pilot darf nicht null sein!");
		this.pilot = pilot;
	}

	public void lauf(int i) {
		pilot.travel(i);
	}

}
