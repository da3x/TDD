package de.binaris.HaraldBaseball.integration;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import de.binaris.HaraldBaseball.errors.NoPilotException;
import de.binaris.HaraldBaseball.impl.Drehen;
import de.binaris.HaraldBaseball.impl.Sehen;
import de.binaris.HaraldBaseball.integration.common.HaraldIntegrationTest;
import lejos.remote.ev3.RemoteRequestSampleProvider;
import lejos.robotics.Color;
import robot.BotEV3;

class DrehenIntegrationTest extends HaraldIntegrationTest {

	@Test
	void testeSehenGelb() throws NoPilotException {

		Drehen drehen = new Drehen(pilot);

		drehen.dreheLinks();
		drehen.dreheRechts();
	}
}
