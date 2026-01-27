package Exercise3;

public class MyPoint {
    private double x;
    private double y;
    private String locationName;

    public MyPoint(double x, double y, String locationName){
        this.x = x;
        this.y = y;
        this.locationName = locationName;
    }

    public double getX() { return this.x; }

    public double getY() { return this.y; }

    public String toString() { return this.locationName + "(" + this.x + "," + this.y + ")"; }

    public double distance(MyPoint point){
        double distance = 0;

        distance = Math.sqrt(Math.pow(point.getY() - this.x,2) + Math.pow(point.y-this.y,2));

        return distance;
    }
}
