package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.Cart;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class IndexController {
    public static ModelAndView getAllInfo(Request req, Response res) throws SQLException{
        Map params = new HashMap<>();
        Cart cart = req.session().attribute("basket");
        Float sumOfBasket;
        Integer countItems;
        if(cart == null){
            countItems = 0;
            sumOfBasket = 0f;
        }
        else{
            countItems = cart.size();
            sumOfBasket = cart.getSum();
        }
        System.out.println(countItems);
        params.put("count",countItems);
        params.put("sum",sumOfBasket);
        params.put("productList", new ProductDaoSqlite().getAll());
        params.put("productCategoryList", new ProductCategoryDaoSqlite().getAll());
        params.put("productSupplierList", new SupplierDaoSqlite().getAll());
        return new ModelAndView(params, "product/index");
    }
}
