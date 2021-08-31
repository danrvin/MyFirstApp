package sample.conection;

public class Const {

    public static final String T_USER = "user";  //user table

    public static final String F_USER_ID = "iduser";
    public static final String F_USER_FIRSTNAME = "firstname";
    public static final String F_USER_LASTNAME = "lastname";
    public static final String F_USER_USERNAME = "username";
    public static final String F_USER_PASSWORD = "password";
    public static final String F_USER_LOCATION = "location";
    public static final String F_USER_GENDER = "gender";
    public static final String F_USER_INVENTORY_ID = "inventoryid";


    public static final String T_INVENTORY = "inventorys"; //inventory table

    public static final String F_INVENTORY_ID = "inventoryid";
    public static final String F_INVENTORY_ID_USER = "iduser";
    public static final String F_INVENTORY_NAME = "inventoryname";


    public static final String T_ITEM = "items"; //item table

    public static final String F_ITEM_ID = "iditem";
    public static final String F_ITEM_NAME = "nameitem";
    public static final String F_ITEM_INVENTORY_ID = "inventoryid";
    public static final String F_ITEM_DESCRIPTION = "itemdescription";
}
