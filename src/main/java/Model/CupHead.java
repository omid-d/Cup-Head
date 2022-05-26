package Model;

import Transitions.Shooting;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class CupHead extends Rectangle {
    private static CupHead instance;
    public int health=10;
    public boolean isBlink=false;

    public static CupHead getInstance() {
        if (instance == null)
            instance = new CupHead();
        return instance;
    }
    private CupHead() {
        super(275, 320, 110, 90);
        ImagePattern redCup=new ImagePattern(new Image(getClass().getResource("/images/red.png").toExternalForm()));
        this.setFill(redCup);
    }

    public void newCupHead(){
        instance=null;
    }

    public void shoot(Pane pane){
        Shooting shooting=new Shooting(pane);
        shooting.play();
    }

    public void moveRight() {
        if (!hitRightWall())
            this.setX(this.getX() + 10);
    }

    public void moveLeft() {
        if (!hitLeftWall())
            this.setX(this.getX() - 10);
    }

    public void moveUp() {
        if (!hitUpperWall())
            this.setY(this.getY() - 10);
    }

    public void moveDown() {
        if (!hitDownerWall())
            this.setY(this.getY() + 10);
    }

    public boolean hitRightWall() {
        return this.getX() + this.getWidth() >= 1280;
    }

    public boolean hitLeftWall() {
        return this.getX() <= 0;
    }

    public boolean hitDownerWall() {
        return this.getY() + this.getHeight() >= 720;
    }

    public boolean hitUpperWall() {
        return this.getY() <= 75;
    }
}
