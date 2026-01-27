package Entity;

import java.util.List;

public class Dish {
    public String cuisine;
    public String dishId;
    public String dishName;
    public String dishImageUrl;
    public Double dishPrice;
    public List<String> dishIngredents;

    public Dish(String cuisine, String dishId, String dishName, String dishImageUrl, Double dishPrice, List<String> dishIngredents) {
        this.cuisine = cuisine;
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishImageUrl = dishImageUrl;
        this.dishPrice = dishPrice;
        this.dishIngredents = dishIngredents;
    }
}
