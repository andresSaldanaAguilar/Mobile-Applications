package e.andressaldana.sqlite;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
public class DbmsActivity extends Activity {
    EditText        jetI, jetN;
    Button          jbnA, jbnL, jbnD, jbnU;
    TextView        jtvL;
    SQLiteDatabase  sqld;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbms);
        jetI = (EditText)   findViewById(R.id.xetI);
        jetN = (EditText)   findViewById(R.id.xetN);
        jbnA = (Button)     findViewById(R.id.xbnA);
        jbnL = (Button)     findViewById(R.id.xbnL);
        jbnD = (Button)     findViewById(R.id.xbnD);
        jbnU = (Button)     findViewById(R.id.xbnU);
        jtvL = (TextView)   findViewById(R.id.xtvL);
        DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(this, "DBContact", null, 1);
        sqld = dsqlh.getWritableDatabase();
        //agregar
        jbnA.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String          nombre = jetN.getText().toString();
                if(!nombre.isEmpty()) {
                    ContentValues   cv = new ContentValues();
                    //buscando ultimo identificador, en la columa cero (id)
                    Cursor c = sqld.rawQuery("SELECT MAX(id) FROM Contactos ", null);
                    c.moveToFirst();
                    int id = 1;
                    //validando que no sea el primer registro
                    if(!c.isNull(0)){
                        if(!c.getString(0).equals("")){
                            id = Integer.parseInt(c.getString(0))+1;
                        }
                    }
                    System.out.println("id"+c.getString(0));
                    cv.put("id", id);
                    cv.put("nombre", nombre);
                    sqld.insert("Contactos", null, cv);
                    jetN.setText("");
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "Please enter a name";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
        //eliminar
        jbnD.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String          id = jetI.getText().toString();
                if(!id.isEmpty()) {
                ContentValues   cv = new ContentValues();
                //buscando ultimo identificador, en la columa cero (id)
                sqld.delete("Contactos", "id="+id, null);
                jetI.setText("");
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Please enter an id";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
        //actualizar
        jbnU.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String          nombre = jetN.getText().toString();
                String          id = jetI.getText().toString();
                if(!nombre.isEmpty() && !id.isEmpty()) {
                    ContentValues cv = new ContentValues();
                    //buscando ultimo identificador, en la columa cero (id)
                    sqld.execSQL("UPDATE Contactos SET nombre='" + nombre + "' WHERE id=" + id + ";");
                    jetI.setText("");
                    jetN.setText("");
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "Please enter a name and id";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
        //listar
        jbnL.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String id, nombre;
                Cursor c = sqld.rawQuery("SELECT id,nombre FROM Contactos", null);
                jtvL.setText("");
                if (c.moveToFirst()) {
                    do {
                        id = c.getString(0);
                        nombre = c.getString(1);
                        jtvL.append(" " + id + "\t" + nombre + "\n");
                    } while(c.moveToNext());
                }
            }
        });
    }
}
