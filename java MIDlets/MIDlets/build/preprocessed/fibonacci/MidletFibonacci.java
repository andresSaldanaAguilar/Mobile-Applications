/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonacci;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextBox;
import javax.microedition.midlet.*;

/**
 * @author Andres
 */
public class MidletFibonacci extends MIDlet {

    Form f;
    Display d;
    StringItem si;
    Command c;

    public MidletFibonacci() {
        d = Display.getDisplay(this);
        c = new Command("SALIR", Command.EXIT, 2);
        f = new Form("NÚMERO MARAVILLOSO");
        si = new StringItem("NÚMERO: ", esFibonacci(144));
        f.append(si);
        f.addCommand(c);
        //f.setCommandListener(this);
    }

    public void startApp() {
        d.setCurrent(f);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command co, Displayable di) {
        if (co == c) {
            destroyApp(false);
            notifyDestroyed();
        }
    }

    public String esFibonacci(int numero) {
        boolean esfibonacci = false;
        int FibonacciUno = 0;
        int FibonacciDos = 1;
        String cadenaValores = "";

        System.out.print(FibonacciUno + " ");
        do{
            cadenaValores += " "+FibonacciDos;
            FibonacciDos = FibonacciUno + FibonacciDos;
            FibonacciUno = FibonacciDos - FibonacciUno;
            if(numero == FibonacciDos){
                esfibonacci = true;
            }            
        }while(FibonacciDos <= numero);
        		
        if(esfibonacci){
            cadenaValores += "... es fibonacci";
            System.out.println(cadenaValores+"... es fibonacci");
        }else{
            cadenaValores += "... no es fibonacci";
            System.out.println(cadenaValores+"...no es fibonacci!");
        }
        return cadenaValores;
    }
}
