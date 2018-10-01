package io.corona.cache.client;

/**
 * 客户端使用cache_server策略方法接口
 * 
 * @author roadsign
 *
 */
public interface CacheStrategyClient {
    

    public  <T> T getData(String name, String moduleName, Cacheable<T> c);

}
