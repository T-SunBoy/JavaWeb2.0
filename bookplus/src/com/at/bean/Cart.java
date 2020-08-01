package com.at.bean;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tao
 * @version 1.0
 * 描述:购物车模型对象
 * @date 2020-07-26 12:11
 */
public class Cart {
    //总商品数量
    //private Integer totalCount;
    //总商品金额
    //private BigDecimal totalPrice;

    //购物车的商品项---每个id对应一种商品

    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    /**
     * Description:添加商品项
     *
     * @param cartItem 被添加的对象
     * @return void
     * @Author tao
     * @Date 12:21 2020/7/26
     **/

    public void addItem(CartItem cartItem) {
        //添加商品，如果购物车没有添加过，则直接添加到购物车，如果购物车已经添加过该商品，则修改该商品数量、该商品总金额
        //1、查找购物车是否添加过此商品---通过id
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            //没有添加过,直接添加该商品
            items.put(cartItem.getId(), cartItem);
        } else {
            //购物车已经存在该商品
            //更新商品数量
            item.setCount(item.getCount() + 1);
            //更新该商品总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }

    /**
     * Description:删除商品项
     *
     * @param id 被删除商品项的id
     * @return void
     * @Author tao
     * @Date 12:21 2020/7/26
     **/

    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * Description:清空购物车
     *
     * @param
     * @return void
     * @Author tao
     * @Date 12:22 2020/7/26
     **/

    public void clear() {
        items.clear();
    }

    /**
     * Description:修改商品数量
     *
     * @param id    被修改的商品id
     * @param count 修改的数量
     * @return void
     * @Author tao
     * @Date 12:24 2020/7/26
     **/

    public void updateCount(Integer id, Integer count) {
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            //查找到该商品后
            //1、修改商品数量
            cartItem.setCount(count);
            //2、更新该商品总金额
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
    /**
     * Description:通过商品项items中的数据返回总商品数量
     * @Author tao
     * @Date 17:51 2020/7/27
     * @param
     * @return java.lang.Integer
     **/


    public Integer getTotalCount() {
        //总商品数量
        Integer totalCount = 0;
        for (CartItem cartItem : items.values()) {

        }

        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {

            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }
    /**
     * Description:通过商品项items中的数据,遍历每一个商品的价格，返回总商品价格
     * @Author tao
     * @Date 17:51 2020/7/27
     * @param
     * @return java.lang.Integer
     **/

    public BigDecimal getTotalPrice() {
        //总商品金额
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
