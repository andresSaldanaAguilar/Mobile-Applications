package com.example.andressaldana.buttons;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;

import android.support.design.widget.FloatingActionButton;
import android.widget.Button;

public class MainActivity extends Activity {
    FloatingActionButton jfab;
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        final Button jbn = (Button) findViewById(R.id.bn_id);
        jbn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Desarrollo de alguna acción.
         } });
    }
}
