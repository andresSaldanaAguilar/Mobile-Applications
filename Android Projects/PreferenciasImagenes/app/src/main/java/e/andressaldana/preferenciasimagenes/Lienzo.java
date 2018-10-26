package e.andressaldana.preferenciasimagenes;

import android.content.*;
import android.graphics.*;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class Lienzo extends View{
    Paint p;
    int x, y;
    Bitmap bitmap;
    public Lienzo(Context c,Bitmap bitmap){
        super(c);
        this.bitmap = bitmap;
    }
    protected void onDraw(Canvas c){
        super.onDraw(c); // Canvas pinta atributos
        p = new Paint(); // Paint asigna atributos
        x = c.getWidth(); // También: getMeasuredWidth() o getRight(), x=480
        y = c.getHeight(); // También: getMeasuredHeight() o getBottom(), y=762
        p.setColor(Color.WHITE); // Fondo blanco
        c.drawBitmap(bitmap,0,0,p);
    }

}