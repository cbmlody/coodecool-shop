import com.codecool.shop.App;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            App.run();
            App.getApp().setConnection("jdbc:sqlite:src/main/database.db");
            App.getApp().dispatchRoutes();
        for (String s: args) {
            if (s.equals("--init-db")){
                App.getApp().resetDb();
            } else if(s.equals("--migrate-db")){
                App.getApp().migrateDb();
            }
        }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Closing db connection...");
            try {
                App.getApp().closeConnection();
                mainThread.join();
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }));

    }
}
