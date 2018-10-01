package io.corona.cache.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import io.corona.cache.CacheRuntimeException;
import io.corona.cache.CacheService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service("cacheService")
public class CacheServiceRedisImp implements CacheService{
    
    private final Log log = LogFactory.getLog(this.getClass());
    
    private final String defaultRegionName ="xplatform";  
    private final int defaultValidTime = 180; //缺省有效时间3分钟
    
    private Map<String,JedisPool> regionPoolMapping;
    
 

    public void setRegionPoolMapping(Map<String, JedisPool> modulePoolMapping) {
        this.regionPoolMapping = modulePoolMapping;
    }

    public void setCache(String name, String value) throws CacheRuntimeException {
        
        this.setCache(name, value, defaultValidTime);
        
    }

    public void setCache(String name, byte[] value) throws CacheRuntimeException {
        
        this.setCache(name, value, defaultValidTime);
        
    }

    
    public void setCache(String name, String value, int validTime) throws CacheRuntimeException {
                                                    
        this.setCache(name, value, validTime, defaultRegionName);
        
    }

    public void setCache(String name, byte[] value, int validTime) throws CacheRuntimeException {
        
        this.setCache(name, value, validTime, defaultRegionName);
        
    }

    
    
    public void setCache(String name, String value, String regionName) throws CacheRuntimeException {
                                                    
        this.setCache(name, value, defaultValidTime, regionName);
        
    }

    public void setCache(String name, byte[] value, String regionName) throws CacheRuntimeException {
        
        this.setCache(name, value, defaultValidTime, regionName);
        
    }

    
    public void setCache(String name, String value, int validTime, String regionName)
                                                                        throws CacheRuntimeException {
       
        log.debug("set value: " + value);
        
        JedisPool pool = null;
        Jedis jedis = null;

        try{
        
            pool = regionPoolMapping.get(regionName);
            if(null==pool)
                pool = regionPoolMapping.get(defaultRegionName);
            
            jedis = pool.getResource();
            jedis.setex(name, validTime, value);
            
        }catch(Exception e){
            
            throw new CacheRuntimeException("set data cache error!",e);
        
        }finally{
            
            if(null!=jedis)
                jedis.close(); 
            
        }
            
    }
    
    public void setCache(String name, byte[] value, int validTime, String regionName)
                                                                throws CacheRuntimeException {

        
        log.debug("set value: " + value);
        
        JedisPool pool = null;
        Jedis jedis = null;

        try{
        
            pool = regionPoolMapping.get(regionName);
            if(null==pool)
                pool = regionPoolMapping.get(defaultRegionName);
            
            jedis = pool.getResource();
            jedis.setex(name.getBytes("UTF-8"), validTime, value);
            
        }catch(Exception e){
            
            throw new CacheRuntimeException("set data cache error!",e);
        
        }finally{
            
            if(null!=jedis)
                jedis.close();   
            
        }



    }

    public String getCache(String name) throws CacheRuntimeException{
        return this.getCache(name, defaultRegionName);
    }

    public byte[] getBytesCache(String name) throws CacheRuntimeException{
        return this.getBytesCache(name, defaultRegionName);
    }

    
    public String getCache(String name, String regionName) throws CacheRuntimeException{
        
        log.debug("regionPoolMapping: " + regionPoolMapping);
        
        String result = null;
        JedisPool pool = null;
        Jedis jedis = null;
        
        try{
        
            pool = regionPoolMapping.get(regionName);
            if(null==pool)
                pool = regionPoolMapping.get(defaultRegionName);
            
            jedis = pool.getResource();
            result = jedis.get(name);
           
        
        }catch(Exception e){
            
            throw new CacheRuntimeException("read data cache error!",e);
        
        }finally{
            
            if(null!=jedis)
                jedis.close();    
            
        }
        
        log.debug("result: " + result);
        
        return result;
        
    }

    public byte[] getBytesCache(String name, String regionName) throws CacheRuntimeException{
        
        log.debug("regionPoolMapping: " + regionPoolMapping);
        
        byte[] result = null;
        JedisPool pool = null;
        Jedis jedis = null;
        
        try{
        
            pool = regionPoolMapping.get(regionName);
            if(null==pool)
                pool = regionPoolMapping.get(defaultRegionName);
            
            jedis = pool.getResource();
            result = jedis.get(name.getBytes("UTF-8"));
           
        
        }catch(Exception e){
            
            throw new CacheRuntimeException("read data cache error!",e);
        
        }finally{
            
            if(null!=jedis)
                jedis.close();      
            
        }
        
        log.debug("result: " + result);
        
        return result;
        
    }

    public boolean lock(String name) {
        
        String result = this.getCache("lock:" +name);
        if(null != result){
            return false;
        }else{
            this.setCache("lock:" +name, "",3);  // 最大生存3s时间
            return true;
        }
    }

    public boolean unlock(String name) {
        
        log.debug("unlock: " + name);
        
        boolean result = false; 
        
        JedisPool pool = null;
        Jedis jedis = null;

        try{
        
            pool = regionPoolMapping.get(defaultRegionName);
            
            jedis = pool.getResource();
            jedis.del("lock:" + name);
            
            result = true;
            
        }catch(Exception e){
            
            throw new CacheRuntimeException("unlock error!",e);
        
        }finally{
            
            if(null!=jedis)
                jedis.close();   
            
        }

        return result;
        
    }

    public void del(String name) throws CacheRuntimeException{
        
        del(name, "defaultModuleName");
        
    }

    public void del(String name, String regionName)throws CacheRuntimeException{
        
        JedisPool pool = null;
        Jedis jedis = null;
        
        try{
            
            pool = regionPoolMapping.get(regionName);
            
            jedis = pool.getResource();
            jedis.del(name);
            
        }catch(Exception e){
            
            throw new CacheRuntimeException("unlock error!",e);
        
        }finally{
            
            if(null!=jedis)
                jedis.close();      
            
        }

        
    }

    
    
}
