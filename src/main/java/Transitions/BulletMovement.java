package Transitions;

import Model.Boss;
import Model.Bullet;
import Model.CupHead;
import Model.MiniBoss;
import View.GameController;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BulletMovement extends Transition {
    private Bullet bullet;
    private Pane pane;

    private double speed = 3;

    public BulletMovement(Bullet bullet, Pane pane) {
        this.bullet = bullet;
        this.pane=pane;
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double dx = speed;
        bullet.move(dx);
        if(bullet.hitRightWall()){
            BulletMovement.this.stop();
            GameController.bullets.remove(bullet);
            pane.getChildren().remove(bullet);
        }
        for (MiniBoss miniBoss : GameController.miniBosses) {
            if(miniBoss.getBoundsInParent().intersects(bullet.getLayoutBounds())){
                miniBoss.health--;
                GameController.bullets.remove(bullet);
                pane.getChildren().remove(bullet);
                this.stop();
            }
        }
        if(Boss.getInstance().getBoundsInParent().intersects(bullet.getLayoutBounds())){
            Boss.getInstance().health--;
            GameController.bossHealth.setWidth(Boss.getInstance().health * 15);
            GameController.bossHealthNum.setText(Integer.toString(Boss.getInstance().health));
            GameController.bossHealthNum.setX(Boss.getInstance().health*15 + 880);
            GameController.bullets.remove(bullet);
            pane.getChildren().remove(bullet);
            this.stop();
        }
    }
}