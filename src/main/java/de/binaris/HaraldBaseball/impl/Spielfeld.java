package de.binaris.HaraldBaseball.impl;

import de.binaris.HaraldBaseball.errors.IchWeissNichtWoIchBinException;
import de.binaris.HaraldBaseball.errors.NotOnBaseException;
import lejos.robotics.Color;

public class Spielfeld {

	private Laufen laufen;
	private Sehen sehen;

	public Spielfeld(Laufen laufen, Sehen sehen) {
		this.laufen = laufen;
		this.sehen = sehen;
	}

	private static final int MAX_TRIES = 100;

	public void laufeZurNaechstenBase() throws NotOnBaseException, IchWeissNichtWoIchBinException {

		int count = 0;

		try {
			int c = sehen.sehen();
			if (c != Color.YELLOW)
				throw new NotOnBaseException("Der Bot muss auf einer gelben Base starten!");

			// Erst mal laufen wir von der Base runter!
			do {
				count++;
				if (count > MAX_TRIES)
					throw new IchWeissNichtWoIchBinException(
							"Ich konnte meine Start-Base nicht verlassen und gebe auf!");

				laufen.lauf(5);
				c = sehen.sehen();
			} while (c == Color.YELLOW);

			// Erst mal laufen wir auf die nächste Base rauf!
			do {
				count++;
				if (count > MAX_TRIES)
					throw new IchWeissNichtWoIchBinException("Ich konnte die Ziel-Base nicht finden und gebe auf!");

				laufen.lauf(5);
				c = sehen.sehen();
			} while (c != Color.YELLOW);
		} finally {
			sehen.augenZu();
		}
	}

}
