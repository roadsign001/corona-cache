package io.corona.cache.client;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.corona.cache.CacheService;
import io.corona.cache.client.CacheStrategyClient;
import io.corona.cache.client.Cacheable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-consumer.xml","classpath:applicationContext-service.xml"})
public class CacheServiceTest extends AbstractJUnit4SpringContextTests {
    
    @Autowired
    private CacheStrategyClient cacheStringStrategyClient;
    
    @Autowired
    private CacheStrategyClient cacheSecureStrategyClient;
    
    @Autowired
    private CacheStrategyClient cacheByteStrategyClient;
    
    @Autowired
    private CacheService  cacheService;
    
    @Test
    public void cacheServiceTest(){
      
        cacheService.del("test","webcache");
        
        
        List<TestUser> users1 = cacheStringStrategyClient.getData("test", "webcache", new Cacheable<List<TestUser>>(){

            @Override
            public List<TestUser>  getDataFromDB() {
                return TestData.getData();
            }
            
        });
        
        List<TestUser> users2 = cacheStringStrategyClient.getData("test", "webcache", new Cacheable<List<TestUser>>(){

            @Override
            public List<TestUser>  getDataFromDB() {
                return TestData.getData();
            }
            
        });
      
        cacheService.del("test","webcache");
        
        
        
        users1 = cacheSecureStrategyClient.getData("test", "webcache", new Cacheable<List<TestUser>>(){

            @Override
            public List<TestUser>  getDataFromDB() {
                return TestData.getData();
            }
            
        });
        
        users2 = cacheSecureStrategyClient.getData("test", "webcache", new Cacheable<List<TestUser>>(){

            @Override
            public List<TestUser>  getDataFromDB() {
                return TestData.getData();
            }
            
        });
        
        cacheService.del("test","webcache");
        
        
        users1 = cacheByteStrategyClient.getData("test", "webcache", new Cacheable<List<TestUser>>(){

            @Override
            public List<TestUser>  getDataFromDB() {
                return TestData.getData();
            }
            
        });
        
        users2 = cacheByteStrategyClient.getData("test", "webcache", new Cacheable<List<TestUser>>(){

            @Override
            public List<TestUser>  getDataFromDB() {
                return TestData.getData();
            }
            
        });
        
        
 	}

}
