package com.rex.testdatabaseflow.database;

/**
 * Created by rex.yau on 11/24/2015.
 */
public interface RequestItem {
    String getTableName();

    String[] getProjection();

    String getSelection();

    String[] getSelectionArgs();

    String getGroupBy();

    String getHaving();

    String getOrderBy();
}
