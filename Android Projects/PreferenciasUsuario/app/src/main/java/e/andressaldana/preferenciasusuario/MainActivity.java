package e.andressaldana.preferenciasusuario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.*;
import android.app.*;
import android.view.View;
import android.widget.*;
public class MainActivity extends Activity{
    SharedPreferences sp;
    EditText jetNP, jetAP, jetAM;
    Button btn;
    Intent itn;

    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jetNP = (EditText) findViewById(R.id.xetn);
        jetAP = (EditText) findViewById(R.id.xetx);
        jetAM = (EditText) findViewById(R.id.xety);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = getSharedPreferences("preferenciasusuario", Context.MODE_PRIVATE);
                SharedPreferences.Editor miEditor = sp.edit();
                miEditor.putString("Nombre", jetNP.getText().toString());
                miEditor.putString("Paterno", jetAP.getText().toString());
                miEditor.putString("Materno", jetAM.getText().toString());
                miEditor.commit();
                Toast.makeText(view.getContext(), "Informacion guardada", Toast.LENGTH_LONG).show();
                itn = new Intent(MainActivity.this, SegundaActivity.class);
                startActivity(itn);
            }
        });

    }

}
