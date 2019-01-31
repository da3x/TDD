package de.binaris.HaraldBaseball.impl;

public class Bewusstsein {

    public Abschlag abschlag(Zufall zufall) {
        int score = zufall.getScore();
        if (score <= 20) return Abschlag.MIES;
        if (score <= 40) return Abschlag.OKAY;
        if (score <= 60) return Abschlag.GUT;
        if (score <= 80) return Abschlag.BESSER;
        return Abschlag.HOMERUN;
    }

}
