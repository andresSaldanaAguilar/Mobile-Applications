package e.andressaldana.pwm_controller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class DispositivosBT extends AppCompatActivity {

    private static final String TAG = "DispositivosBT";
    ListView idLista;
    public static String EXTRA_DEVICE_ADDRESS = "Device Address";

    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos_bt);
    }

    @Override
    public void onResume(){
        super.onResume();
        VerificarEstadoBT();
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this,R.layout.nombre_dispositivos);
        idLista = (ListView) findViewById(R.id.idLista);
        idLista.setAdapter(mPairedDevicesArrayAdapter);
        idLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info = ((TextView)view).getText().toString();
                String address = info.substring(info.length()-17);
                Intent i = new Intent(DispositivosBT.this,MainActivity.class);
                i.putExtra(EXTRA_DEVICE_ADDRESS,address);
                startActivity(i);
            }
        });
        mBtAdapter= BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        if(pairedDevices.size() > 0){
            for(BluetoothDevice device: pairedDevices){
                mPairedDevicesArrayAdapter.add(device.getName()+"\n"+device.getAddress());
            }
        }
    }

    private void VerificarEstadoBT(){
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBtAdapter == null){
            Toast.makeText(getBaseContext(),"El dispositivo no soporta bluetooth",Toast.LENGTH_SHORT).show();
        }
        else{
            if(mBtAdapter.isEnabled()){
                Log.d(TAG,"Bluetooth activado");
            }
            else{
                Intent enableBtIntent= new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent,1);
            }
        }
    }
}
