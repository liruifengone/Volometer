package com.hearing.volometer.util;

import android.content.Context;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

/**
 * author: Aaron
 * date:  2017/3/26
 */

public class NumUtils {
    private static String pattern = "#0.000";
    private static DecimalFormat formatter = new DecimalFormat();

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static String string2String(String s, int size) {
        if (TextUtils.isEmpty(s)) {
            return "NA";
        }
        boolean matched = s.matches("(^-?\\d+((\\.\\d+)|\\.)?)|(^-?\\.\\d+)");
        if (matched) {
            Double d = Double.valueOf(s);
            formatter.applyPattern(pattern);
            if (d == null) {
                return "0.000";
            }
            d = (Math.round(d * (Math.pow(10, size))) / (Math.pow(10, size)));
            String str = formatter.format(d);
            return str;
        } else {
            return "NA";
        }
    }

    /**
     * 将字符串转成MD5值
     * @param string 需要转换的字符串
     * @return 字符串的MD5值
     */
    public static String stringToMD5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }
}
