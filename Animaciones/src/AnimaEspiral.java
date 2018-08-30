/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;

/**
 * @author Andres
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
public class AnimaEspiral extends GameCanvas implements Runnable{
    Graphics    g;
    Thread      t;
    int numEspiras;
    
    public AnimaEspiral(){
	super(true);
	g = getGraphics();
        t = new Thread(this);  
    }
    
    void Start(int numeroEspiras ){
        this.numEspiras = numeroEspiras;
        t.start();
    }
    
    public void run() {
	int ancho = getWidth();
	int alto = getHeight();
        int x = ancho / 2 - 10;
        int y = alto / 2 - 10;
        int w = 20;
        int h = 20;
        int anguloInicio = 0;
        int anguloDelArco = 180;
        int profundidad = 8;
	             
        for (int i = 0; i < numEspiras*2; i++) {
            if (i % 2 == 0) {
                y = y - profundidad;
                w = w + 2 * profundidad;
                h = h + 2 * profundidad;
                g.setColor(0x0000FF);
                g.drawArc(x, y, w, h, anguloInicio, -anguloDelArco);
            } else {
                x = x - 2 * profundidad;
                y = y - profundidad;
                w = w + 2 * profundidad;
                h = h + 2 * profundidad;
                g.setColor(0x00FF00);
                g.drawArc(x, y, w, h, anguloInicio, anguloDelArco);
            }
            try{
                t.sleep(700);
            }catch(InterruptedException ie){}
            flushGraphics(); 
        }
                        
            
            
	 
    }
}

