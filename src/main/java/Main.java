import com.codecool.shop.dao.DatabaseConnection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DatabaseConnection.getInstance().openConnection();
        try {
            DatabaseConnection.getInstance().resetDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            DatabaseConnection.getInstance().closeConnection();
        }
        DatabaseConnection.getInstance().closeConnection();
    }
}
