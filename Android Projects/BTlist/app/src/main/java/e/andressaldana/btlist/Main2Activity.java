package e.andressaldana.btlist;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class Main2Activity extends AppCompatActivity {

    Handler bluetoothIn;

    final int handlerState = 0;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;

    private ConnectedThread mConnectedThread;

    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private static String address;

    private SeekBar sb2 = null, sb1 = null;
    private Switch s1 = null, s2 = null;
    private String mensaje;
    int xsb1 = 0,xsb2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
        checkBTState();

        Intent intent = getIntent();
        address = intent.getStringExtra(MainActivity.EXTRA_DEVICE_ADDRESS);
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Creación del Socket fallida", Toast.LENGTH_LONG).show();
        }

        try {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
            }
        }

        //
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
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

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
        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
        //

        mConnectedThread = new ConnectedThread(btSocket);
        //mConnectedThread.start();
        //mConnectedThread.write("x");
    }

    private void checkBTState() {
        if(btAdapter==null) {
            Toast.makeText(getBaseContext(), "Dispositivo no soporta Bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }


    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];
            int bytes;
            // Se mantiene siempre leyendo
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);

                    // Mandar datos a la UI
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        // Escritura (Por si se necesita mandar algo al Arduino)
        public void write(String input) {
            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application
                Toast.makeText(getBaseContext(), "Falla en la conexion", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
