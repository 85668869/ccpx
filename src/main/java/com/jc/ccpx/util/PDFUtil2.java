package com.jc.ccpx.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import javax.imageio.ImageIO;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @Title: PDFUtil.java
 */
public class PDFUtil2 {

    private static BaseFont bfChinese;
    private Document document;
    private PdfReader reader;
    private FileOutputStream out;
    private ByteArrayOutputStream bos;
    private PdfStamper stamper;
    private AcroFields acroFields;
    private int pageCount;

    static {
        try {
            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", true);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filePath 生成的PDF的保存路径
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public PDFUtil2(String filePath) throws FileNotFoundException, DocumentException {
        this.document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
    }

    /**
     * @param templatePath PDF文件模板路径
     * @param targetPath 生成的PDF的路径
     * @throws IOException
     * @throws DocumentException
     * @throws Exception
     */
    public PDFUtil2(String templatePath, String targetPath) throws IOException, DocumentException {
        this.reader = new PdfReader(templatePath);// 读取pdf模板
        this.pageCount = this.reader.getNumberOfPages();// 获取pdf文件总页数
        this.bos = new ByteArrayOutputStream();// 存储pdf文件每页的内容
        this.stamper = new PdfStamper(this.reader, this.bos);
        this.acroFields = stamper.getAcroFields();// 获取表单域
        this.out = new FileOutputStream(targetPath);

    }

    /**
     * 单个Pdf文件分割成N个文件
     *
     * @param filepath
     * @param N
     */
    public static void partitionPdfFile(String filepath, int N) {
        Document document = null;
        PdfCopy copy = null;

        try {
            PdfReader reader = new PdfReader(filepath);
            int n = reader.getNumberOfPages();
            if (n < N) {
                System.out.println("The document does not have " + N
                        + " pages to partition !");
                return;
            }
            int size = n / N;
            String staticpath = filepath.substring(0,
                    filepath.lastIndexOf("\\") + 1);
            String savepath = null;
            List<String> savepaths = new ArrayList<String>();
            for (int i = 1; i <= N; i++) {
                if (i < 10) {
                    savepath = filepath.substring(
                            filepath.lastIndexOf("\\") + 1,
                            filepath.length() - 4);
                    savepath = staticpath + savepath + "0" + i + ".pdf";
                    savepaths.add(savepath);
                } else {
                    savepath = filepath.substring(
                            filepath.lastIndexOf("\\") + 1,
                            filepath.length() - 4);
                    savepath = staticpath + savepath + i + ".pdf";
                    savepaths.add(savepath);
                }
            }

            for (int i = 0; i < N - 1; i++) {
                document = new Document(reader.getPageSize(1));
                copy = new PdfCopy(document, new FileOutputStream(
                        savepaths.get(i)));
                document.open();
                for (int j = size * i + 1; j <= size * (i + 1); j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
                document.close();
            }

            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(
                    savepaths.get(N - 1)));
            document.open();
            for (int j = size * (N - 1) + 1; j <= n; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    //~~~~~~~~~~~~~生成PDF文件~~~~~~~~~~~~~~~~

    /**
     * 创建table表格并添加到pdf中
     * @param tableHeader 表头信息
     * @param contents 表数据信息
     * @param colsPercent 每一列所占宽度比例
     * @param fontSize 表格字体大写
     * @param hasBorder 是否有边框
     * @throws DocumentException
     * @throws IOException
     */
    public void createTable(String[] tableHeader, String[][] contents, int[] colsPercent, float fontSize, boolean hasBorder) throws DocumentException, IOException {
        Font fontsize = new Font(bfChinese, fontSize, Font.BOLD);
        PdfPTable table = new PdfPTable(tableHeader.length);
        table.setWidthPercentage(523 / 5.23f);
        table.setWidths(colsPercent);
        for (String header : tableHeader) {
            PdfPCell cell = new PdfPCell(new Phrase(header, fontsize));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            if (!hasBorder) {
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(PdfPCell.NO_BORDER);
            } else {
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            }
            table.addCell(cell);
        }
        for (String[] content : contents) {
            for (String header : content) {
                PdfPCell cell = new PdfPCell(new Phrase(header, fontsize));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                if (!hasBorder) {
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBorder(PdfPCell.NO_BORDER);
                } else {
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                }
                table.addCell(cell);
            }
        }
        this.document.add(table);
    }

    /**
     * 创建段落并添加到表格
     * @param paragraphContent 段落内容
     * @param isIndentation 段落首行是否缩进
     * @param fontSize 段落字体大小
     * @param baseColor 段落字体颜色
     * @version 1.0
     * @throws DocumentException
     */
    public void createParagraph(String paragraphContent, boolean isIndentation, float fontSize, BaseColor baseColor) throws DocumentException {
        if (isIndentation) {
            paragraphContent = "          " + paragraphContent;
        }
        Font font = new Font(bfChinese, fontSize, Font.BOLD);
        font.setColor(baseColor);
        Paragraph paragraph = new Paragraph();
        paragraph.setSpacingAfter(10f);
        Chunk chunk = new Chunk(paragraphContent, font);
        paragraph.add(chunk);
        this.document.add(paragraph);
    }

    /**
     * 创建段落
     * @param paragraphContent 主题内容
     * @param fontSize 字体大小
     * @version 1.0
     * @throws DocumentException
     */
    public void createSubject(String paragraphContent, float fontSize) throws DocumentException {
        Font font = new Font(bfChinese, fontSize, Font.BOLD);
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(10f);
        Chunk chunk = new Chunk(paragraphContent, font);
        paragraph.add(chunk);
        this.document.add(paragraph);
    }

    /**
     * 创建图片
     * @Title createPicture
     * @Description TODO
     * @param picturePath
     * @throws MalformedURLException
     * @throws IOException
     * @throws DocumentException
     * void
     */
    public void createPicture(String picturePath) throws MalformedURLException, IOException, DocumentException {
        Image image = Image.getInstance(picturePath);
        BufferedImage sourceImg = ImageIO.read(new FileInputStream(new File(picturePath)));
        int imgWidth = sourceImg.getWidth();//
        System.out.println("图片宽度像素 : " + imgWidth);
        int imgHeight = sourceImg.getHeight();
        System.out.println("图片高度像素 : " + imgWidth);
        float heightPercent = 0.0f;
        float widthPercent = 0.0f;
        if (imgWidth > 500) {
            widthPercent = 500.0f / imgWidth * 100;
        }
        if (imgHeight > 700) {
            heightPercent = 700.0f / imgHeight * 100;
        }
        if (widthPercent > 0) {
            if (heightPercent == 0.0) {
                image.scalePercent(widthPercent);
            } else if (heightPercent > 0) {
                if (widthPercent < heightPercent) {
                    image.scalePercent(widthPercent);
                } else {
                    image.scalePercent(heightPercent);
                }
            }
        } else if (heightPercent != 0.0) {
            image.scalePercent(heightPercent);
        }
        document.add(image);
    }

    /**
     * 保存
     */
    public void save() {
        this.document.close();
    }

    // ~~~~~~~~~~~~~~套打/合并PDF~~~~~~~~~~~~~~~~~~~
    /**
     * 利用模板生成pdf
     * @param values PDF填充域数据
     * @throws Exception
     */
    public void generatePDFByTemplate(Map<String, Object> values) throws Exception {
        Document doc = new Document();
        try {
            setValues(values, acroFields);
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            for (int i = 1; i <= pageCount; i++) {
                PdfImportedPage page = copy.getImportedPage(new PdfReader(bos.toByteArray()), i);
                copy.addPage(page);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            doc.close();
            this.close();
        }
    }

    /**
     * 填充数据
     * @param values 填充域对应的填充数据
     * @param acroFields pdf填充域
     * @throws IOException
     * @throws DocumentException
     */
    private void setValues(Map<String, Object> values, AcroFields acroFields)
            throws IOException, DocumentException {
        BaseFont bf = BaseFont.createFont("STSong-Light,Bold", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Set<String> keys = acroFields.getFields().keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String name = it.next();
            acroFields.setFieldProperty(name, "textfont", bf, null);
            acroFields.setField(name, String.valueOf(values.get(name)));
        }
    }

    /**
     * 关闭流
     */
    private void close() {
        if (this.reader != null) {
            this.reader.close();
        }
        if (this.out != null) {
            try {
                this.out.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (this.bos != null) {
                    try {
                        this.bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 多个PDF合并功能
     * @param pdfPaths 要进行合并的多个PDF路径
     * @param savepath 生成的新PDF路径
     * @throws Exception
     */
    public static boolean mergePDF(String[] pdfPaths, String savepath) throws Exception {
        if (pdfPaths.length > 1) {
            try {
                Document document = new Document(new PdfReader(pdfPaths[0]).getPageSize(1));
                PdfCopy copy = new PdfCopy(document, new FileOutputStream(savepath));
                document.open();
                for (int i = 0; i < pdfPaths.length; i++) {
                    PdfReader reader = new PdfReader(pdfPaths[i]);
                    int n = reader.getNumberOfPages();
                    for (int j = 1; j <= n; j++) {
                        document.newPage();
                        PdfImportedPage page = copy.getImportedPage(reader, j);
                        copy.addPage(page);
                    }
                }
                document.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (DocumentException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            throw new Exception("要进行合成的合同文件数量必须不少于两个");
        }

    }
}