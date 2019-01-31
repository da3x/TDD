package de.binaris.HaraldBaseball;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import de.binaris.HaraldBaseball.errors.NoPilotException;
import de.binaris.HaraldBaseball.impl.Laufen;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestSampleProvider;

import static org.assertj.core.api.Assertions.*;

// Die Reihenfolge der Tests ist nicht deterministisch... und das ist auch gut so! 
class LaufenTest {

	@Test
	void laufeVorwaerts() throws NoPilotException {

		// Wir "mocken" die Klasse aus dem Framework... nur das MOCK kann man dann mit
		// Mockito.verify testen!
		RemoteRequestPilot pilot = mock(RemoteRequestPilot.class);

		// Hier wird der MOCK "programmiert"... liefere TRUE, wenn die Methode aufgerufen wird!
		when(pilot.isMoving()).thenReturn(true);
		
		// Wir wollen nur die Klasse Laufen testen!
		Laufen laufen = new Laufen(pilot);
		laufen.lauf(20);

		// Prüft, ob die Methode 1x aufgerufen wurde...
		verify(pilot, times(1)).travel(20);
		
		// Prüft, ob der Mock auch das tut, was ich erwarte... 
		assertThat(pilot.isMoving()).as("").isTrue();
	}

	@Test
	void laufeMitNullArg() {
		RemoteRequestPilot pilot = mock(RemoteRequestPilot.class);
		assertThrows(NoPilotException.class, () -> new Laufen(null));
	}
}
