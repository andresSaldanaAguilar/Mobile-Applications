package andressaldanas.ecuacion2ogrado;

import android.os.Bundle;
import android.app.Activity;
import android.widget.*;

import java.text.DecimalFormat;

public class SegundaActivity extends Activity{
    TextView    jtv1,jtv2;
    Bundle      bdl;
    double A,B,C;
    String res1,res2;

    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_segunda);
        jtv1 =  (TextView) findViewById(R.id.textView4);
        jtv2 =  (TextView) findViewById(R.id.textView5);
        bdl = getIntent().getExtras();
        A = bdl.getDouble("A");
        B = bdl.getDouble("B");
        C = bdl.getDouble("C");
        System.out.println(A+" "+B+" "+C);

        res1 = quadraticEquationRoot1(A,B,C);
        res2 = quadraticEquationRoot2(A,B,C);

        jtv1.setText(res1);
        jtv2.setText(res2);

    }

    public String quadraticEquationRoot1(double a, double b, double c){
        double d = (b * b - 4 * a * c);
        double re = -b / (2 * a);
        DecimalFormat df = new DecimalFormat("####0.00");
        String aux;

        if (d >= 0) {
            aux = "" + df.format(Math.sqrt(d) / (2 * a) + re);
        }else{
            aux = df.format(re) + " + " + df.format(Math.sqrt(-d) / (2 * a)) + "i";
        }
        return aux;
    }

    public String quadraticEquationRoot2(double a, double b, double c){
        double d = (b * b - 4 * a * c);
        double re = -b / (2 * a);
        DecimalFormat df = new DecimalFormat("####0.00");
        String aux;

        if (d >= 0) {
            aux = "" + df.format(-Math.sqrt(d) / (2 * a) + re);
        }else{
            aux = df.format(re) + " - " + df.format(Math.sqrt(-d) / (2 * a)) + "i";
        }

        return aux;
    }

}


