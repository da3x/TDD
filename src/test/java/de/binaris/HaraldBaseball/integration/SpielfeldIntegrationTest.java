package de.binaris.HaraldBaseball.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import de.binaris.HaraldBaseball.errors.IchWeissNichtWoIchBinException;
import de.binaris.HaraldBaseball.errors.NoPilotException;
import de.binaris.HaraldBaseball.errors.NotOnBaseException;
import de.binaris.HaraldBaseball.impl.Drehen;
import de.binaris.HaraldBaseball.impl.Laufen;
import de.binaris.HaraldBaseball.impl.Sehen;
import de.binaris.HaraldBaseball.impl.Spielfeld;
import de.binaris.HaraldBaseball.integration.common.HaraldIntegrationTest;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestSampleProvider;
import lejos.robotics.Color;
import robot.BotEV3;

import static org.mockito.Mockito.*;

class SpielfeldIntegrationTest extends HaraldIntegrationTest {

	@Test
	void testeEineBase() throws NotOnBaseException, IchWeissNichtWoIchBinException, NoPilotException {
		Spielfeld feld = new Spielfeld(new Laufen(pilot), new Sehen(sample));
		feld.laufeZurNaechstenBase();
	}

	@Test
	void testeHomeRun() throws NotOnBaseException, IchWeissNichtWoIchBinException, NoPilotException {
		Spielfeld feld = new Spielfeld(new Laufen(pilot), new Sehen(sample), new Drehen(pilot));
		feld.laufeBases(4);
	}

}
