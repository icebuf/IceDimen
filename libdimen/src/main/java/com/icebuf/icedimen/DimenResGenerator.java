package com.icebuf.icedimen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author IceTang
 * @version 1.0
 * Data: 2020/8/13
 * E-mail：bflyff@hotmail.com
 */
public class DimenResGenerator {

    private String mDstPath;


    private List<ElementFormat> mElementFormats = new ArrayList<>();

    public DimenResGenerator(String dstPath) {
        mDstPath = dstPath;
    }

    public DimenResGenerator addElementFormat(ElementFormat format) {
        mElementFormats.add(format);
        return this;
    }

    public void generate() throws Exception {
        generate(null);
    }

    public void generate(DPIFilter filter) throws Exception {
        if (mDstPath == null || "".equals(mDstPath)) {
            System.out.println("Invalid path!");
            return;
        }
        File resDir = new File(mDstPath);
        if (!resDir.exists()) {
            resDir.mkdirs();
        }
        if (resDir.isFile()) {
            resDir.delete();
            resDir.mkdir();
        }
        System.out.println("Resources path: " + resDir.getPath());
        DimenResCreator creator = new DimenResCreator(resDir.getPath());
        for (DPI dpi : DPI.values()) {
            if (filter == null || filter.accept(dpi)) {
                creator.createDimenRes(new DimenRes(dpi), mElementFormats);
                System.out.println("Create res of " + dpi.name() + " success !");
            }
        }
        System.out.println("Generate success!");
    }

    public interface DPIFilter {
        boolean accept(DPI dpi);
    }

    public static void main(String[] args) throws Exception {

        String rootPath = System.getProperty("user.dir");
        String resPath = "src/main/res/";
        File moduleFile = new File(rootPath, BuildConfig.MODULE);
        File resFile = new File(moduleFile, resPath);

        new DimenResGenerator(resFile.getPath())
                .addElementFormat(new ElementFormat(
                        "dp_%dpx", ElementFormat.TYPE_PX2DP, 0, 1920))
                .addElementFormat(new ElementFormat(
                        "sp_%dpx", ElementFormat.TYPE_PX2SP, 0, 128))
                .generate();
    }
}
