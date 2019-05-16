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

    @GetMapping("/convertPdf")
    public void convertPdf(HttpServletResponse response, String html){
        log.info("request html:{}", html);
        try {
            Document document = Jsoup.connect("http://localhost:8081/ccpx/hello/url").get();
            document.select("div#btn_print").remove();
            PDFUtil pdfUtil = new PDFUtil();
            ByteArrayOutputStream stream = pdfUtil.html2Pdf(document.html());
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/pdf");
            OutputStream os = response.getOutputStream();
            stream.writeTo(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            log.error("printRecord {}",e.getMessage());
        }
    }
}
