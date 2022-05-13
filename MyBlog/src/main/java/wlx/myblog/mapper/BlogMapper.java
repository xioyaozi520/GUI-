package wlx.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import wlx.myblog.entity.Blog;
import wlx.myblog.entity.User;
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

}
