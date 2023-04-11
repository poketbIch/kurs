package com.example.AttributesREU.Controllers;

import com.example.AttributesREU.Models.Role;
import com.example.AttributesREU.Models.User;
import com.example.AttributesREU.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AuthorizationController {
    @Autowired
    private PasswordEncoder passwordEncoder;
@Autowired
UserRepository userRepository;
    @GetMapping("/Registration")
    public String regView(User user, Model model)
    {
        Iterable<User> listUser= userRepository.findAll();

        model.addAttribute("listUser",listUser);

        return "/Registration";
    }

    @PostMapping("/Registration")
    public String regUser(

            @Valid User user,
            BindingResult bindingResultUs,
            Model model
    )

    {
        String regexp =
//                "^(?=.*[0-9])" +
                "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=\\S+$).{3,50}$";
        Pattern p=Pattern.compile(regexp);
        Matcher match=p.matcher(user.getPassword());
        if (( bindingResultUs.hasErrors()) || !match.matches())
        {
            model.addAttribute("user", user);


            if(user.getPhonenumber()!="" && bindingResultUs.hasErrors()) {
                bindingResultUs.rejectValue("phonenumber", "124", "Телефон должен быть введён в формате этого примера: 80125440293");
            }

            return "/Registration";
        }
        if( userRepository.findUserByUsername(user.getUsername())!=null)
        {
            model.addAttribute("error","Такой пользователь уже существует");
            return "/Registration";
        }
        user.setActive(true);
        if(userRepository.findAll()==null)
        {
            user.setRole(Collections.singleton(Role.Администратор));
        }
        else {
            user.setRole(Collections.singleton(Role.Клиент));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "redirect:/Login";
    }

}
