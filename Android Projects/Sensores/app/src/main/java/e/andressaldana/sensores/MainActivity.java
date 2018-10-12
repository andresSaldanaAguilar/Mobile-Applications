package e.andressaldana.sensores;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements SensorEventListener {
    SensorManager sm;
    ListView jlv;
    ArrayAdapter ad;
    Sensor sA, sM, sL , sLA, sP;
    SensorAdapter sea;
    boolean c = true;
    String infoAcel = "", infoMagne = "", infoLuz = "", infoLA  = "", infoProx ="", infoBattery = "";
    int n;
    //accelerometer
    double xA = 0, yA = 0, zA = 0, aA = 0, mA = 0, g = SensorManager.STANDARD_GRAVITY;
    //magnetometer
    double xM = 0, yM = 0, zM = 0, aM = 0, bM = 0;
    //luxometer
    double lux;
    //linear acceleration
    double xLA = 0, yLA = 0, zLA = 0, aLA = 0;
    //Proximity
    double Pin;
    //Battery
    String Level;
    //Sound
    double dB;
    //Temperature
    double TA;

    double mfeM = SensorManager.MAGNETIC_FIELD_EARTH_MAX;
    double mfem = SensorManager.MAGNETIC_FIELD_EARTH_MIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jlv = (ListView) findViewById(R.id.xlv1);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sA = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sM = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sL = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sLA = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sP = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        sea = new SensorAdapter(this, R.layout.sensor_list);
        jlv.setAdapter(sea);
        new Asincronia().execute();
        for (Sensores i : getEntradas()) {
            sea.add(i);
        }
    }

    private List<Sensores> getEntradas() {
        final List<Sensores> datos = new ArrayList<Sensores>();
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                datos.add(new Sensores("Accelerometer", infoAcel, R.drawable.ic_accel));
            }
            else if (i == 1) {
                datos.add(new Sensores("Magnetism", infoMagne, R.drawable.ic_magnet));
            }
            else if (i == 2){
                datos.add(new Sensores("Lightness", infoLuz, R.drawable.ic_bright));
            }
            else if (i == 3){
                datos.add(new Sensores("Linear Acceleration", infoLA, R.drawable.ic_linear));
            }
            else if (i == 4){
                datos.add(new Sensores("Proximity", infoProx, R.drawable.ic_prox));
            }
            else if (i == 5){
                datos.add(new Sensores("Battery", infoBattery, R.drawable.ic_battery));
            }
        }
        return datos;
    }


    public void onResume() {
        super.onResume();
        sm.registerListener(this, sA, SensorManager.SENSOR_DELAY_FASTEST);
        sm.registerListener(this, sM, SensorManager.SENSOR_DELAY_FASTEST);
        sm.registerListener(this, sL, SensorManager.SENSOR_DELAY_FASTEST);
        sm.registerListener(this,sLA, SensorManager.SENSOR_DELAY_FASTEST);
        sm.registerListener(this, sP, SensorManager.SENSOR_DELAY_FASTEST);
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));

        c = true;
        new Asincronia().execute();
    }

    public void onPause() {
        super.onPause();
        sm.unregisterListener(this);
        c = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xA = event.values[0];
            yA = event.values[1];
            zA = event.values[2];
            aA = Math.sqrt(xA * xA + yA * yA + zA * zA);
            if (aA > mA)
                mA = aA;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            xM = event.values[0];
            yM = event.values[1];
            zM = event.values[2];
            aM = Math.sqrt(xM * xM + yM * yM + zM * zM);
            if (aM > bM)
                bM = aM;
        } else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lux = event.values[0];
        } else if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            xLA = event.values[0];
            yLA = event.values[1];
            zLA = event.values[2];
            aLA = Math.sqrt(xLA * xLA + yLA * yLA + zLA * zLA);
        } else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            Pin = event.values[0];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    class Asincronia extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... x) {
            while (c) {
                try {
                    Thread.sleep(100); // 100 milisegundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                n++;
                publishProgress();
            }
            return null;
        }

        protected void onProgressUpdate(Void... progress) {
            infoAcel += "X : " + xA + "\n";
            infoAcel += "Y : " + yA + "\n";
            infoAcel += "Z : " + zA + "\n";
            infoAcel += "|A|" + mA + "\n";
            infoAcel += "Max Acc: " + aA + "\n";
            infoAcel += "Est. Gravity:" + g + "\n";

            infoMagne += "X : " + xM + "\n";
            infoMagne += "Y : " + yM + "\n";
            infoMagne += "Z : " + zM + "\n";
            infoMagne += "Modulus: " + aM + "\n";
            infoMagne += "Max Field: " + bM + "\n";
            infoMagne += "Earth Field: " + mfem + " - " + mfeM + "\n";

            infoLuz += lux + " lux";

            infoLA += "X : " + xLA + "\n";
            infoLA += "Y : " + yLA + "\n";
            infoLA += "Z : " + zLA + "\n";
            infoLA += "Max Acc: " + aLA + "\n";

            infoProx += Pin + " cms\n";

            infoBattery += Level+"\n";

            sea.clear();

            for (Sensores i : getEntradas()) {
                sea.add(i);
            }
            infoAcel = "";
            infoMagne = "";
            infoLuz = "";
            infoLA = "";
            infoProx = "";
            infoBattery = "";
        }
    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            Level = String.valueOf(level) + "%\n";
        }
    };
}