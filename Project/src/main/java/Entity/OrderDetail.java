package Entity;

public class OrderDetail {
    public String dishId;
    public String dishName; 
    public Double dishPrice;
    public Double dishQuality;
    public Double subTotal;
    
    public OrderDetail(String dishId, String dishName, Double dishPrice, Double dishQuality, Double subTotal) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishQuality = dishQuality;
        this.subTotal = subTotal;
    }
}
