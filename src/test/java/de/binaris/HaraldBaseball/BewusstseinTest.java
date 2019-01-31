package de.binaris.HaraldBaseball;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import de.binaris.HaraldBaseball.errors.NoPilotException;
import de.binaris.HaraldBaseball.impl.Abschlag;
import de.binaris.HaraldBaseball.impl.Bewusstsein;
import de.binaris.HaraldBaseball.impl.Drehen;
import de.binaris.HaraldBaseball.impl.Laufen;
import de.binaris.HaraldBaseball.impl.Sehen;
import de.binaris.HaraldBaseball.impl.Zufall;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestSampleProvider;

import static org.assertj.core.api.Assertions.*;

// Die Reihenfolge der Tests ist nicht deterministisch... und das ist auch gut so!
class BewusstseinTest {

    @ParameterizedTest
    @ValueSource(ints = { 5, 15, 25, 35, 45, 55, 65, 75, 85, 95 })
    void testeAbschlag(int score) {
        Bewusstsein hirn = new Bewusstsein();

        Zufall zufall = mock(Zufall.class);
        when(zufall.getScore()).thenReturn(score);
        
        Abschlag wiegutwarich = hirn.abschlag(zufall);
       
        assertTrue(wiegutwarich != null);
        
             if (score <= 20) assertTrue(wiegutwarich == Abschlag.MIES);
        else if (score <= 40) assertTrue(wiegutwarich == Abschlag.OKAY);
        else if (score <= 60) assertTrue(wiegutwarich == Abschlag.GUT);
        else if (score <= 80) assertTrue(wiegutwarich == Abschlag.BESSER);
        else                  assertTrue(wiegutwarich == Abschlag.HOMERUN);
    }
}
