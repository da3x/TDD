package de.binaris.HaraldBaseball.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import de.binaris.HaraldBaseball.errors.IchWeissNichtWoIchBinException;
import de.binaris.HaraldBaseball.errors.NoPilotException;
import de.binaris.HaraldBaseball.errors.NotOnBaseException;
import de.binaris.HaraldBaseball.impl.Laufen;
import de.binaris.HaraldBaseball.impl.Sehen;
import de.binaris.HaraldBaseball.impl.Spielfeld;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestSampleProvider;
import lejos.robotics.Color;
import robot.BotEV3;

import static org.mockito.Mockito.*;

class SpielfeldIntegrationTest {

	@Test
	void testeEffizienz() throws NotOnBaseException, IchWeissNichtWoIchBinException, NoPilotException {

		BotEV3.createInstanceEV3();
		RemoteRequestPilot pilot = BotEV3.createPilot();
		RemoteRequestSampleProvider sample = BotEV3.createSampleProvider();
		
		Spielfeld feld = new Spielfeld(new Laufen(pilot), new Sehen(sample));
		feld.laufeZurNaechstenBase();
	}

}
