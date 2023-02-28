package com.example.paze.controller;

import com.example.paze.service.PspService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {

    private final PspService pspService;

    public PaymentController(PspService pspService) {
        this.pspService = pspService;
    }

    @GetMapping("/")
    public String showNumberInputForm(Model model) {
        model.addAttribute("number", 0);
        return "index";
    }

    @PostMapping("/submit")
    public String submitNumberInputForm(@RequestParam("numberInput") int number) {
        String redirectUrl = pspService.getRedirectUrl(number);
        return "redirect:" + redirectUrl;
    }

}

