import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.utils.JsonTransformer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by mateuszkaczmarczyk on 25/05/2017.
 */
class JsonTransformerTest {

    @Test
    void testsRenderReturnsJSONObject() {
        ProductCategory testProductCategory = new ProductCategory(1, "CategoryName", "DepartmentName", "DepartmentDesc");
        Supplier testSupplier = new Supplier(1, "SupplierName", "SupplierDesc");
        Product testProduct = new Product(1, "ProductName", 0f, "PLN","ProductDesc", testProductCategory, testSupplier);
        JsonTransformer jsonTransformer = new JsonTransformer();
        String jsonString = jsonTransformer.render(testProduct);
        String expectedJsonString = "{\"id\":1,\"name\":\"ProductName\",\"currency\":\"PLN\",\"price\":0.0,\"description\":\"ProductDesc\",\"supplierName\":\"SupplierName\",\"categoryName\":\"CategoryName\"}";
        assertEquals(expectedJsonString, jsonString);
    }
}