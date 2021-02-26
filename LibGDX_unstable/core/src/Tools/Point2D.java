package Tools;

public class Point2D {
    private float x,y;

    public Point2D(Point2D p){
        x=p.getX();
        y=p.getY();
    }

    public Point2D(float x, float y){
        this.x=x;
        this.y=y;
    }

    public void add(float x, float y){
        this.x+=x;
        this.y+=y;
    }


    public float getX() {return x;}
    public float getY() {return y;}

    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}

    public void setPoint(Point2D point){
        x=point.getX();
        y=point.getY();
    }

    public void setPoint(float x,float y){
        this.x=x;
        this.y=y;
    }
}
