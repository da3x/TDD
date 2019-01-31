package de.binaris.HaraldBaseball;

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

class SehenTest {

	@Test
	void testeSehen() {
		RemoteRequestSampleProvider sample = mock(RemoteRequestSampleProvider.class);

		Sehen sehen = new Sehen(sample);
		sehen.sehen();

		// Behavior...
		verify(sample, times(1)).fetchSample(any(float[].class), anyInt());
	}

	@Test
	void testeSehenGelb() {
		RemoteRequestSampleProvider mock = mock(RemoteRequestSampleProvider.class);

		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				float[] p1 = (float[]) args[0];
				int p2 = (int) args[1];
				p1[p2] = Color.YELLOW;
				return null;
			}
		}).when(mock).fetchSample(any(float[].class), anyInt());

		Sehen sehen = new Sehen(mock);
		int c = sehen.sehen();

		assertTrue(c == Color.YELLOW);

	}

}
