/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
public class AnimaBola extends GameCanvas implements Runnable{
    Graphics    g;
    Thread      t;
    public AnimaBola(){
	super(true);
	g = getGraphics();
        t = new Thread(this);
        t.start();
    }
    public void run() {
        int x=0, y=0;
	int vx = 5;
	int vy = 5;
	int ancho = getWidth();
	int alto = getHeight(); 
	while(true) {
	    g.setColor(0xFFFFFF); //color blanco
	    g.fillRect(0, 0, ancho, alto);
	    g.setColor(0x0000FF); //color azul   
	    g.fillArc(x, y, 20, 20, 0, 360);
	    try {
		t.sleep(3);
	    } catch (InterruptedException e) { }
	    x = x + vx;
	    y = y + vy;
	    if ((x + 20) > ancho || (x < 0 ))
		vx = -vx; 
	    if ((y + 20) > alto || (y < 0 )) 
		vy = -vy; 
		flushGraphics(); 
	} 
    }
}
