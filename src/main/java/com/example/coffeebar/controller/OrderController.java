package com.example.coffeebar.controller;

import com.example.coffeebar.entity.Client;
import com.example.coffeebar.entity.Drink;
import com.example.coffeebar.entity.Order;
import com.example.coffeebar.entity.Personal;
import com.example.coffeebar.service.ClientService;
import com.example.coffeebar.service.MenuService;
import com.example.coffeebar.service.OrderService;
import com.example.coffeebar.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Controller
public class OrderController {


    private OrderService orderService;

    private MenuService menuService;

    private PersonalService personalService;

    private ClientService clientService;


    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setPersonalService(PersonalService personalService) {
        this.personalService = personalService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/order/add/client")
    public String orderAddClient(Model model) {
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("personal", personalService.getAllPersonal());
        return "order-addclient";
    }


    @GetMapping("/order/add")
    public String createOrder(
            @RequestParam(name = "id_client", required = false) Long id_client,
            @RequestParam(name = "id_personal", required = false) Long id_personal,
            @RequestParam(name = "id_order", required = false) Long id_order,
            Model model) {

        System.out.println("id_client = " + id_client);
        System.out.println("id_personal = " + id_personal);
        System.out.println("id_order = " + id_order);
        Order order;

        if (id_order != null) {
            order = orderService.findById(id_order);
        } else {
            Client client = clientService.findById(id_client);
            Personal personal = personalService.findById(id_personal);

            order = new Order();
            order.setClient(client);
            order.setPersonal(personal);
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));

            orderService.save(order);
        }
        model.addAttribute("order", order);
        model.addAttribute("drinks", menuService.getAllDrinks());
        model.addAttribute("deserts", menuService.getAllDeserts());
        model.addAttribute("personal", personalService.getAllPersonal());

        return "add-order";
    }


    @GetMapping("/order/updateDrink/{id_drink}/{id_order}")
    public String addDrinkToOrder(@PathVariable("id_drink") Long id_drink,
                                  @PathVariable("id_order") Long id_order) {
        Drink drink = menuService.getDrinkById(id_drink);
        Order order = orderService.findById(id_order);
        Set<Drink> drinkSet = order.getDrinkSet();
        drinkSet.add(drink);
        order.setDrinkSet(drinkSet);
        orderService.save(order);

        Long id_client = order.getClient().getIdClient();
        Long id_personal = order.getPersonal().getIdPersonal();
        id_order = order.getIdOrder();
        return "redirect:/order/add?id_client=" + id_client + "&id_personal=" + id_personal + "&id_order=" + id_order;
    }
}
