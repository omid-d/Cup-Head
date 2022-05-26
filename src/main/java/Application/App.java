package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {
    private static Stage stage;
    private static Scene scene;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        App.setStage(stage);
        Parent root = loadFXML("loginPage");
        Scene scene = new Scene(root);
        App.scene = scene;
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void changeMenu(String name){
        Parent root = loadFXML(name);
        App.scene.setRoot(root);
    }
    private static Parent loadFXML(String name){
        try {
            URL address = new URL(App.class.getResource("/fxml/" + name + ".fxml").toString());
            return FXMLLoader.load(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Stage getStage () {
        return stage;
    }

    public static void setStage (Stage stage) {
        App.stage = stage;
    }
}
