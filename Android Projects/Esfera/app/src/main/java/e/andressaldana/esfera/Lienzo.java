package e.andressaldana.esfera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View {
    Paint p;
    int maxX, maxY, centerX, centerY;
    Obj obj = new Obj();
    int x = 0, y = 0;

    public Lienzo(Context c) {
        super(c);
    }

    protected void onDraw(Canvas c) {
        super.onDraw(c); // Canvas pinta atributos
        p = new Paint(); // Paint asigna atributos
        int minMaxXY;
        maxX = getWidth() - 1;
        maxY = getHeight() - 1;
        p.setColor(Color.parseColor("#636161"));
        c.drawRect(0,0,getWidth(),getHeight(),p);
        minMaxXY = Math.min(maxX, maxY);
        centerX = maxX / 2;
        centerY = maxY / 2;
        obj.d = obj.rho * minMaxXY / obj.objSize;
        obj.eyeAndScreen();
        line(c, p, 0, 1); line(c, p, 1, 2); line(c, p, 2, 3); line(c, p, 3, 4); line(c, p, 4, 5); line(c, p, 5, 6); //base
        line(c, p, 6, 7); line(c, p, 7, 8); line(c, p, 8, 9); line(c, p, 9, 10); line(c, p, 10, 11); line(c, p, 11, 12); //base
        line(c, p, 12, 13); line(c, p, 13, 14); line(c, p, 14, 15); line(c, p, 15, 0); //base

        line(c, p, 16, 17); line(c, p, 17, 18); line(c, p, 18, 19); line(c, p, 19, 20); line(c, p, 20, 21); line(c, p, 21, 22);
        line(c, p, 22, 23); line(c, p, 23, 24); line(c, p, 24, 25); line(c, p, 25, 26); line(c, p, 26, 27); line(c, p, 27, 28);
        line(c, p, 28, 29); line(c, p, 29, 30); line(c, p, 30, 31); line(c, p, 31, 16);


        line(c, p, 32, 33); line(c, p, 33, 34); line(c, p, 34, 35); line(c, p, 35, 36); line(c, p, 36, 37); line(c, p, 37, 38); //base
        line(c, p, 38, 39); line(c, p, 39, 40); line(c, p, 40, 41); line(c, p, 41, 42); line(c, p, 42, 43); line(c, p, 43, 44); //base
        line(c, p, 44, 45); line(c, p, 45, 46); line(c, p, 46, 47); line(c, p, 47, 32);



        line(c, p, 48, 49); line(c, p, 49, 50); line(c, p, 50, 51); line(c, p, 51, 52); line(c, p, 52, 53); line(c, p, 53, 54); //base
        line(c, p, 54, 55); line(c, p, 55, 56); line(c, p, 56, 57); line(c, p, 57, 58); line(c, p, 58, 59); line(c, p, 59, 60); //base
        line(c, p, 60, 61); line(c, p, 61, 62); line(c, p, 62, 63); line(c, p, 63, 48);


        line(c, p, 64, 65); line(c, p, 65, 66); line(c, p, 66, 67); line(c, p, 67, 68); line(c, p, 68, 69); line(c, p, 69, 70); //base
        line(c, p, 70, 71); line(c, p, 71, 72); line(c, p, 72, 73); line(c, p, 73, 74); line(c, p, 74, 75); line(c, p, 75, 76); //base
        line(c, p, 76, 77); line(c, p, 77, 78); line(c, p, 78, 79); line(c, p, 79, 64);


        line(c, p, 80, 81); line(c, p, 81, 82); line(c, p, 82, 83); line(c, p, 83, 84); line(c, p, 84, 85); line(c, p, 85, 86); //base
        line(c, p, 86, 87); line(c, p, 87, 88); line(c, p, 88, 89); line(c, p, 89, 90); line(c, p, 90, 91); line(c, p, 91, 92); //base
        line(c, p, 92, 93); line(c, p, 93, 94); line(c, p, 94, 95); line(c, p, 95, 80);

        line(c, p, 96, 97); line(c, p, 97, 98); line(c, p, 98, 99); line(c, p, 99, 100); line(c, p, 100, 101); line(c, p, 101, 102); //base
        line(c, p, 102, 103); line(c, p, 103, 104); line(c, p, 104, 105); line(c, p, 105, 106); line(c, p, 106, 107); line(c, p, 107, 108); //base
        line(c, p, 108, 109); line(c, p, 109, 110); line(c, p, 110, 111); line(c, p, 111, 96);
    }

    public boolean onTouchEvent(MotionEvent me){
        float X = me.getX();
        float Y = me.getY();

        if(me.getAction()==MotionEvent.ACTION_MOVE){
            x = (int) (X);
            y = (int) (Y);
        }
        obj.theta   = (float) getWidth()/x;
        obj.phi     = (float) getHeight()/y;
        obj.rho     = (obj.phi/obj.theta)*getHeight();
        centerX     = x;
        centerY     = y;
        invalidate();
        return true;
    }

    void line(Canvas g, Paint pa, int i, int j) {
        Point2D p = obj.vScr[i], q = obj.vScr[j];
        System.out.println(((int) p.x + centerX));
        pa.setColor(Color.WHITE);
        pa.setStrokeWidth(5);
        g.drawLine(centerX + (int) p.x, centerY - (int) p.y, centerX + (int) q.x, centerY - (int) q.y, pa);
    }

    class Obj {    // Posee los datos del objeto 3D
        float rho, theta = 0.3F, phi = 1.3F, d, objSize, v11, v12, v13, v21, v22, v23, v32, v33, v43; // elementos de la matriz V
        Point3D[] w;    // coordenadas universales
        Point2D[] vScr; // coordenadas de la pantalla

        Obj() {    // CAMBIAR LAS COORDENADAS X,Y,Z CON 0,1 PARA CONSTRUIR PRISMA, CILINDRO, PIRAMIDE, CONO Y ESFERA.
            w	= new Point3D[112];
            vScr	= new Point2D[112];

            //base inferior
            w[0]	= new Point3D(1, -1, -1); //0
            w[1]	= new Point3D(1.4, -.5, -1);//2	////////////
            w[2]	= new Point3D(1.5, 0, -1); //1
            w[3]	= new Point3D(1.4, .5, -1);//3  ////////////
            w[4]	= new Point3D(1, 1, -1);//4
            w[5]	= new Point3D(.5, 1.4, -1);//5 /////////////
            w[6]	= new Point3D(0, 1.5, -1);//6
            w[7]	= new Point3D(-.5, 1.4, -1);//7 ////////////
            w[8]	= new Point3D(-1, 1, -1);//14
            w[9]	= new Point3D(-1.4, .5, -1);//8 ////////////
            w[10]	= new Point3D(-1.5, 0, -1);//9
            w[11]	= new Point3D(-1.4, -.5, -1);//10 /////////
            w[12]	= new Point3D(-1, -1, -1);//11
            w[13]	= new Point3D(-.5, -1.4, -1);//12 ////////
            w[14]	= new Point3D(0, -1.5, -1);//13
            w[15]	= new Point3D(.5, -1.4, -1);//15 /////////
/////////////////////////////////////////////////////////////////////////////////////
            w[16]	= new Point3D(1.5, -1, -.5); //0
            w[17]	= new Point3D(1.9, -.5, -.5);//2	////////////
            w[18]	= new Point3D(2, 0, -.5); //1
            w[19]	= new Point3D(1.9, .5, -.5);//3  ////////////
            w[20]	= new Point3D(1, 1.5, -.5);//4
            w[21]	= new Point3D(.5, 1.9, -.5);//5 /////////////
            w[22]	= new Point3D(0, 2, -.5);//6
            w[23]	= new Point3D(-.5, 1.9, -.5);//7 ////////////
            w[24]	= new Point3D(-1.5, 1, -.5);//14
            w[25]	= new Point3D(-1.9, .5, -.5);//8 ////////////
            w[26]	= new Point3D(-2, 0, -.5);//9
            w[27]	= new Point3D(-1.9, -.5, -.5);//10 /////////
            w[28]	= new Point3D(-1, -1.5, -.5);//11
            w[29]	= new Point3D(-.5, -1.9, -.5);//12 ////////
            w[30]	= new Point3D(0, -2, -.5);//13
            w[31]	= new Point3D(.5, -1.9, -.5);//15 /////////

            w[32]	= new Point3D(2, -1, 0); //0
            w[33]	= new Point3D(2.4, -.5, 0);//2	////////////
            w[34]	= new Point3D(2.5, 0, 0); //1
            w[35]	= new Point3D(2.4, .5, 0);//3  ////////////
            w[36]	= new Point3D(1, 2, 0);//4
            w[37]	= new Point3D(.5, 2.4, 0);//5 /////////////
            w[38]	= new Point3D(0, 2.5, 0);//6
            w[39]	= new Point3D(-.5, 2.4, 0);//7 ////////////
            w[40]	= new Point3D(-2, 1, 0);//14
            w[41]	= new Point3D(-2.4, .5, 0);//8 ////////////
            w[42]	= new Point3D(-2.5, 0, 0);//9
            w[43]	= new Point3D(-2.4, -.5, 0);//10 /////////
            w[44]	= new Point3D(-1, -2, 0);//11
            w[45]	= new Point3D(-.5, -2.4, 0);//12 ////////
            w[46]	= new Point3D(0, -2.5, 0);//13
            w[47]	= new Point3D(.5, -2.4, 0);//15 /////////

            w[48]	= new Point3D(1.5, -1, .5); //0
            w[49]	= new Point3D(1.9, -.5, .5);//2	////////////
            w[50]	= new Point3D(2, 0, .5); //1
            w[51]	= new Point3D(1.9, .5, .5);//3  ////////////
            w[52]	= new Point3D(1, 1.5, .5);//4
            w[53]	= new Point3D(.5, 1.9, .5);//5 /////////////
            w[54]	= new Point3D(0, 2, .5);//6
            w[55]	= new Point3D(-.5, 1.9, .5);//7 ////////////
            w[56]	= new Point3D(-1.5, 1, .5);//14
            w[57]	= new Point3D(-1.9, .5, .5);//8 ////////////
            w[58]	= new Point3D(-2, 0, .5);//9
            w[59]	= new Point3D(-1.9, -.5, .5);//10 /////////
            w[60]	= new Point3D(-1, -1.5, .5);//11
            w[61]	= new Point3D(-.5, -1.9, .5);//12 ////////
            w[62]	= new Point3D(0, -2, .5);//13
            w[63]	= new Point3D(.5, -1.9, .5);//15 /////////

//////////////////////////////////////////////////////////////////////////////////////
            w[64]	= new Point3D(1, -1, 1); //0
            w[65]	= new Point3D(1.4, -.5, 1);//2	////////////
            w[66]	= new Point3D(1.5, 0, 1); //1
            w[67]	= new Point3D(1.4, .5, 1);//3  ////////////
            w[68]	= new Point3D(1, 1, 1);//4
            w[69]	= new Point3D(.5, 1.4, 1);//5 /////////////
            w[70]	= new Point3D(0, 1.5, 1);//6
            w[71]	= new Point3D(-.5, 1.4, 1);//7 ////////////
            w[72]	= new Point3D(-1, 1, 1);//14
            w[73]	= new Point3D(-1.4, .5, 1);//8 ////////////
            w[74]	= new Point3D(-1.5, 0, 1);//9
            w[75]	= new Point3D(-1.4, -.5, 1);//10 /////////
            w[76]	= new Point3D(-1, -1, 1);//11
            w[77]	= new Point3D(-.5, -1.4, 1);//12 ////////
            w[78]	= new Point3D(0, -1.5, 1);//13
            w[79]	= new Point3D(.5, -1.4, 1);//15 /////////
/////////////////////////////////////////////////////////////////////TAPAS INFERIORES
            w[80]	= new Point3D(.5, -1, -1.5); //0
            w[81]	= new Point3D(.9, -.5, -1.5);//2	////////////
            w[82]	= new Point3D(1, 0, -1.5); //1
            w[83]	= new Point3D(.9, .5, -1.5);//3  ////////////
            w[84]	= new Point3D(1, .5, -1.5);//4
            w[85]	= new Point3D(.5, .9, -1.5);//5 /////////////
            w[86]	= new Point3D(0, 1, -1.5);//6
            w[87]	= new Point3D(-.5, .9, -1.5);//7 ////////////
            w[88]	= new Point3D(-.5, 1, -1.5);//14
            w[89]	= new Point3D(-.9, .5, -1.5);//8 ////////////
            w[90]	= new Point3D(-1, 0, -1.5);//9
            w[91]	= new Point3D(-.9, -.5, -1.5);//10 /////////
            w[92]	= new Point3D(-1, -.5, -1.5);//11
            w[93]	= new Point3D(-.5, -.9, -1.5);//12 ////////
            w[94]	= new Point3D(0, -1, -1.5);//13
            w[95]	= new Point3D(.5, -.9, -1.5);//15 /////////

            w[96]	= new Point3D(.5, -1, 1.5); //0
            w[97]	= new Point3D(.9, -.5, 1.5);//2	////////////
            w[98]	= new Point3D(1, 0, 1.5); //1
            w[99]	= new Point3D(.9, .5, 1.5);//3  ////////////
            w[100]	= new Point3D(1, .5, 1.5);//4
            w[101]	= new Point3D(.5, .9, 1.5);//5 /////////////
            w[102]	= new Point3D(0, 1, 1.5);//6
            w[103]	= new Point3D(-.5, .9, 1.5);//7 ////////////
            w[104]	= new Point3D(-.5, 1, 1.5);//14
            w[105]	= new Point3D(-.9, .5, 1.5);//8 ////////////
            w[106]	= new Point3D(-1, 0, 1.5);//9
            w[107]	= new Point3D(-.9, -.5, 1.5);//10 /////////
            w[108]	= new Point3D(-1, -.5, 1.5);//11
            w[109]	= new Point3D(-.5, -.9, 1.5);//12 ////////
            w[110]	= new Point3D(0, -1, 1.5);//13
            w[111]	= new Point3D(.5, -.9, 1.5);//15 /////////

            objSize = (float) Math.sqrt(12F); // = sqrt(2*2 + 2*2 + 2*2) es la distancia entre dos vertices opuestos
            rho = 5 * objSize;        // para cambiar la perspectiva
        }

        void initPersp() {
            float costh = (float) Math.cos(theta), sinth = (float) Math.sin(theta), cosph = (float) Math.cos(phi), sinph = (float) Math.sin(phi);
            v11 = -sinth;
            v12 = -cosph * costh;
            v13 = sinph * costh;
            v21 = costh;
            v22 = -cosph * sinth;
            v23 = sinph * sinth;
            v32 = sinph;
            v33 = cosph;
            v43 = -rho;
        }

        void eyeAndScreen() {
            initPersp();
            for (int i = 0; i < 112; i++) {
                Point3D p = w[i];
                float x = v11 * p.x + v21 * p.y, y = v12 * p.x + v22 * p.y + v32 * p.z, z = v13 * p.x + v23 * p.y + v33 * p.z + v43;
                vScr[i] = new Point2D(-d * x / z, -d * y / z);
            }
        }
    }

    class Point2D {
        float x, y;

        Point2D(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    class Point3D {
        float x, y, z;

        Point3D(double x, double y, double z) {
            this.x = (float) x;
            this.y = (float) y;
            this.z = (float) z;
        }
    }
}

