package io.corona.cache.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.TypeReference;

import io.corona.cache.CacheService;
import io.corona.cache.CacheUtil;

/**
 * Json字符串方式进行缓存。
 * 
 * @author roadsign
 *
 */
@Component("cacheStringStrategyClient")
public class CacheStringStrategyClient implements CacheStrategyClient{
    
    private final Log log = LogFactory.getLog(CacheStringStrategyClient.class);
 
    @Autowired
    private CacheService cacheService;
    
   
    public  <T> T getData(String name, String regionName, Cacheable<T> c){
        
        T result = null;
        
        String value = cacheService.getCache(name, regionName);
        
        if(null != value){
            
            result = (T)CacheUtil.fromJSONString(value, new TypeReference<T>(){});
            log.debug(name + ": from cache");
            
            return result; 
            
        }
        
            
        result = c.getDataFromDB();
            
        if(null!=result) {
            cacheService.setCache(name, CacheUtil.toJSONString(result),regionName);
        }

        log.debug(name + ": from db");
            
        
        return result;

        
    }

}
