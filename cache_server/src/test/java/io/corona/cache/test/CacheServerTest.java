package io.corona.cache.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CacheServerTest {

	public static void main(String[] args) {

	    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-cache-provider.xml","spring/spring-redis.xml");
	    
	    try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
  
	}
 
}
