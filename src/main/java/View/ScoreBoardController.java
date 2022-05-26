package View;

import Application.App;
import Model.User;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class ScoreBoardController {

    public BorderPane border;
    public VBox vbox;
    public void initialize(){

            BackgroundImage backgroundImage=new BackgroundImage(new Image(getClass().getResource("/images/menubackground.png").toExternalForm()),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            border.setBackground(new Background(backgroundImage));

        for(int i=0;i<User.getUsers().size();i++){
            for(int j=i;j<User.getUsers().size();j++){
                if(User.getUsers().get(j).getHighScore()>User.getUsers().get(i).getHighScore()){
                    User user=User.getUsers().get(i);
                    User.getUsers().set(i,User.getUsers().get(j));
                    User.getUsers().set(j,user);
                }
            }
        }
        int min=Math.min(User.getUsers().size(),10);
        for (int i=0;i< min;i++){
            Text text=new Text((i+1)+"- "+User.getUsers().get(i).getUsername()+"    Score: "+User.getUsers().get(i).getHighScore());
            text.getStyleClass().add("score");
            vbox.getChildren().add(text);
        }
    }


    public void goBack(){
        App.changeMenu("mainmenuPage");
    }
}
