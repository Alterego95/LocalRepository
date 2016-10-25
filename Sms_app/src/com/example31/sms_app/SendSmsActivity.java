package com.example31.sms_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SendSmsActivity extends Activity {

    Button sendSmsBtn;
    EditText toPhoneNumber;
    EditText smsMessageET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        sendSmsBtn = (Button) findViewById(R.id.btnSendSMS);
        toPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNo);
        smsMessageET = (EditText) findViewById(R.id.editTextSMS);

        sendSmsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSms();
            }
        });
    }

    private void sendSms() {
        String toPhone = toPhoneNumber.getText().toString();
        String smsMessage = smsMessageET.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(toPhone, null, smsMessage, null, null);

            Toast.makeText(this, "SMS sent", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void goToInbox(View v) {
        Intent intent = new Intent(SendSmsActivity.this, ReceiveSmsActivity.class);
        startActivity(intent);
    }
}
