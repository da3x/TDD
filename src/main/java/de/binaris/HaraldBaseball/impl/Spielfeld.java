package de.binaris.HaraldBaseball.impl;

import de.binaris.HaraldBaseball.errors.IchWeissNichtWoIchBinException;
import de.binaris.HaraldBaseball.errors.NotOnBaseException;
import lejos.robotics.Color;

public class Spielfeld {

    private static final int MAX_TRIES = 20;
    private static final int STEP      = 5;

    private final Laufen laufen;
    private final Sehen  sehen;
    private final Drehen drehen;

    public Spielfeld(Laufen laufen, Sehen sehen, Drehen drehen) {
        this.laufen = laufen;
        this.sehen  = sehen;
        this.drehen = drehen;
    }

    public void laufeZurNaechstenBase() throws NotOnBaseException, IchWeissNichtWoIchBinException {
        System.out.println("Spielfeld.laufeZurNaechstenBase()");

        int count = 0;

        int c = sehen.sehen();
        if (c != Color.YELLOW) {
            sehen.augenZu();
            throw new NotOnBaseException("Der Bot muss auf einer gelben Base starten!");
        }

        // Erst mal laufen wir von der Base runter!
        do {
            count++;
            if (count > MAX_TRIES) {
                sehen.augenZu();
                throw new IchWeissNichtWoIchBinException("Ich konnte meine Start-Base nicht verlassen und gebe auf!");
            }

            laufen.lauf(5);
            c = sehen.sehen();
        }
        while (c == Color.YELLOW);

        // Erst mal laufen wir auf die nächste Base rauf!
        do {
            count++;
            if (count > MAX_TRIES) {
                sehen.augenZu();
                throw new IchWeissNichtWoIchBinException("Ich konnte die Ziel-Base nicht finden und gebe auf!");
            }

            laufen.lauf(STEP);
            c = sehen.sehen();
        }
        while (c != Color.YELLOW);

        // Auf die Mitte laufen!
        laufen.lauf(12); // dirty

    }

    public void laufeBases(int i) throws NotOnBaseException, IchWeissNichtWoIchBinException {
        System.out.println("Spielfeld.laufeBases()");

        if (i < 1 || i > 4)
            throw new IllegalArgumentException("Harald will nur 1-4 Bases laufen... der ist faul!");

        for (int n = 0; n < i; n++) {
            laufeZurNaechstenBase();
            drehen.dreheLinks();
            laufen.lauf(-8);
        }

    }

    public void sucheBase() throws IchWeissNichtWoIchBinException, InterruptedException {
        int i = 0;
        while (sehen.sehen() != Color.YELLOW) {
            if (i++ > 20) {
                sehen.augenZu();
                throw new IchWeissNichtWoIchBinException("Ich such doch nicht den ganzen Tag!");
            }
            // laufen.lauf(i);
            laufen.lauf(3);
            drehen.fahreLinks(5 + i);
        }
        sehen.augenZu();
        laufen.stop();
    }

}
