package com.zhang.controller;

import com.zhang.pojo.User;
import com.zhang.service.UserService;
import com.zhang.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin    //允许其他源访问我们的Controller，浏览器就不会拦截
@RestController
@RequestMapping("ssm/schedule")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

        /**
     * 分页查询
     *
     * @param pageSize    每页多少条数据
     * @param currentPage 第几页，例如第一页放三条数据http://localhost:8080/user/3/1
     * @return
     */
    @GetMapping("/{pageSize}/{currentPage}")
    public R page(@PathVariable int pageSize,
                  @PathVariable int currentPage) {
        R r = userService.page(pageSize, currentPage);
        log.info("查询的数据为 ：{}", r);
        return r;
    }

    @DeleteMapping("/{id}")
    public R remove(@PathVariable Integer id) {
        R r = userService.remove(id);
        return r;
    }

    @PostMapping
    public R save(@Validated @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("参数为空，不能保存");
        }
        R r = userService.save(user);
        return r;
    }

    @PutMapping
    public R update(@Validated @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("参数为空，不能修改");
        }
        R r = userService.update(user);
        return r;
    }

}
