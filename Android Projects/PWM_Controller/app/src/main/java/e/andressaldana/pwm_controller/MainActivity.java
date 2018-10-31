package e.andressaldana.pwm_controller;

import android.app.*;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.os.*;
import android.widget.SeekBar.*;
import android.widget.*;

import java.util.Set;

public class MainActivity extends Activity {
    private SeekBar sb2 = null, sb1 = null;
    private  Switch s1 = null, s2 = null;
    private String mensaje;
    int xsb1 = 0,xsb2 = 0;

    Handler bluetoothOut;
    final int HandlerState = 0;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder DataStringOut = new StringBuilder();

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        sb1 = findViewById(R.id.xsb1);
        sb2 = findViewById(R.id.xsb2);
        s1 = findViewById(R.id.switch1);
        s2 = findViewById(R.id.switch2);
        //listeners de eventos sb1
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    xsb1 = sb1.getProgress();
                }
                else{
                    xsb1 = 0;
                }
                mensaje = xsb1+"/"+xsb2;
                System.out.println("Value "+mensaje);
            }
        });
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    xsb2 = sb2.getProgress();
                }
                else{
                    xsb2 = 0;
                }
                mensaje = xsb1+"/"+xsb2;
                System.out.println("Value "+mensaje);
            }
        });
        sb1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar sb, int n, boolean b){
                if(s1.isChecked()){
                    xsb1 = n;
                }else {
                    xsb1 = 0;
                }
                mensaje = xsb1+"/"+xsb2;
                System.out.println("Value "+mensaje);
            }
            public void onStartTrackingTouch(SeekBar sb) { /*nada*/ }
            public void onStopTrackingTouch(SeekBar sb) { /*nada*/ }
        });
        //listeners de eventos sb1
        sb2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar sb, int n, boolean b){
                if(s2.isChecked()){
                    xsb2 = n;
                }
                else {
                    xsb2 = 0;
                }
                mensaje = xsb1+"/"+xsb2;
                System.out.println("Value: "+mensaje);
            }
            public void onStartTrackingTouch(SeekBar sb) { /*nada*/ }
            public void onStopTrackingTouch(SeekBar sb) { /*nada*/ }
        });
    }
}