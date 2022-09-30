package com.zzt.store.service.ex;

/** 密码验证失败的异常 */
public class PasswordNotMatchException extends ServiceException{
    public PasswordNotMatchException() {
        super();
    }

    public PasswordNotMatchException(String s) {
        super(s);
    }

    public PasswordNotMatchException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PasswordNotMatchException(Throwable throwable) {
        super(throwable);
    }

    protected PasswordNotMatchException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
