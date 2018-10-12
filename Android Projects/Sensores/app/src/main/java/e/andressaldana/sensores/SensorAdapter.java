package e.andressaldana.sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SensorAdapter extends ArrayAdapter<Sensores> {

    private final int entradaLayoutRecurso;

    public SensorAdapter(final Context context, final int resource) {
        super(context, 0);
        this.entradaLayoutRecurso = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View v2 = getWorkingView(convertView);
        final ViewHolder vh = getViewHolder(v2);
        final Sensores ne = getItem(position);
        vh.tituloView.setText(ne.getTitulo());
        vh.infoView.setText(ne.getInformacion());
        vh.imagenView.setImageResource(ne.getIcono());
        return v2;
    }

    private View getWorkingView(final View v3) {
        View workingView = null;
        if (null == v3) {
            final Context c2 = getContext();
            final LayoutInflater inflater = (LayoutInflater) c2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            workingView = inflater.inflate(entradaLayoutRecurso, null);
        } else {
            workingView = v3;
        }
        return workingView;
    }

    private ViewHolder getViewHolder(final View workingView) {
        final Object tag = workingView.getTag();
        ViewHolder vh = null;
        if (null == tag || !(tag instanceof ViewHolder)) {
            vh = new ViewHolder();
            vh.tituloView = (TextView) workingView.findViewById(R.id.xtvtitulo);
            vh.infoView = (TextView) workingView.findViewById(R.id.xtvinformacion);
            vh.imagenView = (ImageView) workingView.findViewById(R.id.xivicono);
            workingView.setTag(vh);
        } else {
            vh = (ViewHolder) tag;
        }
        return vh;
    }

    private static class ViewHolder {
        public TextView tituloView;
        public TextView infoView;
        public ImageView imagenView;
    }
}
