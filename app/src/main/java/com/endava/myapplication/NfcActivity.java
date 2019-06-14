package com.endava.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* in this Activity if the app reads a nfc message with the string role1 opens up MainActivity*/

public class NfcActivity extends Activity
        implements NfcAdapter.OnNdefPushCompleteCallback,
        NfcAdapter.CreateNdefMessageCallback {

    private NfcAdapter mNfcAdapter;

    @Override
    public void onNdefPushComplete(NfcEvent event) {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        //This will be called when another NFC capable device is detected.
        //When creating an NdefMessage we need to provide an NdefRecord[]
        return new NdefMessage(new NdefRecord[]{createRoleMessage()});
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public NdefRecord createRoleMessage() {
        return NdefRecord.createMime("text/plain", "role1".getBytes());
    }

    private void handleNfcIntent(Intent NfcIntent) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(NfcIntent.getAction())) {
            Parcelable[] receivedArray =
                    NfcIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if (receivedArray != null) {
                NdefMessage receivedMessage = (NdefMessage) receivedArray[0];
                NdefRecord[] attachedRecords = receivedMessage.getRecords();

                for (NdefRecord record : attachedRecords) {
                    String string = new String(record.getPayload());
                    //Make sure we don't pass along our AAR (Android Applicatoin Record)
                    if (string.equals(getPackageName())) {
                        continue;
                    }
                    Toast.makeText(this, string, Toast.LENGTH_LONG).show();
                    if(string.equals("role1")) {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                Toast.makeText(this, "Received role messages", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Received Blank Parcel", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onNewIntent(Intent intent) {
        handleNfcIntent(intent);
    }


    @Override
    public void onResume() {
        super.onResume();
        handleNfcIntent(getIntent());
    }



    //Save our Array Lists of Messages for if the user navigates away
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    //Load our Array Lists of Messages for when the user navigates back
    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);


        //Check if NFC is available on device
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter != null) {
            //This will refer back to createNdefMessage for what it will send
            mNfcAdapter.setNdefPushMessageCallback(this, this);

            //This will be called if the message is sent successfully
            mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
        } else {
            Toast.makeText(this, "NFC not available on this device",
                    Toast.LENGTH_SHORT).show();
        }

        if (getIntent().getAction().equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            handleNfcIntent(getIntent());
        }
    }
}