package com.rex.testdatabaseflow;

import android.content.Context;
import android.net.Uri;

import com.rex.testdatabaseflow.UserItem;
import com.rex.testdatabaseflow.database.ObjectStorage;
import com.rex.testdatabaseflow.database.RequestItem;

import java.util.ArrayList;

/**
 * Created by rex.yau on 11/24/2015.
 */
public class CPStorage implements ObjectStorage<UserItem> {

    private Context mContext;

    public CPStorage(Context context) {
        mContext = context;
    }

    @Override
    public boolean add(UserItem item) {
        Uri uri = Uri.parse("content://myTesintDatabase/1");

        mContext.getContentResolver().query(uri, null, null, null, null);
        return false;
    }

    @Override
    public ArrayList<UserItem> query(RequestItem requestItem) {
        return null;
    }
}
