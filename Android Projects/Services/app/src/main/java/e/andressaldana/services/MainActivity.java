package e.andressaldana.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.IBinder;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
public class MainActivity extends Activity { // ServiceTimerActivity
    private TextView    jtv;

    MiCrono mService;
    boolean mBound = false;
    LinearLayout linearLayout;
    AnimationDrawable animationDrawable;

    Button startButton, pauseButton, stopButton;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jtv = (TextView) findViewById(R.id.xtvT);

        linearLayout = (LinearLayout)findViewById( R.id.layout);
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(1000);


        startButton = (Button) findViewById(R.id.xbnI);
        pauseButton = (Button) findViewById(R.id.xbnT);
        stopButton = (Button) findViewById(R.id.xbnP);

        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initCrono();
                mService.iniciarCrono();
                animationDrawable.start();
                startButton.setEnabled(false);
                pauseButton.setEnabled(true);
                stopButton.setEnabled(true);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mService.pararCrono();
                stopCrono();
                animationDrawable.stop();
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                stopButton.setEnabled(true);
                startButton.setText("REANUDAR");
            } });

        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mService.reiniciarCrono();
                mService.pararCrono();
                stopCrono();
                jtv.setText("0.00 s");
                animationDrawable.stop();
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                stopButton.setEnabled(false);
                startButton.setText("INICIAR");
            } });
        MiCrono.setUpdateListener(this);
    }
    @Override
    protected void onDestroy() {
        stopCrono();
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, MiCrono.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }


    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MiCrono.LocalBinder binder = (MiCrono.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


    private void initCrono() {
        Intent in = new Intent(this, MiCrono.class);
        startService(in);
    }
    private void stopCrono() {
        Intent in = new Intent(this, MiCrono.class);
        stopService(in);
    }
    public void refreshCrono(double t) {
        jtv.setText(String.format("%.2f", t) + " s");
    }
}
