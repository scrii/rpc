package Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JoystickArea  {

    Texture CircleImg, StickImg;
    public Circle CircleBounds, StickBounds;
    float Rcircle, Rstick;
    private int pointer = -1;
    float length;
    float dx;
    float dy;
    float Speed;
    Point2D direction;

    public JoystickArea(Texture cimg, Texture simg, float Size) {
        CircleImg = cimg;
        StickImg = simg;
        Rcircle = Size / 2;
        Rstick = Rcircle / 2;
        direction = new Point2D(0, 0);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(CircleImg, CircleBounds.pos.getX() - Rcircle, CircleBounds.pos.getY() - Rcircle, Rcircle * 2, Rcircle * 2);
        batch.draw(StickImg, StickBounds.pos.getX() - Rstick, StickBounds.pos.getY() - Rstick, Rstick * 2, Rstick * 2);
    }

}
