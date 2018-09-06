/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonacci;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.*;

/**
 * @author Andres
 */
public class MidletPar extends MIDlet implements CommandListener{
    Form f;     
    Display d;
    StringItem  si;
    Command c;
    
    public MidletPar(){
        d = Display.getDisplay(this);
        c = new Command("Salir", Command.EXIT,1);
	f = new Form("PARIDAD DE UN NÚMERO");         
	si= new StringItem(null , Paridad(-5));
	f.append(si);
        f.addCommand(c);
        f.setCommandListener(this);
    }
    
    public void startApp() {
        d.setCurrent(f);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    public void commandAction(Command co, Displayable di){
        if (co == c){
            destroyApp(false);
            notifyDestroyed();
        }
    }
    
    public String Paridad(int numero){
        String cadenaValores = "";
            if(abs(numero)%2 == 0)
                cadenaValores ="El numero " + numero + " es par.";
            else
                cadenaValores ="El numero " + numero + " es impar.";
        return cadenaValores;  
    }
    
    public static long  abs(long a) {
        return (a < 0) ? -a : a;
    }
}
