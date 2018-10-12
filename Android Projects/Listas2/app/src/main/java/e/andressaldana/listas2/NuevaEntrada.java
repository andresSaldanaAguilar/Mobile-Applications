package e.andressaldana.listas2;

import android.widget.TextView;

import java.util.Date;
public final class NuevaEntrada {
    private final String titulo;
    private  final TextView title;
    private  final TextView att1;
    private  final TextView att2;
    private  final TextView att3;
    private  final TextView att4;
    private  final TextView att5;
    private final TextView att6;
    private final int icono;

    public NuevaEntrada(final String t, final String x, final String y, final Date f, final int i) {
        this.titulo = t;
        this.title = (TextView) findViewById(R.id.mainTitle);
        this.att1 = (TextView) vista.findViewById(R.id.att1);
        this.att2 = (TextView) vista.findViewById(R.id.att2);
        this.att3 = (TextView) vista.findViewById(R.id.att3);
        this.att4 = (TextView) vista.findViewById(R.id.att4);
        this.att5 = (TextView) vista.findViewById(R.id.att5);
        this.att6 = (TextView) vista.findViewById(R.id.att6);
        this.icono = i;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAtt1() {
        return att1;
    }

    public String getAtt2() {
        return y;
    }

    public int getIcono() {
        return icono;
    }
}