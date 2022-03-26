package com.hfad.wellbeingtech;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

public class HRV extends AppCompatActivity {

    public static String id = "test_channel_01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrv);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewhrv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        channelfunction();

        HrvCardData [] mhrvcardData = new HrvCardData[] {
                new HrvCardData("Average BPM",R.drawable.heartbeat),
                new HrvCardData("HRV",R.drawable.pulse),
                new HrvCardData("Quit",R.drawable.remove)
        };

        HeartRateCardAdapter heartRateCardAdapter = new HeartRateCardAdapter(mhrvcardData, HRV.this);
        recyclerView.setAdapter(heartRateCardAdapter);


    }

    private void channelfunction() {
        /*
            Set up the channel for the notifications to be sent to to the user phone.
        * */
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel mChannel = new NotificationChannel(id,
                    getString(R.string.channel_name),  //name of the channel
                    NotificationManager.IMPORTANCE_DEFAULT);   //importance level

            // Configure the notification channel.
            mChannel.setDescription(getString(R.string.channel_description));
            mChannel.enableLights(true);
            //Sets the notification light color for notifications posted to this channel, if the device supports this feature.
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setShowBadge(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            nm.createNotificationChannel(mChannel);

        }
    }

}