import com.codecool.shop.dao.DatabaseConnection;

public class Main {

    public static void main(String[] args) {
        DatabaseConnection dbConn = new DatabaseConnection();
        dbConn.openConnection();
    }
}
