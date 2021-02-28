package Tools;

public class Circle {
    // класс требует изменений
    public Circle(Point2D pos, float R){
        this.pos=new Point2D(pos);
        this.R=R;
    }

    float R;
    public Point2D pos;

    //лежит ли что-то в круге
    public boolean isContains(Point2D point){
        float dx=pos.getX()-point.getX();
        float dy=pos.getY()-point.getY();
        return dx*dx+dy*dy<=R*R;
    }

    //пересекаются ли
    public boolean Overlaps(Circle c){
        float dx=pos.getX()-c.pos.getX();
        float dy =pos.getY()-c.pos.getY();
        float dist=dx*dx+dy*dy;
        float sumR=c.R+R;
        return dist<sumR*sumR;
        //true если пересекаются
    }

    public void add(float x, float y){
        pos.add(x,y);
    }

}
