package vn.uits.bai2;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Copyright © 2017 BAP CO., LTD
 * Created by PHUQUY on 3/15/18.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Realm (just once per application)
        Realm.init(this);

        // The RealmConfiguration is created using the builder pattern.
        // The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .schemaVersion(1)
                .build();

        // Use the config
        Realm.setDefaultConfiguration(config);
    }
}
