package com.ensep.petshelter.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "public endpoint";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "private endpoint";
    }
}
