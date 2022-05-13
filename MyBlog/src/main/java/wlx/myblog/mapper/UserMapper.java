package wlx.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.shiro.authc.Account;
import org.springframework.web.bind.annotation.ResponseBody;
import wlx.myblog.entity.User;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from m_user where username=#{username}")
    int findByUsername(@Param("username")String username);
}
