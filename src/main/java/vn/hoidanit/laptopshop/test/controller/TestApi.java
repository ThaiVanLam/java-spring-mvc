package vn.hoidanit.laptopshop.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class TestApi {
    @GetMapping("/test/restTemplate")
    public String getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://catfact.ninja/fact";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String responseBody = response.getBody();
        System.out.println("Response Body: " + responseBody);
        return "";
    }

}
