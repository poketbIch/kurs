package com.example.AttributesREU.Controllers;

import com.example.AttributesREU.Models.*;
import com.example.AttributesREU.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.criteria.Order;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;
@PreAuthorize("hasAnyAuthority('Клиент')")
@Controller
public class ClientController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChequeRepository chequeRepository;
    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }
    public String UserSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return name;
    }
    @GetMapping("/Poisk")
    public String regView(User user, Model model, Item currentItem, ClientOrder order)
    {
        List<Item> item=new ArrayList<Item>();

        model.addAttribute("item", currentItem);
        model.addAttribute("order", order);
        model.addAttribute("listItem", item);
        model.addAttribute("listCategory", categoryRepository.findAll());
        return "/Poisk";
    }
    @GetMapping("/ClientSearch/{categoryName}")
    public String searchCategory(User user, Model model, @PathVariable(name="categoryName") String categoryName, Item currentItem, ClientOrder order)
    {
        model.addAttribute("item", currentItem);
        model.addAttribute("order", order);
        model.addAttribute("listItem", itemRepository.findByCategoryCategoryName(categoryName));
        model.addAttribute("listCategory", categoryRepository.findAll());
        return "/Poisk";
    }
    @GetMapping("/ClientSearchName/{name}")
    public String searchItemByName(User user, Model model, @PathVariable(name="name") String itemName, Item currentItem, ClientOrder order)
    {
        model.addAttribute("item", currentItem);
        model.addAttribute("order", order);
        model.addAttribute("listItem", itemRepository.findByNameContaining(currentItem.name));
        model.addAttribute("listCategory", categoryRepository.findAll());
        return "/Poisk";
    }
    //Cheque + ClientOrder
    @PostMapping("/Client/Order/{id}")
    public String clientOrder(Model model, @PathVariable(name="id") Long id, Category category, Item currentItem, ClientOrder order)
    {
        Item itm=itemRepository.findById(id).orElseThrow();
        List<ClientOrder>orders=itm.getOrders();

       // items.add(itm);
        if(orders.size()<=1) {
            orders.add(order);
        }
        else {
            orders.set(1,order);
        }

        orderRepository.save(order);
        itm.setOrders(orders);
        itemRepository.save(itm);
        Cheque chq=new Cheque();
        chq.setDate_of_order(new Date());
        chq.setSummary(itm.Cost*order.amount);
        chq.setUser(userRepository.findUserByUsername(UserSession()));
        chq.setOrder(order);
        chequeRepository.save(chq);

        List<Item> item=new ArrayList<Item>();
        model.addAttribute("item", currentItem);
        model.addAttribute("order", order);
        model.addAttribute("listItem", item);
        model.addAttribute("listCategory", categoryRepository.findAll());

        return "/Poisk";
    }
    // Cheques page
    @GetMapping("/ClientCheque")
    public String getChequePage(User user, Model model, Item items, ClientOrder order)
    {
List<Cheque> chqs=chequeRepository.findAllByUser(userRepository.findUserByUsername(UserSession()));
        model.addAttribute("listCheque", chqs);
        model.addAttribute("order", order);
        List<Item> itemList=new ArrayList<Item>();
        for (Cheque chq:chqs
             ) {
           List<Item>currentItems= chq.getOrder().getItems();
            itemList.addAll(currentItems);


        }


        return "/ClientCheque";
    }

}
