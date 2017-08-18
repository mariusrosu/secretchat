package com.example.marosu.secretchat.notifications;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Marius-Andrei Rosu on 8/18/2017.
 */
public class EncryptedFirebaseInstanceService extends FirebaseInstanceIdService {
    private static final String TAG = EncryptedFirebaseInstanceService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        /* If you want to send messages to this application instance or
         manage this apps subscriptions on the server side, send the
         Instance ID token to your app server. */
        // sendRegistrationToServer(refreshedToken);
    }
}
