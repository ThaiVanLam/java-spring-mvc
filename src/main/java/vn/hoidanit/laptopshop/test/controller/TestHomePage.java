package vn.hoidanit.laptopshop.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestHomePage {
    @GetMapping("/test")
    public String getTestPage() {
        return "test/show";
    }

    @GetMapping("/test/homepage")
    public String getHomePage() {
        return "test/client/product_detail";
    }

}
