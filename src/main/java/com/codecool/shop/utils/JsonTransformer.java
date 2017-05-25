package com.codecool.shop.utils;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import spark.ResponseTransformer;

import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class JsonTransformer implements ResponseTransformer {
    private Gson gson = new GsonBuilder().registerTypeAdapter(Product.class, new ProductAdapter()).create();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

//    public JsonObject getJsonObject(String line){
//        return gson.fromJson(line, JsonObject.class);
//    }

//    public <T> List<T> parseToList(String obj, Class<T> classe){
//        return  gson.fromJson(obj, new TypeToken<List<T>>(){}.getType());
//    }

    private class ProductAdapter extends TypeAdapter<Product>{
        public Product read(JsonReader in) throws IOException {
            return null;
        }

        public void write(JsonWriter out, Product product) throws IOException {
            if (product == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            out.name("id").value(product.getId());
            out.name("name").value(product.getName());
            out.name("currency").value(product.getBaseCurrency().toString());
            out.name("price").value(product.getConvertedFloatPrice());
            out.name("description").value(product.getDescription());
            out.name("supplierName").value(product.getSupplier().getName());
            out.name("categoryName").value(product.getProductCategory().getName());
            out.endObject();
        }
    }
}