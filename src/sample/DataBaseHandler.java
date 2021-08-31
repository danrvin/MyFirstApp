package sample;

import sample.conection.Configs;
import sample.conection.Const;
import sample.objects.Inventory;
import sample.objects.Item;
import sample.objects.User;

import java.sql.*;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver"); //com.mysql.cj.jdbc.Driver
        dbConnection = DriverManager.getConnection(connectionString
                , dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.T_USER + "(" +
                Const.F_USER_FIRSTNAME + "," + Const.F_USER_LASTNAME + "," +
                Const.F_USER_USERNAME + "," + Const.F_USER_PASSWORD + "," +
                Const.F_USER_LOCATION + "," + Const.F_USER_GENDER + ")" +
                "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getUserName());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getLocation());
            prSt.setString(6, user.getGender());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.T_USER + " WHERE " +
                Const.F_USER_USERNAME + "=? AND " + Const.F_USER_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getUserInformation(String username) {
        ResultSet resultSet = null;
        String get = "SELECT " + Const.F_USER_ID + " FROM " + Const.T_USER
                + " WHERE " + Const.F_USER_USERNAME + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(get);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void addUserInformation(String inventoryID, String userID) {
        String remake = "UPDATE " + Const.T_USER + " SET " + Const.F_USER_INVENTORY_ID
                + "=" +inventoryID + " WHERE " + Const.F_USER_ID + "=" + userID;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(remake);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserInformation(String userID) {
        String remake = "UPDATE " + Const.T_USER + " SET " + Const.F_USER_INVENTORY_ID
                + "=" + null + " WHERE " + Const.F_USER_ID + "=" + userID;
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(remake);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getItem(Item item) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.T_ITEM + " WHERE " +
                Const.F_ITEM_NAME + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, item.getNameItem());
            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void signUpItem(Item item) {
        String insert = "INSERT INTO " + Const.T_ITEM + "(" + Const.F_ITEM_NAME
                + "," + Const.F_ITEM_DESCRIPTION + ")" + "VALUES(?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, item.getNameItem());
            prSt.setString(2, item.getDescriptionItem());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(Item item) {
        String select = "DELETE FROM " + Const.T_ITEM + " WHERE "
                + Const.F_ITEM_NAME + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, item.getNameItem());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void signUpAdminInventory(Inventory inventory) {
        String insert = "INSERT INTO " + Const.T_INVENTORY + "(" +
                Const.F_INVENTORY_NAME + ")" + "VALUES(?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, inventory.getNameField());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void signUpInventory(String nameInventory, String idUser) {
        String insert = "INSERT INTO " + Const.T_INVENTORY + "(" +
                Const.F_INVENTORY_NAME + "," + Const.F_INVENTORY_ID_USER + ")" + "VALUES(?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, nameInventory);
            preparedStatement.setString(2, idUser);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getInventory(Inventory inventory) {
        ResultSet resultSet = null;
        String insert = "SELECT * FROM " + Const.T_INVENTORY + " WHERE "
                + Const.F_INVENTORY_NAME + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, inventory.getNameField());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getInventoryInformation(String nameInventory) {
        ResultSet resultSet = null;
        String get = "SELECT " + Const.F_INVENTORY_ID + " FROM " + Const.T_INVENTORY
                + " WHERE " + Const.F_INVENTORY_NAME + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(get);
            preparedStatement.setString(1, nameInventory);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void adminDeleteInventory(Inventory inventory) {
        String delete = "DELETE FROM " + Const.T_INVENTORY + " WHERE "
                + Const.F_INVENTORY_NAME + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setString(1, inventory.getNameField());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteInventory(String idUser) {
        String delete = "DELETE FROM " + Const.T_INVENTORY + " WHERE "
                + Const.F_INVENTORY_ID_USER + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(delete);
            preparedStatement.setString(1,idUser);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
