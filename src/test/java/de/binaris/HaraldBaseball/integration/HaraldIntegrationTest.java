package de.binaris.HaraldBaseball.integration;

import org.junit.jupiter.api.Test;

import de.binaris.HaraldBaseball.errors.IchWeissNichtWoIchBinException;
import de.binaris.HaraldBaseball.errors.NoPilotException;
import de.binaris.HaraldBaseball.errors.NotOnBaseException;
import de.binaris.HaraldBaseball.impl.Bewusstsein;
import de.binaris.HaraldBaseball.impl.Drehen;
import de.binaris.HaraldBaseball.impl.Harald;
import de.binaris.HaraldBaseball.impl.Laufen;
import de.binaris.HaraldBaseball.impl.Sehen;
import de.binaris.HaraldBaseball.impl.Spielfeld;
import de.binaris.HaraldBaseball.impl.Zufall;
import de.binaris.HaraldBaseball.integration.common.BaseIntegrationTest;

// Die Reihenfolge der Tests ist nicht deterministisch... und das ist auch gut so!
class HaraldIntegrationTest extends BaseIntegrationTest {

    @Test
    void testeSpiel() throws NotOnBaseException, IchWeissNichtWoIchBinException, NoPilotException {

        Harald harald = new Harald(new Bewusstsein(), new Spielfeld(new Laufen(pilot), new Sehen(sample), new Drehen(pilot)), new Zufall());
        harald.spiele();
    }
}
