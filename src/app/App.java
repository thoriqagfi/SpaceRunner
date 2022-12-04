package app;
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;

public class App extends Application{
    public void start(Stage stage) {
        try{
            ViewManager manager = new ViewManager();
            stage = manager.getMainStage();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}