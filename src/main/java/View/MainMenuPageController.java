package View;

import Application.App;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class MainMenuPageController {

    public BorderPane border;

    public void initialize(){
        BackgroundImage backgroundImage=new BackgroundImage(new Image(getClass().getResource("/images/menubackground.png").toExternalForm()),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        border.setBackground(new Background(backgroundImage));
    }
    public void goToProfileMenu() throws Exception {
        App.changeMenu("profilemenuPage");
    }

    public void newGame() throws  Exception{
        App.changeMenu("game");
    }

    public void scoreBoard(){
        App.changeMenu("scoreBoard");
    }


    public void exit(){
        System.exit(0);
    }
}
