package com.zzt.store.service;


import com.zzt.store.entity.User;

//用户模块业务层接口
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户注册的数据对象
     *
     */
    void reg(User user);

    /**
     *
     * @param username 用户名
     * @param password 密码
     * @return  当前匹配的用户数据，如果没有则返回null
     */
    User login(String username,String password);
}
