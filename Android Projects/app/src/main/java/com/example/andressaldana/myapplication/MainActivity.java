package com.example.andressaldana.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {
    //etiquetas
    TextView jtvsum,jtvparidad,jtvmar,jtvpal,jtvfibo;
    EditText jedParidad,jedMar,jedPal,jedFibo;
    Button jbParidad,jbMar,jbPal,jbFibo;

    int x,y,z;
    Actividades pi = new Actividades();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pasando referencia al archivo de vista (setCurrent)
        setContentView(R.layout.activity_main);

        //inicializacion de elementos
        jtvsum = findViewById(R.id.xtvsuma);

        jtvparidad = findViewById(R.id.xtvparidad);
        jedParidad = findViewById(R.id.xedParidad);
        jbParidad = findViewById(R.id.xbParidad);
        jbParidad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jtvparidad.setText(pi.paridad(Integer.parseInt(jedParidad.getText().toString())));
            }
        });

        jtvmar = findViewById(R.id.xtvMar);
        jedMar = findViewById(R.id.xedMar);
        jbMar = findViewById(R.id.xbMar);
        jbMar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jtvmar.setText(pi.Maravilloso(Integer.parseInt(jedMar.getText().toString())));
            }
        });

        jtvpal = findViewById(R.id.xtvPal);
        jedPal = findViewById(R.id.xedPal);
        jbPal = findViewById(R.id.xbPal);
        jbPal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jtvpal.setText(pi.Palindrome(jedPal.getText().toString()));
            }
        });

        jtvfibo = findViewById(R.id.xtvFibo);
        jedFibo = findViewById(R.id.xedFibo);
        jbFibo = findViewById(R.id.xbFibo);
        jbFibo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jtvfibo.setText(pi.Fibonacci(Integer.parseInt(jedFibo.getText().toString())));
            }
        });

        x=2;
        y=3;

        //aciones
        z=x+y;
        jtvsum.setText(x+"+"+y+"="+z);

    }

}

