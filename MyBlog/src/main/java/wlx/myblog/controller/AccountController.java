package wlx.myblog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wlx.myblog.common.dto.LoginDto;
import wlx.myblog.common.lang.Result;
import wlx.myblog.entity.User;
import wlx.myblog.mapper.UserMapper;
import wlx.myblog.service.UserService;
import wlx.myblog.util.JwtUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestController
public class AccountController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtils jwtUtils;

    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");

        if(!user.getPassword().equals(loginDto.getPassword())){
            return Result.fail("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }
    @CrossOrigin
    @PostMapping("/register")
    public Result register(@Validated @RequestBody User user) {
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
        if (res!=null){
            return Result.fail("用户已存在");

        }
        if (user.getPassword()==null){
            user.setPassword("123456");
        }
        user.setStatus(0);
        user.setCreated(LocalDateTime.now());
        userMapper.insert(user);
        return Result.succ("注册成功");

    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }

}
