package Exercise3;

public class Rectangle
{
	private double height;
	private double width;
	
	public Rectangle(double hei, double wid){
		height = hei; width = wid;
	}
	public double getArea(){
		return width * height;
	}
	public double getDiagonal(){
		double diag = Math.sqrt( (height*height) + (width*width) );
		return diag;
	}
}
