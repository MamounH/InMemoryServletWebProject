package com.atypon.dbproject.dao;

import java.util.TreeMap;

public interface LibraryDao<K,V> {

    boolean recordIsAdded(V value);
    boolean recordIsDeleted(K key);
    boolean recordIsUpdated(V value, K key);
    TreeMap<K,V> loadRecords();
    boolean RecordsAreDeleted(K key);
}
