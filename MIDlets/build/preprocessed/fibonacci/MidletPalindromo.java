package fibonacci;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.*;

public class MidletPalindromo extends MIDlet implements CommandListener{
    
    Form f;     
    Display d;
    StringItem  si;
    Command c;
    
    public MidletPalindromo(){
        d = Display.getDisplay(this);
        c = new Command("SALIR", Command.EXIT,2);
	f = new Form("PRUEBA DE UN PALINDROMO");         
	si= new StringItem(null,esPalindromo("Anita lava la tina"));
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
    
    public String esPalindromo(String cadenaAnalizar){
        String cadenaAnalizada="" + cadenaAnalizar;
        String cadenaInvertida = "";
        String cadenaSinEspacios = "";
        
        for(int i=0; i<cadenaAnalizar.length();i++){
            if(cadenaAnalizar.charAt(i)!= 32){
                cadenaSinEspacios = cadenaSinEspacios + cadenaAnalizar.charAt(i);
            }
        }
        for(int i=cadenaSinEspacios.length()-1; i>=0; i--){
            cadenaInvertida = cadenaInvertida + cadenaSinEspacios.charAt(i);
        }

        if(cadenaSinEspacios.equalsIgnoreCase(cadenaInvertida)){
            cadenaAnalizada = cadenaAnalizada + " = " + cadenaInvertida + " ,Es palindroma.";
        }
        else{
            cadenaAnalizada = cadenaAnalizada + " != " + cadenaInvertida + " ,No es palindroma";
        }
                
        return cadenaAnalizada;
    }
}
