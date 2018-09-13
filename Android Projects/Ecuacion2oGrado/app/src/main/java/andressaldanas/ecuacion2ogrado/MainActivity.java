package andressaldanas.ecuacion2ogrado;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.*;
import android.content.Intent;
import android.widget.*;
public class MainActivity extends Activity{
    EditText    jet,jA,jB,jC;
    Button      jbn;
    Bundle      bdl;
    Intent      itn;
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jbn = (Button) findViewById(R.id.xbn);
        jA = (EditText) findViewById(R.id.A);
        jB = (EditText) findViewById(R.id.B);
        jC = (EditText) findViewById(R.id.C);


        jbn.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                itn = new Intent(MainActivity.this, SegundaActivity.class);
                bdl = new Bundle();

                bdl.putDouble("A", Double.parseDouble(jA.getText().toString()));
                bdl.putDouble("B", Double.parseDouble(jB.getText().toString()));
                bdl.putDouble("C", Double.parseDouble(jC.getText().toString()));
                itn.putExtras(bdl);
                startActivity(itn);
            } });
    } }