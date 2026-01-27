package Exercise4;

public class Helicopter extends Vehicle implements hasEngine {

    private int rotors;

    public Helicopter (String color, double topSpeed, int rotors){
        super(color, topSpeed);
        this.rotors = rotors;
    }

    @Override
    public String getEngineType() {
        return "Solar";
    }

    @Override
    public String toString() {
        return "Color:" + super.getColor() + "\n" + 
                "Top speed:" + super.getTopSpeed() + "\n" +
                "Rotors:" + this.rotors+  "\n" +
                "Engine:Solar";
    }
}
