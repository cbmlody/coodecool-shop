package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class IndexController {
    public static ModelAndView getAllInfo(Request req, Response res) throws SQLException{
        Map params = mapHelper(req);
        params.put("productList", new ProductDaoSqlite().getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView getByCategory(Request req, Response res) throws SQLException{
        Map params = mapHelper(req);
        Integer categoryId = Integer.parseInt(req.params(":categoryid"));
        params.put("productList", new ProductDaoSqlite().getBy( new ProductCategoryDaoSqlite().find(categoryId)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView getBySupplier(Request req, Response res) throws SQLException{
        Map params = mapHelper(req);
        Integer supplierId = Integer.parseInt(req.params(":supplierid"));
        params.put("productList", new ProductDaoSqlite().getBy(new SupplierDaoSqlite().find(supplierId)));
        return new ModelAndView(params, "product/index");
    }

    private static Map mapHelper(Request req) throws SQLException{
        if  (req.session().attribute("basket") == null){
            Cart cart = new Cart();
            req.session().attribute("basket",cart);
        }

        Map params = new HashMap<>();
        Cart cart = req.session().attribute("basket");
        Float sumOfBasket;
        Integer countItems;
        if(cart == null){
            countItems = 0;
            sumOfBasket = 0f;
        }
        else{
            countItems = cart.numOfitemsInCart();
            sumOfBasket = cart.getSum();
        }
        params.put("count", countItems);
        params.put("sum",sumOfBasket);
        params.put("productCategoryList", new ProductCategoryDaoSqlite().getAll());
        params.put("productSupplierList", new SupplierDaoSqlite().getAll());
        return params;
    }
}
