package com.audiovideocalling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{

    EditText editTextRoom;
    Button buttonJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextRoom = findViewById(R.id.edit_room);
        buttonJoin = findViewById(R.id.btn_join);

        buttonJoin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String room = editTextRoom.getText().toString();

                if (TextUtils.isEmpty(room))
                {
                    Toast.makeText(MainActivity.this, "Please enter a room id", Toast.LENGTH_SHORT).show();
                    editTextRoom.requestFocus();
                    return;
                }
                else
                {
                    try
                    {
                        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                                .setServerURL(new URL("https://meet.jit.si"))
                                .setRoom(room)
                                .setAudioOnly(false)
                                .build();

                        JitsiMeetActivity.launch(MainActivity.this, options);
                    }
                    catch (MalformedURLException e)
                    {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}