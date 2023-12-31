package com.boilerplate.common.config.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/docs/")
@Controller
public class ServingDocsController {

    @GetMapping("/restdocs")
    public String toRestdocsIndex() {
        return "redirect:/docs/restdocs/index.html";
    }

    @GetMapping("/swagger")
    public String toSwaggerIndex() {
        return "redirect:/docs/swagger/index.html";
    }
}
