package fh.hagenberg.PenederMauler;

import java.util.TimerTask;

/**
 * Created by Felix on 13.12.2016.
 */
public class EnvDataTask extends TimerTask {

    private EnvData envData;

    public EnvData getEnvData() {
        return envData;
    }

    public EnvDataTask(EnvData _data) {
        envData = _data;
    }

    @Override
    public void run() {
        envData.setAirPressure((float) (Math.random()*100));
        //System.out.println(envData.getAirPressure());
    }
}
