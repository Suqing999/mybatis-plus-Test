package com.baidu.mapper;

import com.baidu.pojo.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

//对应的Mapper 上面实现基本的接口 basemapper
@Component
public interface UserMapper extends BaseMapper<User> {

}
