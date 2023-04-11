package com.example.AttributesREU.Controllers;

import com.example.AttributesREU.Models.ClientOrder;
import com.example.AttributesREU.Models.Item;
import com.example.AttributesREU.Models.User;
import com.example.AttributesREU.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("permitAll")

@Controller
public class AnonController {
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
    @GetMapping("/PoiskAnon")
    public String regView(User user, Model model, Item currentItem, ClientOrder order)
    {
        List<Item> item=new ArrayList<Item>();

        model.addAttribute("item", currentItem);
        model.addAttribute("order", order);
        model.addAttribute("listItem", item);
        model.addAttribute("listCategory", categoryRepository.findAll());
        return "/PoiskAnon";
    }
    @GetMapping("/PoiskAnon/AnonClientSearch/{categoryName}")
    public String searchCategorys(User user, Model model, @PathVariable(name="categoryName") String categoryName, Item currentItem, ClientOrder order)
    {
        model.addAttribute("item", currentItem);
        model.addAttribute("order", order);
        model.addAttribute("listItem", itemRepository.findByCategoryCategoryName(categoryName));
        model.addAttribute("listCategory", categoryRepository.findAll());
        return "/PoiskAnon";
    }
    @GetMapping("/PoiskAnon/AnonClientSearchName/{name}")
    public String searchItemByNames(User user, Model model, @PathVariable(name="name") String itemName, Item currentItem, ClientOrder order)
    {
        model.addAttribute("item", currentItem);
        model.addAttribute("order", order);
        model.addAttribute("listItem", itemRepository.findByNameContaining(currentItem.name));
        model.addAttribute("listCategory", categoryRepository.findAll());
        return "/PoiskAnon";
    }
}
