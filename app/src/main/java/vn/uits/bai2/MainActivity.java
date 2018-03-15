package vn.uits.bai2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;
import vn.uits.bai2.model.Student;
import vn.uits.bai2.sqlite.DBController;

/**
 * Copyright © 2017 BAP CO., LTD
 * Created by PHUQUY on 3/6/18.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mBtnRegister;
    private SharedPreferences mSharedPreference;
    private SharedPreferences.Editor mEditor;
    private EditText mEdtEmail;
    private EditText mEditPassword;

    private Realm mRealm;
    private DBController mDataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // instance
        mBtnRegister = findViewById(R.id.mBtnRegister);
        mEdtEmail = findViewById(R.id.etEmail);
        mEditPassword = findViewById(R.id.etPass);

        mRealm = Realm.getDefaultInstance();
        mDataBase = new DBController(this);

        mSharedPreference = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreference.edit();

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEdtEmail.getText().toString().trim().equals("")
                        && !mEditPassword.getText().toString().trim().equals("")) {
                    // Realm

                    mRealm.beginTransaction();
                    Student student = new Student();
                    student.setName(mEdtEmail.getText().toString().trim());
                    student.setNumberPhone(mEditPassword.getText().toString().trim());
                    mRealm.copyToRealm(student);
                    mRealm.commitTransaction();

                    // SQLite
                   /* mDataBase.addContact(new Contact(mEdtEmail.getText().toString().trim(),
                            mEditPassword.getText().toString().trim()));*/

                    mEditPassword.setText("");
                    mEdtEmail.setText("");

                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập giá trị ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Thread name = Thread.currentThread();
        Log.d(TAG, "onCreate: " + name.getName());
    }

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String string = bundle.getString("a");
            Log.d(TAG, "handleMessage: " + string);
        }
    };

    private final Runnable mMessageSender = new Runnable() {
        public void run() {
            Message msg = mHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("a", getCurrentTime());
            msg.setData(bundle);
            mHandler.sendMessage(msg);
        }
    };

    private String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US);
        return dateFormat.format(new Date());
    }

    /**
     * clear EditText
     */
    private void clear() {
        mEdtEmail.setText("");
        mEditPassword.setText("");
    }

    /**
     * login view button
     *
     * @param view
     */
    public void onLogin(View view) {
        Thread thread = new Thread(mMessageSender);
        thread.start();
    }

    public void onGetInformation(View view) {
        // Realm
        mRealm.beginTransaction();
        RealmResults<Student> realmResults = mRealm.where(Student.class).findAll();
        for (Student student : realmResults) {
            Log.d(TAG, "onGetInformation: " + student.getName());
        }
        mRealm.commitTransaction();

        // SQLite
        /*for (Contact contact : mDataBase.getAllContacts()) {
            Log.d(TAG, "onGetInformation: " + contact.getName());
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mMessageSender);
    }

    /**
     * show Messager
     *
     * @param context
     * @param email
     * @param password
     */
    private void showMessage(Context context, String email, String password) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Thông tin của bạn là ")
                .setMessage("Email :  " + email + "\n" + "Password :  " + password)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
