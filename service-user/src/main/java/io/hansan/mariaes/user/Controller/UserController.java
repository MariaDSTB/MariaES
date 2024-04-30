package io.hansan.mariaes.user.Controller;

import io.hansan.mariaes.user.Service.UserService;
import io.hansan.mariaes.user.data.vo.UserVo;
import io.hansan.mariaes.user.database.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：何汉叁
 * @date ：2024/4/25 18:16
 * @description：TODO
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = "用户管理")
public class UserController {

    private final UserService userService;
    @ApiOperation("获取用户信息")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserVo getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @ApiOperation("注册用户")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserEntity userEntity){
        userService.addUser(userEntity);
    }

    @ApiOperation("获取所有用户信息")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserVo> getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size) {
        List<UserVo> users = userService.getAllUsers(page, size);
    return userService.getAllUsers(page, size);
    }
}