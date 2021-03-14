package com.openapi.shortenurl.controller;

import com.openapi.shortenurl.Utils.Codec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String hello(Model model, @RequestParam("orgUrl") String orgUrl){
        String newUrl = codec.encode(orgUrl);
        model.addAttribute("data",newUrl);
        return "hello";
    }

    @GetMapping("/{newUrl}")
    public String hello(@PathVariable("newUrl") String newUrl){
        String orgUrl = codec.decode(newUrl);
        return "redirect:"+orgUrl;
    }
/*
    @PostMapping(value = "/shorten-url/find")
    public String find(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(),
                form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        memberService.join(member);
        return "redirect:/";
    }*/
}
