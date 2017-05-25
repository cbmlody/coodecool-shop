package com.codecool.shop.model;

import com.codecool.shop.utils.CurrencyCalculator;
import java.util.Currency;

public class Product extends BaseModel {
    private static CurrencyCalculator currencyCalculator = new CurrencyCalculator(Currency.getInstance("PLN"));
    private float defaultPrice;
    private Currency defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;

    public Product(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, description);
        this.defaultPrice = defaultPrice;
        this.defaultCurrency = Currency.getInstance(currencyString);
        this.productCategory = productCategory;
        this.supplier = supplier;
    }

    public Product(int id, String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(id, name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);

    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) throws IllegalArgumentException {
        if(defaultPrice < 0){
            throw new IllegalArgumentException("Default price must be above 0.");
        } else {
            this.defaultPrice = defaultPrice;
        }
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(float price, String currency) throws IllegalArgumentException {
        if(price < 0){
            throw new IllegalArgumentException("Price must be above 0.");
        } else {
            this.defaultPrice = price;
            this.defaultCurrency = Currency.getInstance(currency);
        }
    }

    public float getConvertedFloatPrice(){
        return currencyCalculator.calculatePrice(this.defaultCurrency, this.defaultPrice);
    }

    public static Currency getBaseCurrency(){
        return currencyCalculator.getBaseCurrency();
    }

    public static void changeBaseCurrency(Currency currency){
        currencyCalculator.setCurrencyData(currency);
    }


    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Product product = (Product) obj;
        return id == product.id;
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.productCategory.getName(),
                this.supplier.getName());
    }
}
