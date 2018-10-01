package io.corona.cache.client;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    
    public static List<TestUser> getData(){
        
        List<TestUser> users =  new ArrayList<TestUser>();
        TestUser user = new TestUser("赵传",30);
        
        users.add(user);
        user = new TestUser("张学友",35);
        users.add(user);
        user = new TestUser("童安格",40);
        users.add(user);
     
        return users;
    }
    
}
