package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Egg extends Rectangle {

    public Egg(Boss boss){
        super(boss.getX()-20,boss.getY()+75,136/2,116/2);
        ImagePattern egg=new ImagePattern(new Image(getClass().getResource("/images/egg.png").toExternalForm()));
        this.setFill(egg);
    }

    public void move(double dx) {
        setX(getX() - dx);
    }

    public boolean hitLeftWall() {
        return this.getX() <= 0;
    }
}
