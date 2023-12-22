package com.example.poductmanagement.model.dao.impl;

import com.example.poductmanagement.model.dao.IShoppingCartDAO;
import com.example.poductmanagement.model.entity.Product;
import com.example.poductmanagement.model.entity.ShoppingCart;
import com.example.poductmanagement.model.utils.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDAO implements IShoppingCartDAO {
    Connection connection = MyConnection.getConnection();

    private final String SELECT_ALL_CART = "select sum(quantity) as soluong, product_id from shopping_cart group by product_id;";
    private final String SELECT_CART = "SELECT * FROM shopping_cart where id = ?;";
    private final String INSERT_CART = "INSERT INTO shopping_cart(quantity, product_id) values (?,?)";
    private final String UPDATE_CART = "update shopping_cart set quantity = ?, product_id = ? where id = ?";
    private final String DELETE_CART = "delete from shopping_cart where product_id = ?";

    private final String SELECT_CART_BY_PRODUCT_ID = "select sum(quantity) as soluong, product_id from shopping_cart where product_id = ? group by product_id;";

    @Override
    public List<ShoppingCart> selectAll() {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_CART);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("soluong");
                ShoppingCart shoppingCart = new ShoppingCart( productId,quantity);
                shoppingCarts.add(shoppingCart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to database successful");
        return shoppingCarts;
    }


    @Override
    public boolean insert(ShoppingCart shoppingCart) {
        boolean isSave = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART);
            preparedStatement.setInt(1, shoppingCart.getQuantity());
            preparedStatement.setInt(2, shoppingCart.getProductID());
            isSave = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("add to shopping cart success");
        return isSave;
    }

    @Override
    public ShoppingCart select(int id) {
        ShoppingCart cart = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CART);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int quantity = resultSet.getInt("quantity");
                int productId = resultSet.getInt("product_id");
                cart = new ShoppingCart(id, quantity, productId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public boolean delete(int id) {
        boolean isDelete = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART);
            preparedStatement.setInt(1, id);
            isDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDelete;
    }

    @Override
    public boolean update(ShoppingCart shoppingCart) {
        boolean isEdit = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART);
            preparedStatement.setInt(1, shoppingCart.getQuantity());
            preparedStatement.setInt(2, shoppingCart.getProductID());
            preparedStatement.setInt(3, shoppingCart.getId());
            isEdit = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isEdit;
    }

    public ShoppingCart selectByProduct(int productId) {
        ShoppingCart shoppingCart = null;
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_CART_BY_PRODUCT_ID);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int quantity = rs.getInt("soluong");
                 shoppingCart = new ShoppingCart( productId, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to database successful");
        return shoppingCart;

    }
}
