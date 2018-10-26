package e.andressaldana.preferenciasusuario;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {
    TextView jetNP, jetAP, jetAM;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        jetNP = (TextView) findViewById(R.id.nombre);
        jetAP = (TextView) findViewById(R.id.paterno);
        jetAM = (TextView) findViewById(R.id.materno);
        sp = getSharedPreferences("preferenciasusuario", Context.MODE_PRIVATE);
        jetNP.setText(sp.getString("Nombre", "DEFAULT"));
        jetAP.setText(sp.getString("Paterno", "DEFAULT"));
        jetAM.setText(sp.getString("Materno", "DEFAULT"));
    }
}

