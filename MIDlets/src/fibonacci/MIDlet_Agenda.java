/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fibonacci;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;

/**
 * @author Andres
 */
public class MIDlet_Agenda extends MIDlet implements CommandListener{

    Form f,f1;
    Display d;
    Command exitCommand,okCommand,returnCommand;
    TextField nombre,correo,telefono;
    ChoiceGroup genero,pasatiempos,perfil;
    DateField fechaNac;
    Image img;
    ImageItem foto;
    Alert a;

    public MIDlet_Agenda() {
        d = Display.getDisplay(this);
        exitCommand = new Command("Salir", Command.EXIT, 1);
        f = new Form("AGENDA V.1");
        nombre = new TextField("Nombre:", "", 20, TextField.ANY);
        
        String [] genero_lista = {"Masculino","Femenino"};
        genero = new ChoiceGroup("Genero",List.EXCLUSIVE,genero_lista,null);
        
        correo = new TextField("Correo Electronico:", "", 30, TextField.EMAILADDR);
        telefono = new TextField("Telefono:", "", 30, TextField.PHONENUMBER);
        fechaNac = new DateField("Fecha de nacimiento:", DateField.DATE);
        
        String [] pasatiempo_lista = {"Deporte","Arte","Lectura","Autos"};
        pasatiempos = new ChoiceGroup("Pasatiempos",List.MULTIPLE,pasatiempo_lista,null);
        
        String [] foto_lista = {"Desierto","Tulipanes","Medusa","Pinguinos"};
        perfil = new ChoiceGroup("Fotografia",List.EXCLUSIVE,foto_lista,null);
       
        okCommand = new Command("Confirmar",Command.ITEM,1);
       
        f.append(nombre);
        f.append(genero);
        f.append(correo);
        f.append(telefono);
        f.append(fechaNac);
        f.append(pasatiempos);
        f.append(perfil);
        f.addCommand(okCommand);
        f.addCommand(exitCommand);
        f.setCommandListener(this);
    }

    public void startApp() {
        d.setCurrent(f);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command co, Displayable di) {
        if (co == exitCommand) {
            destroyApp(false);
            notifyDestroyed();
        }
        if (co == okCommand) {
            
            f1 = new Form("Notificacion");
            boolean seleccion[] = new boolean[pasatiempos.size()];
            pasatiempos.getSelectedFlags(seleccion);
            String pasatiempos_cadena = "";          
            for(int i = 0 ; i < seleccion.length; i ++){
                if(seleccion[i]){
                    pasatiempos_cadena += "\t"+pasatiempos.getString(i)+"\n";             
                }else{
                }
            }
            
            StringItem si = new StringItem(null,"Nombre:"+nombre.getString()+"\nGenero:"+genero.getString(genero.getSelectedIndex())
            +"\nCorreo:"+correo.getString()+"\nTelefono:"+telefono.getString()+"\nFechaNac:"+fechaNac.getDate().toString()+
            "\nPasatiempos:\n"+pasatiempos_cadena);
                    
            try {
                img = Image.createImage("/"+perfil.getString(perfil.getSelectedIndex())+".png");
            } catch (Exception e) {
            }
            foto = new ImageItem("Java 2", img, ImageItem.LAYOUT_CENTER, "image");
            
            returnCommand = new Command("Volver",Command.ITEM,1);
            
            f1.append(si);
            f1.addCommand(returnCommand);
            f1.setCommandListener(this);
            f1.append(img);               
            d.setCurrent(f1);
            
        }
        if (co == returnCommand) {
            d.setCurrent(f);
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
