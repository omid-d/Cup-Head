package Transitions;

import Application.App;
import Model.*;
import Model.Egg;
import Model.MiniBoss;
import View.GameController;
import View.LoginPageController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Loop extends Transition {

    long start=System.currentTimeMillis();
    long startTime;
    long blink;
    long egg=System.currentTimeMillis();
    Pane pane;
    public Loop(long startTime,Pane pane){
        this.pane=pane;
        this.startTime=startTime;
        setCycleCount(-1);
        this.setCycleDuration(Duration.millis(100));
    }

    @Override
    protected void interpolate(double v) {
        GameController.timer.setText("Timer:  "+((System.currentTimeMillis()-start)/60000)+" : "+(((System.currentTimeMillis()-start)%60000)/1000));
        if(((System.currentTimeMillis()-startTime)>10000)){
            startTime=System.currentTimeMillis();
            MiniBoss miniBoss=new MiniBoss();
            MiniBoss miniBoss1=new MiniBoss();
            GameController.miniBosses.add(miniBoss);
            GameController.miniBosses.add(miniBoss1);
            pane.getChildren().add(miniBoss);
            pane.getChildren().add(miniBoss1);
            Transitions.MiniBoss miniBoss2=new Transitions.MiniBoss(miniBoss,pane);
            Transitions.MiniBoss miniBoss3=new Transitions.MiniBoss(miniBoss1,pane);
            miniBoss2.play();
            miniBoss3.play();
        }
        if((System.currentTimeMillis()-egg)>3500){
            egg=System.currentTimeMillis();
            Egg egg=new Egg(Boss.getInstance());
            GameController.eggs.add(egg);
            pane.getChildren().add(egg);
            GameController.bossFly.stop();
            BossShoot bossShoot=new BossShoot();
            bossShoot.play();
            Transitions.Egg egg1=new Transitions.Egg(egg,pane);
            egg1.play();
        }
        if(!CupHead.getInstance().isBlink){
            blink=System.currentTimeMillis();
        }else {
            if(System.currentTimeMillis()-blink>2000){
                CupHead.getInstance().isBlink=false;
                CupHead.getInstance().setOpacity(1);
            }
        }
        if(CupHead.getInstance().getBoundsInParent().intersects(Boss.getInstance().getLayoutBounds())){
            if(!CupHead.getInstance().isBlink) {
                Media media=new Media(getClass().getResource("/music/crash.wav").toExternalForm());
                MediaPlayer mediaPlayer=new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                CupHead.getInstance().health--;
                GameController.cupHeadHealth.setWidth(CupHead.getInstance().health * 15);
                GameController.health.setX(CupHead.getInstance().health*15 + 100);
                GameController.health.setText(Integer.toString(CupHead.getInstance().health));
                CupHead.getInstance().setOpacity(0.5);
                CupHead.getInstance().isBlink = true;
            }
        }
        if(CupHead.getInstance().health<=0){
            if(User.getUserLogged().getScore()>User.getUserLogged().getHighScore()){
                User.getUserLogged().setHighScore(User.getUserLogged().getScore());
            }
            User.updateUsersInfo();
            GameController.mediaPlayer.setAutoPlay(false);
            GameController.mediaPlayer.stop();
            LoginPageController.mediaPlayer.play();
            GameController.bossRemained.setWidth((20-Boss.getInstance().health)*20);
            GameController.bossRemained.setHeight(30);
            GameController.bossFly.stop();
            CupHead.getInstance().newCupHead();
            Boss.getInstance().newBoss();
            this.stop();
            App.changeMenu("gameoverPage");
        }
        if(CupHead.getInstance().health<=3){
            GameController.cupHeadHealth.getStyleClass().add("board1");
        }
        if(Boss.getInstance().health<=0){
            User.getUserLogged().setScore(User.getUserLogged().getScore()+200);
            User.getUserLogged().setScore(User.getUserLogged().getScore()+5*CupHead.getInstance().health);
            if(User.getUserLogged().getScore()>User.getUserLogged().getHighScore()){
                User.getUserLogged().setHighScore(User.getUserLogged().getScore());
            }
            GameController.score.setText("Score: "+Integer.toString(User.getUserLogged().getScore()));
            User.updateUsersInfo();
            GameController.mediaPlayer.setAutoPlay(false);
            GameController.mediaPlayer.stop();
            LoginPageController.mediaPlayer.play();
            GameController.bossFly.stop();
            CupHead.getInstance().newCupHead();
            Boss.getInstance().newBoss();
            GameController.cupHeadHealth.getStyleClass().add("board");
            this.stop();
            App.changeMenu("winPage");
        }
    }
}
