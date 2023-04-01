package nus.iss.day22workshop_practice_joel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import nus.iss.day22workshop_practice_joel.models.Order;
import nus.iss.day22workshop_practice_joel.models.OrderDetail;
import nus.iss.day22workshop_practice_joel.services.OrderServices;


@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @GetMapping
    public String getIndexPage(Model model, HttpSession session){

        Order order = getOrder(session);

        int nextId = orderServices.getNextOrderId();

        model.addAttribute("nextid", nextId); //as hidden form field
        model.addAttribute("orderlist", order); //generate current list of orderdetails
        model.addAttribute("incomingOrderDetail", new OrderDetail()); //for new orderdetail
        model.addAttribute("listOfOrderDetails", order.getListOfOrderDetails());


        return "index";
    }

    @PostMapping("/add")
    public String addOrderDetail(OrderDetail incomingOrderDetail, HttpSession session){

        return "index";
    }

    @GetMapping("/shippingaddress")
    public String getShippingAddressPage(Model model, HttpSession session){

        return "shippingaddress";
    }

    private Order getOrder(HttpSession sess) {
		Order order = (Order)sess.getAttribute("order");
		if (null == order) {
			order = new Order();
			sess.setAttribute("order", order);
		}
		return order;
	}
    
    
}
