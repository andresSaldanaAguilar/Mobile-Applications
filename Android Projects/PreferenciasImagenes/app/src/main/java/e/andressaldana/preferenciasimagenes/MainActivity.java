package e.andressaldana.preferenciasimagenes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.*;
import android.app.*;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import java.net.URISyntaxException;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

public class MainActivity extends Activity{
    SharedPreferences sp;
    Button btn;
    Intent itn,i;

    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{CAMERA,WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE},100);
        }
        StrictMode.VmPolicy.Builder newbuilder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(newbuilder.build());
        i =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri image = data.getData();
        String path = null;
        try {
            path = PathUtil.getPath(this,image);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("path: "+path);
        sp = getSharedPreferences("preferenciasimagen", Context.MODE_PRIVATE);
        SharedPreferences.Editor miEditor = sp.edit();
        miEditor.putString("imagen",path);
        miEditor.commit();

        itn = new Intent(MainActivity.this, SegundaActivity.class);
        startActivity(itn);
    }
}
