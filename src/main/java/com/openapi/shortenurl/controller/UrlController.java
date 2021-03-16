package com.openapi.shortenurl.controller;

import com.openapi.shortenurl.Utils.Codec;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UrlController {

    @Autowired
    private Codec codec;

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","안녕하세요!!!!!!!!!");
        return "hello";
    }

    @GetMapping("shorten-url")
    public String shortenUrl(Model model){
        return "shortenUrl";
    }

    @RequestMapping(value = "/dataSend",method = RequestMethod.POST)
    public String dataSend(Model model,MessageDTO dto){

        String newUrl = codec.encode(dto.getData());
        model.addAttribute("result",newUrl);

        return "/shortenUrl :: #resultDiv";
    }

    @Data
    static class MessageDTO {
        private String data;
    }

    @GetMapping("/{newUrl}")
    public String hello(@PathVariable("newUrl") String newUrl){
        String orgUrl = codec.decode(newUrl);
        return "redirect:"+orgUrl;
    }
}
