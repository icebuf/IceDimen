package com.icebuf.icedimen;

import android.text.TextUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.util.List;
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

    public void makeDocument(Document document, List<ElementFormat> formats) {
        document.setXmlVersion("1.0");
        Text nextLine = document.createTextNode("\n");
        Element resources = document.createElement("resources");
        document.appendChild(resources);
        resources.appendChild(nextLine);

        float scale = DPI.getScale(mDpi);
        for (ElementFormat format : formats) {
            String densityUnit = getUnit(format.type);
            for (int i = format.formPx; i <= format.toPx; i++) {
                Element dimen = createDimenElement(document, i, scale, format.nameFormat, densityUnit);
                resources.appendChild(dimen);
                resources.appendChild(nextLine);
            }
        }
    }

    private String getUnit(int type) {
        switch (type) {
            case ElementFormat.TYPE_PX2SP:
                return  "sp";
            case ElementFormat.TYPE_PX2DP:
                return "dp";
        }
        return "dp";
    }

    public Element createDimenElement(Document document, int px, float scale,
                                      String nameFormat, String densityUnit) {
        Element dimen = document.createElement("dimen");
        dimen.setAttribute("name", String.format(Locale.getDefault(), nameFormat, px));
        String format = px % scale == 0 ? "%.0f" : "%.1f";
        format += densityUnit;
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
