/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fibonacci;

import agendaRegistros.RMSOps;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 * @author Andres
 */
public class MIDlet_Agenda extends MIDlet implements CommandListener{

    Form f_main,f_info,f_list;
    Display d;
    Command exitCommand,okCommand,returnCommand,createCommand,borrarCommand,modificarCommand;
    TextField nombre,correo,telefono,borrar;
    ChoiceGroup genero,pasatiempos,perfil;
    DateField fechaNac;
    Image img;
    ImageItem foto;
    ImageItem [] fotos;
    Alert a;
    RMSOps gestor_registros;
    StringItem si;
    StringItem [] sia;
    String zona = "ZonaAgendav5";

    public MIDlet_Agenda() throws RecordStoreNotOpenException {
        gestor_registros = new RMSOps(zona);
        d = Display.getDisplay(this);
        exitCommand = new Command("Salir", Command.EXIT, 1);
        createCommand = new Command("Añadir", Command.OK, 1);
        borrarCommand = new Command("Borrar", Command.OK, 1);
        
        f_list = new Form("Agenda V.2");
        
        refrescarListaArr();   
        f_list.addCommand(borrarCommand);
        f_list.addCommand(exitCommand);
        f_list.addCommand(createCommand);
        f_list.setCommandListener(this);
        d.setCurrent(f_list);     
    }

    public void startApp() {
        d.setCurrent(f_list);
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
        else if (co == createCommand) {
            f_main = new Form("Crear Contacto");
            nombre = new TextField("Nombre:", "", 20, TextField.ANY);
            returnCommand = new Command("Atras", Command.EXIT, 1);

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

            f_main.append(nombre);
            f_main.append(genero);
            f_main.append(correo);
            f_main.append(telefono);
            f_main.append(fechaNac);
            f_main.append(pasatiempos);
            f_main.append(perfil);
            f_main.addCommand(okCommand);
            f_main.addCommand(returnCommand);
            f_main.setCommandListener(this);
          
            try {
                refrescarListaArr();
            } catch (RecordStoreNotOpenException ex) {
                ex.printStackTrace();
            }         
            d.setCurrent(f_main);
        }
        else if (co == returnCommand) {
            d.setCurrent(f_list);
        }
        else if(co == borrarCommand){           
            gestor_registros.borrarRegistro(Integer.parseInt(borrar.getString()));
        }
        else if (co == okCommand){
            //preparando registro
            boolean seleccion[] = new boolean[pasatiempos.size()];
            pasatiempos.getSelectedFlags(seleccion);
            String pasatiempos_cadena = "";          
            for(int i = 0 ; i < seleccion.length; i ++){
                if(seleccion[i]){
                    pasatiempos_cadena += "\t"+pasatiempos.getString(i)+"\n";             
                }else{
                }
            }
            String registro = "Nombre:"+nombre.getString()+"\nGenero:"+genero.getString(genero.getSelectedIndex())
            +"\nCorreo:"+correo.getString()+"\nTelefono:"+telefono.getString()+"\nFechaNac:"+fechaNac.getDate().toString()+
            "\nPasatiempos:\n"+pasatiempos_cadena+"_"+perfil.getString(perfil.getSelectedIndex());
            //guardando el registro
            try{
                gestor_registros.abrir();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);
                dos.writeUTF(registro);
                dos.close();
                gestor_registros.adicionarRegistro(registro);
                System.out.println("guardando...");
                gestor_registros.cerrar();
            }catch(IOException ioe){
                System.out.println("error al guardar");
                ioe.printStackTrace();
            }
            d.setCurrent(f_list);
        }
        else{
            f_info = new Form("Notificacion");
            boolean seleccion[] = new boolean[pasatiempos.size()];
            pasatiempos.getSelectedFlags(seleccion);
            String pasatiempos_cadena = "";          
            for(int i = 0 ; i < seleccion.length; i ++){
                if(seleccion[i]){
                    pasatiempos_cadena += "\t"+pasatiempos.getString(i)+"\n";             
                }else{
                }
            }
            
            //mostrando en pantalla resultados
            StringItem si = new StringItem(null,"Nombre:"+nombre.getString()+"\nGenero:"+genero.getString(genero.getSelectedIndex())
            +"\nCorreo:"+correo.getString()+"\nTelefono:"+telefono.getString()+"\nFechaNac:"+fechaNac.getDate().toString()+
            "\nPasatiempos:\n"+pasatiempos_cadena);
                 
            //mostrando imagen seleccionada
            try {
                img = Image.createImage("/"+perfil.getString(perfil.getSelectedIndex())+".png");
            } catch (Exception e) {
                System.out.println("no existe la foto");
            }
            foto = new ImageItem("Java 2", img, ImageItem.LAYOUT_CENTER, "image");
                    
            //comando de retorno a adicion
            returnCommand = new Command("Volver",Command.ITEM,1);
            
            f_info.append(si);
            f_info.addCommand(returnCommand);
            f_info.setCommandListener(this);
            f_info.append(img);               
            d.setCurrent(f_info);
        }
    }
    
    public void refrescarListaArr() throws RecordStoreNotOpenException{

        borrar = new TextField("Borrar:", "", 4, TextField.NUMERIC);
        gestor_registros.abrir();
        String [] resultset = gestor_registros.listarRegistroArr();        
        gestor_registros.cerrar();
        
        sia = new StringItem[resultset.length];
        fotos = new ImageItem[resultset.length];
        
        try{
            f_list.deleteAll();
            for(int i = 0; i < resultset.length ; i++){          
                sia[i] = new StringItem(null,resultset[i]);

                int index = resultset[i].indexOf('_');
                String imgaux = resultset[i].substring(index+1);
                //mostrando imagen seleccionada
                try {
                    img = Image.createImage("/"+imgaux+".png");
                } catch (Exception e) {
                    System.out.println("no existe la foto");
                }
                fotos[i] = new ImageItem(null, img, ImageItem.LAYOUT_CENTER, null);

                f_list.append(sia[i]);
                f_list.append(fotos[i]);
            }
        }catch(Exception e){
            System.out.println("Sin elementos que borrar");
        }
                

        f_list.append(borrar);
    }
    
    
}
