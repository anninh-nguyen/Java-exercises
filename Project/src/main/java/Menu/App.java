package Menu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Entity.*;

public class App extends Application {
    
    private ListView mealCategory = new ListView<>();
    private ListView dishesList = new ListView<>();
    private ImageView dishView = new ImageView("Home-map.png");
    private Text dishIngredients = new Text();
    private TextArea dishIngredentsArea = new TextArea("Dishes ingredents show here");
    private ArrayList<Dish> dishes = new ArrayList<Dish>();
    private Document orderDocument;
    private Order order;
    private GridPane orderSection = new GridPane();
    private GridPane main = new GridPane();
    private String customerId = "";
    private Image homeView = new Image("Home-map.png");
    private ComboBox customerOrders = new ComboBox();

    public void start(Stage stage) {

        // Employees employee1 = new Employees("John 1", "Doe 1");
        // employee1.insert();

        // Employees employee2 = new Employees("John 2", "Doe 2");
        // employee2.insert();

        // Employees employee3 = new Employees("John 3", "Doe 3");
        // employee3.insert();

        // Employees employeesRetrive = new Employees().select(2);
        // System.out.println("Employee ID: " + employeesRetrive.EmployeeID + " " + 
        //                    "Employee Name: " + employeesRetrive.FirstName + " " + employeesRetrive.LastName);

        prepareForm();

        Text copyRightText = new Text("Sourse: Top 69 Finnish Foods - TasteAtlas.com");
        Hyperlink copyrightLink = new Hyperlink("https://www.tasteatlas.com/best-rated-dishes-in-finland", copyRightText);

        VBox root = new VBox();
        root.setSpacing(30);
        root.getChildren().addAll(main, copyrightLink);

        Scene scene = new Scene(root, 1300, 800);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        stage.setOnShowing(e -> {onStageShowing();});
        
        stage.setScene(scene);
        stage.setTitle("International Restaurant Menu");
        stage.show();
    }

    private void onStageShowing(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.getDialogPane().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        dialog.setTitle("Phone Number Required");
        dialog.setHeaderText("Please enter your phone number and click Ok. If you don't have one, just click Cancel to create a new profile.");
        dialog.setContentText("Phone Number:");

        dialog.showAndWait().ifPresent(phoneNumber -> {
            if (phoneNumber.isEmpty()) {
                customerId = String.valueOf(new Random().nextInt(99999));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                alert.setTitle("Customer profile");
                alert.setHeaderText("Your profile has been created");
                alert.setContentText("Customer ID is: " + customerId);
                alert.showAndWait();

                order = new Order(customerId);
            }
            else {
                customerId = phoneNumber;
                
                getCustomerOrders();
                
                if (customerOrders.getItems().size() > 0) {
                    order = readOrderFromXml(phoneNumber, customerOrders.getSelectionModel().getSelectedItem().toString());
                }
                else {
                    order = readOrderFromXml(phoneNumber, "");
                }
            }
                
            orderBinding();
        });
    }

