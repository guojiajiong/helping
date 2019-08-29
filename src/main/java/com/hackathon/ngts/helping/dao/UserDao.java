package com.hackathon.ngts.helping.dao;

import com.hackathon.ngts.helping.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by wuzhenjie on 2019-08-29.
 */
@Repository
@Mapper
public interface UserDao {

    @Insert(value = {"insert into t_user(weixin_id,`name`,avatar) values ",
            "(#{weixin_id},#{name},#{avatar} )"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Select("select id from t_user where weixin_id = #{openid}")
    Integer countByOpenId(@Param("openid") String openid);


}
