package Transitions;

import Model.Boss;
import Model.CupHead;
import View.GameController;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BossShoot extends Transition {


    public BossShoot() {
        setCycleDuration(Duration.millis(400));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 11);
        ImagePattern imagePattern=new ImagePattern(new Image(getClass().getResource("/images/BossShoot/"+frame+".png").toExternalForm()));
        Boss.getInstance().setFill(imagePattern);
        if(v==1){
            GameController.bossFly.play();
        }
    }
}