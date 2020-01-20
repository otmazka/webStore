/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Buyer;
import entity.History;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.BuyerFacade;
import session.HistoryFacade;
import session.ProductFacade;
import session.UserFacade;

/**
 *
 * @author Sveta
 */
@WebServlet(name = "ControllerStore", urlPatterns = {
    "/login",
    "/showLogin",
    "/newProduct",
    "/addProduct",
    "/listProducts",
    "/newBuyer",
    "/addBuyer",
    "/listBuyers",
    "/showBuyProducts",
    "/buyProduct",
    "/showBoughtProducts",
    "/boughtProduct"
})
public class ControllerStore extends HttpServlet {

    @EJB
    ProductFacade productFacade;
    @EJB
    BuyerFacade buyerFacade;
    @EJB
    HistoryFacade historyFacade;
    @EJB
    UserFacade userFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/newProduct":
                request.getRequestDispatcher("/newProduct.jsp")
                        .forward(request, response);
                break;

            case "/addProduct":
                String title = request.getParameter("title");
                String model = request.getParameter("model");
                String price = request.getParameter("price");
                String quantity = request.getParameter("quantity");
                Product product = new Product(title, model, Integer.parseInt(price), Integer.parseInt(quantity));
                productFacade.create(product);
                request.setAttribute("info", "Новый телефон добавлен");
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;

            case "/listProducts":
                List<Product> listProducts = productFacade.findAll();//vqvodim spisok knig
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/listProducts.jsp")
                        .forward(request, response);
                break;

            case "/newBuyer":
                request.getRequestDispatcher("/newBuyer.jsp")
                        .forward(request, response);
                break;

            case "/addBuyer":
                String name = request.getParameter("name");
                String lastname = request.getParameter("lastname");
                String email = request.getParameter("email");
                String money = request.getParameter("money");
                Buyer buyer = new Buyer(name, lastname, Integer.parseInt(money), email);// dolznq stojat na svojom meste, kak v kostruktore
                buyerFacade.create(buyer);
                request.setAttribute("info", "Покупатель создан");
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;

            case "/listBuyers":
                List<Buyer> listBuyers = buyerFacade.findAll();//vqvodim spisok читателей
                request.setAttribute("listBuyers", listBuyers);
                request.getRequestDispatcher("/listBuyers.jsp")
                        .forward(request, response);
                break;

            case "/showLogin":
                request.getRequestDispatcher("/showLogin.jsp")
                        .forward(request, response);
                break;

            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                if ("ivan".equals(login) && "123123".equals(password)) {
                    request.setAttribute("info", "Привет, " + login + "!");
                } else {
                    request.setAttribute("info", "Неправильный логин или пароль!");
                }
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;

            case "/showBuyProducts":
                listBuyers = buyerFacade.findAll();
                listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.setAttribute("listBuyers", listBuyers);
                request.getRequestDispatcher("/showBuyProducts.jsp")
                        .forward(request, response);
                break;

            case "/buyProduct":
                String productId = request.getParameter("productId");
                String buyerId = request.getParameter("buyerId");
                product = productFacade.find(Long.parseLong(productId));
                buyer = buyerFacade.find(Long.parseLong(buyerId));
                if (product.getCount() > 0) {
                    if (buyer.getMoney() - product.getPrice() >= 0) {
                        product.setCount(product.getCount() - 1);
                        productFacade.edit(product);
                        buyer.setMoney(buyer.getMoney() - product.getPrice());
                        buyerFacade.edit(buyer);
                        History history = new History();
                        history.setProduct(product);
                        history.setBuyer(buyer);
                        history.setTakeOn(new Date());
                        historyFacade.create(history);
                        request.setAttribute("info", "Телефон \"" + product.getTitle() + "\"куплен клиентом: " + buyer.getName() + " " + buyer.getLastname());
                    } else {
                        request.setAttribute("info", "Недостаточно средств");
                    }

                } else {
                    request.setAttribute("info", "Все телефоны проданы");
                }
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;

            case "/showBoughtProducts":
                List<History> listHistories = historyFacade.findBuyProduct();
                request.setAttribute("listHistories", listHistories);
                request.getRequestDispatcher("/showBoughtProducts.jsp")
                        .forward(request, response);
                break;
                
                case "/boughtProduct":
                String historyId = request.getParameter("historyId");
                History history = historyFacade.find(Long.parseLong(historyId));
                history.setTakeOn(new Date());
                historyFacade.edit(history);
                request.setAttribute("info",
                            "Телефон \""
                            + history.getProduct().getTitle()
                            + " " + history.getProduct().getModel()
                            + "\" продан покупателю: "
                            + history.getBuyer().getName()
                            + " " + history.getBuyer().getLastname()
                );
                request.getRequestDispatcher("/index.jsp")
                            .forward(request, response);
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
