package com.at.el.dom4j;


/**
 * @author tao
 * @version 1.0
 * @date 2020-07-10 17:13
 * @description
 */
public class Book {
    /*
     * 1.书名编号
     * */
    private String sn;
    /*
     * 2.书名
     * */
    private String name;
    /*
     * 3.作者
     * */
    private String author;
    /*
     * 4.价格
     * */
    private double price;

    public Book() {
    }

    public Book(String sn, String name, String author, double price) {
        this.sn = sn;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
