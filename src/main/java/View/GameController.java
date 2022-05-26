package View;

import Model.*;
import Transitions.BossFly;
import Transitions.BulletMovement;
import Transitions.Loop;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameController {

    public static MediaPlayer mediaPlayer;
    public  static Text health=new Text();
    public  static Text bossHealthNum=new Text();
    public static Text score;
    public static Text timer=new Text(600,60,"Timer:  0 : 0");
    public static Rectangle cupHeadHealth=new Rectangle();
    public static Rectangle bossHealth=new Rectangle();
    public static Rectangle bossRemained=new Rectangle();
    public static ArrayList<Egg> eggs=new ArrayList<>();
    public Pane pane;
    public static BossFly bossFly;
    public static ArrayList<Bullet> bullets=new ArrayList<>();
    public static ArrayList<MiniBoss> miniBosses=new ArrayList<>();
    public void initialize() {
        bullets=new ArrayList<>();
        eggs=new ArrayList<>();
        miniBosses=new ArrayList<>();
        User.getUserLogged().setScore(0);
        LoginPageController.mediaPlayer.setAutoPlay(false);
        LoginPageController.mediaPlayer.stop();
        Loop loop=new Loop(System.currentTimeMillis(),pane);
        loop.play();
        Boss boss=Boss.getInstance();
        CupHead cupHead=createCupHead();
        pane.getChildren().add(cupHead);
        pane.getChildren().add(boss);
        bossFly=new BossFly();
        bossFly.play();
        Platform.runLater(()->cupHead.requestFocus());
        BackgroundImage backgroundImage=new BackgroundImage(new Image(getClass().getResource("/images/background.png").toExternalForm()),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(backgroundImage));
        Media media=new Media(getClass().getResource("/music/theme.mp3").toExternalForm());
        mediaPlayer=new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        cupHeadHealth=new Rectangle();
        cupHeadHealth.setX(80);
        cupHeadHealth.setY(40);
        cupHeadHealth.setHeight(30);
        cupHeadHealth.setWidth(cupHead.health*15);
        health.setText(Integer.toString(cupHead.health));
        health.setX(250);
        health.setY(60);
        health.setStyle("health");
        cupHeadHealth.getStyleClass().add("board");
        bossHealth.setX(850);
        bossHealth.setY(40);
        bossHealth.setHeight(30);
        bossHealth.setWidth(boss.health*15);
        bossHealth.getStyleClass().add("boardBoss");
        bossHealthNum.setText(Integer.toString(boss.health));
        bossHealthNum.setX(1180);
        bossHealthNum.setY(60);
        bossHealthNum.setStyle("health");
        pane.getChildren().add(bossHealthNum);
        pane.getChildren().add(health);
        pane.getChildren().add(cupHeadHealth);
        pane.getChildren().add(bossHealth);
        score=new Text(450,60,"Score: "+Integer.toString(User.getUserLogged().getScore()));
        pane.getChildren().add(score);
        pane.getChildren().add(timer);
    }

    private CupHead createCupHead() {
        CupHead cupHead = CupHead.getInstance();
        cupHead.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                switch (keyName) {
                    case "Left":
                        cupHead.moveLeft();
                        break;
                    case "Right":
                        cupHead.moveRight();
                        break;
                    case "Up":
                        cupHead.moveUp();
                        break;
                    case "Down":
                        cupHead.moveDown();
                        break;
                }
                if(keyName.equals("Space")){
                    Media media=new Media(getClass().getResource("/music/shot.mp3").toExternalForm());
                    MediaPlayer mediaPlayer1=new MediaPlayer(media);
                    mediaPlayer1.setAutoPlay(true);
                    CupHead.getInstance().shoot(pane);
                    Bullet bullet=new Bullet(cupHead);
                    pane.getChildren().add(bullet);
                    bullets.add(bullet);
                    BulletMovement bulletMovement=new BulletMovement(bullet,pane);
                    bulletMovement.play();
                }
            }
        });
        return cupHead;
    }




}
