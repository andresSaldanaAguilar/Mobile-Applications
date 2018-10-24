package e.andressaldana.pattern;

import android.content.Context;
import android.graphics.*;  // Canvas, Color, Paint, Path;
import android.view.*;      //MotionEvent, View;

import java.util.ArrayList;
import java.util.LinkedList;

class MiLienzo extends View {
    float   x=50, y=50;
    String  s="";
    Path pa=new Path();
    Float[][] matriz;
    String claveCorrecta = "21036", clave = "";
    float height, width;
    boolean newLine = false, isCorrect = false;

    public MiLienzo(Context c){
        super(c);
    }
    @Override
    protected void onDraw(Canvas c){
        c.drawColor(Color.parseColor("#74909E"));
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(6);
        p.setColor(Color.WHITE);

        height = c.getHeight();
        width = c.getWidth();

        if(isCorrect){
            //mostrar mensaje
            invalidate();
            p.setTypeface(Typeface.DEFAULT);
            p.setTextSize(90);
            c.drawText("Patron correcto",width/5,height/2,p);
        }
        else {
            //cuadricula
            c.drawCircle(1 * width / 4, 1 * (height / 4)+150, 10, p);
            c.drawCircle(2 * width / 4, 1 * (height / 4)+150, 10, p);
            c.drawCircle(3 * width / 4, 1 * (height / 4)+150, 10, p);
            c.drawCircle(1 * width / 4, 2 * (height / 4), 10, p);
            c.drawCircle(2 * width / 4, 2 * (height / 4), 10, p);
            c.drawCircle(3 * width / 4, 2 * (height / 4), 10, p);
            c.drawCircle(1 * width / 4, 3 * (height / 4)-150, 10, p);
            c.drawCircle(2 * width / 4, 3 * (height / 4)-150, 10, p);
            c.drawCircle(3 * width / 4, 3 * (height / 4)-150, 10, p);

            c.drawCircle(1 * width / 4, 1 * (height / 4)+150, 20, p);
            c.drawCircle(2 * width / 4, 1 * (height / 4)+150, 20, p);
            c.drawCircle(3 * width / 4, 1 * (height / 4)+150, 20, p);
            c.drawCircle(1 * width / 4, 2 * (height / 4), 20, p);
            c.drawCircle(2 * width / 4, 2 * (height / 4), 20, p);
            c.drawCircle(3 * width / 4, 2 * (height / 4), 20, p);
            c.drawCircle(1 * width / 4, 3 * (height / 4)-150, 20, p);
            c.drawCircle(2 * width / 4, 3 * (height / 4)-150, 20, p);
            c.drawCircle(3 * width / 4, 3 * (height / 4)-150, 20, p);

            //matriz de cuadricula
            matriz = new Float[][]{
                    {1 * width / 4, (1 * height / 4)+150},
                    {2 * width / 4, (1 * height / 4)+150},
                    {3 * width / 4, (1 * height / 4)+150},
                    {1 * width / 4, 2 * height / 4},
                    {2 * width / 4, 2 * height / 4},
                    {3 * width / 4, 2 * height / 4},
                    {1 * width / 4, (3 * height / 4)-150},
                    {2 * width / 4, (3 * height / 4)-150},
                    {3 * width / 4, (3 * height / 4)-150}
            };
            //vamos dibujando el patron de principio a fin
            if (newLine) {
                try {
                    if (clave.length() > 1) {
                        for (int i = 0; i < clave.length(); i++) {
                            int aux1 = Integer.parseInt(clave.charAt(i) + "");
                            int aux2 = Integer.parseInt(clave.charAt(i + 1) + "");
                            System.out.println(aux1 + " " + aux2);
                            c.drawLine(matriz[aux1][0], matriz[aux1][1], matriz[aux2][0], matriz[aux2][1], p);
                        }
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Not more elements");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Not more elements");
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent me){
        x = me.getX();
        y = me.getY();
        int punto = isOver(x,y);
        if(punto != -1){
            if(!clave.contains(punto+"")){
                //validaciones para mejor UX
                //horizontales
                try {
                    if (clave.charAt(clave.length() - 1) == '0' && punto == 2 || clave.charAt(clave.length() - 1) == '2' && punto == 0) {
                        clave += 1;
                    } else if (clave.charAt(clave.length() - 1) == '3' && punto == 5 || clave.charAt(clave.length() - 1) == '5' && punto == 3) {
                        clave += 4;
                    } else if (clave.charAt(clave.length() - 1) == '6' && punto == 8 || clave.charAt(clave.length() - 1) == '8' && punto == 6) {
                        clave += 7;
                    }
                    //verticales
                    else if (clave.charAt(clave.length() - 1) == '0' && punto == 6 || clave.charAt(clave.length() - 1) == '6' && punto == 0) {
                        clave += 3;
                    } else if (clave.charAt(clave.length() - 1) == '1' && punto == 7 || clave.charAt(clave.length() - 1) == '7' && punto == 1) {
                        clave += 4;
                    } else if (clave.charAt(clave.length() - 1) == '2' && punto == 8 || clave.charAt(clave.length() - 1) == '8' && punto == 2) {
                        clave += 5;
                    }
                    //diagonales
                    else if (clave.charAt(clave.length() - 1) == '0' && punto == 8 || clave.charAt(clave.length() - 1) == '8' && punto == 0) {
                        clave += 4;
                    } else if (clave.charAt(clave.length() - 1) == '2' && punto == 6 || clave.charAt(clave.length() - 1) == '6' && punto == 2) {
                        clave += 4;
                    }
                }catch (StringIndexOutOfBoundsException e){
                    //nada, es el primer punto
                }
                clave += punto;
                newLine = true;
            }
            else{
                //nada
            }
        }

        if(me.getAction()==MotionEvent.ACTION_UP){
            if(clave.equals(claveCorrecta)){
                isCorrect = true;
            }
            else{
                clave = "";
            }
        }
        invalidate();
        return true;
    }

    public int isOver(float x, float y){
        for(int i = 0; i < matriz.length; i++){
            if (x < matriz[i][0]+40 && x > matriz[i][0]-40 && y < matriz[i][1]+40 && y > matriz[i][1]-40){
                return i;
            }
        }
        return -1;
    }
}