package de.binaris.HaraldBaseball.integration.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestSampleProvider;
import robot.BotEV3;

public class HaraldIntegrationTest {

	protected RemoteRequestSampleProvider sample;
	protected RemoteRequestPilot pilot;

	@BeforeEach
	void setUp() {
		BotEV3.createInstanceEV3();
		System.out.println("HaraldIntegrationTest.setUp()");
		this.sample = BotEV3.createSampleProvider();
		this.pilot = BotEV3.createPilot();
	}

	@AfterEach
	void tearDown() {
		System.out.println("HaraldIntegrationTest.tearDown()");
		sample.close();
		BotEV3.shutDown();
	}
}
