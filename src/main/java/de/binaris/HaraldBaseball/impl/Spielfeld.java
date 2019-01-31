package de.binaris.HaraldBaseball.impl;

import de.binaris.HaraldBaseball.errors.IchWeissNichtWoIchBinException;
import de.binaris.HaraldBaseball.errors.NotOnBaseException;
import lejos.robotics.Color;

public class Spielfeld {

	private static final int STEP = 5;

	private final Laufen laufen;
	private final Sehen sehen;
	private final Drehen drehen;

	public Spielfeld(Laufen laufen, Sehen sehen) {
		this.laufen = laufen;
		this.sehen = sehen;
		this.drehen = null;
	}

	public Spielfeld(Laufen laufen, Sehen sehen, Drehen drehen) {
		this.laufen = laufen;
		this.sehen = sehen;
		this.drehen = drehen;
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

				laufen.lauf(STEP);
				c = sehen.sehen();
			} while (c != Color.YELLOW);

		} finally {
			sehen.augenZu();
		}
	}

	public void laufeBases(int i) throws NotOnBaseException, IchWeissNichtWoIchBinException {
		if (i < 1 || i > 4)
			throw new IllegalArgumentException("Harald will nur 1-4 Bases laufen... der ist faul!");

		for (int n = 0; n < i; n++) {
			laufeZurNaechstenBase();
			drehen.dreheLinks();
		}
		
	}

}
