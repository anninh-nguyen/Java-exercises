package Exercise2;

public class Fan {
    private int speed = 1;
    
    private boolean on = false;
    
    private double radius = 5;

    private String color = "Blue";

    public int getSpeed ()
    {
        return speed;
    }

    public void setSpeed (int Speed)
    {
        speed = Speed;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double Radius)
    {
        radius = Radius;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String Color)
    {
        color = Color;
    }

    public Fan ()
    {
        speed = 1;

        on = false;
    
        radius = 5;
    
        color = "Blue";
    }

    public void turnOn()
    {
        on = true;
    }

    public void turnOff()
    {
        on = false;
    }

    public void increaseSpeed()
    {
        if (speed < 3)
        {
            speed ++;
        }
        else
        { 
            System.out.println("Fan is already running at highest speed.");
        }
    }

    public void decreaseSpeed()
    {
        if (speed > 1)
        {
            speed --;
        }
        else
        { 
            System.out.println("Fan is already running at lowest speed.");
        }
    }

    public String toString()
    {
        if (on)
        {
            String text = "";
            if (speed == 1)
            {
                text = "Slow";
            }
            else if (speed == 2)
            {
                text = "Medium";
            }
            else
            {
                text = "Fast";
            }

            return "Speed:" + text + " Color:" + color + " Radius:" + radius;
        }
        else
        {
            return "Color:" + color + " Radius:" + radius + " (fan is off)";
        }

    }
}
