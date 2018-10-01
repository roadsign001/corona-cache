package io.corona.cache.client;

import java.io.Serializable;

public class TestUser implements Serializable{
    
    
    private static final long serialVersionUID = 1L;
    
    private String username;
    private int age;
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TestUser(String username,int age){
        
       this.username = username;
       this.age = age;
    }
    
    @Override
    public String toString(){
        
        return "username: " + username + "; age: "  + age;
        
    }

}
