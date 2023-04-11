package com.example.AttributesREU.Controllers;

import com.example.AttributesREU.Models.*;
import com.example.AttributesREU.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
@PreAuthorize("hasAnyAuthority('Администратор')")
@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    ItemRepository itemRepository;
    @GetMapping("/Admin")
    public String adminView(User user, Model model)
    {
        model.addAttribute("listRole", Role.values());
        model.addAttribute("listUser",userRepository.findAll());
        return "/Admin";
    }
    //Account
    @PostMapping("/Admin/AccountEdit/{id}")
    public String adminEdit(@PathVariable(name="id") Long id,

                            @RequestParam(name="role[]") String[] userRoles,
                            Model model)
    {
        User user =userRepository.findById(id).orElseThrow();
        user.getRole().clear();
        for (String roleOne:
                userRoles)
        {
            user.getRole().add(Role.valueOf(roleOne));
        }
        userRepository.save(user);
        model.addAttribute("listRole", Role.values());
        model.addAttribute("listUser",userRepository.findAll());
        return "/Admin";
    }
//Category
    @GetMapping("/AdminCategory")
    public String adminCategory( Model model, Category category)
    {

        model.addAttribute("trueCategory",category);
        model.addAttribute("listCategory", categoryRepository.findAll());

        return "/AdminCategory";
    }

    @PostMapping("/AdminCategory/CategoryAdd/")
    public String adminAddCategory( Model model, Category category)
    {
        if(categoryRepository.findByCategoryName(category.getCategoryName()).size()==0) {
            categoryRepository.save(category);
        }
        model.addAttribute("trueCategory",category);
        model.addAttribute("listCategory", categoryRepository.findAll());

        return "redirect:/AdminCategory";
    }
    @PostMapping("/AdminCategory/CategoryEdit/{id}")
    public String adminEditCategory(@PathVariable(name="id")Long id, Model model, Category category)
    {
        Category cat=categoryRepository.findById(id).orElseThrow();
        cat.setCategoryName(category.getCategoryName());
        categoryRepository.save(cat);
        model.addAttribute("trueCategory",category);
        model.addAttribute("listCategory", categoryRepository.findAll());

        return "redirect:/AdminCategory";
    }
    @PostMapping("/AdminCategory/CategoryDelete/{id}")
    public String adminDeleteCategory(@PathVariable(name="id")Long id, Model model, Category category)
    {
        Category cat=categoryRepository.findById(id).orElseThrow();
        if(cat.getItems().size()==0) {
            categoryRepository.delete(cat);
        }
        model.addAttribute("trueCategory",category);
        model.addAttribute("listCategory", categoryRepository.findAll());

        return "redirect:/AdminCategory";
    }
