package Entity;


import Core.DatabaseConnectionManager;
import Core.MasterObject;

public class Employees extends MasterObject  {
    public int EmployeeID;
    public String FirstName;
    public String LastName;

    public Employees(int employeeID, String firstName, String lastName) {
        this.EmployeeID = employeeID;
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public Employees(String firstName, String lastName) {
        this.EmployeeID = 0;
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public Employees() {
        this.EmployeeID = 0;
        this.FirstName = "";
        this.LastName = "";
    }

    @Override
    protected Employees select() {
        return select(this.EmployeeID);
    }

    public Employees select(int employeeID) {
        String query = "SELECT * FROM Employees WHERE EmployeeID = ?";
        try (var connection = DatabaseConnectionManager.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeID);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employees employee = new Employees();
                employee.EmployeeID = resultSet.getInt("EmployeeID");
                employee.FirstName = resultSet.getString("FirstName");
                employee.LastName = resultSet.getString("LastName");

                return employee;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert() {
        String query = "INSERT INTO Employees (FirstName, LastName) VALUES (?, ?)";
        try (var connection = DatabaseConnectionManager.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, FirstName);
            preparedStatement.setString(2, LastName);
            if (preparedStatement.executeUpdate() > 0) {
                try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        EmployeeID = generatedKeys.getInt(1);
                        return EmployeeID;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean update() {
        String query = "UPDATE Employees SET FirstName = ?, LastName = ? WHERE EmployeeID = ?";
        try (var connection = DatabaseConnectionManager.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, FirstName);
            preparedStatement.setString(2, LastName);
            preparedStatement.setInt(3, EmployeeID);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean delete() {
        return delete(this.EmployeeID);
    }

    public boolean delete(int employeeID) {
        String query = "DELETE FROM Employees WHERE EmployeeID = ?";
        try (var connection = DatabaseConnectionManager.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeID);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
