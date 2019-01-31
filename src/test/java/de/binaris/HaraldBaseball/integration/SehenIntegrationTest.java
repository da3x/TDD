package de.binaris.HaraldBaseball.integration;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import de.binaris.HaraldBaseball.impl.Sehen;
import de.binaris.HaraldBaseball.integration.common.BaseIntegrationTest;
import lejos.remote.ev3.RemoteRequestSampleProvider;
import lejos.robotics.Color;
import robot.BotEV3;

class SehenIntegrationTest extends BaseIntegrationTest {

	@Test
	void testeSehenGelb() {
		Sehen sehen = new Sehen(this.sample);
		
		int c = sehen.sehen();
		sehen.augenZu();

		assertTrue(c == Color.YELLOW);
	}
}
