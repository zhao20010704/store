package com.zzt.store.service.ex;


//在数据插入过程中所产生的异常
public class InsertException extends ServiceException{
    public InsertException() {
        super();
    }

    public InsertException(String s) {
        super(s);
    }

    public InsertException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InsertException(Throwable throwable) {
        super(throwable);
    }

    protected InsertException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
