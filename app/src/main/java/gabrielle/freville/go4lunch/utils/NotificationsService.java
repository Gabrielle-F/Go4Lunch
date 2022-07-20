package gabrielle.freville.go4lunch.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import gabrielle.freville.go4lunch.R;
import gabrielle.freville.go4lunch.ui.MainActivity;

public class NotificationsService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        if (message.getNotification() != null) {
            RemoteMessage.Notification notification = message.getNotification();
            //ADD SEND VISUAL NOTIFICATION//
        }
    }

    public void sendVisualNotification(RemoteMessage.Notification notification) {

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity
                (this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setContentTitle(notification.getTitle())
                        .setContentText(notification.getBody()).setAutoCancel(true)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        //ADD SUPPORT VERSION WITH OPENCLASSROOMS COURSE//
    }
}
