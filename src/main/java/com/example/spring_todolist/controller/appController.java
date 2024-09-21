package com.example.spring_todolist.controller;

import com.example.spring_todolist.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
/*Thymeleaf 練習
https://ithelp.ithome.com.tw/articles/10240823
https://ithelp.ithome.com.tw/articles/10241526
 */
@Controller
public class appController {

    @GetMapping("/helloworld")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World");
        return "helloworld-view"; // 返回模板名稱，而不是 URL
    }

    //條件判斷練習
    @GetMapping("/person")
    public String showGender(Model model){
        model.addAttribute("gender", "female");
        return "person";
    }

    //可迭代物件練習
    @GetMapping("/list")
    public String listNumber(Model model){
        List<String> list = new ArrayList<>();
        for(int i = 0; i <= 10; i++){
            list.add("This is ArrayList" + i);
        }
        model.addAttribute("list", list);
        return "list";
    }

    //物件屬性簡寫練習 直接使用之前創好的user而非照著教學創個person
    @GetMapping("/form")
    public String form(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "form-view";
    }
    //物件屬性簡寫練習 顯示上面輸入的form中的資料
    @PostMapping("/add")
    public String add(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "add";
    }

}
