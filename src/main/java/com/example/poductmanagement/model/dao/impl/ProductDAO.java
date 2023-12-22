package com.example.poductmanagement.model.dao.impl;

import com.example.poductmanagement.model.dao.IProductDAO;
import com.example.poductmanagement.model.entity.Product;
import com.example.poductmanagement.model.utils.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private Connection connection;
    private final String SELECT_ALL_PRODUCT = "select * from product where is_deleted = 0;";
    private final String SELECT_PRODUCT = "select * from product where id = ? and is_deleted = 0;";
    private final String INSERT_PRODUCT = "insert into product(name, price, description, image_url) values(?,?,?,?);";
    private final String UPDATE_PRODUCT = "update product set name = ?, price = ?, description = ?, image_url = ?) where id = ?;";
    private final String DELETE_PRODUCT = "update product set is_deleted = 1 where id = ?";

    public ProductDAO() {
        connection = MyConnection.getConnection();
    }

    @Override
    public List<Product> selectAll() {
        List<Product> productList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                String description = rs.getString("description");
                String imageURL = rs.getString("image_url");
                Product product = new Product(id, name, price, description, imageURL);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to database successful");
        return productList;
    }

    @Override
    public Product select(int id) {
        Product product = null;
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCT);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                String description = rs.getString("description");
                String imageURL = rs.getString("image_url");
                product = new Product(id, name, price, description, imageURL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean insert(Product product) {
        boolean isInserted = false;
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT);
            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getImageURL());
            isInserted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isInserted;
    }

    @Override
    public boolean update(Product product) {
        boolean isUpdated = false;
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT);
            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getImageURL());
            ps.setInt(5, product.getId());
            isUpdated = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int id) {
        boolean isDeleted = false;
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT);
            ps.setInt(1, id);
            isDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
}
