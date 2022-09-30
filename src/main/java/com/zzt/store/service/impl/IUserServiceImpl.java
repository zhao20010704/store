package com.zzt.store.service.impl;

import com.zzt.store.entity.User;
import com.zzt.store.mapper.UserMapper;
import com.zzt.store.service.IUserService;
import com.zzt.store.service.ex.InsertException;
import com.zzt.store.service.ex.PasswordNotMatchException;
import com.zzt.store.service.ex.UserNotFoundException;
import com.zzt.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

//用户模块业务层的实现类
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    /**
     *
     * @param user 用户注册的数据对象
     */
    @Override
    public void reg(User user) {
        //通过user参数来获取传递过来的username
        String username = user.getUsername();
//        调用findByUsername()，查看用户是否被注册
        User result = userMapper.findByUsername(username);
        //判断结果集是否不为null则抛出用户名被占用的异常
        if (result != null) {
            //抛出异常
            throw new UsernameDuplicatedException("用户名被占用");
        }

        //密码加密处理实现：md5算法的形式
        //（串+password+串）----md5算法进行加密,连续加载三次
        //盐值+password+盐值--盐值就是随机生成的一个随机串
        String oldPassword = user.getPassword();
//         获取盐值(随机生成一个盐值)，生成一个随机串
        String salt = UUID.randomUUID().toString().toUpperCase();//随机生成字符串
//        获取盐值记录
        user.setSalt(salt);
        //将密码和盐值作为一个整体进行加密处理
        String MD5password = getMD5password(oldPassword,salt);
        //将密码加密后重新补全设计到user对象中
        user.setPassword(MD5password);


//        补全数据； is_delete设置为0
        user.setIsDelete(0);
//        补全数据： 4个日志字段信息自动补全
        Date date = new Date();
        user.setCreatedUser(user.getUsername());
        user.setCreatedTime(date);
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(date);

        //执行注册业务功能的实现（rows==1）
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在用户注册中出现了异常的未知错误");
        }
    }
    @Override
    public User login(String username, String password) {
        //根据用户名查询，用户是否在数据库内存在，如果不存在则抛出异常
        User result = userMapper.findByUsername(username);
        if (result==null){
            throw new UserNotFoundException("用户数据不存在");
        }
        //检查密码是否匹配
        //1.先获取数据库内加密后的密码
//        result.getPassword()
        //2.将用户传递过来的密码进行比较
        //2.1 获取数据库内的盐值，并进行加密
        String salt = result.getSalt();
        String newMd5Password = getMD5password(password,salt);
        //3.将双方进行比较，查看是否相同
        if (!result.getPassword().equals(newMd5Password)){
            throw new PasswordNotMatchException("用户密码错误");
        }
        //判断用户is_delete是否进行过逻辑注销
        if (result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //调用mapper层findByUsername查询用户数据,确定展示信息，将需要的信息独立摘出，提高系统性能
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }
//    定义一个md5算法的加密
    private String getMD5password(String password,String salt){
        //        MD5加密算法的调用,进行三次加密
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }


}
