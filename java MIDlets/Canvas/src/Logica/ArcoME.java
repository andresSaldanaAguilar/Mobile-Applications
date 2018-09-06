/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
public class ArcoME extends MIDlet implements CommandListener {
	private Display d;
	private Command cs; 
	private Canvas  ca; 
	public ArcoME(  ) {
		d  = Display.getDisplay(this);
		ca = new Canvas() {
			private int w;
			private int h;
			public void paint (Graphics g){
                            w = getWidth();
                            h = getHeight();
                            g.setColor(0, 0, 0);
                            g.fillRect(0, 0, w, h);
                            //inicio
                            g.setColor(255, 255, 255);
                            g.setStrokeStyle(Graphics.SOLID);
                            g.fillArc(0, 0, h-100, w-100, 0, 360);
                            //inicio
                            g.setColor(255, 0, 0);
                            g.drawArc(60, 20, 97, 97, -90, 180);
                            //inicio
                            g.setColor(0, 255, 0);
                            g.drawArc(60, 20, 97, 97, 90, 180);
                            g.drawString("w:"+w+" h:"+h, 50, 50, 0);
                        }
		};
		cs = new Command("Salir",Command.EXIT, 3);
		ca.addCommand(cs);
		ca.setCommandListener(this);
	}
	protected void startApp(  ) {
		d.setCurrent(ca);
	}
	protected void pauseApp(  ) {    }
	protected void destroyApp(boolean b) {    }
	public void commandAction(Command co, Displayable di) {
		if (co == cs) {
			destroyApp(true);
			notifyDestroyed();
		} else d.setCurrent(new Alert("", "Otro comando digitado...", null, AlertType.ERROR));
	} 
}
