package com.example.poductmanagement.model.service.impl;

import com.example.poductmanagement.model.dao.IEntityDAO;
import com.example.poductmanagement.model.dao.IProductDAO;
import com.example.poductmanagement.model.dao.impl.ProductDAO;
import com.example.poductmanagement.model.entity.Product;
import com.example.poductmanagement.model.service.IProductService;

import java.util.List;

public class ProductService implements IProductService {
    private IProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }

    @Override
    public List<Product> selectAll() {
        return productDAO.selectAll();
    }

    @Override
    public Product select(int id) {
        return productDAO.select(id);
    }

    @Override
    public boolean insert(Product product) {
        return productDAO.insert(product);
    }

    @Override
    public boolean update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public boolean delete(int id) {
        return productDAO.delete(id);
    }
}
