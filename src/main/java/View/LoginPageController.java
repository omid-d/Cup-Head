package View;

import Application.App;
import Controller.RegisterController;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;


public class LoginPageController {
    @FXML
    private VBox vbox;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private TextField password;
    @FXML
    private TextField username;

    public BorderPane border;

    public static Text text;

    public RegisterController registerController= RegisterController.getInstance();
    public static MediaPlayer mediaPlayer;

    public void initialize(){
        Media media=new Media(getClass().getResource("/music/menu.mp3").toExternalForm());
        if(mediaPlayer!=null) mediaPlayer.stop();
        mediaPlayer=new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        BackgroundImage backgroundImage=new BackgroundImage(new Image(getClass().getResource("/images/menubackground.png").toExternalForm()),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        border.setBackground(new Background(backgroundImage));
    }

    public LoginPageController(){
        for (User user : User.getUsers()) {
            if(user.getUsername().equals("guest")){
                User.getUsers().remove(user);
                User.updateUsersInfo();
                break;
            }
        }
        User.updateUsersInfo();
    }

    public void register(MouseEvent mouseEvent) {
        String output= registerController.addUser(username.getText(),password.getText());
        if(output.equals("user created successfully!")){
            if(text==null) {
                text = new Text();
                text.setText(output);
                text.setFill(Color.GREEN);
                vbox.getChildren().add(text);
            }else {
                text.setFill(Color.GREEN);
                text.setText(output);
            }
        }else {
            if(text==null) {
                text = new Text();
                text.setText(output);
                text.setFill(Color.RED);
                vbox.getChildren().add(text);
            }else {
                text.setFill(Color.RED);
                text.setText(output);
            }
        }
    }

    public void type(KeyEvent keyEvent) {
        int strength = password.getText().length();
        if (strength < 5){
            password.setStyle("-fx-border-color: #ff0066;");
            registerButton.setDisable(true);
            loginButton.setDisable(true);
        }else{
            password.setStyle("-fx-border-width: 0");
            registerButton.setDisable(false);
            loginButton.setDisable(false);
        }
    }

    public void login (MouseEvent mouseEvent) throws Exception {
        String output= registerController.login(username.getText(),password.getText());
        if(output.equals("user logged in successfully!")){
            App.changeMenu("mainmenuPage");
        }else {
            if(text==null) {
                text = new Text();
                text.setText(output);
                text.setFill(Color.RED);
                vbox.getChildren().add(text);
            }else {
                text.setFill(Color.RED);
                text.setText(output);
            }
        }
    }

    public void loginAsGuest() throws Exception{
        registerController.addUser("guest","guest");
        registerController.login("guest","guest");
        App.changeMenu("mainmenuPage");
    }

}
