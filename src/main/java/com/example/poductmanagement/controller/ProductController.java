package com.example.poductmanagement.controller;

import com.example.poductmanagement.model.dao.impl.ProductDAO;
import com.example.poductmanagement.model.entity.Product;
import com.example.poductmanagement.model.entity.ShoppingCart;
import com.example.poductmanagement.model.service.IProductService;
import com.example.poductmanagement.model.service.IShoppingCartService;
import com.example.poductmanagement.model.service.impl.ProductService;
import com.example.poductmanagement.model.service.impl.ShoppingCartService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    private IProductService productService;
    private IShoppingCartService shoppingCartService;
    @Override
    public void init() {
        productService = new ProductService();
        shoppingCartService = new ShoppingCartService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action ="";
        }
        if (action.equals("add")) {
            int id = Integer.parseInt(req.getParameter("id"));
            ShoppingCart shoppingCart = new ShoppingCart(id, 1);
            shoppingCartService.insert(shoppingCart);
            resp.sendRedirect("/shoppingCart?action=list");
        } else {
            List<Product> productList = productService.selectAll();
            req.setAttribute("productList", productList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }

    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int id = Integer.parseInt(req.getParameter("id"));
//        ShoppingCart shoppingCart = new ShoppingCart(id, 1);
//        shoppingCartService.insert(shoppingCart);
//        resp.sendRedirect("/shoppingCart?action=list");
//    }
}
