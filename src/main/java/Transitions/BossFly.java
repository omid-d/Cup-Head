package Transitions;

import Model.Boss;
import Model.CupHead;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BossFly extends Transition {

    private double speed=2;

    public BossFly() {
        setCycleDuration(Duration.millis(500));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 5);
        ImagePattern imagePattern=new ImagePattern(new Image(getClass().getResource("/images/BossFly/"+frame+".png").toExternalForm()));
        Boss.getInstance().setFill(imagePattern);
        Boss.getInstance().move(speed);
        if(Boss.getInstance().hitUpperWall() || Boss.getInstance().hitDownerWall()) speed *= -1;
    }
}