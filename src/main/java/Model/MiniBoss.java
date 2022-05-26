package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class MiniBoss extends Rectangle {

    public int health=2;

    public MiniBoss(){
        super(1280, new Random().nextInt(70,650),159/2,109/2);
        ImagePattern miniYellow=new ImagePattern(new Image(getClass().getResource("/images/MiniBossFly/1.png").toExternalForm()));
        this.setFill(miniYellow);
    }

    public void move(double dx) {
        setX(getX() - dx);
    }

    public boolean hitLeftWall() {
        return this.getX()  <= 0;
    }
}
