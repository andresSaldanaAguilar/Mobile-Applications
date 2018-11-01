package e.andressaldana.btlist;

import java.util.Set;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends Activity {
    TextView textView1;
    public static String EXTRA_DEVICE_ADDRESS = "device_address";

    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBTState();

        textView1 = (TextView) findViewById(R.id.connecting);
        textView1.setText("");

        // Se inicializa el Adapter para ListView
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);

        // Se pone el Adapted anterior y el evento ItemClickListener
        ListView pairedListView = (ListView) findViewById(R.id.paired_devices);
        pairedListView.setAdapter(mPairedDevicesArrayAdapter);
        pairedListView.setOnItemClickListener(mDeviceClickListener);

        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        // Se obtienen los dispositivos emparejados

        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

        // Se añaden a la lista los dispositivos encontrados
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        } else {
            // Se dice que no se encontraron dispositivos emparejados
            String noDevices = getResources().getText(R.string.none_paired).toString();
            mPairedDevicesArrayAdapter.add(noDevices);
        }
    }

    private OnItemClickListener mDeviceClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
            textView1.setText("Conectando...");
            // Se obtiene la MAC del módulo HC
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            // Se hace un Intent hacia la actividad
            Intent i = new Intent(MainActivity.this, Main2Activity.class);
            i.putExtra(EXTRA_DEVICE_ADDRESS, address);
            startActivity(i);
        }
    };

    private void checkBTState() {
        // Verifica si está prendido o apagado el BT
        mBtAdapter = BluetoothAdapter.getDefaultAdapter(); // CHECK THIS OUT THAT IT WORKS!!!
        if(mBtAdapter == null) {
            Toast.makeText(getBaseContext(), "Dispositivo no soporta Bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            if (mBtAdapter.isEnabled()) { // Está activo BT
            } else { // Está desactivado
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }
}
