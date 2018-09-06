/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaRegistros;

import javax.microedition.midlet.*;
import javax.microedition.rms.RecordStore;

/**
 * @author Andres
 */
public class ReadWriteRMS extends MIDlet {

    private RecordStore rs = null;
    static final String REC_STORE = "ZonaJavaZone";

    public void startApp() {
        openRecStore();
        //writeRecord("Core J2ME Technology");
        //writeRecord("J2ME Wireless Toolkit");
        readRecords();
        closeRecStore();
        deleteRecStore();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }

    public void openRecStore() {
        try {
            rs = RecordStore.openRecordStore(REC_STORE, true);
        } catch (Exception e) {
        }
    }

    public void closeRecStore() {
        try {
            rs.closeRecordStore();
        } catch (Exception e) {
        }
    }

    public void deleteRecStore() {
        if (RecordStore.listRecordStores() != null) {
            try {
                RecordStore.deleteRecordStore(REC_STORE);
            } catch (Exception e) {
            }
        }
    }

    public void writeRecord(String str) {
        byte[] rec = str.getBytes();
        try {
            rs.addRecord(rec, 0, rec.length);
        } catch (Exception e) {
        }
    }

    public void readRecords() {
        try {
            byte[] recData = new byte[5];
            int len;

            for (int i = 1; i <= rs.getNumRecords(); i++) {
                if (rs.getRecordSize(i) > recData.length) {
                    recData = new byte[rs.getRecordSize(i)];
                }
                len = rs.getRecord(i, recData, 0);
                System.out.println("------------------------------");
                System.out.println("Record " + i + " : " + new String(recData, 0, len));
                System.out.println("------------------------------");
            }
        } catch (Exception e) {
        }
    }
}
