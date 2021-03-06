package com.example31.sms_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class ReceiveSmsActivity extends Activity implements AdapterView.OnItemClickListener{

    private static ReceiveSmsActivity inst;
    ArrayList<String> smsMessageList = new ArrayList<String>();
    ListView smsListView;
    ArrayAdapter<String> arrayAdapter;

    public static ReceiveSmsActivity instance() {
        return inst;
    }

    @Override
    protected void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_sms);

        smsListView = (ListView) findViewById(R.id.SMSList);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, smsMessageList);
        smsListView.setAdapter(arrayAdapter);
        smsListView.setOnItemClickListener(this);

        refreshSmsInbox();
    }

    public void refreshSmsInbox() {
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, "date DESC");
        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        
        
        
        int timeColumn = smsInboxCursor.getColumnIndex("date");
        
        
       
       /* SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        String dateText = format.format(date);*/

        if(indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
        arrayAdapter.clear();
        do {
        	Date date = new Date(Long.parseLong(smsInboxCursor.getString(timeColumn)));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
            String dateText = format.format(date);
             String str = smsInboxCursor.getString(indexAddress) + " at "
                     + "\n" + smsInboxCursor.getString(indexBody) + dateText + "\n";
             arrayAdapter.add(str);
        } while(smsInboxCursor.moveToNext());
    }
public void updateList(final String smsMessage) {
        arrayAdapter.insert(smsMessage, 0);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        try {
            String[] smsMessages = smsMessageList.get(position).split("\n");
            String address = smsMessages[0];
            String smsMessage = "";

            for(int i = 1; i < smsMessages.length; i++) {
                smsMessage += smsMessages[i];
            }

            String smsMessageStr = address + "\n";
            smsMessageStr += smsMessage;
            Toast.makeText(this, smsMessageStr, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToCompose(View v) {
        Intent intent = new Intent(ReceiveSmsActivity.this, SendSmsActivity.class);
        startActivity(intent);
    }
}
