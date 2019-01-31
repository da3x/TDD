package de.binaris.HaraldBaseball;

import lejos.remote.ev3.RemoteRequestPilot;
import robot.BotEV3;

public class Main {

	
	public static void main(String... args) {
		BotEV3.createInstanceEV3();
		RemoteRequestPilot pilot = BotEV3.createPilot();
		pilot.travel(20);
		pilot.close();
	}
}
