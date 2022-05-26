package Transitions;

import Model.Bullet;
import Model.CupHead;
import Model.User;
import View.GameController;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class MiniBoss extends Transition {
    private Model.MiniBoss miniBoss;
    private Pane pane;

    private double speed = 2;

    public MiniBoss(Model.MiniBoss miniBoss, Pane pane) {
        this.miniBoss=miniBoss;
        this.pane=pane;
        this.setCycleDuration(Duration.millis(300));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        miniBoss.move(speed);
        if(miniBoss.hitLeftWall()){
            MiniBoss.this.stop();
            GameController.miniBosses.remove(miniBoss);
            pane.getChildren().remove(miniBoss);
        }
        if(miniBoss.health<=0){
            Media media=new Media(getClass().getResource("/music/pop.mp3").toExternalForm());
            MediaPlayer mediaPlayer1=new MediaPlayer(media);
            mediaPlayer1.setAutoPlay(true);
            MiniBossPop miniBossPop=new MiniBossPop(pane,miniBoss);
            miniBossPop.play();
            User.getUserLogged().setScore(User.getUserLogged().getScore()+10);
            GameController.score.setText("Score: "+Integer.toString(User.getUserLogged().getScore()));
            this.stop();
        }
        if(miniBoss.getBoundsInParent().intersects(CupHead.getInstance().getLayoutBounds())){
            Media media=new Media(getClass().getResource("/music/pop.mp3").toExternalForm());
            MediaPlayer mediaPlayer1=new MediaPlayer(media);
            mediaPlayer1.setAutoPlay(true);
            Media media1=new Media(getClass().getResource("/music/crash.wav").toExternalForm());
            MediaPlayer mediaPlayer=new MediaPlayer(media1);
            mediaPlayer.setAutoPlay(true);
            User.getUserLogged().setScore(User.getUserLogged().getScore()+10);
            GameController.score.setText("Score: "+Integer.toString(User.getUserLogged().getScore()));
            MiniBossPop miniBossPop=new MiniBossPop(pane,miniBoss);
            miniBossPop.play();
            if(!CupHead.getInstance().isBlink) {
                CupHead.getInstance().health--;
                GameController.cupHeadHealth.setWidth(CupHead.getInstance().health * 15);
                GameController.health.setX(CupHead.getInstance().health*15 + 100);
                GameController.health.setText(Integer.toString(CupHead.getInstance().health));
                CupHead.getInstance().setOpacity(0.5);
                CupHead.getInstance().isBlink = true;
            }
            this.stop();
        }
        int frame = (int) Math.floor(v * 3);
        ImagePattern imagePattern=new ImagePattern(new Image(getClass().getResource("/images/MiniBossFly/"+frame+".png").toExternalForm()));
        miniBoss.setFill(imagePattern);
    }
}