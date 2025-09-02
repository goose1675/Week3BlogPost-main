//ignore
package com.agam.pcc.blogpost;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class test {


    @GetMapping("/")
    
    
    public String hello(){
        return "Hello World";
    }
    
}
