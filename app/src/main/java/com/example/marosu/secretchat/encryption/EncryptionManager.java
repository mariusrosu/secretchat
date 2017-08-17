package com.example.marosu.secretchat.encryption;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
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
    private static final String ENCRIPTION_KEYS = "encryption_keys";
    private static final String IDENTITY_KEY_PAIR = "identity_key_pair";
    private static final String REGISTRATION_ID = "registration_id";
    private static final String PRE_KEYS = "pre_keys";
    private static final String SIGNED_PRE_KEY = "signed_pre_key";

    private IdentityKeyPair identityKeyPair;
    private int registrationId;
    private List<PreKeyRecord> preKeys;
    private SignedPreKeyRecord signedPreKey;

    private Context context;

    public EncryptionManager(Context context) {
        this.context = context;
    }

    public void initialize() {
        generateKeys();
        storeKeys();
    }

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
        final SharedPreferences.Editor editor = context.getSharedPreferences(ENCRIPTION_KEYS, 0).edit();

        final String identityKeyPairString = Base64.encodeToString(identityKeyPair.serialize(), Base64.DEFAULT);
        editor.putString(IDENTITY_KEY_PAIR, identityKeyPairString);

        final String signedPreKeyString = Base64.encodeToString(signedPreKey.serialize(), Base64.DEFAULT);
        editor.putString(SIGNED_PRE_KEY, signedPreKeyString);

        editor.putInt(REGISTRATION_ID, registrationId);
        Log.d(TAG, "Keys stored...");
    }

    public static IdentityKeyPair getIdentityKeyPair(Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(ENCRIPTION_KEYS, 0);
        final String identityKeyPairString = sharedPreferences.getString(IDENTITY_KEY_PAIR, null);
        return null;
    }
}