    private Document readXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse("Orders.xml");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Order readOrderFromXml(String phoneNumber, String orderId) {
        try {
            Order order = new Order();

            NodeList orderNodes = orderDocument.getElementsByTagName("orders").item(0).getChildNodes(); //orders

            for (int i = 0; i < orderNodes.getLength(); i++) {
                Node orderNode = orderNodes.item(i);
                if (orderNode.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element orderElement = (Element) orderNode;

                String customerId = orderElement.getAttribute("customerId");
                if (!customerId.equals(phoneNumber) || (!orderId.isEmpty() && !orderElement.getAttribute("orderId").equals(orderId))) {
                    continue;
                }
                order.orderId = orderElement.getAttribute("orderId");
                order.customerId = customerId;
                order.orderDate = orderElement.getAttribute("orderDate");
                order.orderTotal = orderElement.getAttribute("orderTotal") != null 
                            ? Double.parseDouble(orderElement.getAttribute("orderTotal")) : 0.0;

                NodeList orderDetailNodes = orderElement.getElementsByTagName("orderDetails").item(0).getChildNodes(); 
                for (int j = 0; j < orderDetailNodes.getLength(); j++) {
                    Node orderDetailNode = orderDetailNodes.item(j);
                    if (orderDetailNode.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    Element orderDetailElement = (Element) orderDetailNode;

                    String dishId = orderDetailElement.getAttribute("dishId");
                    String dishName = orderDetailElement.getAttribute("dishName");
                    Double dishPrice = orderDetailElement.getAttribute("dishPrice") != null ? Double.parseDouble(orderDetailElement.getAttribute("dishPrice")) : 0.0;
                    Double dishQuality = orderDetailElement.getAttribute("dishQuality") != null ? Double.parseDouble(orderDetailElement.getAttribute("dishQuality")) : 0.0;
                    Double subTotal = orderDetailElement.getAttribute("subTotal") != null ? Double.parseDouble(orderDetailElement.getAttribute("subTotal")) : 0.0;
                    order.orderDetails.add(new OrderDetail(dishId, dishName, dishPrice, dishQuality, subTotal));
                }
            }
            return order;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<Dish> prepareDishes () {
        
        var dishes = new ArrayList<Dish>();
        
        dishes.add(new Dish("Finnish", "11", "Mämmi", "https://www.tasteatlas.com/images/dishes/5213ad8d28b1454d826fc8071080c180.jpg?mw=800", 3.5, 
                        List.of("Rye flour", "Rye Malt", "Orange Zest", "Molasses", "Sugar")));                        
        dishes.add(new Dish("Finnish", "12","Finnish fish soup (Kalakeitto)", "https://www.tasteatlas.com/images/dishes/4e2f55e4763a424c9f6f6ea892521c1f.jpg?mw=800", 5.8, 
                        List.of("Fish", "Potatoes", "Onion", "Bay Leaves", "Allspice", "Dill")));
        dishes.add(new Dish("Finnish", "13","Salmon soup (Lohikeitto)", "https://www.tasteatlas.com/Images/Dishes/38e5693d7406474aa49c8e346430a9cb.jpg?mw=800", 6.3, 
                        List.of("Salmon", "Potatoes", "Carrot", "Leek", "Butter", "Dill", "Heavy Cream", "Fish Stock", "Black Pepper",  "Salt")));
        dishes.add(new Dish("Finnish", "14","Sautéed reindeer (Poronkäristys)","https://www.tasteatlas.com/Images/Dishes/4ebf1baa83914a87a142fea7402c7440.jpg?mw=800", 4.6, 
                        List.of("Rye flour", "Wheat Flour", "Bilberries", "Blueberries", "Butter", "Eggs", "Cardamom", "Cinnamon", "Vanilla", "Baking Powder")));
        
        dishes.add(new Dish("German", "21","Pretzel (Brezel)","https://www.tasteatlas.com/images/dishes/a3d9f03625f14029a24dcadc88910171.jpg?mw=800", 2.2, 
                        List.of("Wheat Flour","Yeast", "Baking Soda", "Butter", "Sugar", "Salt")));
        dishes.add(new Dish("German", "22","Bratwurst","https://www.tasteatlas.com/Images/Dishes/de9c2d8d21c5438580ee42ed9dfa41e4.jpg?mw=800", 5.3, 
                        List.of("Ground Pork", "Marjoram", "Mace", "Coriander Seeds", "Black Pepper", "Salt")));
        dishes.add(new Dish("German", "23","Schwarzwälder Kirschtorte","https://www.tasteatlas.com/Images/Dishes/b19aebb29acd4062b729913ed0af1692.jpg?mw=800", 3.9, 
                        List.of("Wheat Flour", "Eggs", "Baking Powder", "Cocoa Powder", "Chocolate", "Sour Cherries", "Cherries", "Redcurrant Jam")));
        
        dishes.add(new Dish("Japanese", "31","Ramen","https://www.tasteatlas.com/images/dishes/b6b9680a32c84a9381a1ea5f5e529698.jpg?mw=800", 8.6, 
                        List.of("Beef", "Potatoes", "Onion", "Garlic", "Salt")));
        dishes.add(new Dish("Japanese", "32","Sushi","https://www.tasteatlas.com/Images/Dishes/fde953e5aa504e8c83a84691582b0399.jpg?mw=800", 12.3, 
                        List.of("Ramen Noodles", "Pork", "Root Vegetable", "Mushrooms", "Chashu")));
        dishes.add(new Dish("Japanese", "33","Tonkatsu","https://www.tasteatlas.com/images/dishes/bad3bf5847bc4dee9ad59f1d9ca84725.jpg?mw=800", 4.9, 
                        List.of("Pork", "Flour", "Breadcrumbs", "Eggs", "Oil")));

        dishes.add(new Dish("Chinese", "41","Chow mein","https://www.tasteatlas.com/Images/Dishes/0946b9809ef24450a6cfcb738de3dd51.jpg?mw=800", 7.1, 
                        List.of("Noodles", "Chicken", "Seafood", "Scallions", "Bok choy", "Bean Sprouts")));
        dishes.add(new Dish("Chinese", "42","Jiaozi","https://www.tasteatlas.com/images/dishes/82fbbf7148654aa29148960471e86393.jpg?mw=800", 6.4, 
                        List.of("Wheat Flour", "Ground Pork", "Napa cabbage", "Bamboo shoots","Scallions", "Ginger", "Garlic")));

        return dishes;
    }

    private void prepareForm() {

        dishes = prepareDishes();

        orderDocument = readXML();
        
        mealCategory.setPrefHeight(100);
        mealCategory.setOnMouseClicked(e -> { onSelectCuisine(); });

        List<String> cuisineList = new ArrayList<String>();
        dishes.forEach(t-> cuisineList.add(t.cuisine));
        var distintCuisineList = cuisineList.stream().distinct().collect(Collectors.toList());
        mealCategory.getItems().addAll(distintCuisineList);
        mealCategory.getSelectionModel().selectFirst();
        onSelectCuisine();

        orderSection.setBorder(new Border(new BorderStroke(Color.AQUA, BorderStrokeStyle.SOLID, 
                                            CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        dishesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        dishesList.setOnMouseClicked(e -> { onSelectDish(); });;
        
        dishIngredentsArea.setPrefRowCount(20);
        dishIngredentsArea.setPrefColumnCount(10);

        main.setHgap(10);
        main.setVgap(10);
        main.setPadding(new Insets(10));

        main.add(new Text("Select your cuisine style"), 0, 0);
        main.add(mealCategory, 0, 1);
        main.add(new Text("Select your dishes"), 0, 2);
        main.add(dishesList, 0, 3);

        HBox customerOrderBox = new HBox();
        customerOrderBox.getChildren().addAll(new Text("Your orders  "), customerOrders);
        main.add(customerOrderBox, 1, 0);
        orderSection.setHgap(10);
        orderSection.setVgap(10);
        orderSection.setPadding(new Insets(10));
        main.add(orderSection, 1, 1);
        main.add(new Text("Selected dish"), 1, 2);
        VBox dishViewBox = new VBox();
        dishViewBox.setSpacing(10);

        Button addToOrder = new Button("Add to order");
        addToOrder.setOnAction(e -> { onAddToOrder(); });
        dishViewBox.getChildren().addAll(dishView, dishIngredients, addToOrder);

        main.add(dishViewBox, 1, 3);

        main.add(new Text("Order action"), 2, 0);

        Button newOrder = new Button("New Order");
        newOrder.setOnAction(e -> { resetFormForNewOrder();; });
        Button submitOrder = new Button("Place Order");
        submitOrder.setOnAction(e -> { onSubmitOrder(); });
        Button cancelOrder = new Button("Cancel Order");
        cancelOrder.setOnAction(e -> { onCancelOrder(); });
        VBox orderActionBox = new VBox();
        orderActionBox.setSpacing(10);
        orderActionBox.getChildren().addAll(newOrder, submitOrder, cancelOrder);
        orderActionBox.setPadding(new Insets(10));

        main.add(orderActionBox, 2, 1);
        main.add(new Text("Ingredents in your order"), 2, 2);
        main.add(dishIngredentsArea, 2, 3);
    }

    private void getCustomerOrders() {
        List<String> orderIds = new ArrayList<>();
        try {
            NodeList orderNodes = orderDocument.getElementsByTagName("orders").item(0).getChildNodes();
            for (int i = 0; i < orderNodes.getLength(); i++) {
                Node orderNode = orderNodes.item(i);
                if (orderNode.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element orderElement = (Element) orderNode;
                if (orderElement.getAttribute("customerId").equals(customerId)) {
                    orderIds.add(orderElement.getAttribute("orderId"));
                }
            }
            customerOrders.getItems().addAll(orderIds);
            customerOrders.getSelectionModel().selectFirst();
            customerOrders.setOnAction(e -> {
                if (customerOrders.getItems().size() > 0) {
                    order = readOrderFromXml(customerId, customerOrders.getSelectionModel().getSelectedItem().toString());
                }
                orderBinding();
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void onSelectCuisine(){
        // Get dishes for selected cuisine
        dishesList.getItems().clear();

        dishesList.getSelectionModel().clearSelection();
        mealCategory.getSelectionModel().getSelectedItems().forEach(selectedCuisine -> {
            dishes.forEach(dish -> {
                if(dish.cuisine.equals(selectedCuisine)){
                    dishesList.getItems().add(dish.dishName);
                }
            });
        });
        dishesList.getSelectionModel().selectFirst();
        onSelectDish();
    }
 
    private void onSelectDish (){

        dishesList.getSelectionModel().getSelectedItems().forEach(selectedDish -> {
            dishes.forEach(dish -> {
                if(dish.dishName.equals(selectedDish)){
                    dishView.setImage(new ImageView(dish.dishImageUrl).getImage());
                }
            });
        });
        var selectedIngredents = new ArrayList<String>();
        dishesList.getItems().forEach(dish -> {
            dishesList.getSelectionModel().getSelectedItems().forEach(selectedDish -> {
                dishes.forEach(cuisine -> {
                    if(cuisine.dishName.equals(selectedDish)){
                        cuisine.dishIngredents.forEach(ingredent -> {
                            selectedIngredents.add(ingredent);
                        });
                    }
                });
            });
        });
        var distinctSelectedIngredents = selectedIngredents.stream().distinct().collect(Collectors.toList());
        dishIngredients.setText("Dish ingredients: ");
        distinctSelectedIngredents.forEach(t -> dishIngredients.setText(dishIngredients.getText() + t + "; "));
    }

    private void onAddToOrder (){
        if (order.orderId.isEmpty()) {
            order.orderId = String.valueOf(new Random().nextInt(99999));
            order.customerId = customerId;
            order.orderDate = java.time.LocalDate.now().toString();
        }
        // Add selected dish to order
        dishesList.getSelectionModel().getSelectedItems().forEach(selectedDish -> {
            dishes.forEach(dish -> {
                if(dish.dishName.equals(selectedDish)){
                    OrderDetail orderDetail = null;
                    for (int i = 0; i < order.orderDetails.size(); i++) {
                        if (order.orderDetails.get(i).dishId.equals(dish.dishId)) {
                            orderDetail = order.orderDetails.get(i);
                            break;
                        }
                    }
                    
                    if (orderDetail != null) {
                        orderDetail.dishQuality += 1.0;
                        orderDetail.subTotal = Math.round(orderDetail.dishPrice * orderDetail.dishQuality * 100.0) / 100.0;
                    }
                    else {
                        orderDetail = new OrderDetail(dish.dishId, dish.dishName, dish.dishPrice, 1.0, dish.dishPrice);
                        order.orderDetails.add(orderDetail);
                    }
                    order.orderTotal = 0.0;
                    order.orderDetails.forEach(t -> order.orderTotal += t.subTotal);
                    order.orderTotal = Math.round(order.orderTotal * 100.0) / 100.0;
                }
            });
        });
        
        orderBinding();
    }
    
    private void orderBinding() {

        orderSection.getChildren().clear();

        orderSection.add(new Text("Order"), 0, 0);
        orderSection.add(new Text("Order ID: " + order.orderId), 0, 1);
        orderSection.add(new Text("Order Phone: " + order.customerId), 0, 2);
        orderSection.add(new Text("Order Date: " + order.orderDate), 0, 3);
        orderSection.add(new Text("Order Total: " + order.orderTotal), 0, 4);
        
        orderSection.add(new Text("Dish name"), 1, 0);
        orderSection.add(new Text("Price"), 2, 0);
        orderSection.add(new Text("Quantity"), 3, 0);
        orderSection.add(new Text("Sub total"), 4, 0);
        var action = new Text("Action");
        orderSection.add(action, 5, 0);
        orderSection.setColumnSpan(action, 2);
        
        for (int i = 0; i < order.orderDetails.size(); i++) {
            var orderDetail = order.orderDetails.get(i);

            var dishName = new Text(orderDetail.dishName);
            var dishPrice = new Text(orderDetail.dishPrice.toString());
            var dishQuality = new Text(orderDetail.dishQuality.toString());
            var subTotal = new Text(orderDetail.subTotal.toString());
            
            orderSection.add(dishName, 1, i+1);
            orderSection.add(dishPrice, 2, i+1);
            orderSection.add(dishQuality, 3, i+1);
            orderSection.add(subTotal, 4, i+1);
            
            // Add remove dish action
            Hyperlink removeDish = new Hyperlink("-");
            removeDish.setOnAction(e -> {
                if (orderDetail.dishQuality > 1.0) {
                    orderDetail.dishQuality -= 1.0;
                } else if (orderDetail.dishQuality <= 1.0) {
                    order.orderDetails.remove(orderDetail);
                }
                orderDetail.subTotal = Math.round(orderDetail.dishPrice * orderDetail.dishQuality * 100.0) / 100.0;
                order.orderTotal = order.orderDetails.stream().mapToDouble(t -> t.subTotal).sum();
                order.orderTotal = Math.round(order.orderTotal * 100.0) / 100.0;
                
                orderBinding();
            });
            orderSection.add(removeDish, 5, i+1);

            Hyperlink addDish = new Hyperlink("+");
            addDish.setOnAction(e -> {
                if (orderDetail.dishQuality == 0.0) {
                    orderDetail.dishQuality = 1.0;
                } else {
                    orderDetail.dishQuality += 1.0;
                }
                orderDetail.subTotal = Math.round(orderDetail.dishPrice * orderDetail.dishQuality * 100.0) / 100.0;
                order.orderTotal = order.orderDetails.stream().mapToDouble(t -> t.subTotal).sum();
                order.orderTotal = Math.round(order.orderTotal * 100.0) / 100.0;
    
                orderBinding();
            });
            orderSection.add(addDish, 6, i+1);
        }

        // Get all ingredients in all order details
        var allIngredients = new ArrayList<String>();
        order.orderDetails.forEach(orderDetail -> {
            dishes.forEach(dish -> {
            if (dish.dishId.equals(orderDetail.dishId)) {
                allIngredients.addAll(dish.dishIngredents);
            }
            });
        });

        dishIngredentsArea.setText("");
        order.orderDetails.forEach(od -> {
            dishes.forEach(dish -> {
                if (dish.dishId.equals(od.dishId)) {
                    dish.dishIngredents.forEach(ingredient -> {
                        dishIngredentsArea.setText(dishIngredentsArea.getText() + ingredient + "\n");
                    });
                }
            });
        });
    }
    
    private void onSubmitOrder(){
        // check if order is exists in order XML document
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            if (order.orderDetails.size() == 0) {
                alert.setTitle("Order Status");
                alert.setHeaderText("No order detail!");
                alert.setContentText("You can add the first dish order.");
                alert.showAndWait();
                return;
            }

            NodeList orderNodes = orderDocument.getElementsByTagName("orders").item(0).getChildNodes();
            
            var orderNodesCount =  orderNodes.getLength();
            for (int i = 0; i < orderNodesCount; i++) {
                Node orderNode = orderNodes.item(i);
                if (orderNode.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                Element orderElement = (Element) orderNode;
                if (orderElement.getAttribute("orderId").equals(order.orderId)) {
                    orderNode.getParentNode().removeChild(orderNode);
                    break;
                }
            }

            var newOrderNode = order.toXmlElement();
            orderNodes.item(0).getParentNode().appendChild(orderDocument.importNode(newOrderNode, true));

            if (!customerOrders.getItems().contains(order.orderId)) {
                customerOrders.getItems().add(order.orderId);
            }

            writeToXMLFile();

            orderBinding();

            alert.setTitle("Order Placed");
            alert.setHeaderText("Your order has been placed successfully!");
            alert.setContentText("Thank you for your order.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onCancelOrder(){
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            if (order.orderDetails.size() == 0) {
                alert.setTitle("Order Status");
                alert.setHeaderText("No order detail!");
                alert.setContentText("You can add the first dish order.");
                alert.showAndWait();
                return;
            }
            
            NodeList orderNodes = orderDocument.getElementsByTagName("orders").item(0).getChildNodes();
            
            for (int i = 0; i < orderNodes.getLength(); i++) {
                Node orderNode = orderNodes.item(i);
                if (orderNode.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                Element orderElement = (Element) orderNode;
                if (!orderElement.getAttribute("orderId").equals(order.orderId)) {
                    continue;
                }
                orderNode.getParentNode().removeChild(orderNode);

                customerOrders.getItems().remove(order.orderId);

                writeToXMLFile();

                resetFormForNewOrder();
                orderBinding();
                dishIngredients.setText("");
                dishIngredentsArea.setText("");
                orderSection.getChildren().clear();
    
                alert.setTitle("Order Canceled");
                alert.setHeaderText("Your order has been canceled successfully!");
                alert.setContentText("Wish you happy with our restaurant.");
                alert.showAndWait();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetFormForNewOrder() {
        order.orderId = "";
        order.orderDate = "";
        order.orderTotal = 0.0;
        order.orderDetails.clear();

        dishIngredients.setText("");
        dishIngredentsArea.setText("");
        dishView.setImage(homeView);
        orderSection.getChildren().clear();
    }

    private void writeToXMLFile() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(orderDocument);
            StreamResult result = new StreamResult("Orders.xml");
            transformer.transform(source, result);

            readXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg0) {
        launch(arg0);
    }
}
