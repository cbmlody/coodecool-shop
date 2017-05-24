import com.codecool.shop.App;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            App.run("jdbc:sqlite:src/main/database.db");
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
            App.getApp().closeConnection();
            try {
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

    }
}
