package com.zzt.store.mapper;

import com.zzt.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest ：表示当前的类是一个测试类，测试类有不会协调项目打包发送的特点
@SpringBootTest
//@RunWith(SpringRunner.class) 启动这个单元测试类（如果不写这个单元测试是不能运行的），需要传递一个参数，必须是SpringRunner的实列类型
@RunWith(SpringRunner.class)
public class UserMapperTests {
    /**
     * 原因 idea他是有检查的功能，接口是不能直接创建bean的，（动态代理技术解决）
     * 解决：降低权限
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 单元测试法：就可以单独独立运行，不用启动整个项目，可以做单元测试，提升了代码的测试效率
     * 1.必须被test注解所修饰
     * 2.返回值必须是void
     * 3.方法的参数列表不指定任何类型
     * 4.方法的访问修饰符必须是public
     */
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("zzt3");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }
    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("zzt");
        System.out.println(user);
    }
}
