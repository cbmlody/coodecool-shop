package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView getAllProducts(Request req, Response res) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        Map params = new HashMap<>();
        params.put("productList", productDaoSqlite.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView getProductsBySearch(Request req, Response res) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        Map params = new HashMap<>();
        String productSearch = req.queryParams("search");
        if (productSearch.length() >= 3 && !productDaoSqlite.getBy(productSearch).isEmpty()) {
            params.put("productList", productDaoSqlite.getBy(productSearch));
        } else if (productSearch.length() == 0) {
            params.put("productList", productDaoSqlite.getAll());
        } else {
            params.put("info", "Products not found...");
        }
        params.put("productCategoryList", new ProductCategoryDaoSqlite().getAll());
        params.put("productSupplierList", new SupplierDaoSqlite().getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderAddForm(Request req, Response res) throws SQLException {
        Map params = new HashMap<>();
        params.put("productList", new ProductDaoSqlite().getAll());
        params.put("productCategoryList", new ProductCategoryDaoSqlite().getAll());
        params.put("productSupplierList", new SupplierDaoSqlite().getAll());
        return new ModelAndView(params, "product/add");
    }

    public static ModelAndView addProduct(Request req, Response res) throws SQLException {
        String name = req.queryParams("name");
        String price = req.queryParams("defaultprice");
        String description = req.queryParams("description");
        String category = req.queryParams("chooseCategory");
        String supplier = req.queryParams("chooseSupplier");
        if (name.isEmpty() || name.charAt(0) == ' ' || description.isEmpty() || description.charAt(0) == ' ' ||
                supplier.isEmpty() || category.isEmpty() || price.isEmpty() || price.charAt(0) == ' ') {
            Map params = new HashMap<>();
            String error = "Inputs can't be empty!";
            params.put("error", error);
            params.put("productList", new ProductDaoSqlite().getAll());
            params.put("productCategoryList", new ProductCategoryDaoSqlite().getAll());
            params.put("productSupplierList", new SupplierDaoSqlite().getAll());
            return new ModelAndView(params, "product/add");
        } else {
            Integer catId = Integer.parseInt(category);
            Integer supId = Integer.parseInt(supplier);
            float pricee = Float.parseFloat(price);
            new ProductDaoSqlite().add(new Product(name, pricee, "PLN", description,
                    new ProductCategoryDaoSqlite().find(catId), new SupplierDaoSqlite().find(supId)));
            res.redirect("/");
            return null;
        }
    }

    public static ModelAndView getBySupplier(Request req, Response res) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        SupplierDaoSqlite supplierDaoSqlite = new SupplierDaoSqlite();
        Integer supplierId = Integer.parseInt(req.params(":supplierid"));
        Map params = new HashMap<>();
        params.put("productsBySupplier", productDaoSqlite.getBy(supplierDaoSqlite.find(supplierId)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView getByProductCategory(Request req, Response res) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite();
        Integer categoryId = Integer.parseInt(req.params(":categoryid"));
        Map params = new HashMap<>();
        params.put("productsByCategory", productDaoSqlite.getBy(productCategoryDaoSqlite.find(categoryId)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView getProductsByName(Request req, Response res) throws SQLException {
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();
        String name = req.queryParams("productName");
        Map params = new HashMap<>();
        params.put("productsByName", productDaoSqlite.getBy(name));
        return new ModelAndView(params, "product/index");
    }
}
