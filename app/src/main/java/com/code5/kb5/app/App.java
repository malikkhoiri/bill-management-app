package com.code5.kb5.app;

import android.app.Application;

import com.code5.kb5.utils.ConnectivityReceiver;
import com.google.firebase.FirebaseApp;

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        instance = this;
    }

    public static synchronized App getInstance(){
        return instance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
