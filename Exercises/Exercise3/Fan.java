package Exercise3;

public class Fan 
{
	private int speed;
	private boolean on;
	private double radius;
	private String color;
	
	public Fan(){
		speed = 1; on=false; radius=5; color="Blue";
	}
	
	public int getSpeed() {return speed;}
	public double getRadius() {return radius;}
	public String getColor() {return color;}
	
	public void setSpeed(int sp) { if (sp >= 1 && sp <=3) speed = sp;}
	public void setRadius(double rd) {radius = rd;}
	public void setColor(String ss) {color = ss;}
	
	public void turnOn() {on = true;}
	public void turnOff() {on = false;}
	public void increaseSpeed() {
		if (speed < 3)
			speed += 1;
		else
			System.out.println("Fan is already running at highest speed.");
	}
	
	public void decreaseSpeed() {
		if (speed > 1)
			speed -= 1;
		else
			System.out.println("Fan is already running at lowest speed.");
	}
	
	public String toString() {
		String s = "";
		if (on == true){
			s += "Speed:";
			if (speed == 1)
				s+="Slow ";
			else if (speed == 2)
				s+="Medium ";
			else
				s+="Fast ";
		}
		s+="Color:"+color+" Radius:"+radius;
		
		if (on == false)
			s+=" (fan is off)";
		return s;
	}
	public boolean equals(Fan f) {
	    if (f.speed == this.speed && f.radius == this.radius && f.color == this.color)
	    {
            return true;
        }
	    return false;
	}
	public String fastest(Fan f) {
	    Fan returnFan;
        if (this.speed == f.speed)
            returnFan = this;
        else
        {
            if (this.speed > f.speed)
            {
                returnFan = this;
            }
            else
            {
                returnFan = f;
            }


        }
        if (returnFan.speed == 1)
                return "Slow";
            if (returnFan.speed == 2)
                return "Medium";
            if (returnFan.speed == 3)
                return "Fast";
	    return "";
	}
}