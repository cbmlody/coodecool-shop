import com.codecool.shop.dao.DatabaseConnection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DatabaseConnection dbConn = new DatabaseConnection();
        dbConn.openConnection();
        try {
            DatabaseConnection.resetDatabase(dbConn.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            dbConn.closeConnection();
        }
        dbConn.closeConnection();
    }
}
