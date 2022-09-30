package com.zzt.store.controller;

import com.zzt.store.service.ex.*;
import com.zzt.store.util.JsonResult;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

/** 控制器类的基类 */

public class BaseController {
    /**
     *操作成功的状态码
     */
    public static final int OK =200;

    /** @ExceptionHandler用于统一处理方法抛出的异常 */
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMassage("用户名已经被占用");
        }else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMassage("用户数据不存在");
        }else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMassage("用户密码错误");
        }else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMassage("注册时产生未知的异常");
        }
        return result;
    }


}
