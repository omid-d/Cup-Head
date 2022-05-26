package View;

import Application.App;
import Model.User;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameOverController {

    public VBox vbox;
    public BorderPane border;

    public void initialize(){

            BackgroundImage backgroundImage=new BackgroundImage(new Image(getClass().getResource("/images/menubackground.png").toExternalForm()),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            border.setBackground(new Background(backgroundImage));

        Rectangle bossHealth=new Rectangle();
        Text text=new Text(360,470,"Finished: ");
        Text text1=new Text(600,520,GameController.score.getText());
        text1.getStyleClass().add("score");
        Text text2=new Text(600,560,GameController.timer.getText());
        text2.getStyleClass().add("score");
        text.getStyleClass().add("score");
        bossHealth.setWidth(400);
        bossHealth.setHeight(30);
        bossHealth.setX(450);
        bossHealth.setY(450);
        GameController.bossRemained.setX(450);
        GameController.bossRemained.setY(450);
        bossHealth.setOpacity(0.4);
        bossHealth.getStyleClass().add("board");
        border.getChildren().add(bossHealth);
        GameController.bossRemained.getStyleClass().add("board");
        border.getChildren().add(GameController.bossRemained);
        border.getChildren().add(text);
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
