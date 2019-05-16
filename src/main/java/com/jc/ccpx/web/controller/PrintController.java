package com.jc.ccpx.web.controller;

import com.jc.ccpx.util.PDFUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Controller
@RequestMapping("/print")
public class PrintController {

    @GetMapping("/urlToPdf")
    public void convertPdfByUrl(HttpServletResponse response, String url)throws Exception{
        log.info("request url:{}", url);
        Document document = Jsoup.connect(url).get();
        document.select("div#btn_print").remove();
        convertPdf(response, document.html());
    }

    @GetMapping("/htmlToPdf")
    public void convertPdf(HttpServletResponse response, String html){
        log.info("request html:{}", html);
        try {
            PDFUtil pdfUtil = new PDFUtil();
            ByteArrayOutputStream stream = pdfUtil.html2Pdf(html);
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
