package wlx.myblog;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wlx.myblog.entity.Blog;
import wlx.myblog.entity.User;
import wlx.myblog.mapper.BlogMapper;
import wlx.myblog.mapper.UserMapper;
import wlx.myblog.service.BlogService;
import wlx.myblog.service.UserService;

import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class MyBlogApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;
    @Autowired
    BlogMapper blogMapper;
    @Test
    @Transactional
    public void saveTest() {
        User user=new User();
        user.setUsername("Admin");
        user.setPassword("123456");
        user.setStatus(0);
        user.setCreated(LocalDateTime.now());
        user.setAvatar("https://cn.bing.com/images/search?q=%e5%a4%b4%e5%83%8f&id=B449EBD6567D7E5881175450802C6EC8313F7D09&FORM=IQFRBA");
        int user1=userMapper.insert(user);
        System.out.println(user1);
    }
    @Transactional
    @Test
    public void getBlogList(){
       List<Blog> blogList=blogService.list();
       int count=blogMapper.selectCount(Wrappers.query());
       System.out.println(blogList);
       System.out.println(count);
    }
    @Test
    public void updateBlog(){
        Blog blog=blogService.getById(32);
        blog.setStatus(1);
        blog.setContent("大时空的距离未开发急啊离开房间啊李开复氨基酸的拉萨看得见啊");
        blog.setTitle("大苏打大幅度随风倒十分");
        System.out.println(blogService.saveOrUpdate(blog));

    }

}
