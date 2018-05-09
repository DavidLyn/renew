package com.vivi.renew.backend.test.controller;

import com.vivi.renew.backend.accessory.CommonResult;
import com.vivi.renew.backend.test.entity.Test;
import com.vivi.renew.backend.test.mapper.TestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/testObject", method= RequestMethod.GET)
    public CommonResult testObject() {
        return new CommonResult(new Test());
    }

    @RequestMapping(value="/testPost", method= RequestMethod.POST)
    public CommonResult testPost(@RequestParam("jsonStr")String jsonStr,@RequestParam("arg2")String arg2) {
        logger.debug("jsonStr = "+jsonStr);
        logger.debug("arg2 = "+arg2);
        return new CommonResult(jsonStr);

    }

    @RequestMapping(value="/testPostJson", method= RequestMethod.POST)
    public CommonResult testPostJson(@RequestBody Test test) {
        logger.debug("test.userName="+test.getUserName());
        logger.debug("books=" + test.getBooks().size());
        return new CommonResult("ok");
    }

}
