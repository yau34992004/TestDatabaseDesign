package com.rex.testdatabaseflow;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.rex.boilerplate.task.CommonTask;
import com.rex.boilerplate.task.Execute;
import com.rex.boilerplate.task.ThreadModule;
import com.rex.testdatabaseflow.database.ObjectStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ThreadModule.getInstance().dispatch(new CommonTask(this) {
            @Execute(thread = Execute.SAFE_THREAD)
            @Override
            public void run() {

                ObjectStorage<UserItem> userItemObjectStorage = new SQLStorage(getContext());
                UserItem userItem = new UserItem();
                userItem.mId = "001";
                userItem.mTitle = "title";
                userItem.mContent = "content";

                final boolean isSuccess = userItemObjectStorage.add(userItem);
                Log.d(TAG, "checkAddResult->" + isSuccess);
            }
        });

        ThreadModule.getInstance().dispatch(new CommonTask(this) {
            @Execute(thread = Execute.SAFE_THREAD)
            @Override
            public void run() {
                ObjectStorage<UserItem> userItemObjectStorage = new SQLStorage(getContext());
                final ArrayList<UserItem> arrayList = userItemObjectStorage.query(new BasicRequestItem());
                Log.d(TAG, "checkResult->" + arrayList.size());
            }
        });


    }

}
