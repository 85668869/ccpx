/**
 * Created by jingchun.zhang on 2019/5/15.
 */
package com.jc.ccpx.web.controller;

import com.jc.ccpx.dao.mapper.HelloMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "table_fushen.html";
    }

    @GetMapping("/url")
    public String getUrl(){
        return "print.html";
    }

    @GetMapping("/print")
    public String getUrl1(){
        return "print1.html";
    }


    @GetMapping("/zige")
    public String getUrl2(){
        return "zige.html";
    }


}
