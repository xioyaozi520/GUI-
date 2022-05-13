package wlx.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wlx.myblog.entity.Blog;
import wlx.myblog.mapper.BlogMapper;
import wlx.myblog.service.BlogService;


@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
