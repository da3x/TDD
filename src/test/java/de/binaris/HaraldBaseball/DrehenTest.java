package de.binaris.HaraldBaseball;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import de.binaris.HaraldBaseball.errors.NoPilotException;
import de.binaris.HaraldBaseball.impl.Drehen;
import de.binaris.HaraldBaseball.impl.Laufen;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestSampleProvider;

import static org.assertj.core.api.Assertions.*;

// Die Reihenfolge der Tests ist nicht deterministisch... und das ist auch gut so! 
class DrehenTest {

	@Test
	void dreheLinks() throws NoPilotException {
		RemoteRequestPilot pilot = mock(RemoteRequestPilot.class);

		Drehen drehen = new Drehen(pilot);
		drehen.dreheLinks();

		verify(pilot, times(1)).rotate(90);
	}

	@Test
	void dreheRechts() throws NoPilotException {
		RemoteRequestPilot pilot = mock(RemoteRequestPilot.class);

		Drehen drehen = new Drehen(pilot);
		drehen.dreheRechts();

		verify(pilot, times(1)).rotate(-90);
	}
}
