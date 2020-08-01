package com.at.servlet;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-18 11:47
 */
public class TestBase64 {
    public static void main(String[] args) throws IOException {
        String name ="这是base64编码";
        //编码
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //将字符串编码
        String encode = base64Encoder.encode(name.getBytes("utf-8"));
        System.out.println(encode);
        //解码
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] bytes = base64Decoder.decodeBuffer(encode);

        String s = new String(bytes, "utf-8");
        System.out.println(s);
    }
}
