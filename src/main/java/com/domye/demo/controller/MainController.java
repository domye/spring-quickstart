package com.domye.demo.controller;

import com.domye.demo.common.BaseResponse;
import com.domye.demo.common.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(tags = "健康检查")
public class MainController {

    /**
     * 健康检查
     */
    @GetMapping("/health")
    @ApiOperation(value = "健康检查接口", notes = "用于检查服务是否正常运行")
    public BaseResponse<String> health() {
        return ResultUtils.success("ok", "服务运行正常");
    }
}