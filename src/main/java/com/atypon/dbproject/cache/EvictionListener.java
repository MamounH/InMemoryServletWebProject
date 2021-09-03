package com.atypon.dbproject.cache;

public interface EvictionListener<K, V> {
    void onEvict(K key, V value);
}
