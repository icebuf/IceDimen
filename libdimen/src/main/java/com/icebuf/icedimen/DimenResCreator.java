package com.icebuf.icedimen;


import org.w3c.dom.Document;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by IceTang
 * 日期：2019-06-11
 * 邮箱：jie.tang.kty@tcl.com
 */
public class DimenResCreator {

    private String mPath = "./";

    private String mResDir = "values";

    private String mResFileName = "dimens.xml";

    private DocumentBuilderFactory mBuilderFactory;

    private DocumentBuilder mBuilder;

    private TransformerFactory mTransformerFactory;

    public DimenResCreator() {

    }

    public DimenResCreator(String resDir) {
        mPath = resDir;
    }

    public void createDimenRes(DimenRes dimenRes, List<ElementFormat> formats) throws Exception {
        if (mBuilderFactory == null) {
            mBuilderFactory = DocumentBuilderFactory.newInstance();
        }
        if (mBuilder == null) {
            mBuilder = mBuilderFactory.newDocumentBuilder();
        }
        Document document = dimenRes.createResDocument(mBuilder, formats);
        File dir = new File(mPath, mResDir + dimenRes.getDirSuffix());
        if (!dir.exists() || !dir.isDirectory()) {
            dir.mkdirs();
        }
        outputFile(document, new File(dir, mResFileName));
    }

    private void outputFile(Document document, File file) throws Exception {
        if (mTransformerFactory == null) {
            mTransformerFactory = TransformerFactory.newInstance();
        }
        Transformer transformer = mTransformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        //通过转换器对象的transform方法将源与目标文件相连接
        StreamResult result = new StreamResult(file);
        transformer.transform(new DOMSource(document), result);
    }

    public void setPath(String path) {
        this.mPath = path;
    }

    public String getPath() {
        return mPath;
    }

    public void setBuilder(DocumentBuilder builder) {
        this.mBuilder = builder;
    }

    public void setBuilderFactory(DocumentBuilderFactory factory) {
        this.mBuilderFactory = factory;
    }

    public void setResDir(String resDir) {
        this.mPath = resDir;
    }

    public String getResDir() {
        return mResDir;
    }

    public void setResFileName(String fileName) {
        this.mResFileName = fileName;
    }

    public String getResFileName() {
        return mResFileName;
    }


}
