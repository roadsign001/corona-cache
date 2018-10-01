package io.corona.cache.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.corona.cache.CacheService;
import io.corona.cache.CacheUtil;
/**
 * 字节数组方式进行缓存，效率高。
 * 
 * @author roadsign
 *
 */
@Component("cacheByteStrategyClient")
public class CacheByteStrategyClient implements CacheStrategyClient{
    
    private final Log log = LogFactory.getLog(CacheByteStrategyClient.class);
 
    @Autowired
    private CacheService cacheService;
    
   
    public  <T> T getData(String name, String moduleName, Cacheable<T> c){
        
        T result = null;
        
        byte[] value = cacheService.getBytesCache(name, moduleName);
        
        if(null != value){
            
            result = (T)CacheUtil.deserialize(value);
            
            log.debug(name + ": from cache");
            
            return result; 
            
        }
        
            
        result = c.getDataFromDB();
            
        if(null!=result) {
            cacheService.setCache(name, CacheUtil.serialize(result),moduleName);
        }
            
        log.debug(name + ": from db");
        
        return result;

        
    }

}
