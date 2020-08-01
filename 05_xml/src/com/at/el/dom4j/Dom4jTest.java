package com.at.el.dom4j;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

/**
 * @author tao
 * @version 1.0
 * @date 2020-07-10 17:14
 * @description
 */
public class Dom4jTest {
    @Test
    public void test() throws Exception {
        //创建一个SAXReader输入流对象，读取xml文件，生成Document对象
        SAXReader saxReader = new SAXReader();
        Document read = saxReader.read("src/books.xml");
        System.out.println(read);
    }


    @Test
    public void test1() throws Exception {
        //1.创建SAXReader
        SAXReader saxReader = new SAXReader();
        //2.加载xml文件,获取Document对象
        Document read = saxReader.read("src/books.xml");
        //3.获取xml文件中的根元素
        Element rootElement = read.getRootElement();
        //4.通过根元素获取book标签对象的一个集合
        List<Element> books = rootElement.elements("book");
        //5.遍历
        for (Element book : books) {
            /*//asXML():把标签对象转换为标签字符串
            String s = book.asXML();
            System.out.println(s);*/

            //获取对应标签名的标签对象
            /*Element name = book.element("name");*/
            //System.out.println(name.asXML());

            //获取标签中的文本内容
            /*String text = name.getText();
            System.out.println(text);*/

            //直接获取标签的文本内容
            String name = book.elementText("name");
            //System.out.println(name);

            String author = book.elementText("author");
            String price = book.elementText("price");
            //获取标签属性的value值
            String sn = book.attributeValue("sn");
            System.out.println(new Book(sn, name, author, Double.parseDouble(price)));

        }

    }
}
