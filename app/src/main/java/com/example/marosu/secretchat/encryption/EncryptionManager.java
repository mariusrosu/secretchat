package com.example.marosu.secretchat.encryption;

import android.util.Log;

import org.whispersystems.libsignal.IdentityKeyPair;
import org.whispersystems.libsignal.InvalidKeyException;
import org.whispersystems.libsignal.state.PreKeyRecord;
import org.whispersystems.libsignal.state.SignedPreKeyRecord;
import org.whispersystems.libsignal.util.KeyHelper;

import java.util.List;

/**
 * Created by Marius-Andrei Rosu on 8/14/2017.
 */
public class EncryptionManager {
    private static final String TAG = EncryptionManager.class.getSimpleName();

    private IdentityKeyPair identityKeyPair;
    private int registrationId;
    private List<PreKeyRecord> preKeys;
    private SignedPreKeyRecord signedPreKey;

    public void generateKeys() {
        Log.d(TAG, "Generating keys...");
        try {
            identityKeyPair = KeyHelper.generateIdentityKeyPair();
            registrationId = KeyHelper.generateRegistrationId(false);
            preKeys = KeyHelper.generatePreKeys(0, 100);
            signedPreKey = KeyHelper.generateSignedPreKey(identityKeyPair, 5);
        } catch (InvalidKeyException e) {
            Log.d(TAG, "Exception thrown while generating keys: " + e.getStackTrace());
        }
    }

    public void storeKeys() {

    }
}
