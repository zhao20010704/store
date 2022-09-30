package com.zzt.store.controller;


import com.zzt.store.entity.User;
import com.zzt.store.service.IUserService;
import com.zzt.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zzt.store.controller.BaseController.OK;

/** 处理用户相关请求的控制器类 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    /**
     * 第一种，接收数据方式：请求处理方法的参数列表设置为pojo类型来接收前端数据，
     * Springboot会将前端的url地址中的参数名和pojo类的属性名进行比较，如果
     * 这俩个名称项目，则将值注入到pojo类对应的属性上
     * @param user
     * @return
     */
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        /**
         *调用业务层执行注册
         */
        userService.reg(user);
//返回
        return new JsonResult<Void>(OK);
    }

    /**
     *第二种接收数据方式：请求处理方法的参数列表设置为非pojo类型，
     *springboot会直接将请求的参数名和方法参数名直接进行比较
     * 如果名称相同则自动完成值的依赖注入
     *
     * @param username
     * @param password
     * @return json
     */
    @RequestMapping("login")
    public JsonResult<User> login(String username,String password){
        User data = userService.login(username,password);
        return new JsonResult<User>(OK,data);
    }
}
