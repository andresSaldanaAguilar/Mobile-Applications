package e.andressaldana.lienzo;

import android.content.*;
import android.graphics.*;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class Lienzo extends View{
    Paint p;
    int x, y;
    public Lienzo(Context c){
        super(c);
    }
    protected void onDraw(Canvas c){
        super.onDraw(c); // Canvas pinta atributos
        p = new Paint(); // Paint asigna atributos
        x = c.getWidth(); // También: getMeasuredWidth() o getRight(), x=480
        y = c.getHeight(); // También: getMeasuredHeight() o getBottom(), y=762
        p.setColor(Color.WHITE); // Fondo blanco
        c.drawPaint(p);
        p.setColor(Color.BLACK); // Texto negro
        p.setTextSize(40);
        p.setColor(Color.rgb(0, 0, 255)); // Ejes azules
        //c.drawLine(x/2, 0, x/2, y, p);
        //c.drawLine(0, y/2, x, y/2, p);
        c.drawText(x+","+y,10,40,p);

        ArrayList<ArrayList<Float>> vector2D = new ArrayList<ArrayList<Float>>();
        ArrayList<Float> v1 = new ArrayList<Float>();
        v1.add((float) (1*x/4));
        v1.add((float) (5*y/8));
        ArrayList<Float> v2 = new ArrayList<Float>();
        v2.add((float) (3*x/4));
        v2.add((float) (5*y/8));
        ArrayList<Float> v3 = new ArrayList<Float>();
        v3.add((float) (x/2));
        v3.add((float) ((5*y/8)-467.65));
        ArrayList<Float> out = new ArrayList<Float>();
        out.add((float) (1*x/4));
        out.add((float) ((5*y/8)-467.65));
        vector2D.add(v1);
        vector2D.add(v2);
        vector2D.add(v3);
        vector2D.add(out);

        c.drawCircle(1*x/4,5*y/8,5,p);
        c.drawCircle(3*x/4,5*y/8,5,p);
        c.drawCircle(x/2, (float) ((5*y/8)-467.65),5,p);
        c.drawCircle(1*x/4,(float) ((5*y/8)-467.65),5,p);
        Random rnd = new Random();
        for(int i = 0; i < 100000; i++){
            ArrayList<Float> aux = new ArrayList<Float>();
            p.setColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
            aux = getMP(vector2D.get(getRandBetween()),vector2D.get(3));
            System.out.println(getRandBetween());
            c.drawCircle(aux.get(0),aux.get(1),5,p);
            vector2D.set(3,aux);
        }

    }

    public ArrayList<Float> getMP(ArrayList<Float> p1, ArrayList<Float> p2){
        ArrayList<Float> aux = new ArrayList<Float>();
        aux.add((p1.get(0)+p2.get(0))/2);
        aux.add((p1.get(1)+p2.get(1))/2);
        return aux;
    }

    public int getRandBetween(){
        Random r = new Random();
        int Low = 0;
        int High = 3;
        return r.nextInt(High-Low) + Low;
    }
}