package org.hy.common.report.junit.word;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;

import javax.imageio.ImageIO;

import org.apache.poi.ooxml.POIXMLDocumentPart.RelationPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.hy.common.Help;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;





public class QrCode
{

    public static void main(String [] args)
    {
        try
        {
            addPageHead2("D:\\WorkSpace_SearchDesktop\\hy.common.report\\test\\org\\hy\\common\\report\\junit\\word\\原.docx"
                        ,"C:\\Users\\ZhengWei\\Desktop\\页眉测试.docx"
                        ,"D:\\WorkSpace_SearchDesktop\\hy.common.report\\test\\org\\hy\\common\\report\\junit\\word\\1.png");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    public static void addPageHead2(String beforePath ,String afterPath ,String imagePath) throws IOException, InvalidFormatException
    {
        File is = new File(beforePath);//文件路径
        FileInputStream fis = new FileInputStream(is);
        XWPFDocument docx = new XWPFDocument(fis);//文档对象
        
        
        CTSectPr sectPr = docx.getDocument().getBody().isSetSectPr() ? docx.getDocument().getBody().getSectPr() : docx.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(docx, sectPr);
        XWPFHeader header = policy.createHeader(STHdrFtr.DEFAULT);
        
        
        XWPFParagraph paragraph = header.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.addTab();
        
        File file=new File(imagePath);
        FileInputStream in=new FileInputStream(file);
        BufferedImage image=ImageIO.read(file);
        
        XWPFPicture picture = run.addPicture(in
                ,XWPFDocument.PICTURE_TYPE_PNG
                ,imagePath
                ,Units.pixelToEMU(image.getWidth())
                ,Units.pixelToEMU(image.getHeight()));
        
        
        String blipID = "";
        for (XWPFPictureData picturedata : header.getAllPackagePictures())
        {
            blipID = header.getRelationId(picturedata);
        }
        
        if ( !Help.isNull(blipID) )
        {
            picture.getCTPicture().getBlipFill().getBlip().setEmbed(blipID);
        }
        
        header.setXWPFDocument(docx);
        OutputStream os = new FileOutputStream(afterPath);
        docx.write(os);//输出到本地
    }
    
    
    
    public static void addPageHead(String beforePath ,String afterPath ,String imagePath) throws IOException, InvalidFormatException
    {
        File is = new File(beforePath);//文件路径
        FileInputStream fis = new FileInputStream(is);
        XWPFDocument docx = new XWPFDocument(fis);//文档对象
        
        CTP ctp = CTP.Factory.newInstance();
        XWPFParagraph paragraph = new XWPFParagraph(ctp, docx);//段落对象
        ctp.addNewR().addNewT().setStringValue("华丽的测试页眉2019051488888888");//设置页眉参数
        ctp.addNewR().addNewT().setSpace(SpaceAttribute.Space.PRESERVE);

        
        XWPFRun run = paragraph.createRun();
        run.addTab();
        
        File file=new File(imagePath);
        FileInputStream in=new FileInputStream(file);
        BufferedImage image=ImageIO.read(file);
        
        XWPFPicture picture = run.addPicture(in
                ,XWPFDocument.PICTURE_TYPE_PNG
                ,imagePath
                ,Units.pixelToEMU(image.getWidth())
                , Units.pixelToEMU(image.getHeight()));
        run.setText( "你好" );
        
        
        CTSectPr sectPr = docx.getDocument().getBody().isSetSectPr() ? docx.getDocument().getBody().getSectPr() : docx.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(docx, sectPr);
        XWPFHeader header = policy.createHeader(STHdrFtr.DEFAULT, new XWPFParagraph[] { paragraph });
        
        
        // header.addPictureData(picture.getPictureData().getData() ,XWPFDocument.PICTURE_TYPE_PNG);
        
        
        String blipID = "";
        for (XWPFPictureData picturedata : header.getAllPackagePictures())
        {
            blipID = header.getRelationId(picturedata);
            
            for (RelationPart v_Item : header.getRelationParts())
            {
                System.out.println(v_Item.getRelationship().getId());
            }
            
            if ( !Help.isNull(blipID) )
            {
                picture.getCTPicture().getBlipFill().getBlip().setEmbed(blipID);
            }
        }
        
        header.setXWPFDocument(docx);
        OutputStream os = new FileOutputStream(afterPath);
        docx.write(os);//输出到本地
    }
    
    
    
    public static void qq(String beforePath ,String afterPath ,String imagePath) throws FileNotFoundException, IOException, InvalidFormatException
    {
        XWPFDocument docx=new XWPFDocument(new FileInputStream(beforePath));

        XWPFParagraph paragraph=docx.createParagraph();
        InputStream in;
        BufferedImage image;
        //这样图片是显示不出来的
        // in=new URL(src).openStream();
        // image = ImageIO.read(in);

        // 获取远程图片
        // URL url = new URL("");
        // in = url.openStream();
        // image = ImageIO.read(url);


        //获取本地图片
        File file=new File(imagePath);
        in=new FileInputStream(file);
        image=ImageIO.read(file);


        // 将图片添加到word中
        XWPFRun run = paragraph.createRun();
        run.addPicture(in, org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_PNG, "",
        Units.pixelToEMU(image.getWidth()), Units.pixelToEMU(image.getHeight()));
        
        OutputStream os = new FileOutputStream(afterPath);
        docx.write(os);
    }



    /**
     * 
     * @param beforePath   原始文件
     * @param afterPath    生成文件
     * @param imagePath    图片路径
     * @throws Exception
     */
    public static void addQRCode(String beforePath ,String afterPath ,String imagePath) throws Exception
    {
        InputStream is = new FileInputStream(new File(beforePath));
        XWPFDocument doc = new XWPFDocument(is);
        //添加页眉，在页眉上添加二维码图片
        CTSectPr sectPr = doc.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(doc, sectPr);
 
        //创建页眉
        XWPFHeader header = headerFooterPolicy.createHeader(STHdrFtr.DEFAULT);
 
        XWPFParagraph paragraph = header.getParagraphArray(0);
        paragraph.setAlignment(ParagraphAlignment.LEFT);
 
        CTTabStop tabStop = paragraph.getCTP().getPPr().addNewTabs().addNewTab();
        tabStop.setVal(STTabJc.LEFT);
 
        int twipsPerInch = 1440;
        tabStop.setPos(BigInteger.valueOf(6 * twipsPerInch));
 
        XWPFRun run = paragraph.createRun();
        run.addTab();
 
        XWPFPicture picture = run.addPicture(new FileInputStream(imagePath), XWPFDocument.PICTURE_TYPE_PNG, imagePath, Units.toEMU(40), Units.toEMU(40));
        String blipID = "";
        for (XWPFPictureData picturedata : header.getAllPackagePictures()) {
            blipID = header.getRelationId(picturedata);
        }
        picture.getCTPicture().getBlipFill().getBlip().setEmbed(blipID);
 
        doc.write(new FileOutputStream(afterPath));
    }
}
