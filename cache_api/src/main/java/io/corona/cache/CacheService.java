package io.corona.cache;


/**
 * 数据缓存服务接口。
 * 缓存中的数据，到期自动清零。
 * 根据应用场景，设置好缓存数据有效时间。缺省是3分钟。
 * 
 * @author roadsign
 * 
 */
public interface CacheService {
    
    /**
     * 缓存数据方法，从数据库中读取的数据存入缓存中
     * @param name 缓存对象标识名
     * @param value 被缓存的数据，如是复杂类型数值，可先使用CacheUtil工具序列化成字符串。
     * @throws CacheRuntimeException 如果存储出错
     * 
     */
    public void setCache(String name,String value) throws CacheRuntimeException;
  
    
    /**
     * 缓存数据方法，从数据库中读取的数据存入缓存中
     * @param name 缓存对象标识名
     * @param value 被缓存的数据，如是复杂类型数值，可先使用CacheUtil工具序列化成字节数组。
     * @throws CacheRuntimeException 如果存储出错
     * 
     */
    public void setCache(String name,byte[] value) throws CacheRuntimeException;
    
    /**
     * 缓存数据方法，从数据库中读取的数据存入缓存中
     * @param name  缓存对象标识名
     * @param value 被缓存的数据，如是复杂类型数值，可先使用CacheUtil工具序列化成字符串。
     * @param validTime 缓存有效时间，秒为计算单位，缺省是3分钟。
     * @throws CacheRuntimeException 如果存储出错
     * @see #setCache(String name,String value)
     * 
     */
    public void setCache(String name,String value,int validTime) throws CacheRuntimeException;
                                                                               
    /**
     * 缓存数据方法，从数据库中读取的数据存入缓存中
     * @param name 缓存对象标识名
     * @param value 被缓存的数据，如是复杂类型数值，可先使用CacheUtil工具序列化成字节数组。
     * @param validTime 缓存有效时间，秒为计算单位，缺省是3分钟。
     * @throws CacheRuntimeException 如果存储出错
     * @see #setCache(String name,byte[] value)
     * 
     */
    public void setCache(String name,byte[] value,int validTime) throws CacheRuntimeException;
 
    
    /**
     * 缓存数据方法，从数据库中读取的数据存入缓存中,此方法最常用。
     * @param name  缓存对象标识名
     * @param value 被缓存的数据，如是复杂类型数值，可先使用CacheUtil工具序列化成字符串。
     * @param regionName 缓存区名称，可使用应用工程名、应用模块名等来命名。缓存区对应缓存服务器逻辑数据库。
     * @throws CacheRuntimeException 如果存储出错
     * @see #setCache(String name,String value)
     * 
     */
    public void setCache(String name,String value,String regionName) throws CacheRuntimeException; 
                                                                                

    /**
     * 缓存数据方法，从数据库中读取的数据存入缓存中,此方法最常用。
     * @param name 缓存对象标识名
     * @param value 被缓存的数据，如是复杂类型数值，可先使用CacheUtil工具序列化成字节数组。
     * @param regionName 缓存区名称，可使用应用工程名、应用模块名等来命名。缓存区对应缓存服务器逻辑数据库。
     * @throws CacheRuntimeException 如果存储出错
     * @see #setCache(String name,byte[] value)
     * 
     */
    public void setCache(String name,byte[] value,String regionName) throws CacheRuntimeException; 


    /**
     * 缓存数据方法，从数据库中读取的数据存入缓存中
     * @param name  缓存对象标识名
     * @param value 被缓存的数据，如是复杂类型数值，可先使用CacheUtil工具序列化成字符串。
     * @param validTime 缓存有效时间，秒为计算单位，缺省是3分钟。
     * @param regionName 缓存区名称，可使用应用工程名、应用模块名等来命名。缓存区对应缓存服务器逻辑数据库。
     * @throws CacheRuntimeException 如果存储出错
     * @see #setCache(String name,String value)
     * @see #setCache(String name,String value,int validTime)
     * @see #setCache(String name,String value,String regionName)
     * 
     */
    public void setCache(String name,String value,int validTime, String regionName) throws CacheRuntimeException;
 

    /**
     * 缓存数据方法，从数据库中读取的数据存入缓存中,此方法最常用。
     * @param name 缓存对象标识名
     * @param value 被缓存的数据，如是复杂类型数值，可先使用CacheUtil工具序列化成字节数组。
     * @param validTime 缓存有效时间，秒为计算单位，缺省是3分钟。
     * @param regionName 缓存区名称，可使用应用工程名、应用模块名等来命名。缓存区对应缓存服务器逻辑数据库。
     * @throws CacheRuntimeException 如果存储出错
     * @see #setCache(String name,byte[] value)
     * @see #setCache(String name,byte[] value,int validTime)
     * @see #setCache(String name,byte[] value,String regionName)
     * 
     */
    public void setCache(String name,byte[] value,int validTime, String regionName) throws CacheRuntimeException;
 

    
    /**
     * 数据缓存读取方法，读取缓存数据。读取不到返回null。
     * @param name 缓存对象标识名
     * @return String 应用层面使用CacheUtil做类型转换。
     * @see #setCache(String name,String value)     
     */
    public String getCache(String name) throws CacheRuntimeException;
   
    /**
     * 数据缓存读取方法，读取缓存数据。读取不到返回null。
     * @param name 缓存对象标识名
     * @return byte数组，应用层面使用CacheUtil做类型转换。
     * @see #setCache(String name,String value)     
     */
    public byte[] getBytesCache(String name) throws CacheRuntimeException;

    
    
    /**
     * 数据缓存读取方法，读取缓存数据。读取不到返回null。
     * @param name 缓存对象标识名
     * @param regionName setCache时使用的是什么名字，这里也使用什么名字。
     * @return String 应用层面使用CacheUtil做类型转换。
     * @see #getCache(String name)    
     */
    public String getCache(String name, String regionName) throws CacheRuntimeException;
    
    /**
     * 数据缓存读取方法，读取缓存数据。读取不到返回null。
     * @param name 缓存对象标识名
     * @param regionName setCache时使用的是什么名字，这里也使用什么名字。
     * @return byte数组，应用层面使用CacheUtil做类型转换。
     * @see #getCache(String name)    
     */
    public byte[] getBytesCache(String name, String regionName) throws CacheRuntimeException;
    
    /**
     * 申请锁，处理防止缓存击穿模式时用到
     * @param name 锁名字
     * @return true,表示申请成功
     */
    public boolean lock(String name);
    
    /**
     * 解锁，处理防止缓存击穿模式时用到
     * @param name
     * @return true,表示解锁成功
     * @see #lock(String name)
     */
    public boolean unlock(String name); 
    
    /**
     * 删除指定的缓存数据。更新数据库数据，需要实时更新缓存数据时用到。
     * 
     * @param name 缓存对象标识名
     * @throws CacheRuntimeException
     * @see #del(String name,String moduleName)
     */
    public void del(String name) throws CacheRuntimeException;
  
    /**
     * 
     * @param name 缓存对象标识名
     * @param regionName setCache时使用的是什么名字，这里也使用什么名字。
     * @throws CacheRuntimeException
     */
    public void del(String name,String regionName) throws CacheRuntimeException;
    
}
