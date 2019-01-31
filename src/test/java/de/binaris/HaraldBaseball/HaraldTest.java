package de.binaris.HaraldBaseball;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import de.binaris.HaraldBaseball.errors.IchWeissNichtWoIchBinException;
import de.binaris.HaraldBaseball.errors.NoPilotException;
import de.binaris.HaraldBaseball.errors.NotOnBaseException;
import de.binaris.HaraldBaseball.impl.Abschlag;
import de.binaris.HaraldBaseball.impl.Bewusstsein;
import de.binaris.HaraldBaseball.impl.Drehen;
import de.binaris.HaraldBaseball.impl.Harald;
import de.binaris.HaraldBaseball.impl.Laufen;
import de.binaris.HaraldBaseball.impl.Sehen;
import de.binaris.HaraldBaseball.impl.Spielfeld;
import de.binaris.HaraldBaseball.impl.Zufall;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestSampleProvider;

import static org.assertj.core.api.Assertions.*;

// Die Reihenfolge der Tests ist nicht deterministisch... und das ist auch gut so!
class HaraldTest {

    @Test
    void testeSpielHomeRun() throws NotOnBaseException, IchWeissNichtWoIchBinException {

        Bewusstsein bewusstsein = mock(Bewusstsein.class);
        Spielfeld   spielfeld   = mock(Spielfeld.class);
        Zufall      zufall      = mock(Zufall.class);

        when(bewusstsein.abschlag(zufall)).thenReturn(Abschlag.HOMERUN);

        Harald harald = new Harald(bewusstsein, spielfeld, zufall);
        harald.spiele();

        verify(bewusstsein, times(1)).abschlag(zufall);
        verify(spielfeld, times(1)).laufeBases(4);
    }

    @Test
    void testeSpielMies() throws NotOnBaseException, IchWeissNichtWoIchBinException {
        
        Bewusstsein bewusstsein = mock(Bewusstsein.class);
        Spielfeld   spielfeld   = mock(Spielfeld.class);
        Zufall      zufall      = mock(Zufall.class);
        
        when(bewusstsein.abschlag(zufall)).thenReturn(Abschlag.MIES);
        
        Harald harald = new Harald(bewusstsein, spielfeld, zufall);
        harald.spiele();
        
        verify(bewusstsein, times(1)).abschlag(zufall);
        verify(spielfeld, never()).laufeBases(anyInt());
    }
}
