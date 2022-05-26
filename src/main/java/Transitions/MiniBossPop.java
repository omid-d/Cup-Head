package Transitions;

import Model.CupHead;
import Model.MiniBoss;
import View.GameController;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MiniBossPop extends Transition {
    public Pane pane;
    public MiniBoss miniBoss;
    public MiniBossPop(Pane pane,MiniBoss miniBoss) {
        this.miniBoss=miniBoss;
        this.pane=pane;
        setCycleDuration(Duration.millis(100));
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 4);
        ImagePattern imagePattern=new ImagePattern(new Image(getClass().getResource("/images/MiniBossPop/"+frame+".png").toExternalForm()));
        miniBoss.setFill(imagePattern);
        if(v==1){
            GameController.miniBosses.remove(miniBoss);
            pane.getChildren().remove(miniBoss);
        }
    }
}
