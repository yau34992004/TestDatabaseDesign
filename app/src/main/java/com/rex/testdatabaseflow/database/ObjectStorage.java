package com.rex.testdatabaseflow.database;

import java.util.ArrayList;

/**
 * Created by rex.yau on 11/24/2015.
 */
public interface ObjectStorage<T> {

    boolean add(T item);

    ArrayList<T> query(RequestItem requestItem);
}
