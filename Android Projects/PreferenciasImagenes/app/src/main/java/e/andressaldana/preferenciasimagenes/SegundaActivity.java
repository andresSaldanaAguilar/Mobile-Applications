package e.andressaldana.preferenciasimagenes;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class SegundaActivity extends AppCompatActivity {
    SharedPreferences sp;
    Bitmap myBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        sp = getSharedPreferences("preferenciasimagen", Context.MODE_PRIVATE);
        System.out.println("Recieved path:"+sp.getString("imagen", "DEFAULT"));
        File imgFile = new  File(sp.getString("imagen", "DEFAULT"));

        if(imgFile.exists()){
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        else{
            System.out.println("File not found.");
        }

        Lienzo l = new Lienzo(this, myBitmap);
        setContentView(l);

    }
}
