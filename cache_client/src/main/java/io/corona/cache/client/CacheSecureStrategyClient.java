package io.corona.cache.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.corona.cache.CacheService;
import io.corona.cache.CacheUtil;
/**
 * 安全缓存使用策略，防止雪崩击穿。
 * 
 * @author roadsign
 *
 */
@Component("cacheSecureStrategyClient")
public class CacheSecureStrategyClient implements CacheStrategyClient{
    
    private final Log log = LogFactory.getLog(CacheSecureStrategyClient.class);
 
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
        
        if(cacheService.lock(name)){
            
            result = c.getDataFromDB();
            
            if(null!=result) {
                cacheService.setCache(name, CacheUtil.serialize(result),moduleName);
            }
 
            cacheService.unlock(name);
            
            log.debug(name + ": from db");
        
        }else{
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return result;
            }
            result = getData(name, moduleName, c);
        }

        return result;

        
    }

}
