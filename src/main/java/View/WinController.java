package View;

import Application.App;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class WinController {

    public BorderPane border;

    public void initialize(){

            BackgroundImage backgroundImage=new BackgroundImage(new Image(getClass().getResource("/images/menubackground.png").toExternalForm()),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            border.setBackground(new Background(backgroundImage));

        Text text1=new Text(600,450,GameController.score.getText());
        text1.getStyleClass().add("score");
        Text text2=new Text(600,490,GameController.timer.getText());
        text2.getStyleClass().add("score");
        border.getChildren().add(text1);
        border.getChildren().add(text2);
    }
    public void restart(){
        App.changeMenu("game");
    }

    public void goToMainMenu(){
        App.changeMenu("mainmenuPage");
    }
}
