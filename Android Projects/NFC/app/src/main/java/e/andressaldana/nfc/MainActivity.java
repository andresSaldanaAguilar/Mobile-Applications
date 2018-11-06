package e.andressaldana.nfc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {
    private  static  final  int DIALOG_WRITE_URL = 1;
    private  static  final  int DIALOG_NEW_TAG = 3;
    private  static  final  String ARG_MESSAGE = "message";
    private EditText myUrl;
    private Button myWriteUrlButton;
    private boolean writeUrl = false;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myUrl = findViewById(R.id.myUrl);
        myWriteUrlButton = findViewById(R.id.myWriteUrlButton);
        myWriteUrlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeUrl = true;
                ShowDialog(DIALOG_WRITE_URL);
            }
        });
    }

    private void ShowDialog(int action){
        switch (action){
            case DIALOG_WRITE_URL;
                builder.setTitle("Write URL to tag")
                .setMessage("Touch tag to start writing")
                .setCancelable(true)
                .setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        writeUrl = false;
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
                break;
            case DIALOG_NEW_TAG:
                builder.setTitle("Tag Detected")
                .setCancelable(true)
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        }
    }

    @Override
    protected  void onPreparedDialog(int id, Dialog dialog, Bundle args){
        switch (id){
            case DIALOG_NEW_TAG:

        }
    }

    private  static final int PENDING_INTENT_TECH_DISCOVERED = 1;
    private NfcAdapter mNfcAdapter;

    @Override
    public void onResume() {
        super.onResume();

        NfcManager nfcManager = (NfcManager) this.getSystemService(Context.NFC_SERVICE);
        mNfcAdapter = nfcManager.getDefaultAdapter();

        PendingIntent pi = createPendingResult(PENDING_INTENT_TECH_DISCOVERED,new Intent(),0);
        mNfcAdapter.enableForegroundDispatch(
            this,
            pi,
            new IntentFilter[]{new IntentFilter((NfcAdapter.ACTION_TECH_DISCOVERED))},
            new String [][]{
                new String[]{"android.nfc.tech.NdefFormatable"},
                new String[]{"android.nfc.tech.Ndef"}
            }
        );
    }

    @Override
    public void onPause(){
        super.onPause();
        mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case PENDING_INTENT_TECH_DISCOVERED:
                resolveIntent(data,true);
                break;
        }
    }

    private void resolveIntent(Intent data, boolean foregroundDispatch){
        String action = data.getAction();
        if(NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)){

            if(foregroundDispatch &&writeUrl){

                writeUrl = false;

                String urlStr = myUrl.getText().toString();
                byte[] urlBytes = urlStr.getBytes(Charset.forName("UTF-8"));
                byte[] urlPayload = new byte[urlBytes.length+1];
                urlPayload[0] = 0;
                System.arraycopy(urlBytes,0,urlPayload,1,urlBytes.length);
                NdefRecord urlRecord = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                                                      NdefRecord.RTD_URI,
                                                      new byte[0],
                                                      urlPayload);

                Tag tag = data.getParcelableExtra(NfcAdapter.EXTRA_TAG);

                NdefMessage msg = new NdefMessage(new NdefRecord[]{urlRecord});

                Ndef ndefTag = Ndef.get(tag);
                if(ndefTag != null){
                    try{
                        ndefTag.connect();
                        ndefTag.writeNdefMessage(msg);

                    }catch (Exception e){ }
                    finally {
                        try{
                            ndefTag.close();
                        }catch (Exception e){ }
                    }
                }
                else{
                    NdefFormatable ndefFormatableTag = NdefFormatable.get(tag);
                    if(ndefFormatableTag != null){
                        try{
                            ndefFormatableTag.connect();
                            ndefFormatableTag.format(msg);
                        }catch (Exception e){}
                        finally {
                            try{
                                ndefFormatableTag.close();
                            }catch (Exception e){}
                        }
                    }
                }
                }else{
                    StringBuilder tagInfo = new StringBuilder();
                    byte[] uid = tag.getId();
                    tagInfo.append("UUID: ")
                            .append(StringUtils.convertByteArrayToHexString(uid))
                            .append("\n\n");
                    Parcelable[] ndefRaw = data.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                    NdefMessage[] ndefMsgs = null;
                    if(ndefRaw != null){
                        ndefMsgs = new NdefMessage[ndefRaw.length];
                        for(int i = 0; i < ndefMsgs.length; i++){
                            ndefMsgs[i] = (NdefMessage)ndefRaw[i];
                        }
                    }
                }
                //dismissDialog();
            }
    }

}
