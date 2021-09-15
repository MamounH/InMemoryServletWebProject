package com.atypon.dbproject.database;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessSynch<V>{

    private HashMap<V, ReentrantReadWriteLock> locks = new HashMap<>();
    private HashMap<V,Integer> threadQueue = new HashMap<>();
    private final Object lock = new Object();



    public void getWriteLock(final V value) throws InterruptedException {
        ReentrantReadWriteLock lock;
        synchronized (this.lock) {
            lock = obtainLock(value);
            addToThreadQueue(value);
        }
        lock.writeLock().lockInterruptibly();
    }

    public void unlockWriteLock(final V value) {
        synchronized (lock) {
            ReentrantReadWriteLock lock = obtainLock(value);
            removeFromThreadQueue(value);
            lock.writeLock().unlock();
        }
    }


    public void getReadLock(final V value) throws InterruptedException {
        ReentrantReadWriteLock lock;
        synchronized (this.lock) {
            lock = obtainLock(value);
            addToThreadQueue(value);
        }
        lock.readLock().lockInterruptibly();
    }

    public void unlockReadLock(final V value) {
        synchronized (lock) {
            ReentrantReadWriteLock lock = obtainLock(value);
            removeFromThreadQueue(value);
            lock.readLock().unlock();
        }
    }


    private void addToThreadQueue(final V value) {
        Integer threadNum = threadQueue.get(value);
        if (threadNum == null) {
            threadNum = 0;
        }
        threadNum = threadNum + 1;
        threadQueue.put(value, threadNum);
    }

    private void removeFromThreadQueue(final V value) {
        Integer threadNum = threadQueue.get(value);
        if (threadNum == null) {
            throw new IllegalStateException(
                    "Exception, Trying to decrement thread count that does even not exist.");
        }

        threadNum = threadNum - 1;
        threadQueue.put(value, threadNum);

        if (threadNum == 0) {
            locks.remove(value);
            threadQueue.remove(value);
        }

    }

    private ReentrantReadWriteLock obtainLock(final V value) {
        ReentrantReadWriteLock lock = locks.get(value);
        if (lock == null) {
            lock = new ReentrantReadWriteLock(true);
            locks.put(value, lock);
        }
        return lock;
    }




}
