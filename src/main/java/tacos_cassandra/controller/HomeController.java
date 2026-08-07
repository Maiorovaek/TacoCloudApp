package tacos_cassandra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //этот метод обрабатывает HTTP-запросы GET с корневым путем /
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
