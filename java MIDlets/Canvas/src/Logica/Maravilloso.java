/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
public class Maravilloso extends MIDlet implements CommandListener {
	private Display d;
	private Command c; 
	private Canvas  ca; 
	public Maravilloso ( ) {
		d  = Display.getDisplay(this);
		ca = new Canvas() {
			private int w, h;
			public void paint (Graphics g){
				w = getWidth();
				h = getHeight();
				g.setColor(0, 0, 0);
				g.fillRect(0, 0, w, h);
				try {
					Image i= Image.createImage("/java.png");
					g.drawImage(i, w/2, h/2,(Graphics.VCENTER| Graphics.HCENTER));		      
				} catch (java.io.IOException e) {
					g.setColor(255, 255, 255);
					g.setStrokeStyle(Graphics.SOLID);
					g.drawString("Error al leer java.png", 0, h/2, (Graphics.BASELINE|Graphics.LEFT));
				}
			}
		};
		c = new Command("Salir", Command.EXIT, 3);
		ca.addCommand(c);
		ca.setCommandListener(this);
	}
	protected void startApp(  ) {
		d.setCurrent(ca);
	}
	protected void pauseApp(  ) {    }
	protected void destroyApp(boolean b) {    }
	public void commandAction(Command co, Displayable di) {
		if (co == c) {
			destroyApp(true);
			notifyDestroyed();
		} else d.setCurrent(new Alert("", "Otro comando digitado...", null, AlertType.ERROR));
	} 
}
