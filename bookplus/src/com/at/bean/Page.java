package com.at.bean;

import java.util.List;

/**
 * @author tao
 * @version 1.0
 * 描述:页面模型对象
 * 泛型指定我们要分页的数据(Bean)模型
 * @date 2020-07-22 15:22
 */
public class Page<T> {
    //页面显示数量
    public static final Integer PAGE_SIZE = 4;

    //当前页码
    private Integer pageNow;
    //总页码
    private Integer pageTotal;
    //数据总记录数
    private Integer pageTotalCount;
    //当前页显示的数量
    private Integer pageSize = PAGE_SIZE;
    //当前页数据
    private List<T> items;
    //分页条的请求地址抽取
    private String url;

    @Override
    public String toString() {
        return "Page{" +
                "pageNow=" + pageNow +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        //设置分页边界,当查询页数小于1时，显示第一页，大于末页则显示最后一页
        if (pageNow < 1) {
            pageNow = 1;
        }
        if (pageNow > pageTotal) {
            pageNow = pageTotal;

        }
        this.pageNow = pageNow;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
