package com.icebuf.icedimen;

import android.annotation.SuppressLint;
import android.content.Context;

import java.lang.reflect.Field;

/**
 * 作者：Ice Nation
 * 日期：2019/2/14 8:51
 * 邮箱：bflyff@hotmail.com
 */
public class DisplayUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context the context
     * @param dp      dp value
     * @return px value
     */
    public static int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context the context
     * @param px      px value
     * @return dp value
     */
    public static float px2dp(Context context, int px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    /**
     * @param context 上下文
     * @param px      输入像素值
     * @return 输出sp值
     */
    public static int px2sp(Context context, float px) {
        float scaleDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scaleDensity + 0.5f);
    }

    /**
     * @param context 上下文
     * @param sp      输入sp值
     * @return 输出像素值
     */
    public static int sp2px(Context context, float sp) {
        float scaleDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scaleDensity + 0.5f);
    }

    /**
     * 屏幕宽度（像素）
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 屏幕高度（像素）
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取状态栏高度（像素），直接获取属性
     *
     * @param context 上下文
     * @return 状态栏高度
     */
    public static int getStateBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier(
                "status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取状态栏高度（像素），使用反射
     *
     * @param context 上下文
     * @return 状态栏高度
     */
    public static int getStateBarHeight2(Context context) {
        int statusBarHeight = 0;
        try {
            @SuppressLint("PrivateApi")
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int x = (int) field.get(obj);
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }


}
