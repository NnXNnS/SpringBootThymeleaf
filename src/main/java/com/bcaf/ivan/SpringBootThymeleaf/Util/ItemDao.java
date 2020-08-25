package com.bcaf.ivan.SpringBootThymeleaf.Util;

import com.bcaf.ivan.SpringBootThymeleaf.Entity.Item;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ItemDao {
    private Connection conn;
    final String INSERT_ITEMS = "INSERT INTO items (item_name, item_type,description, price, stock, created_date) VALUES (?,?,?,?,?,?)";
    final String UPDATE_ITEMS = "UPDATE items SET item_name=?, item_type=?, description=?, price=?, stock=?, updated_date=? WHERE _id=?";
    final String DELETE_ITEMS = "DELETE FROM items WHERE _id=?";
    final String SELECT_ALL_ITEMS = "SELECT _id, item_name, item_type, description,price,stock,created_date,updated_date from items";
    final String SELECT_ITEM_ID = "SELECT _id, item_name, item_type, description,price,stock,created_date,updated_date from items where _id=?";
    final String SELECT_ITEM = "SELECT `_id`, item_name, item_type, description, price, stock, created_date, updated_date " +
            "FROM items WHERE LOWER(item_name) LIKE LOWER(?)" +
            "   OR LOWER(price) LIKE LOWER(?)" +
            "   OR LOWER(description) LIKE LOWER(?)" +
            "   OR LOWER(stock) LIKE LOWER(?)" +
            "   OR LOWER(DATE_FORMAT(created_date , \"%d %b %Y\")) LIKE LOWER(?)";

    public ItemDao() {
        conn = AdapterJDBC.getConnection();
    }

    public Item doSelectItembyId(int id) {
        Item newItem = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ITEM_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                newItem=new Item();
                //Retrieve by column name
                int idN = rs.getInt("_id");
                String itemName = rs.getString("item_name");
                int itemType = rs.getInt("item_type");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                Timestamp createdDate = rs.getTimestamp("created_date");
                Timestamp updatedDate = rs.getTimestamp("updated_date");

                //Display values
                newItem.setId(idN);
                newItem.setItemName(itemName);
                newItem.setItemType(itemType);
                newItem.setDescription(description);
                newItem.setPrice(price);
                newItem.setStock(stock);
                newItem.setCreatedDate(createdDate);
                newItem.setUpdatedDate(updatedDate);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newItem;
    }

    public List<Item> doSelectItem(String value) {
        List<Item> itemList = new LinkedList<>();
        value = "%" + value + "%";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ITEM);
            long count = SELECT_ITEM.chars().filter(ch -> ch == '?').count();
            for (int i = 1; i <= count; i++) {
                preparedStatement.setString(i, value);
            }
//            preparedStatement.setString(2, value);
//            preparedStatement.setString(3, value);
//            preparedStatement.setString(4, value);
//            preparedStatement.setString(5, value);
            ResultSet rs = preparedStatement.executeQuery();
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("_id");
                String itemName = rs.getString("item_name");
                int itemType = rs.getInt("item_type");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                Timestamp createdDate = rs.getTimestamp("created_date");
                Timestamp updatedDate = rs.getTimestamp("updated_date");

                //Display values
                Item newItem = new Item();
                newItem.setId(id);
                newItem.setItemName(itemName);
                newItem.setItemType(itemType);
                newItem.setDescription(description);
                newItem.setPrice(price);
                newItem.setStock(stock);
                newItem.setCreatedDate(createdDate);
                newItem.setUpdatedDate(updatedDate);
                itemList.add(newItem);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemList;
    }

    public List<Item> doSelectAllItems() {

        List<Item> itemList = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_ITEMS);
            ResultSet rs = preparedStatement.executeQuery();
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("_id");
                String itemName = rs.getString("item_name");
                int itemType = rs.getInt("item_type");
                String description = rs.getString("description");
                Double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                Timestamp createdDate = rs.getTimestamp("created_date");
                Timestamp updatedDate = rs.getTimestamp("updated_date");

                //Display values
                Item newItem = new Item();
                newItem.setId(id);
                newItem.setItemName(itemName);
                newItem.setItemType(itemType);
                newItem.setDescription(description);
                newItem.setPrice(price);
                newItem.setStock(stock);
                newItem.setCreatedDate(createdDate);
                newItem.setUpdatedDate(updatedDate);
                itemList.add(newItem);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemList;
    }

    public int doInsertItems(Item item) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_ITEMS);
            preparedStatement.setString(1, item.getItemName());
            preparedStatement.setInt(2, item.getItemType());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setDouble(4, item.getPrice());
            preparedStatement.setInt(5, item.getStock());
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

            int row = preparedStatement.executeUpdate();
            preparedStatement.close();
            return row;
//            System.out.println((row==1?"Insert Success":"Failed to Insert"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    public int doUpdateItems(Item updItem) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_ITEMS);
            preparedStatement.setString(1, updItem.getItemName());
            preparedStatement.setInt(2, updItem.getItemType());
            preparedStatement.setString(3, updItem.getDescription());
            preparedStatement.setDouble(4, updItem.getPrice());
            preparedStatement.setInt(5, updItem.getStock());
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(7, updItem.getId());

            int row = preparedStatement.executeUpdate();
            preparedStatement.close();
            return row;
//            System.out.println((row==1?"Insert Success":"Failed to Insert"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    public int doDeleteItem(Item updItem) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_ITEMS);
            preparedStatement.setInt(1, updItem.getId());

            int row = preparedStatement.executeUpdate();
            preparedStatement.close();
            return row;
//            System.out.println((row==1?"Insert Success":"Failed to Insert"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }
}
