package com.domye.demo.controller;

import com.domye.demo.common.BaseResponse;
import com.domye.demo.common.ResultUtils;
import com.domye.demo.exception.BusinessException;
import com.domye.demo.exception.ErrorCode;
import com.domye.demo.model.dto.UserCreateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    /**
     * 创建用户
     */
    @PostMapping("/create")
    @ApiOperation(value = "创建用户", notes = "创建新用户")
    public BaseResponse<String> createUser(@Valid @RequestBody UserCreateRequest request) {
        // 模拟业务逻辑
        if ("admin".equals(request.getUsername())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "用户名已存在");
        }
        
        // 模拟创建用户
        return ResultUtils.success("用户创建成功", "用户创建成功");
    }

    /**
     * 获取用户
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取用户", notes = "根据ID获取用户信息")
    public BaseResponse<UserInfo> getUser(@PathVariable("id") Long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户ID不合法");
        }
        
        // 模拟获取用户
        if (id == 99999) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
        }
        
        // 模拟返回用户数据
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setUsername("testuser");
        userInfo.setEmail("test@example.com");
        return ResultUtils.success(userInfo);
    }

    /**
     * 测试异常
     */
    @GetMapping("/test-error")
    @ApiOperation(value = "测试异常", notes = "用于测试异常处理")
    public BaseResponse<String> testError() {
        throw new BusinessException(ErrorCode.SYSTEM_ERROR, "这是一个测试异常");
    }
    
    /**
     * 用户信息响应类
     */
    public static class UserInfo {
        private Long id;
        private String username;
        private String email;
        
        // Getters and Setters
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
        public String getUsername() {
            return username;
        }
        
        public void setUsername(String username) {
            this.username = username;
        }
        
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
    }
}