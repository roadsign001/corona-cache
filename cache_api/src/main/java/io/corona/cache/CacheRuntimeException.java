package io.corona.cache;

public class CacheRuntimeException extends RuntimeException {
    
   
    private static final long serialVersionUID = 6735739538609818449L;

    public CacheRuntimeException() {
        super();
    }
    
    public CacheRuntimeException(String s) {
        super(s);
    }

    public CacheRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CacheRuntimeException(Throwable cause) {
        super(cause);
    }
}
