package Exercise4;

public abstract class Vehicle {
    private String color;
    private double topSpeed;

    public Vehicle() { }
    
    protected Vehicle (String color, double topSpeed){
        this.color = color;
        this.topSpeed = topSpeed;
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public double getTopSpeed(){
        return this.topSpeed;
    }

    public void setTopSpeed(double topSpeed){
        this.topSpeed = topSpeed;
    }

    public abstract String toString();
}
