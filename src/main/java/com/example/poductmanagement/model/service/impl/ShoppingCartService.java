    package com.example.poductmanagement.model.service.impl;

    import com.example.poductmanagement.model.dao.impl.ShoppingCartDAO;
    import com.example.poductmanagement.model.entity.ShoppingCart;
    import com.example.poductmanagement.model.service.IShoppingCartService;

    import java.util.List;

    public class ShoppingCartService implements IShoppingCartService {
        private ShoppingCartDAO shoppingCartDAO;
        public ShoppingCartService() {
            shoppingCartDAO = new ShoppingCartDAO();
        }

        @Override
        public List<ShoppingCart> selectAll() {
            return shoppingCartDAO.selectAll();
        }

        @Override
        public ShoppingCart select(int id) {
            return shoppingCartDAO.select(id);
        }

        @Override
        public boolean insert(ShoppingCart object) {
            return shoppingCartDAO.insert(object);
        }


        public boolean update2(int productId, int quantity) {
            return shoppingCartDAO.update2(productId, quantity);
        }

        @Override
        public boolean delete(int id) {
            return shoppingCartDAO.delete(id);
        }

        public ShoppingCart selectByProductId(int productId) {
            return shoppingCartDAO.selectByProduct(productId);
        }

        public void insertUpdateProduct(int productId, int quantity) {
            shoppingCartDAO.insertUpdateProduct(productId, quantity);
        }

        @Override
        public boolean update() {
            return false;
        }
    }
