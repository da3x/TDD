package de.binaris.HaraldBaseball.impl;

import java.io.File;

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
        
//        new Thread(new Runnable() {
//            
//            @Override
//            public void run() {
//                Sound.playSample(new File("/Users/daniel/Desktop/TDD/Last Christmas - Wham.wav"));
//            }
//        }).start();

        Abschlag abschlag = this.bewusstsein.abschlag(zufall);
        System.out.println("Harald.spiele() : " + abschlag.name());
        
        switch (abschlag) {
            case OKAY:
                Sound.beep();
                spielfeld.laufeBases(1);
                break;

            case GUT:
                Sound.beep();
                Sound.beep();
                spielfeld.laufeBases(2);
                break;
                
            case BESSER:
                Sound.beep();
                Sound.beep();
                Sound.beep();
                spielfeld.laufeBases(3);
                break;
                
            case HOMERUN:
                Sound.beep();
                Sound.beep();
                Sound.beep();
                Sound.beep();
                spielfeld.laufeBases(4);
                break;
                
            default:
                break;
        }
    }

}
