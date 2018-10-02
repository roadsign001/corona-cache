# corona-cache
common cache service for webapp

通用数据缓存服务

后端web应用响应前端的数据结果集，可放入缓存中，减少后续访问对数据库的压力。

缓存支持字符串模式、字节数组模式、防雪崩击穿三种模式。

底层支撑的内存数据库：Redis

服务接口封装成Dubbo服务提供
