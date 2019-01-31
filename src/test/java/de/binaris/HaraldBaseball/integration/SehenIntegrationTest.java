package de.binaris.HaraldBaseball.integration;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import de.binaris.HaraldBaseball.impl.Sehen;
import lejos.remote.ev3.RemoteRequestSampleProvider;
import lejos.robotics.Color;
import robot.BotEV3;

class SehenIntegrationTest {

	@Test
	void testeSehenGelb() {
		
		BotEV3.createInstanceEV3();
		Sehen sehen = new Sehen(BotEV3.createSampleProvider());
		
		int c = sehen.sehen();
		sehen.augenZu();

		assertTrue(c == Color.YELLOW);
	}
}
