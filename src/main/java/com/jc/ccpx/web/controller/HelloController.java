/**
 * Created by jingchun.zhang on 2019/5/15.
 */
package com.jc.ccpx.web.controller;

import com.jc.ccpx.dao.HelloMapper;
import com.jc.ccpx.util.PDFUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2019/5/15
 */
@Slf4j
@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    HelloMapper helloMapper;

    @GetMapping("/aa")
    @ResponseBody
    public Object hello(String hello){
        return "hello" + helloMapper.queryUser(1);
    }


    @GetMapping("/ha")
    public String ha(){
        return "index.html";
    }

    @GetMapping("/url")
    public String getUrl(){
        return "print_modify.html";
    }


}
