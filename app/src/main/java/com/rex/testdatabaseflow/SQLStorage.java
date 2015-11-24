package com.rex.testdatabaseflow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rex.testdatabaseflow.database.DatabaseHelper;
import com.rex.testdatabaseflow.database.ObjectStorage;
import com.rex.testdatabaseflow.database.RequestItem;

import java.util.ArrayList;

/**
 * Created by rex.yau on 11/24/2015.
 */
public class SQLStorage implements ObjectStorage<UserItem> {

    private Context mContext;
    private DatabaseHelper mDatabaseHelper;

    public SQLStorage(Context context) {
        mContext = context;
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public SQLStorage(Context context, DatabaseHelper databaseHelper) {
        mContext = context;
        mDatabaseHelper = databaseHelper;
    }

    @Override
    public boolean add(UserItem item) {
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseColumns.COLUMN_NAME_ENTRY_ID, item.mId);
        values.put(DatabaseColumns.COLUMN_NAME_TITLE, item.mTitle);
        values.put(DatabaseColumns.COLUMN_NAME_CONTENT, item.mContent);

        long newRowId;
        newRowId = db.insert(
                DatabaseColumns.TABLE_NAME,
                DatabaseColumns.COLUMN_NAME_NULLABLE,
                values);

        mDatabaseHelper.close();
        return newRowId >= 0;
    }

    @Override
    public ArrayList<UserItem> query(RequestItem requestItem) {
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        ArrayList<UserItem> arrayList = new ArrayList<>();

        Cursor cursor = db.query(
                requestItem.getTableName(),  // The table to query
                requestItem.getProjection(),                               // The columns to return
                requestItem.getSelection(),                                // The columns for the WHERE clause
                requestItem.getSelectionArgs(),                            // The values for the WHERE clause
                requestItem.getGroupBy(),                                     // don't group the rows
                requestItem.getHaving(),                                     // don't filter by row groups
                requestItem.getOrderBy()                                 // The sort order
        );
        while (cursor.moveToNext()) {
            UserItem userItem = new UserItem();
            userItem.mId = cursor.getString(cursor.getColumnIndex(DatabaseColumns.COLUMN_NAME_ENTRY_ID));
            userItem.mTitle = cursor.getString(cursor.getColumnIndex(DatabaseColumns.COLUMN_NAME_TITLE));
            userItem.mContent = cursor.getString(cursor.getColumnIndex(DatabaseColumns.COLUMN_NAME_CONTENT));
            arrayList.add(userItem);
        }
        mDatabaseHelper.close();
        return arrayList;
    }
}
