package model;
import com.codecool.shop.model.BaseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


class BaseModelTest {

    private BaseModel model;

    @BeforeEach
    public void createNewBaseModel() {
        this.model = new BaseModel("test");
    }

    @Test
    public void testGetIdAfterSetId() {
        this.model.setId(1);
        assertEquals(1, this.model.getId());
    }

    @Test
    public void testGetNameAfterSetName() {
        this.model.setName("testName");
        assertEquals("testName", this.model.getName());
    }

    @Test
    public void testGetDescriptionAfterSetDescription() {
        this.model.setDescription("testDescription");
        assertEquals("testDescription", this.model.getDescription());
    }

    @Test
    public void testToStringReturnCorrectString() {
        BaseModel baseModel = new BaseModel(1, "name", "description");
        String expectedString = "id:1,name:name,description:description,";
        BaseModel baseModelWithoutId = new BaseModel("name", "description");
        String expectedStringWithoutId = "id:0,name:name,description:description,";
        assertEquals(expectedString, baseModel.toString());
        assertEquals(expectedStringWithoutId, baseModelWithoutId.toString());
    }

}