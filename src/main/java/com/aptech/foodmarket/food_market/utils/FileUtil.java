package com.aptech.foodmarket.food_market.utils;

import java.io.*;
import java.net.URL;

public class FileUtil {
    public static String getImages(String src,String tar) throws IOException {
        //Exctract the name of the image from the src attribute
        int indexname = src.lastIndexOf("/");
        if (indexname == src.length()) {
            src = src.substring(1, indexname);
        }
        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname, src.length());
//        System.out.println(tar);
//        System.out.println(name);
//        //Open a URL Stream
//        URL url = new URL(src);
//        InputStream in = url.openStream();
//
//        OutputStream out = new BufferedOutputStream(new FileOutputStream(tar + name));
//
//        for (int b; (b = in.read()) != -1;) {
//            out.write(b);
//        }
//        out.close();
//        in.close();
        return name;
    }
}
