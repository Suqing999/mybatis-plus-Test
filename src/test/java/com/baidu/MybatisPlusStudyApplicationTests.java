package com.baidu;

import com.baidu.mapper.UserMapper;
import com.baidu.pojo.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusStudyApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        //参数是wapper 条件构造器 这里先不用 null
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    void contextLoads2() {

        User user = new User();
        user.setAge(10);
        user.setEmail("116423222@qq.com");
        user.setName("哈哈");
        int flag = userMapper.insert(user);

        System.out.println(flag);

        System.out.println(user);
    }


    @Test
    void contextLoads3() {
        User user = new User();
        user.setId(1458045430804787201L);
        user.setAge(12433);
        user.setName("suki");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    void contextLoads4() {
        Page<User> page = new Page<>(1,2);
        IPage<User> userIPage = userMapper.selectPage(page, null);

        List<User> records = page.getRecords();

        for (User record : records) {
            System.out.println(record);
        }

        System.out.println(page.getTotal());

    }


    //测试成功乐观锁
    @Test
    public void testLocker_Success(){
        //查用户信息
        User user = userMapper.selectById(1L);
        //修改用户信息
        user.setName("kkkk");
        user.setEmail("2321321@test.com");
        //修改
        userMapper.updateById(user);
    }

    //测试失败乐观锁
    @Test
    public void testLocker_Success2(){
        //线程 1
        User user = userMapper.selectById(1L);
        user.setName("kkkk111111");
        user.setEmail("2321321@test.com");

        // 模拟另一个线程执行插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("kkkk333333");
        user2.setEmail("2321321@test.com");
        userMapper.updateById(user2);

        userMapper.updateById(user); //线程1还没有来得及更新（如果没有乐观锁，就会覆盖插队线程的值）
    }




}
