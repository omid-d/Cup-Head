package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Boss extends Rectangle {
    private static Boss instance;
    public int health=20;

    public static Boss getInstance() {
        if (instance == null)
            instance = new Boss();
        return instance;
    }
    private Boss() {
        super(950, 250, 260, 204);
        ImagePattern boss=new ImagePattern(new Image(getClass().getResource("/images/BossFly/0.png").toExternalForm()));
        this.setFill(boss);
    }

    public void newBoss(){
        instance=null;
    }

    public void move(double dx) {
            this.setY(this.getY() + dx);
    }

    public boolean hitDownerWall() {
        return this.getY() + this.getHeight() >= 720;
    }

    public boolean hitUpperWall() {
        return this.getY() <= 55;
    }

}
