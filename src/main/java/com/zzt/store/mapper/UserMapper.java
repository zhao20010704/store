package com.zzt.store.mapper;

import com.zzt.store.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//用户模块持久层
//@Mapper

public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 受影响的行数(增，删，改都受到的行数作为返回值，可以根据返回值来判断是否执行成功)
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户的数据
     * @param username
     * @return 如果找到对应的用户则返回这个用户的数据，如果没有找到则返回null
     */
    User findByUsername(String username);

}
