package com.vivi.renew.backend.test.controller;

import com.vivi.renew.backend.accessory.CommonResult;
import com.vivi.renew.backend.test.mapper.TestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    // 使用日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TestMapper testMapper;

    @RequestMapping(value="/users/{userId}", method= RequestMethod.GET)
    public CommonResult getUserById(@PathVariable int userId) {
        return new CommonResult(testMapper.findByUserId(userId));
    }

    @RequestMapping(value="/test", method= RequestMethod.GET)
    public String test() {
        return "ok";
    }

}
