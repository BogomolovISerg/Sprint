package org.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web.model.Product;
import org.web.service.ProductService;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import javax.sql.DataSource;
import java.util.ArrayList;

@WebServlet(name = "ListProduct", urlPatterns = "/listproduct")
public class ListProduct extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(ListProduct.class);

    private Connection conn;
    private DataSource ds;

    @EJB
    private ProductService productService;

       @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        logger.info("print list product");

        resp.getWriter().printf("<table border=\"1\" >");
        resp.getWriter().printf("<caption>Список продуктов</caption>");
        resp.getWriter().printf("<tr><th>Код</th><th>Наименование</th><th>Цена</th></tr>");

        productService.getAllProduct().forEach(e ->{
            try{
                resp.getWriter().printf(e.printProduct());
            }catch (IOException ex){
                ex.printStackTrace();
            }
        });

        resp.getWriter().printf("</table>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New POST request"); resp.getWriter().printf("<h1>New POST request</h1>");
    }
}
