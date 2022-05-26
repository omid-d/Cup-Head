package Transitions;

import Model.CupHead;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Shooting extends Transition {
    public Rectangle shot=new Rectangle();
    public Pane pane;
    public Shooting(Pane pane) {
        this.pane=pane;
        pane.getChildren().add(shot);
        setCycleDuration(Duration.millis(150));
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 3);
        shot.setX(CupHead.getInstance().getX()+85);
        shot.setY(CupHead.getInstance().getY()+25);
        shot.setWidth(68);
        shot.setHeight(54);
        ImagePattern imagePattern=new ImagePattern(new Image(getClass().getResource("/images/CupheadShoot/"+frame+".png").toExternalForm()));
        shot.setFill(imagePattern);
        if(v==1){
            pane.getChildren().remove(shot);
        }
    }
}
