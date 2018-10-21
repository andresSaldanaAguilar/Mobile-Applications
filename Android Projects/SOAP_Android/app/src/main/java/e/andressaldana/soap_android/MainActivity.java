package e.andressaldana.soap_android;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private EditText et1, et2;
    private TextView tvr;
    private Button btn1;
    public static Spinner spn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.editText1);
        et2 = findViewById(R.id.editText2);
        tvr = findViewById(R.id.res);
        btn1 = findViewById(R.id.button);
        spn = findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.operations_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spn.setAdapter(adapter);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                operacionSoap op = (operacionSoap) new operacionSoap();
                op.METHODNAME = spn.getSelectedItem().toString();
                op.execute(et1.getText().toString().trim(),et2.getText().toString().trim());
            }
        });
    }

    private class operacionSoap extends AsyncTask<String,String,String>{
        //config. del servicio SOAP
        static final String NAMESPACE = "http://ws.mx.ipn.com/";
        //nombre del metodo
        String METHODNAME = "";
        //url / ip
        static final String URL = "http://10.0.2.2:8080/EjerOperaciones/Operaciones?WSDL";
        String SOAP_ACTION = NAMESPACE+METHODNAME;
        //segundo plano
        @Override
        protected String doInBackground(String... params) {
            System.out.println("accion: "+METHODNAME);
            String resultado = "";
            //importar libreria para poder usar metodos
            SoapObject request  = new SoapObject(NAMESPACE,METHODNAME);
            request.addProperty("numeroUno",params[0]);
            if(!METHODNAME.equals("raiz") && !METHODNAME.equals("cuadrado") && !METHODNAME.equals("paridad") && !METHODNAME.equals("fibonacci"))
            request.addProperty("numeroDos",params[1]);

            SoapSerializationEnvelope envelope  = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = false;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL);
            try{
                transporte.call(SOAP_ACTION, envelope);
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                resultado = response.toString();
                Log.d("response",response.toString());
            }catch (Exception e){
                Log.d("error",e.getMessage());
            }
            return resultado;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvr.setText(s);
        }


    }
}
