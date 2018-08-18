/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agendaRegistros;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.rms.*;

/**
 * @author Andres
 */
public class RMSOps{
    RecordStore rs;
    public RMSOps(){
    }
    public boolean abrir(String nombreZona){
        try{
            rs = RecordStore.openRecordStore(nombreZona, true);
            return true;
        }catch(RecordStoreException e){
            e.toString();
            return false;
        }
    }
    public boolean adicionarRegistro(ByteArrayOutputStream baos){
        byte[] mensaje;
        mensaje = baos.toByteArray();
        try{
            rs.addRecord(mensaje, 0, mensaje.length);
            return true;
        }catch(RecordStoreException e){
            e.toString();
            return false;
        }
    }
    public boolean adicionarRegistro(String valor){
        byte[] mensaje;
        mensaje = valor.getBytes();
        try{
            rs.addRecord(mensaje, 0, mensaje.length);
            return true;
        }catch(RecordStoreException e){
            e.toString();
            return false;
        }
    }
    public String listarRegistro(){
        byte[] reg = new byte[1000];
        int tam;
        String buffer = "";
        try{
            for(int i = 1; i<= rs.getNumRecords(); i++){
                tam = rs.getRecordSize(i);
                reg = rs.getRecord(i);
                buffer += "\n" + i + new String(reg,0,tam);
            } 
        }catch(RecordStoreException e){
            System.out.println("Error de Lectura");
        }
        return buffer;
    }
    public String[] listarRegistro(int r){
        byte[] reg = new byte[100];
        ByteArrayInputStream bais;
        DataInputStream dis;
        String[] datos = new String[3];
        try{
            reg= rs.getRecord(r);
            bais = new ByteArrayInputStream(reg);
            dis = new DataInputStream(bais);
            datos[0] = dis.readUTF();
            datos[1] = dis.readUTF();
            datos[2] = dis.readInt() + "";
        }catch(RecordStoreException e){
            System.out.println("Error de lectura");
        }catch(IOException e){
            e.printStackTrace();
        }
        return datos;
    }
    public boolean cerrar(){
        try{
            rs.closeRecordStore();
            return true;
        }catch(RecordStoreException e){
            e.toString();
            return false;
        }
    }
}
