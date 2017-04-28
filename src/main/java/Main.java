import com.codecool.shop.controller.MainMenuController;
import com.codecool.shop.dao.DatabaseConnection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            DatabaseConnection.getInstance().resetDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            DatabaseConnection.getInstance().closeConnection();
        }
        new MainMenuController().runMenu();
    }
}
