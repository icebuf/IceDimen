package com.icebuf.icedimen;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;

/**
 * @author IceTang
 * @version 1.0
 * Data: 2020/8/12
 * E-mail：bflyff@hotmail.com
 */
public class DimenRes {

    private DPI mDpi;

    private String mFormat;

    public DimenRes(DPI dpi) {
        mDpi = dpi;
    }

    public DimenRes(DPI dpi, String format) {
        mDpi = dpi;
        mFormat = format;
    }

    public void setFormat(String format) {
        this.mFormat = format;
    }

    public Document createResDocument(DocumentBuilder builder, int formIndex, int toIndex) {
        Document document = builder.newDocument();
        document.setXmlVersion("1.0");
        Text nextLine = document.createTextNode("\n");
        Element resources = document.createElement("resources");
        document.appendChild(resources);
        resources.appendChild(nextLine);

        float scale = DPI.getScale(mDpi);
        for (int i = formIndex; i <= toIndex; i++) {
            Element dimen = createDimenElement(document, i, scale);
            resources.appendChild(dimen);
            resources.appendChild(nextLine);
        }
        return document;
    }

    public Element createDimenElement(Document document, int px, float scale) {
        Element dimen = document.createElement("dimen");
        dimen.setAttribute("name", String.format(Locale.getDefault(), mFormat, px));
        String format = px % scale == 0 ? "%.0fdp" : "%.1fdp";
        String dpStr = String.format(Locale.getDefault(), format, px / scale);
        dimen.setTextContent(dpStr);
        return dimen;
    }

    public String getDirSuffix() {
        if (DPI.DEFAULT.equals(mDpi)) {
            return "";
        }
        return "-" + mDpi.name().toLowerCase();
    }
}
