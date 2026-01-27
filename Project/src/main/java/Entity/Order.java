package Entity;

import java.sql.Connection;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;

import Core.DatabaseConnectionManager;

public class Order{
    public String orderId;
    public String customerId;
    public String orderDate;
    public Double orderTotal;
    public ArrayList<OrderDetail> orderDetails;
    
    public Order() {
        this.orderId = "";
        this.customerId = "";
        this.orderDate = "";
        this.orderTotal = 0.0;
        this.orderDetails = null;
        orderDetails = new ArrayList<>();
    }
    
    public Order(String customerId)  {
        this.customerId = "";
        this.orderDetails = new ArrayList<>();
    }

    public Element toXmlElement() {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            var builder = factory.newDocumentBuilder();
            var doc = builder.newDocument();

            var orderRoot = doc.createElement("order");

            orderRoot.setAttribute("orderId", this.orderId);
            orderRoot.setAttribute("customerId", this.customerId);
            orderRoot.setAttribute("orderDate", this.orderDate);
            orderRoot.setAttribute("orderTotal", this.orderTotal.toString());

            var detailsElement = doc.createElement("orderDetails");
            orderRoot.appendChild(detailsElement);

            for (var detail : this.orderDetails) {
                var detailElement = doc.createElement("orderDetail");

                detailElement.setAttribute("dishId", detail.dishId);
                detailElement.setAttribute("dishName", detail.dishName);
                detailElement.setAttribute("dishPrice", detail.dishPrice.toString());
                detailElement.setAttribute("dishQuality", detail.dishQuality.toString());
                detailElement.setAttribute("subTotal", detail.subTotal.toString());

                detailsElement.appendChild(detailElement);
            }

            return orderRoot;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Order select() {
        return select(this.orderId);
    }

    public Order select(String orderId) {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();

            String selectOrderQuery = "SELECT * FROM orders WHERE orderId = ?";
            var preparedStatement = connection.prepareStatement(selectOrderQuery);
            preparedStatement.setString(1, orderId);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Order order = new Order();
                order.orderId = resultSet.getString("orderId");
                order.customerId = resultSet.getString("customerId");
                order.orderDate = resultSet.getString("orderDate");
                order.orderTotal = resultSet.getDouble("orderTotal");

                String selectOrderDetailsQuery = "SELECT * FROM order_details WHERE orderId = ?";
                var detailsStatement = connection.prepareStatement(selectOrderDetailsQuery);
                detailsStatement.setString(1, orderId);
                var detailsResultSet = detailsStatement.executeQuery();

                while (detailsResultSet.next()) {
                    String dishId = detailsResultSet.getString("dishId");
                    String dishName = detailsResultSet.getString("dishName");
                    double dishPrice = detailsResultSet.getDouble("dishPrice");
                    double dishQuality = detailsResultSet.getDouble("dishQuality");
                    double subTotal = detailsResultSet.getDouble("subTotal");

                    order.orderDetails.add(new OrderDetail(dishId, dishName, dishPrice, dishQuality, subTotal));
                }
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String insert() {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            
            String insertOrderQuery = "INSERT INTO orders (customerId, orderDate, orderTotal) " +
                          "VALUES (?, ?, ?)";
            var preparedStatement = connection.prepareStatement(insertOrderQuery, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, this.customerId);
            preparedStatement.setString(2, this.orderDate);
            preparedStatement.setDouble(3, this.orderTotal);
            preparedStatement.executeUpdate();

            // Retrieve the generated orderId
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.orderId = generatedKeys.getString(1);
            }

            String insertOrderDetailsQuery = "INSERT INTO order_details (orderId, dishId, dishName, dishPrice, dishQuality, subTotal) " +
                             "VALUES (?, ?, ?, ?, ?, ?)";
            var detailsStatement = connection.prepareStatement(insertOrderDetailsQuery);
            for (OrderDetail detail : this.orderDetails) {
            detailsStatement.setString(1, this.orderId);
            detailsStatement.setString(2, detail.dishId);
            detailsStatement.setString(3, detail.dishName);
            detailsStatement.setDouble(4, detail.dishPrice);
            detailsStatement.setDouble(5, detail.dishQuality);
            detailsStatement.setDouble(6, detail.subTotal);
            detailsStatement.addBatch();
            }
            detailsStatement.executeBatch();

            return this.orderId; // Success
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update() {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();

            String updateOrderQuery = "UPDATE orders SET customerId = ?, orderDate = ?, orderTotal = ? WHERE orderId = ?";
            var preparedStatement = connection.prepareStatement(updateOrderQuery);
            preparedStatement.setString(1, this.customerId);
            preparedStatement.setString(2, this.orderDate);
            preparedStatement.setDouble(3, this.orderTotal);
            preparedStatement.setString(4, this.orderId);
            preparedStatement.executeUpdate();

            String deleteOrderDetailsQuery = "DELETE FROM order_details WHERE orderId = ?";
            var deleteStatement = connection.prepareStatement(deleteOrderDetailsQuery);
            deleteStatement.setString(1, this.orderId);
            deleteStatement.executeUpdate();

            String insertOrderDetailsQuery = "INSERT INTO order_details (orderId, dishId, dishName, dishPrice, dishQuality, subTotal) " +
                                             "VALUES (?, ?, ?, ?, ?, ?)";
            var detailsStatement = connection.prepareStatement(insertOrderDetailsQuery);
            for (OrderDetail detail : this.orderDetails) {
                detailsStatement.setString(1, this.orderId);
                detailsStatement.setString(2, detail.dishId);
                detailsStatement.setString(3, detail.dishName);
                detailsStatement.setDouble(4, detail.dishPrice);
                detailsStatement.setDouble(5, detail.dishQuality);
                detailsStatement.setDouble(6, detail.subTotal);
                detailsStatement.addBatch();
            }
            detailsStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void delete() {
        delete(this.orderId);
    }

    public void delete(String orderId) {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();

            String deleteOrderDetailsQuery = "DELETE FROM order_details WHERE orderId = ?";
            var deleteDetailsStatement = connection.prepareStatement(deleteOrderDetailsQuery);
            deleteDetailsStatement.setString(1, orderId);
            deleteDetailsStatement.executeUpdate();

            String deleteOrderQuery = "DELETE FROM orders WHERE orderId = ?";
            var deleteOrderStatement = connection.prepareStatement(deleteOrderQuery);
            deleteOrderStatement.setString(1, orderId);
            deleteOrderStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
