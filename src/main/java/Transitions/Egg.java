package Transitions;

import Model.Boss;
import Model.Bullet;
import Model.CupHead;
import Model.MiniBoss;
import View.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Egg extends Transition {
    private Model.Egg egg;
    private Pane pane;

    private double speed = 3;

    public Egg(Model.Egg egg, Pane pane) {
        this.egg = egg;
        this.pane=pane;
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double dx = speed;
        egg.move(dx);
        if(egg.hitLeftWall()){
            GameController.eggs.remove(egg);
            pane.getChildren().remove(egg);
            this.stop();
        }
        if(CupHead.getInstance().getBoundsInParent().intersects(egg.getLayoutBounds())){
            GameController.eggs.remove(egg);
            pane.getChildren().remove(egg);
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
            this.stop();
        }
    }
}
