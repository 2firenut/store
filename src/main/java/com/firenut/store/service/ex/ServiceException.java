package com.firenut.store.service.ex;

//业务层异常的基类
public class ServiceException extends RuntimeException {
    public ServiceException() {
        super();
    }

    /**
     * 抛出异常信息
     * @param message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * 抛出异常的信息及异常对象
     * @param message
     * @param cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
