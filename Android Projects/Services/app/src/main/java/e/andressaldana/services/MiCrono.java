package e.andressaldana.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.*;
import android.app.Service;
import android.content.Intent;
import android.os.*;
public class MiCrono extends Service {
    private Timer t = null;
    private static final long INTERVALO_ACTUALIZACION = 10; // En milisegundos
    public static MainActivity UPDATE_LISTENER;
    private double n=0;
    private Handler h;
    public static void setUpdateListener(MainActivity sta) {
        UPDATE_LISTENER = sta;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //iniciarCrono();
        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                UPDATE_LISTENER.refreshCrono(n);
            }
        }; }
    @Override
    public void onDestroy() {
        pararCrono();
        super.onDestroy();
    }


    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        MiCrono getService() {
            // Return this instance of LocalService so clients can call public methods
            return MiCrono.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void iniciarCrono() {
        if(t == null) {
            t = new Timer();
        }
            t.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    n += 0.01;
                    h.sendEmptyMessage(0);
                }
            }, 0, INTERVALO_ACTUALIZACION);

    }


    public void reiniciarCrono() {
        n = 0;
    }


    public void pararCrono() {
        if (t != null) {
            t.cancel();
            t = null;
        }
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}
