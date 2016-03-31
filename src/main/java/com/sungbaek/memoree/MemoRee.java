package com.sungbaek.memoree;

import android.app.Application;

/**
 * Created by joon8_000 on 3/28/2016.
 */
public class MemoRee extends Application {
    DatabaseConnector db;

    public DatabaseConnector getDatabase(){
        return db;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        db = new DatabaseConnector(getBaseContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
