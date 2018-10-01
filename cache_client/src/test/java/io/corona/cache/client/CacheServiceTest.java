package io.corona.cache.client;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.corona.cache.client.CacheStrategyClient;
import io.corona.cache.client.Cacheable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-consumer.xml","classpath:applicationContext-service.xml"})
public class CacheServiceTest extends AbstractJUnit4SpringContextTests {
    
    @Autowired
    private CacheStrategyClient cacheStringStrategyClient;
    
    
    
    @Test
    public void cacheServiceTest(){
        
        List<TestUser> users= cacheStringStrategyClient.getData("test", "zfwx_api", new Cacheable<List<TestUser>>(){

            @Override
            public List<TestUser>  getDataFromDB() {
                return TestData.getData();
            }
            
        });
        
        System.out.println(users);
      
        
 	}

}
