package com.example.poductmanagement.controller;

import com.example.poductmanagement.model.entity.Product;
import com.example.poductmanagement.model.entity.ShoppingCart;
import com.example.poductmanagement.model.entity.ShoppingCartDetail;
import com.example.poductmanagement.model.service.impl.ProductService;
import com.example.poductmanagement.model.service.impl.ShoppingCartService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/shoppingCart")
public class ShoppingCartController extends HttpServlet {
    private ShoppingCartService shoppingCartService;

    @Override
    public void init() throws ServletException {
        shoppingCartService = new ShoppingCartService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit" :
                showEdit(req, resp);
                break;
            case "remove" :
                remove(req, resp);
            default:
                list(req, resp);
        }

    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int productId = Integer.parseInt(req.getParameter("productId"));
        req.setAttribute("quantity", productId);
        RequestDispatcher dispatcher = req.getRequestDispatcher("edit.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit" :
                edit(req, resp);
                break;
            case "remove" :
                remove(req, resp);
                break;
        }
    }

    private void remove(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        shoppingCartService.delete(id);
        list(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int id = Integer.parseInt(req.getParameter("id"));
        ShoppingCart shoppingCart = new ShoppingCart(id, quantity);
        shoppingCartService.update(shoppingCart);
        doGet(req, resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ShoppingCart> cartList = shoppingCartService.selectAll();
        ProductService productService = new ProductService();
        req.setAttribute("cartList", cartList);
        List<Product> productList = new ArrayList<>();
        for (ShoppingCart shoppingCart : cartList) {
            int productId = shoppingCart.getProductID();
            Product product = productService.select(productId);
            productList.add(product);

        }
        List<ShoppingCartDetail> cartDetailList = new ArrayList<>();
        for (Product product : productList) {
            int quantity = shoppingCartService.selectByProductId(product.getId()).getQuantity();
            cartDetailList.add(new ShoppingCartDetail(product,quantity));
        }
        req.setAttribute("CartDetailList", cartDetailList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("shoppingCart.jsp");
        dispatcher.forward(req, resp);

    }

}
