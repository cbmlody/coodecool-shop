import com.codecool.shop.utils.FileReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    private FileReader fileReader = new FileReader();

    @Test
    public void testGetStringFromFileReturnsCorrectString() {
        String result = fileReader.getStringFromFile("/test_text.txt");
        String expectedText = "Test Test Test;" + "test, test, test;";
        assertEquals(expectedText, result);
    }

    @Test
    public void testGetStringFromFileReturnsEmptyString() {
        String result = fileReader.getStringFromFile("/wrong_name.txt");
        assertEquals("", result);
    }
}