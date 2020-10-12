package com.imooc.reader.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {

    public static String md5Digest(String source,Integer salt){
        char[] ca = source.toCharArray();
        for (int i = 0; i < ca.length ; i++) {
            ca[i]  = (char) (ca[i] + salt);
        }
        String target = new String(ca);
        String md5 = DigestUtils.md5Hex(target);
        return  md5;
    }

}
