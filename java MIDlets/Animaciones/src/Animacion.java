/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
public class Animacion extends MIDlet implements CommandListener{
    Display     d;
    AnimaBola   ab;
    AnimaImagen ai;
    AnimaEspiral ae;
    Form menu;
    TextField   numeroEspiras;
    Command ok;
    public void startApp() {
        d = Display.getDisplay(this);
        menu = new Form("Generador de espirales");
        numeroEspiras = new TextField("Numero de giros:", "", 1, TextField.ANY);
        ok = new Command("Crear", Command.OK, 1);
        menu.append(numeroEspiras);
        
        menu.addCommand(ok);
        menu.setCommandListener(this);
        d.setCurrent(menu);
    }
    public void commandAction(Command co, Displayable di) {
        if (co == ok) {
            ae = new AnimaEspiral();
            ae.Start(Integer.parseInt(numeroEspiras.getString()));
            d.setCurrent(ae);
        }
    }
    public void pauseApp() {    }
    public void destroyApp(boolean unconditional) {    }
}

