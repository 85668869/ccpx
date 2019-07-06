/**
 * Created by jingchun.zhang on 2019/5/16.
 */
package com.jc.ccpx.util;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.layout.font.FontProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2019/5/16
 */
public class Test {


    public static void main(String[] args) {
//        String source = "E:/work/ccpx/表格/模板/复审申请表模板.pdf";
//        PDFUtil2.partitionPdfFile(source,2);
        t3(args);
    }

    public static void t3(String[] args) {

        String source = "E:/work/ccpx/表格/模板/复审申请表模板.pdf";
        String target = "E:/work/ccpx/表格/模板/复审表_填值.pdf";

        Map<String, Object> values = new HashMap<String, Object>();
        values.put( "name", "张三");
        values.put( "sex", "女" );

        try {
            PDFUtil2 util = new PDFUtil2(source, target);
            util.generatePDFByTemplate(values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PDFUtil2.partitionPdfFile(target,2);
    }



    public static void t1(String[] args) throws Exception {
        ConverterProperties properties = new ConverterProperties();
        FontProvider font = new FontProvider();
        font.addSystemFonts();
        properties.setFontProvider(font);

        File outputPdf = new File("/output.pdf");

        File htmlFile = new File("d:/zzzzz/print.html");
        HtmlConverter.convertToPdf(htmlFile,outputPdf,properties);

//        Document document = Jsoup.connect("http://www.chachepeixun.com:20001/PrintTemplate.aspx?id=5891").get();
//        String s = document.html();
//        System.out.println(s);
//        HtmlConverter.convertToPdf(s,new FileOutputStream(outputPdf),properties);
    }

}
