package com.atypon.dbproject.database;

import com.atypon.dbproject.dao.LibraryDao;

import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Table<K,V>  {

    AccessSynch<V> lock= new AccessSynch<>();

    private final TreeMap<K , V> table;
    LibraryDao<K,V> tableDao;

    private final Logger logger = Logger.getLogger("Table Logger");

    private static final Integer CAPACITY = 40;

    public Table(LibraryDao<K,V> tableDao) {
        this.tableDao=tableDao;
        table = tableDao.loadRecords();
    }

    public TreeMap<K, V> getAll() {
        return table;
    }

    public V get(K key){
            try {
                lock.getReadLock(table.get(key));
                return table.get(key);
            } catch (InterruptedException e) {
                logError(e);
            } finally {
                lock.unlockReadLock(table.get(key));
            }
        return (V) new Object();
    }

    public void add(V value, K key){
        try {
            lock.getWriteLock(value);
            addNewRecord(value, key);
        } catch (InterruptedException e) {
            logError(e);
        } finally {
            lock.unlockWriteLock(value);
        }
    }

    private void addNewRecord(V value, K key) {
        if (!isStorageFull() && table.get(key)== null) {
            addToTable(value, key);
        } else
            logger.log(Level.INFO,"In-Memory Storage is full or Record already exists.");
    }

    private void addToTable(V value, K key) {
        if (tableDao.recordIsAdded(value)){
            table.put(key, value);
            logger.log(Level.INFO,"Successfully Added new Record to the In-Memory");}
        else {
            logger.log(Level.INFO,"Record couldn't be added... ");
        }
    }

    public void update(V value, K key) {
        try {
            lock.getWriteLock(value);
            updateRecord(value, key);
        } catch (Exception e) {
            logError(e);
        } finally {
            lock.unlockWriteLock(value);
        }
    }

    private void updateRecord(V value, K key) {

        if (tableDao.recordIsUpdated(value,key)){
            logger.log(Level.INFO,"Client Updated an existing record..");
            table.put(key, value);}
        else {
            logger.log(Level.INFO,"record couldn't be updated..");
        }
    }

    public void remove(K key) {
        try {
            lock.getWriteLock(table.get(key));
            removeRecord(key);
        } catch (InterruptedException e) {
            logError(e);
        }

    }

    private void removeRecord(K value) {

        if (tableDao.recordIsDeleted(value)){
            table.remove(value);
        } else {
            logger.log(Level.INFO , "Couldn't delete record....");
        }
    }

    private boolean isStorageFull() { return table.size() == CAPACITY; }

    public boolean recordExists(K key){
        return table.containsKey(key);
    }

    public int getCacheSize(){return (int) (0.3 * table.size());}


    private void logError (Exception e){
        logger.log(Level.SEVERE,e.getMessage());
    }




}
