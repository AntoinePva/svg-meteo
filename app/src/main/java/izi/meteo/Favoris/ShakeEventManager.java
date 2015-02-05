package izi.meteo.Favoris;

/**
 * Created by thomas on 05/02/2015.
 */
public class ShakeEventManager {
}

public void init(Context ctx) {
    sManager = (SensorManager)  ctx.getSystemService(Context.SENSOR_SERVICE);
    s = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    register();
}

public void register() {
        sManager.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
}