package vn.hoidanit.laptopshop.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class test {
    @GetMapping("/test")
    public String getTestPage() {
        return "test/show";
    }

}
