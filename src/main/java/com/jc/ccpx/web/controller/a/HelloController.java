/**
 * Created by jingchun.zhang on 2019/5/15.
 */
package com.jc.ccpx.web.controller.a;

import com.jc.ccpx.dao.HelloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2019/5/15
 */
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
}
