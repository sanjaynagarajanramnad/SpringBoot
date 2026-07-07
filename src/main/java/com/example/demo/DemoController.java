package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class DemoController {
    @GetMapping("/hello")
    String Hello(){
        return "Hello World";
    }
    @GetMapping("/name/{name}")
    String name(@PathVariable String name){
        return "Hello "+name;
    }

    @GetMapping("/Req")
    String nameReq(@RequestParam String id){
        return id;
    }

}
