import com.codecool.shop.App;

public class Main {

    public static void main(String[] args) {
        App.run();
        App.getApp().dispatchRoutes();
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
