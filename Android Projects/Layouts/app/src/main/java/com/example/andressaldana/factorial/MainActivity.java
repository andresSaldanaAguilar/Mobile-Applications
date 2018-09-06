package com.example.andressaldana.factorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView jtv;
    int n=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //jtv = findViewById(R.id.xtv);
        //jtv.setText(n+"! = "+factorial(n));

        configureNextButton();
    }

    public int factorial(int n){
        int f=1;
        for (int i = n; i > 0 ; i--){
            f = f * i ;
        }
        return f;
    }

    public void configureNextButton(){
        Button nextButton = (Button) findViewById(R.id.BtnAdelante1);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }
}
