package com.vivi.renew.backend.test.controller;

import com.vivi.renew.backend.accessory.CommonResult;
import com.vivi.renew.backend.test.entity.Test;
import com.vivi.renew.backend.test.mapper.TestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

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

    // 分别解析出客户端post的各个参数
    @RequestMapping(value="/testPost", method= RequestMethod.POST)
    public CommonResult testPost(@RequestParam("jsonStr")String jsonStr,@RequestParam("arg2")String arg2) {
        logger.debug("jsonStr = "+jsonStr);
        logger.debug("arg2 = "+arg2);
        return new CommonResult(jsonStr);

    }

    // 将post的json串直接解析成对象
    @RequestMapping(value="/testPostJson", method= RequestMethod.POST)
    public CommonResult testPostJson(@RequestBody Test test) {
        logger.debug("test.userName="+test.getUserName());
        logger.debug("books=" + test.getBooks().size());
        return new CommonResult("ok");
    }

    // 接收上载的文件
    @RequestMapping(value="/testUpload", method = RequestMethod.POST)
    public CommonResult upload(@RequestParam MultipartFile file, HttpServletRequest request) {
        // 注意采用下述指定明确参数名的形式，要求客户端  Content-Disposition: form-data; name="file"; filename="IMG_20180514_213010.jpg"
        // 注意：@RequestParam MultipartFile file, 等同于@RequestParam("file") MultipartFile file,
        //public CommonResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        logger.debug("contentType = " + contentType);
        logger.debug("OriginalFilename = " + fileName);

        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");

        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>filePath = " + filePath);

        try {
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(filePath+fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
            logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>upload ok!!");
        } catch (Exception e) {
            logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>upload error!!",e);
        }

        return new CommonResult("ok");

    }

    // 接收多个文件同时上传
    @RequestMapping(value="/testUploads", method = RequestMethod.POST)
    public CommonResult uploads(HttpServletRequest request) {
        // MultipartFile[] files好像得不到files
//        public CommonResult uploads(@RequestParam MultipartFile[] files, HttpServletRequest request) {
//        logger.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<enter multi files upload,files=" + files.length);
//
//        for (int i = 0; i < files.length; i++) {
//            logger.debug("file name =" + files[i].getOriginalFilename());
//        }

        // 下面的getFiles("file")中的"file"必须与客户端的createFormData("file",的"file"一致
        List<MultipartFile> ofiles =((MultipartHttpServletRequest)request).getFiles("file");
        logger.debug("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<enter multi files upload,other files=" + ofiles.size());

        for (int i = 0; i< ofiles.size(); i++) {
            logger.debug("file name =" + ofiles.get(i).getOriginalFilename());
        }

        return new CommonResult("ok");
    }

    // 测试retrofit
    @RequestMapping(value="/retrofitTest", method= RequestMethod.GET)
    public CommonResult testRetrofitTest() {
        return new CommonResult("okokokokok");
    }

}
