package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.views.MenuView;
import com.codecool.shop.views.ProductCategoryView;
import com.codecool.shop.views.ProductView;
import com.codecool.shop.views.SupplierView;

public class ProductMenuController extends BaseMenuController {
    private ProductCategoryDaoSqlite categoryDao = new ProductCategoryDaoSqlite();
    private SupplierDaoSqlite supplierDao = new SupplierDaoSqlite();
    private ProductCategoryView categoryView = new ProductCategoryView();
    private ProductView productView = new ProductView();
    private SupplierView supplierView = new SupplierView();

    public void runMenu(){
        Boolean quitProductMenu = false;
        while (!quitProductMenu) {
            MenuView.displayProducts();
            Integer choice = getUserChoice();
            if (choice == 0) {
                quitProductMenu = true;
            } else if(choice == 1){
                ProductController.getInstance().getAllProducts();
            } else if(choice == 2){
                showProductsByCategory();
            } else if(choice == 3) {
                showProductsBySupplier();
            }
            if (choice > 0 && choice < 4) {
                backOrAddToBasket();
            }
        }
    }

    private void showProductsByCategory(){
        ProductCategoryController.getInstance().getAllProductCategories();

        Integer categoryChoice = getUserChoice("Please enter product category id to see products");
        ProductCategory chosenCat = categoryDao.find(categoryChoice);
        while (chosenCat == null){
            categoryChoice = getUserChoice("Error, incorrect category id, please try again");
            chosenCat = categoryDao.find(categoryChoice);
        }
        MenuView.flashMessage("\nDisplaying products from category:");
        categoryView.showTableHead();
        categoryView.displayOne(chosenCat);
        MenuView.flashMessage("\nPRODUCTS:");
        ProductController.getInstance().getByProductCategory(chosenCat);
    }

    private void showProductsBySupplier(){
        SupplierController.getInstance().getAllSuppliers();
        Integer supplierChoice = getUserChoice("Please enter product supplier id to see products");
        Supplier chosenSup = supplierDao.find(supplierChoice);
        while (chosenSup == null){
            supplierChoice = getUserChoice("Error, incorrect supplier id, please try again");
            chosenSup = supplierDao.find(supplierChoice);
        }
        MenuView.flashMessage("\nDisplaying products from supplier:");
        supplierView.showTableHead();
        supplierView.displayOne(chosenSup);
        MenuView.flashMessage("\nPRODUCTS:");
        ProductController.getInstance().getBySupplier(chosenSup);
    }

}
