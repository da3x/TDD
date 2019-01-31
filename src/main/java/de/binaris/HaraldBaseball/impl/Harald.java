package de.binaris.HaraldBaseball.impl;

import de.binaris.HaraldBaseball.errors.IchWeissNichtWoIchBinException;
import de.binaris.HaraldBaseball.errors.NotOnBaseException;
import lejos.hardware.Sound;

public class Harald {

    private Bewusstsein bewusstsein;
    private Spielfeld   spielfeld;
    private Zufall      zufall;

    public Harald(Bewusstsein bewusstsein, Spielfeld spielfeld, Zufall zufall) {
        this.bewusstsein = bewusstsein;
        this.spielfeld   = spielfeld;
        this.zufall      = zufall;
    }

    public void spiele() throws NotOnBaseException, IchWeissNichtWoIchBinException {
        System.out.println("Harald.spiele()");
        
        Abschlag abschlag = this.bewusstsein.abschlag(zufall);
        System.out.println("Harald.spiele() : " + abschlag.name());
        
        switch (abschlag) {
            case OKAY:
                spielfeld.laufeBases(1);
                break;

            case GUT:
                spielfeld.laufeBases(2);
                break;
                
            case BESSER:
                spielfeld.laufeBases(3);
                break;
                
            case HOMERUN:
                spielfeld.laufeBases(4);
                break;
                
            default:
                break;
        }
    }

}
