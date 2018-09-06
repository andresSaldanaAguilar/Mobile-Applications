
package fibonacci;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.*;

public class MidletMaravilloso extends MIDlet implements CommandListener{
    Form f;     
    Display d;
    StringItem  si;
    Command c;
	
    public MidletMaravilloso(){
        d = Display.getDisplay(this);
        c = new Command("SALIR", Command.EXIT,2);
	f = new Form("NÚMERO MARAVILLOSO");         
	si= new StringItem(null, getMaravilloso(10));
	f.append(si);
        f.addCommand(c);
        f.setCommandListener(this);
    }     
	
    public void startApp() {         
	d.setCurrent(f);     
    }     
	
    public void pauseApp() {}     
	
    public void destroyApp(boolean unconditional) {} 
    
    public void commandAction(Command co, Displayable di){
        if (co == c){
            destroyApp(false);
            notifyDestroyed();
        }
    }
    
    public String getMaravilloso(int numero){
        String cadenaValores = "";
        
        cadenaValores = numero + " ";
        if(numero>=1)
            cadenaValores = "El numero " + cadenaValores + "Es maravilloso.";
        else
            cadenaValores = "El numero " + cadenaValores + "No es maravilloso.";
        while(numero>1){
            if(numero%2==0){//PAR
                numero = numero/2;
                cadenaValores = cadenaValores + "\n" + numero;
            }    
            else{//IMPAR
                numero = numero*3+1;
                cadenaValores = cadenaValores + "\n" + numero;
            }
        }        
        return cadenaValores;
    
    }
    
}
