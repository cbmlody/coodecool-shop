package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CartController {
    public static ModelAndView addToBasket(
            Request req, Response res) throws SQLException {
        String id = req.queryParams("productid");
        Integer productid = Integer.parseInt(id);
        Integer quantity = Integer.parseInt(req.queryParams("quantity"));
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        Product product = productDaoSqlite.find(productid);
        if  (req.session().attribute("basket") == null){
            Cart cart = new Cart();
            cart.add(product,quantity);
            req.session().attribute("basket",cart);
        }
        else {
            Cart cart = req.session().attribute("basket");
            cart.add(product,quantity);
        }
        res.redirect("/");
        return null;
    }
    public static ModelAndView renderCart(Request req, Response res) throws SQLException {
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
        params.put("basket",cart);
        return new ModelAndView(params, "product/cart");
    }

    public static ModelAndView removeProduct(Request req, Response res) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        Integer productId = Integer.parseInt(req.params(":productid"));
        Cart cart = req.session().attribute("basket");
        cart.remove(cart.getIndexIfExists(productId));
        res.redirect("/cart");
        return null;
    }
}
