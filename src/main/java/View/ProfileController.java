package View;

import Application.App;
import Model.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ProfileController {
    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Button changeUsername;
    @FXML Button changePassword;
    @FXML VBox vbox;
    @FXML
    private VBox vbox1;
    private HBox hbox2;
    Controller.ProfileController profileController= Controller.ProfileController.getInstance();
    public BorderPane border;

    public void initialize(){
        hbox2=new HBox();
        BackgroundImage backgroundImage=new BackgroundImage(new Image(getClass().getResource("/images/menubackground.png").toExternalForm()),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        border.setBackground(new Background(backgroundImage));
        Circle circle1=new Circle(300,300,50);
        ImagePattern image=new ImagePattern(new Image(User.getUserLogged().getAvatar()));
        circle1.setFill(image);
        border.getChildren().add(circle1);
        Text text2 = new Text("choose new avatar:  ");
        text2.setFill(Color.rgb(10,100,50));
        text2.getStyleClass().add("text2");
        hbox2.getChildren().add(text2);
        for(int i=0;i<6;i++){
            Circle circle=new Circle(20);
            ImagePattern imagePattern1=new ImagePattern(new Image(getClass().getResource("/images/avatars/"+i+".png").toExternalForm()));
            circle.setFill(imagePattern1);
            hbox2.getChildren().add(circle);
            int finalI = i;
            circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent mouseEvent) {
                    circle1.setFill(imagePattern1);
                    User.getUserLogged().setAvatar(getClass().getResource("/images/avatars/"+ finalI +".png").toExternalForm());
                    User.updateUsersInfo();
                }
            });
        }
        Button button=new Button();
        button.setText("choose");
        button.getStyleClass().add("button1");
        hbox2.getChildren().add(button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent mouseEvent) {
                File file=new FileChooser().showOpenDialog(App.getStage());
                try {
                    User.getUserLogged().setAvatar(file.toURI().toURL().toExternalForm());
                    ImagePattern imagePattern1=new ImagePattern(new Image(User.getUserLogged().getAvatar()));
                    circle1.setFill(imagePattern1);
                    User.updateUsersInfo();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
        vbox1.getChildren().add(hbox2);
    }
    public void type(KeyEvent keyEvent) {
        int strength = password.getText().length();
        if (strength < 5){
            password.setStyle("-fx-border-color: #ff0066;");
            changePassword.setDisable(true);
        }else{
            password.setStyle("-fx-border-width: 0");
            changePassword.setDisable(false);
        }
    }

    public void goBack() throws Exception {
        App.changeMenu("mainmenuPage");
    }

    public void deleteAccount() throws Exception {
        User.getUsers().remove(User.getUserLogged());
        User.setUserLogged(null);
        User.updateUsersInfo();
        App.changeMenu("loginPage");
    }

    public void exitAccount() throws Exception{
        User.setUserLogged(null);
        App.changeMenu("loginPage");
    }

    public void changeUsername(){
        String output=profileController.changeUsername(username.getText());
        if(output.equals("username changed successfully")){
            Text text=new Text();
            text.setText(output);
            text.setFill(Color.GREEN);
            vbox.getChildren().add(text);
        }else {
            Text text=new Text();
            text.setText(output);
            text.setFill(Color.RED);
            vbox.getChildren().add(text);
        }
    }

    public void changePassword(){
        String output=profileController.changePassword(password.getText());
            Text text=new Text();
            text.setText(output);
            text.setFill(Color.GREEN);
            vbox.getChildren().add(text);
    }
}
