package io.corona.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * 缓存数据序列化与反序列化工具
 * 
 * @author roadsign
 *
 */
public class CacheUtil {
  
    
    private static Log log = LogFactory.getLog(CacheUtil.class);
    
    /**
     * 对象转换成json字符串
     * 
     * @param object 
     * @return
     */
    public static String toJSONString(Object value){
        
        return JSON.toJSONString(value);
        
    }
    
    /**
     * json字符串转换成java对象，对象类型是T
     * 
     * @param value
     * @param type  
     * @return
     */
    public static <T> T fromJSONString(String value, TypeReference<T> type){
        
        return JSON.parseObject(value,type);
    }
    
    /**
     * 对象序列化成byte数组
     * 
     * @param value
     * @return byte[]
     */
    public static byte[] serialize(Object value) {
        
        if (value == null)  return null;
        
        byte[] result = null;
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(value);
            result = bos.toByteArray();
        
        } catch (IOException e) {
        
            log.debug("serialize object error", e);
        
        } finally {
            
            try{ 
                if(null != oos) oos.close();
            }catch(Exception e){
                log.debug("close oos error", e);
            }
           
        }
        return result;
    }
    
    /**
     * 反序列化成对象
     * 
     * @param value
     * @return
     */
    public static Object deserialize(byte[] value) {
        
        if (value == null)  return null;
        
        Object result=null;
        ByteArrayInputStream bais = new ByteArrayInputStream(value);
        ObjectInputStream ois = null;
        
        try {
            
            bais = new ByteArrayInputStream(value);
            ois = new ObjectInputStream(bais);
            result = ois.readObject();

        } catch (Exception e) {
            
            log.debug("deserialize object error", e);
            
        } finally {
            
            try{ 
                if(null != ois) ois.close();
            }catch(Exception e){
                log.debug("close ois error", e);
            }
        
        }
        
        return result;
        
    }


    public static void main(String[] args) {
        
        
        
    }

}
