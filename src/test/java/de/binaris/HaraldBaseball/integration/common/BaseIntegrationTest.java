package de.binaris.HaraldBaseball.integration.common;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestSampleProvider;
import robot.BotEV3;

public class BaseIntegrationTest {

    protected RemoteRequestSampleProvider sample;
    protected RemoteRequestPilot          pilot;

    @BeforeEach
    void setUp() {
        System.out.println("BaseIntegrationTest.setUp()");
        BotEV3.createInstanceEV3();
        this.sample = BotEV3.createSampleProvider();
        this.pilot  = BotEV3.createPilot();
    }

    @AfterEach
    void tearDown() {
        System.out.println("BaseIntegrationTest.tearDown()");
        if (pilot.isMoving()) pilot.stop();
        sample.close();
        BotEV3.shutDown();
    }

    @BeforeAll
    static void init() {
        // 1x pro Test-Klasse – aber nur static
    }

    @AfterAll
    static void die() {
        // 1x pro Test-Klasse – aber nur static
    }
}
