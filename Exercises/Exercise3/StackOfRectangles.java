package Exercise3;

import java.util.Stack;
import java.util.Queue;

public class StackOfRectangles {
    private Stack<Rectangle> elements;
    private Queue<Rectangle> abc;
    private int size;

    public StackOfRectangles(int size){
        elements = new Stack<Rectangle>();
        this.size = size;
    }

    public StackOfRectangles(){
        elements = new Stack<Rectangle>();
        this.size = 25;
    }

    public void push(Rectangle rectangle){
        if (elements.size() == size)
            return;
        elements.push(rectangle);
        size++;

    }

    public boolean isEmpty(){
        return elements.isEmpty();
    }

    public Rectangle peek(){
        return elements.lastElement();
    }

    public Rectangle pop(){
        size--;
        return elements.pop();
    }

    public int getSize() { return elements.size(); }

    public double getTotalArea(){
        double totalArea = 0;
        
        for (int i = 0; i < elements.size(); i++){
            var rec = elements.elementAt(i);
            totalArea += rec.getArea();
        }

        return totalArea;
    }
}
