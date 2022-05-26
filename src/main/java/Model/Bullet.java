package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Bullet extends Rectangle {

    public Bullet(CupHead cupHead){
        super(cupHead.getX()+80,cupHead.getY()+40,75,15);
        ImagePattern bullet=new ImagePattern(new Image(getClass().getResource("/images/bullet.png").toExternalForm()));
        this.setFill(bullet);
    }

    public void move(double dx) {
        setX(getX() + dx);
    }

    public boolean hitRightWall() {
        return this.getX() + this.getWidth() >= 1280;
    }
}
