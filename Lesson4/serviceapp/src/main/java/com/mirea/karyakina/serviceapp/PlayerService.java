package com.mirea.karyakina.serviceapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class PlayerService extends Service {
    private MediaPlayer mp3Player;
    public static final String CHANNEL_ID =	"ForegroundServiceChannel";

    public PlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        mp3Player.start();
        mp3Player.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void	onCompletion(MediaPlayer mp )	{
                stopForeground(true);
            }
        });
        return super.onStartCommand(intent,	flags, startId);
    }

    public void	onCreate()	{
        super.onCreate();

        NotificationCompat.Builder builder	= new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentText("The song 'MAYDAY' is playing ")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Artist: TheFatRat "))
                .setContentTitle("MAYDAY");
        int	importance	= NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel	= new NotificationChannel(CHANNEL_ID,"KaryakinaAO	Notification",
                importance);
        channel.setDescription("Karyakina_Music_Channel");
        NotificationManagerCompat notificationManager =	NotificationManagerCompat.from(this);
        notificationManager.createNotificationChannel(channel);
        startForeground(1, builder.build());
        mp3Player = MediaPlayer.create(this, R.raw.thefatrat);
        mp3Player.setLooping(false);
    }

    public void onDestroy()	{
        stopForeground(true);
        mp3Player.stop();
    }
}