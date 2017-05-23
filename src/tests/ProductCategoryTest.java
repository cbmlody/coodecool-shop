import com.codecool.shop.model.ProductCategory;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

/**
 * Created by stonedheart on 22.05.17.
 */

public class ProductCategoryTest {
    ProductCategory productCategoryWithoutId;
    ProductCategory productCategoryWithId;

    @BeforeEach
    public void setUp() {
        this.productCategoryWithoutId = new ProductCategory("name", "department", "description");
        this.productCategoryWithId = new ProductCategory(1,"name", "department", "description");
    }
    @Test
    public void testProductCategoryConstructorWithoutId() {
        assertEquals(this.productCategoryWithoutId, new ProductCategory("name", "descr", "dprt"));
    }

}
