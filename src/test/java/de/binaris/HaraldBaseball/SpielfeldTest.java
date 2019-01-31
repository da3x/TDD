package de.binaris.HaraldBaseball;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import de.binaris.HaraldBaseball.errors.IchWeissNichtWoIchBinException;
import de.binaris.HaraldBaseball.errors.NoPilotException;
import de.binaris.HaraldBaseball.errors.NotOnBaseException;
import de.binaris.HaraldBaseball.impl.Laufen;
import de.binaris.HaraldBaseball.impl.Sehen;
import de.binaris.HaraldBaseball.impl.Spielfeld;
import lejos.robotics.Color;

import static org.mockito.Mockito.*;

class SpielfeldTest {

	@Test
	void testeEffizienz() throws NotOnBaseException, IchWeissNichtWoIchBinException {

		Laufen laufen = mock(Laufen.class);
		Sehen sehen = mock(Sehen.class);

		when(sehen.sehen()).thenReturn(Color.YELLOW, Color.BROWN, Color.RED, Color.BROWN, Color.YELLOW);

		Spielfeld feld = new Spielfeld(laufen, sehen);
		feld.laufeZurNaechstenBase();

		verify(sehen, times(5)).sehen();
		verify(laufen, times(4)).lauf(anyInt());
		verify(sehen, times(1)).augenZu();
	}

	@Test
	void testeAbbruch() throws NotOnBaseException {
		
		Laufen laufen = mock(Laufen.class);
		Sehen sehen = mock(Sehen.class);
		
		when(sehen.sehen()).thenReturn(Color.YELLOW, Color.BROWN, Color.RED);
		
		Spielfeld feld = new Spielfeld(laufen, sehen);

		// Er muss abbrechen!
		assertThrows(IchWeissNichtWoIchBinException.class, () -> feld.laufeZurNaechstenBase());
		
		// Er muss das "Auge" schließen!
		verify(sehen, times(1)).augenZu();
	}

	@Test
	void testeReihenfolge() throws NotOnBaseException, IchWeissNichtWoIchBinException {
		
		Laufen laufen = mock(Laufen.class);
		Sehen sehen = mock(Sehen.class);
		
		when(sehen.sehen()).thenReturn(Color.YELLOW, Color.BROWN, Color.RED, Color.BROWN, Color.YELLOW);
		
		Spielfeld feld = new Spielfeld(laufen, sehen);
		feld.laufeZurNaechstenBase();
		
		InOrder order = inOrder(sehen, laufen);
		order.verify(sehen).sehen();
		order.verify(laufen).lauf(anyInt());
		order.verify(sehen).sehen();
		order.verify(laufen).lauf(anyInt());
		order.verify(sehen).sehen();
		order.verify(laufen).lauf(anyInt());
		order.verify(sehen).sehen();
		order.verify(laufen).lauf(anyInt());
		order.verify(sehen).sehen();
		order.verify(sehen).augenZu();
	}
	
}
