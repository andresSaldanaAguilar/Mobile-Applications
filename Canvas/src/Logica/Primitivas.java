/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
public class Primitivas extends MIDlet implements CommandListener {
	private Display	d;
	private Command	cs;
	private Canvas	ca;
	public Primitivas(  ) {
		d  = Display.getDisplay(this);
		ca = new Canvas() {
			private int w;
			private int h;
			public void paint (Graphics g){
				w=getWidth();
				h=getHeight();
				g.setColor(0, 0, 0);
				g.fillRect(0, 0, w, h);
				g.setColor(255, 255, 255);
				g.setStrokeStyle(Graphics.SOLID);
				g.drawLine(0, h/2, w-1, h/2);
				g.setColor(0, 255, 0);
				g.setStrokeStyle(Graphics.DOTTED);
				g.drawLine(0, 0, w-1, h-1);
				g.setColor(255, 0, 0);
				g.setStrokeStyle(Graphics.DOTTED);
				g.drawRect(w/4, 0, w/2, h/4);
				g.setColor(0, 0, 255);
				g.setStrokeStyle(Graphics.SOLID);
				g.drawRoundRect(w/4 + 4, 4, w/2 -8, h/4 -8, 8,8);
			}
		};
		cs=new Command("Salir",Command.EXIT, 3);
		ca.addCommand(cs);
		ca.setCommandListener(this);
	}
	protected void startApp(  ) {
		d.setCurrent(ca);
	}
	protected void pauseApp(  ) {    }
	protected void destroyApp(boolean b) {    }
	public void commandAction(Command co, Displayable di) {
		if (co ==cs) {
			destroyApp(true);
			notifyDestroyed();
		} else d.setCurrent(new Alert("", "Otro comando digitado...", null, AlertType.ERROR));
	}
}

