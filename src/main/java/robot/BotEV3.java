package robot;


import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestPilot;
import lejos.remote.ev3.RemoteRequestSampleProvider;

import java.io.IOException;

/**
 * Created by pc on 16.06.2016.
 */
public class BotEV3 {

    private static RemoteRequestEV3 ev3;

    public static void createInstanceEV3() {

        String EV3BRICK_IP = "192.168.189.110";

        try {
            ev3 = new RemoteRequestEV3(EV3BRICK_IP);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static RemoteRequestEV3 getBrick() {
    	return ev3;
    }

    public static RemoteRequestPilot createPilot() {
        return (RemoteRequestPilot) ev3.createPilot(3.5, 20.0, "A", "B");
    }


    public static RemoteRequestSampleProvider createSampleProvider() {
        //return (RemoteRequestSampleProvider) ev3.createSampleProvider("S3", "lejos.hardware.sensor.EV3ColorSensor", "Red");
        return (RemoteRequestSampleProvider) ev3.createSampleProvider("S1", "lejos.hardware.sensor.EV3ColorSensor", "ColorID");
    }

    public static void shutDown() {
    	
        ev3.disConnect();
    }


}
