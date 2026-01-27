package Exercise4;

public class Bicycle extends Vehicle {
    private int gears;

    public Bicycle (int gears){
        this.gears = gears;
    }

    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        this.gears = gears;
    }

    public String toString() {
        return "Color:" + super.getColor() + "\n" +
                "Top speed:" + super.getTopSpeed() + "\n" + 
                "Gears:3" + this.gears;
    }
}
