package e.andressaldana.draganddrop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Arrastra a;
    public void onCreate(Bundle b){
        super.onCreate(b);
        a = new Arrastra(this);
        setContentView(a);
    }
    class Arrastra extends View {
        Paint paint=new Paint();
        Paint	p;
        int     s=-1;
        float x=100, y=100, r=50;
        public Arrastra(Context c){
            super(c);
            paint = new Paint();
            paint.setColor(Color.GREEN);
        }
        protected void onDraw(Canvas can){
            can.drawColor(Color.WHITE);
            can.drawCircle(x, y, r, paint);
        }
        public boolean onTouchEvent(MotionEvent me){
            float X = me.getX();
            float Y = me.getY();
            if(me.getAction()==MotionEvent.ACTION_DOWN){
                s=-1;
                for(int i=0; i<2; i++){
                    double dx=X-x, dy=Y-y;
                    float d=(float) Math.sqrt(dx*dx + dy*dy);
                    if(d <= r){
                        s=i;
                        invalidate();
                    }
                }
            }
            if(me.getAction()==MotionEvent.ACTION_MOVE){
                if(s>-1){
                    x=X;
                    y=Y;
                    invalidate();
                }
            }
            return true;
        }
    }
}