// Supplier
    @GetMapping("/AdminSupplier")
    public String adminSupplier( Model model, Supplier supplier)
    {

        model.addAttribute("supplier",supplier);
        model.addAttribute("listSupplier", supplierRepository.findAll());

        return "/AdminSupplier";
    }
    @PostMapping("/AdminSupplier/SupplierAdd/")
    public String adminAddSupplier( Model model, Supplier supplier)
    {

        supplierRepository.save(supplier);
        model.addAttribute("supplier",supplier);
        model.addAttribute("listSupplier", supplierRepository.findAll());

        return "redirect:/AdminSupplier";
    }
    @PostMapping("/AdminSupplier/SupplierEdit/{id}")
    public String adminEditSupplier(@PathVariable(name="id")Long id, Model model, Supplier supplier)
    {
        Supplier sup=supplierRepository.findById(id).orElseThrow();
        sup.setContactInfo(supplier.getContactInfo());
        sup.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(sup);
        model.addAttribute("supplier",supplier);
        model.addAttribute("listSupplier", supplierRepository.findAll());

        return "redirect:/AdminSupplier";
    }
    @PostMapping("/AdminSupplier/SupplierDelete/{id}")
    public String adminDeleteSupplier(@PathVariable(name="id")Long id, Model model, Supplier supplier)
    {
        Supplier sup=supplierRepository.findById(id).orElseThrow();
        if(sup.getStorages().size()==0)
        supplierRepository.delete(sup);
        model.addAttribute("supplier",supplier);
        model.addAttribute("listSupplier", supplierRepository.findAll());

        return "redirect:/AdminSupplier";
    }

    //Storage
    @GetMapping("/AdminStorage")
    public String adminSupplier( Model model, Storage storage)
    {

        model.addAttribute("storage",storage);
        List<Storage> storages= storageRepository.findAll();
        model.addAttribute("listStorage",storages);
        model.addAttribute("listSuppliers", supplierRepository.findAll());

        return "/AdminStorage";
    }
    @PostMapping("/AdminStorage/StorageAdd/")
    public String adminAddStorage( Model model, Storage storage, @RequestParam Long listSuppliers)
    {
        storage.setSupplier(supplierRepository.findById(listSuppliers).orElseThrow());
        storageRepository.save(storage);
        model.addAttribute("storage",storage);
        model.addAttribute("listStorage", storageRepository.findAll());
        model.addAttribute("listSupplier", supplierRepository.findAll());

        return "redirect:/AdminStorage";
    }
    @PostMapping("/AdminStorage/StorageEdit/{id}")
    public String adminEditStorage(@PathVariable(name="id")Long id, @RequestParam Long listSuppliers, Model model, Supplier supplier,Storage storage)
    {
        Storage stor=storageRepository.findById(id).orElseThrow();
        stor.setSupplier(supplierRepository.findById(listSuppliers).orElseThrow());
        stor.setAdresStorage(storage.getAdresStorage());

        storageRepository.save(stor);
        model.addAttribute("storage",storage);
        model.addAttribute("listStorage", storageRepository.findAll());
        model.addAttribute("listSupplier", supplierRepository.findAll());

        return "redirect:/AdminStorage";
    }
    @PostMapping("/AdminStorage/StorageDelete/{id}")
    public String adminDeleteStorage(@PathVariable(name="id")Long id, Model model, Supplier supplier,Storage storage)
    {
        Storage stor=storageRepository.findById(id).orElseThrow();
    if(stor.getItems().size()==0)
        storageRepository.delete(stor);
        model.addAttribute("storage",storage);
        model.addAttribute("listStorage", storageRepository.findAll());
        model.addAttribute("listSupplier", supplierRepository.findAll());

        return "redirect:/AdminStorage";
    }

    // Item
    @GetMapping("/AdminItem")
    public String adminItem( Model model, Item item)
    {

        model.addAttribute("item",item);
        List<Storage> storages= storageRepository.findAll();
        model.addAttribute("listStorage",storages);
        model.addAttribute("listCategory", categoryRepository.findAll());
        model.addAttribute("listItem",itemRepository.findAll());

        return "/AdminItem";
    }
    @PostMapping("/AdminItem/ItemAdd/")
    public String adminAddItem( Model model, Item item, @RequestParam Long listStorage, @RequestParam Long listCategory)
    {
        item.setCategory(categoryRepository.findById(listCategory).orElseThrow());
        item.setStorage(storageRepository.findById(listStorage).orElseThrow());
        itemRepository.save(item);
      //  model.addAttribute("item",item);
        List<Storage> storages= storageRepository.findAll();
        model.addAttribute("listStorage",storages);
        model.addAttribute("listItem",itemRepository.findAll());
        model.addAttribute("listCategory", categoryRepository.findAll());

        return "redirect:/AdminItem";
    }
    @PostMapping("/AdminItem/ItemEdit/{id}")
    public String adminEditStorage(@PathVariable(name="id")Long id, @RequestParam Long listStorage, @RequestParam Long listCategory, Model model, Item item,Storage storage)
    {
        Item itm=itemRepository.findById(id).orElseThrow();
        itm.setStorage(storageRepository.findById(listStorage).orElseThrow());
        itm.setCategory(categoryRepository.findById(listCategory).orElseThrow());
        itm.setArticul(item.articul);
        itm.setCost(item.Cost);
        itm.setBrand(item.brand);
        itm.setColor(item.color);
        itm.setDescription(item.description);
        itm.setName(item.name);
        itm.setLength(item.length);
        itm.setProfessionalism(item.professionalism);
        itm.setReleaseYear(item.releaseYear);
        itm.setPhoto(item.photo);
        itemRepository.save(itm);
       // model.addAttribute("item",item);
        List<Storage> storages= storageRepository.findAll();
        model.addAttribute("listStorage",storages);
        model.addAttribute("listItem",itemRepository.findAll());
        model.addAttribute("listCategory", categoryRepository.findAll());

        return "redirect:/AdminItem";
    }
    @PostMapping("/AdminItem/ItemDelete/{id}")
    public String adminDeleteItem(@PathVariable(name="id")Long id, Model model, Item item,Storage storage)
    {
        Item itm=itemRepository.findById(id).orElseThrow();
        if(itm.getOrders().size()==0)
        itemRepository.delete(itm);
      //  model.addAttribute("item",item);
        List<Storage> storages= storageRepository.findAll();
        model.addAttribute("listStorage",storages);
        model.addAttribute("listItem",itemRepository.findAll());
        model.addAttribute("listCategory", categoryRepository.findAll());

        return "redirect:/AdminItem";
    }
}
