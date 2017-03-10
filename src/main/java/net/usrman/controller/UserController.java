package net.usrman.controller;



import net.usrman.model.User;
import net.usrman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class UserController {
    private UserService userService;


    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //===

    private int begin=0;
    private int step=14;
    private String nameSelect="";

    @RequestMapping(value = "usersList",method = RequestMethod.GET)
    public String userList(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("poleName",nameSelect);
        model.addAttribute("listUsers", this.userService.userList(this.begin,this.step,nameSelect));
        return "users";
    }
    @RequestMapping(value = "setName")
    public String setName(@ModelAttribute("nameSelect") String name){
        this.nameSelect=name;
        return "redirect:/usersList";
    }

    @RequestMapping(value="/nextPage")
    public String nextPage(){
        this.begin+=this.step;
        if(this.userService.userList(this.begin,this.step,nameSelect).isEmpty()){
            begin-=step;
        }
        return "redirect:/usersList";
    }
    @RequestMapping(value="/previousPage")
    public String previousPage(){
        this.begin-=this.step;
        if(begin<0) begin+=step;
        return "redirect:/usersList";
    }
    //===

    @RequestMapping(value="users",method = RequestMethod.GET)
    public String listUsers(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("listUsers",this.userService.listUsers());
        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            this.userService.addUser(user);
        } else {
            this.userService.updateUser(user);
        }
        return "redirect:/usersList";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        this.userService.removeUser(id);
        return "redirect:/usersList";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.userList(this.begin,this.step,this.nameSelect));
        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "userdata";
    }
}