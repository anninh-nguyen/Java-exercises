package Exercise2;

public class Rectangle {

    private double height;
    private double width;

    public Rectangle ()
    {
        height = 1;
        width = 1;
    }

    public Rectangle(double Height, double Width)
    {
        height = Height;
        width = Width;
    }

    public Rectangle(double value)
    {
        height = width = value;
    }

    public double getDiagonal()
    {
        return Math.sqrt(width*width + height*height);
    }

    public double getArea()
    {
        return width * height;
    }
}
