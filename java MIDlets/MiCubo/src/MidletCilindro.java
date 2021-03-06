
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.*;

public class MidletCilindro extends MIDlet implements CommandListener{

    private Display d;
    Canvas ca;
    private Command cs;

    public MidletCilindro(){
        d = Display.getDisplay(this); 

        ca = new Canvas(){
            int centerX, centerY, maxX, maxY, minMaxXY;
            ObjCilindro obj = new ObjCilindro();
            int x=0, y=0;
            public void paint(Graphics g){             
                g.setColor(0x5B98FF);
                g.fillRect(0, 0, getWidth(), getHeight());
                maxX = getWidth()-1; maxY = getHeight()-1; minMaxXY=Math.min(maxX, maxY);
                centerX = maxX/2;
                centerY = maxY/2;
                obj.d = obj.rho*minMaxXY/obj.objSize;
                obj.eyeAndScreen();
                line(g, 0, 1); line(g, 1, 2); line(g, 2, 3); line(g, 3, 4); line(g, 4, 5); line(g, 5, 6); //base
                line(g, 6, 7); line(g, 7, 0); //base 1
                line(g, 8, 9); line(g, 9, 10); line(g, 10, 11); line(g, 11, 12); line(g, 12, 13); line(g, 13, 14);
                line(g, 14, 15); line(g, 15, 8); //base 2     
                line(g, 0, 8); line(g, 1, 9); line(g, 2, 10); line(g, 3, 11); line(g, 4, 12); line(g, 5, 13);
                line(g, 6, 14); line(g, 7, 15); //union de bases 
            }
            void line(Graphics g, int i, int j){
                Point2D p = obj.vScr[i], q = obj.vScr[j];
                System.out.println(((int)p.x+centerX));
                g.setColor(255,255,255);
                g.drawLine(centerX+(int)p.x ,centerY-(int)p.y, centerX+(int)q.x, centerY-(int)q.y);
            }
            void arc(Graphics g, int i, int j){
                Point2D p = obj.vScr[i], q = obj.vScr[j];
                System.out.println(((int)p.x+centerX));
                g.setColor(255,255,255);
                g.drawArc(centerX+(int)p.x ,centerY-(int)p.y, 100, 100,180,90);
            }
            public void keyPressed(int keyCode){
            switch (getGameAction(keyCode)) {
                case Canvas.DOWN: {
                    y = y + 1;
                    break;
                }
                case Canvas.LEFT: {
                    x = x - 1;
                    break;
                }
                case Canvas.UP: {
                    y = y - 1;
                    break;
                }
                case Canvas.RIGHT: {
                    x = x + 1;
                    break;
                }
                default:
                    x=x;
                    y=y;
            }
            obj.theta   = (float) getWidth()/x;
            obj.phi     = (float) getHeight()/y;
            obj.rho     = (obj.phi/obj.theta)*getHeight();
            centerX     = x;
            centerY     = y;
            repaint();
        }
        };
        cs = new Command("Salir",Command.EXIT, 3);
        ca.addCommand(cs);
        ca.setCommandListener(this);
    }
    public void startApp(){
 	d.setCurrent(ca);       
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    public void commandAction(Command co, Displayable di) {
        if (co == cs) {
                destroyApp(true);
                notifyDestroyed();
        } else d.setCurrent(new Alert("", "Otro comando digitado...", null, AlertType.ERROR));
    } 
}

class ObjCilindro{	// Posee los datos del objeto 3D
    float rho, theta=0.3F, phi=1.3F, d, objSize, v11, v12, v13, v21, v22, v23, v32, v33, v43; // elementos de la matriz V
    Point3D [] w;	// coordenadas universales
    Point2D [] vScr; // coordenadas de la pantalla
    ObjCilindro(){	// CAMBIAR LAS COORDENADAS X,Y,Z CON 0,1 PARA CONSTRUIR PRISMA, CILINDRO, PIRAMIDE, CONO Y ESFERA.
        w	= new Point3D[16];
	vScr	= new Point2D[16];
        
        //base 1
        w[0]	= new Point3D(1, -1, -1); //0
        w[1]	= new Point3D(1.5, 0, -1);//2
	w[2]	= new Point3D(1, 1, -1); //1
        w[3]	= new Point3D(0, 1.5, -1);//3
	w[4]	= new Point3D(-1, 1, -1);//4
        w[5]	= new Point3D(-1.5, 0, -1);//5
	w[6]	= new Point3D(-1, -1, -1);//6
	w[7]	= new Point3D(0, -1.5, -1);//7
        
        //base 2
        w[8]	= new Point3D(1, -1, 2); //8
        w[9]	= new Point3D(1.5, 0, 2);//9
	w[10]	= new Point3D(1, 1, 2); //10
        w[11]	= new Point3D(0, 1.5, 2);//11
	w[12]	= new Point3D(-1, 1, 2);//12
        w[13]	= new Point3D(-1.5, 0, 2);//13
	w[14]	= new Point3D(-1, -1, 2);//14
	w[15]	= new Point3D(0, -1.5, 2);//15      
    
	objSize = (float) Math.sqrt(12F); // = sqrt(2*2 + 2*2 + 2*2) es la distancia entre dos vertices opuestos
	rho	= 5*objSize;		// para cambiar la perspectiva
    }
    void initPersp(){
        float costh = (float)Math.cos(theta), sinth=(float)Math.sin(theta), cosph=(float)Math.cos(phi), sinph=(float)Math.sin(phi);
	v11 = -sinth; v12 = -cosph*costh; v13 = sinph*costh;
	v21 = costh; v22 = -cosph*sinth; v23 = sinph*sinth;
	v32 = sinph; v33 = cosph; v43 = -rho;
    }
    void eyeAndScreen(){
        initPersp();
	for(int i=0; i<16; i++){
            Point3D p = w[i];
            float x = v11*p.x + v21*p.y, y = v12*p.x + v22*p.y + v32*p.z, z = v13*p.x + v23*p.y + v33*p.z + v43;
            vScr[i] = new Point2D(-d*x/z, -d*y/z);
	}
    }
}

