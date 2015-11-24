package com.rex.testdatabaseflow;

import com.rex.testdatabaseflow.database.RequestItem;

/**
 * Created by rex.yau on 11/24/2015.
 */
public class BasicRequestItem  implements RequestItem {
    @Override
    public String getTableName() {
        return DatabaseColumns.TABLE_NAME;
    }

    @Override
    public String[] getProjection() {
        return new String[] {
                DatabaseColumns.COLUMN_NAME_ENTRY_ID,
                DatabaseColumns.COLUMN_NAME_TITLE,
                DatabaseColumns.COLUMN_NAME_CONTENT,
        };
    }

    @Override
    public String getSelection() {
        return null;
    }

    @Override
    public String[] getSelectionArgs() {
        return null;
    }

    @Override
    public String getGroupBy() {
        return null;
    }

    @Override
    public String getHaving() {
        return null;
    }

    @Override
    public String getOrderBy() {
        return DatabaseColumns.COLUMN_NAME_CONTENT + " DESC";
    }
}
