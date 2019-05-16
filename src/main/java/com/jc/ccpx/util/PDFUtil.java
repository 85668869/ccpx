/**
 * Created by jingchun.zhang on 2019/5/16.
 */
package com.jc.ccpx.util;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.font.FontProvider;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2019/5/16
 */
@Slf4j
public class PDFUtil {

    private static final String FONT = "./pdf/font/NotoSansCJKsc-Regular.otf";


    public static void main(String[] args) throws Exception {
        File outputPdf = new File("/output.pdf");
        File htmlFile = new File("d:/zzzzz/print_modify.html");

//        org.jsoup.nodes.Document  document = Jsoup.connect("http://www.chachepeixun.com:20001/PrintTemplate.aspx?id=5891").get();
//        String s = document.html();
        String s = txt2String(htmlFile);
        PDFUtil pdfUtil = new PDFUtil();

        FileOutputStream  fos = new FileOutputStream(outputPdf);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(pdfUtil.html2Pdf(s).toByteArray());
//        HtmlConverter.convertToPdf(htmlFile,outputPdf,properties);
        bos.close();
        fos.close();
    }

    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }


    /**
     * @Description 将html转换为pdf文件
     * @param html html页面字符串
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ByteArrayOutputStream html2Pdf(String html) throws FileNotFoundException, IOException {
        ConverterProperties props = new ConverterProperties();
//        DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false, false, false);
//        defaultFontProvider.addFont(FONT);
//        props.setFontProvider(defaultFontProvider);

        FontProvider font = new FontProvider();
        font.addSystemFonts();
        props.setFontProvider(font);

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(bao);
        PdfDocument pdf = new PdfDocument(writer);
//        pdf.setDefaultPageSize(new PageSize(595, 1320.0F));  // 8.26  400
        //595.0F, 842.0F
        pdf.setDefaultPageSize(PageSize.A4);                 // 8.26  11.69
        Document document = HtmlConverter.convertToDocument(html, pdf, props);
        EndPosition endPosition = new EndPosition();
        LineSeparator separator = new LineSeparator(endPosition);
        document.add(separator);
        document.getRenderer().close();
//        PdfPage page = pdf.getPage(1);
//        float y = endPosition.getY() - 36;
//        page.setMediaBox(new Rectangle(0, y, 595, 14400 - y));
        document.close();
        return bao;
    }

    /**
     * @Description 打印备案申请书
     * @param res http响应
     */
    public void printRecord(HttpServletResponse res){
        try {
//        　　String htmlStr = FileUtil.readFile(null);
            PDFUtil pdfUtil = new PDFUtil();
            ByteArrayOutputStream stream = pdfUtil.html2Pdf("");
            res.setHeader("Expires", "0");
            res.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            res.setHeader("Pragma", "public");
            res.setContentType("application/pdf");
            OutputStream os = res.getOutputStream();
            stream.writeTo(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            log.error("printRecord {}",e.getMessage());
//        　　throw new IOException("-1","服务内部错误,请稍后再试!");
        }
    }

    /**
     * 定义操作区域
     */
    class EndPosition implements ILineDrawer {
        // y坐标
        protected float y;

        /**
         * @Description: 获取y坐标
         * @return
         */
        public float getY() {
            return y;
        }

        /**
         * @Description: 操作画布特定区域
         * @param pdfCanvas:操作画布
         * @param rect:操作区域
         */
        @Override
        public void draw(PdfCanvas pdfCanvas, Rectangle rect) {
            this.y = rect.getY();
        }

        /**
         * @Description: 获取行颜色
         * @return
         */
        @Override
        public Color getColor() {
            return null;
        }

        /**
         * @Description: 获取行宽
         * @return
         */
        @Override
        public float getLineWidth() {
            return 0;
        }

        /**
         * @Description: 设置行颜色
         * @param color
         */
        @Override
        public void setColor(Color color) {
        }

        /**
         * @Description: 设置行宽
         * @param lineWidth:宽度
         */
        @Override
        public void setLineWidth(float lineWidth) {
        }
    }
}
