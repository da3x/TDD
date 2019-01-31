package de.binaris.HaraldBaseball;

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
	
	@Test
	void testeHomeRunZuWeit() {

		Laufen laufen = mock(Laufen.class);
		Sehen  sehen  = mock(Sehen.class);
		Drehen drehen = mock(Drehen.class);
		
		Spielfeld feld = new Spielfeld(laufen, sehen, drehen);

		assertThrows(IllegalArgumentException.class, () -> feld.laufeBases(5));
	}

	@Test
	void testeHomeRunZuKurz() {
		
		Laufen laufen = mock(Laufen.class);
		Sehen  sehen  = mock(Sehen.class);
		Drehen drehen = mock(Drehen.class);
		
		Spielfeld feld = new Spielfeld(laufen, sehen, drehen);
		
		assertThrows(IllegalArgumentException.class, () -> feld.laufeBases(0));
	}

	@Test
	void testeHomeRunFalscherStart() {
		
		Laufen laufen = mock(Laufen.class);
		Sehen  sehen  = mock(Sehen.class);
		Drehen drehen = mock(Drehen.class);
		
		Spielfeld feld = new Spielfeld(laufen, sehen, drehen);

		when(sehen.sehen()).thenReturn(Color.BROWN);

		assertThrows(NotOnBaseException.class, () -> feld.laufeBases(1));
	}
	
	@Test
	void testeHomeRun() throws NotOnBaseException, IchWeissNichtWoIchBinException {
		
		Laufen laufen = mock(Laufen.class);
		Sehen  sehen  = mock(Sehen.class);
		Drehen drehen = mock(Drehen.class);
		
		//                                   1.                         2.            2.                         3.            3.                         4.            4.                         1.
		when(sehen.sehen()).thenReturn(Color.YELLOW, Color.BROWN, Color.YELLOW, Color.YELLOW, Color.BROWN, Color.YELLOW, Color.YELLOW, Color.BROWN, Color.YELLOW, Color.YELLOW, Color.BROWN, Color.YELLOW);
		
		Spielfeld feld = new Spielfeld(laufen, sehen, drehen);
		feld.laufeBases(4);
		
		InOrder order = inOrder(sehen, laufen, drehen);
		
		// 1. Schritt
		order.verify(sehen).sehen();
		order.verify(laufen).lauf(anyInt());
		order.verify(sehen).sehen();
		order.verify(laufen).lauf(anyInt());
		order.verify(sehen).sehen();
		order.verify(drehen).dreheLinks();
		
		// 2. Schritt
		order.verify(sehen).sehen();
		order.verify(laufen).lauf(anyInt());
		order.verify(sehen).sehen();
		order.verify(laufen).lauf(anyInt());
		order.verify(sehen).sehen();
		order.verify(drehen).dreheLinks();
		
		// 3. Schritt
		order.verify(sehen).sehen();
		order.verify(laufen).lauf(anyInt());
		order.verify(sehen).sehen();
		order.verify(laufen).lauf(anyInt());
		order.verify(sehen).sehen();
		order.verify(drehen).dreheLinks();
		
		// 4. Schritt
		order.verify(sehen).sehen();
		order.verify(laufen).lauf(anyInt());
		order.verify(sehen).sehen();
		order.verify(laufen).lauf(anyInt());
		order.verify(sehen).sehen();
		order.verify(drehen).dreheLinks();
		
	}
	
}
