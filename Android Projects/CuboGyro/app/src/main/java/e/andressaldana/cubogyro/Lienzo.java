package e.andressaldana.cubogyro;

import android.content.*;
import android.graphics.*;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.view.View;

import static android.support.v4.content.ContextCompat.getSystemService;

public class Lienzo extends View implements SensorEventListener {

    Paint p;
    float maxX, maxY, centerX, centerY;
    Obj obj = new Obj();
    double x = 0, y = 0;
    SensorManager sm;
    Sensor s;
    int n;

    public Lienzo(Context c) {
        super(c);
        sm = (SensorManager) c.getSystemService(Context.SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        new MiAsincronia().execute();
    }

    protected void onDraw(Canvas c) {

        super.onDraw(c); // Canvas pinta atributos
        p = new Paint(); // Paint asigna atributos
        float minMaxXY;
        maxX = getWidth() - 1;
        maxY = getHeight() - 1;
        p.setColor(Color.parseColor("#636161"));
        c.drawRect(0, 0, getWidth(), getHeight(), p);
        minMaxXY = Math.min(maxX, maxY);
        centerX = maxX / 2;
        centerY = maxY / 2;
        obj.d = obj.rho * minMaxXY / obj.objSize;
        obj.eyeAndScreen();
        line(c, p, 0, 1);
        line(c, p, 1, 2);
        line(c, p, 2, 3);
        line(c, p, 3, 0); // aristas horizontales inferiores
        line(c, p, 4, 5);
        line(c, p, 5, 6);
        line(c, p, 6, 7);
        line(c, p, 7, 4); // aristas horizontales superiores
        line(c, p, 0, 4);
        line(c, p, 1, 5);
        line(c, p, 2, 6);
        line(c, p, 3, 7); // aristas verticales
    }

    public void onSensorChanged(SensorEvent se) {
        float X = se.values[0];
        float Y = se.values[1];
        System.out.println(se.values[0]);
        System.out.println(se.values[1]);

        x = (int) (X);
        y = (int) (Y);

        obj.theta = (float) ((float) getWidth() / x);
        obj.phi = (float) ((float) getHeight() / y);
        obj.rho = (obj.phi / obj.theta) * getHeight();
        centerX = (float) x;
        centerY = (float) y;
        invalidate();
    }

    public void onAccuracyChanged(Sensor s, int i) {
    }

    void line(Canvas g, Paint pa, int i, int j) {
        Point2D p = obj.vScr[i], q = obj.vScr[j];
        pa.setColor(Color.WHITE);
        g.drawLine(centerX + (int) p.x, centerY - (int) p.y, centerX + (int) q.x, centerY - (int) q.y, pa);
    }

    class Obj {    // Posee los datos del objeto 3D
        float rho, theta = 0.3F, phi = 1.3F, d, objSize, v11, v12, v13, v21, v22, v23, v32, v33, v43; // elementos de la matriz V
        Point3D[] w;    // coordenadas universales
        Point2D[] vScr; // coordenadas de la pantalla

        Obj() {    // CAMBIAR LAS COORDENADAS X,Y,Z CON 0,1 PARA CONSTRUIR PRISMA, CILINDRO, PIRAMIDE, CONO Y ESFERA.
            w = new Point3D[8];
            vScr = new Point2D[8];

            //Cubo
            w[0] = new Point3D(1, -1, -1); // desde la base
            w[1] = new Point3D(1, 1, -1);
            w[2] = new Point3D(-1, 1, -1);
            w[3] = new Point3D(-1, -1, -1);
            w[4] = new Point3D(1, -1, 1);
            w[5] = new Point3D(1, 1, 1);
            w[6] = new Point3D(-1, 1, 1);
            w[7] = new Point3D(-1, -1, 1);

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
            for (int i = 0; i < 8; i++) {
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

    class MiAsincronia extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... x) {
            while (true) {
                try {
                    Thread.sleep(100); // 100 milisegundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                n++;
                publishProgress();
            }
        }

        protected void onProgressUpdate(Void... progress) {
            System.out.println(x);
            System.out.println(y);

        }

    }
}
