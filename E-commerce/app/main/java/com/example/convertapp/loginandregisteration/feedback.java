package com.example.convertapp.loginandregisteration;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.convertapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class feedback extends AppCompatActivity {
public  static Notification notification=null;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        String CHANNEL_ID = "channel";
        EditText name=findViewById(R.id.name);
        EditText message=findViewById(R.id.message);

        RatingBar ratingBar=findViewById(R.id.simpleRatingBar);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        Button send=findViewById(R.id.submit);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (name.getText().toString().isEmpty()) {
                   name.setError("Enter name");
                   name.requestFocus();
               }
            else   if(message.getText().toString().isEmpty()){
                   message.setError("Enter message");
                   message.requestFocus();

               }

else if(!name.getText().toString().isEmpty()&&!message.getText().toString().isEmpty()){
                   float rate = ratingBar.getRating();
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                       NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "channelName", NotificationManager.IMPORTANCE_HIGH);
                       notificationChannel.setLightColor(Color.BLUE);
                       notificationChannel.setVibrationPattern(new long[]{500, 500, 500, 500});
                       notificationChannel.enableVibration(true);
                       notificationManager.createNotificationChannel(notificationChannel);
                   }


                   notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                           .setSmallIcon(R.drawable.dresses)
                           .setContentTitle("Name :" + name.getText().toString())
                           .setContentText("Message :" + message.getText().toString()+'\n' + " Rating is  " + rate + " from 5")
                           .build();

                   Toast.makeText(feedback.this, "Feedback send", Toast.LENGTH_SHORT).show();
                   name.setText("");

                   name.setHint("Name ");
                   message.setText("");
                   ratingBar.setRating(2.5f);
               }
            }
        });


    }
}