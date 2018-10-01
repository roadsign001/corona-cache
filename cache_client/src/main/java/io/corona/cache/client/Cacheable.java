package io.corona.cache.client;

public interface Cacheable<T> {
    
    public T getDataFromDB();

}
