package de.binaris.HaraldBaseball;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.configuration.injection.MockInjection;
import org.mockito.junit.jupiter.MockitoExtension;

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
@ExtendWith(MockitoExtension.class) // für die @Mock
class HaraldTest {

    @Mock
    Bewusstsein bewusstsein;
    @Mock
    Spielfeld   spielfeld;
    @Mock
    Zufall      zufall;

    @InjectMocks
    Harald harald;

    @Test
    void testeSpielHomeRun() throws NotOnBaseException, IchWeissNichtWoIchBinException {

        when(bewusstsein.abschlag(zufall)).thenReturn(Abschlag.HOMERUN);

        harald.spiele();

        verify(bewusstsein, times(1)).abschlag(zufall);
        verify(spielfeld, times(1)).laufeBases(4);
    }

    @Test
    void testeSpielMies() throws NotOnBaseException, IchWeissNichtWoIchBinException {

        when(bewusstsein.abschlag(zufall)).thenReturn(Abschlag.MIES);

        harald.spiele();

        verify(bewusstsein, times(1)).abschlag(zufall);
        verify(spielfeld, never()).laufeBases(anyInt());
    }
}
