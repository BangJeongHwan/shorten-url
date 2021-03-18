package com.shortenurl.controller;

import com.shortenurl.Utils.Base62;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UrlController {

    @Autowired
    private Base62 base62;

    /**
     * 기본 화면
     * @param model
     * @return
     */
    @GetMapping("/")
    public String hello(Model model, HttpServletRequest request){
        model.addAttribute("data",request.getRequestURL()+"shorten-url 주소를 입력해주세요!");
        return "hello";
    }

    /**
     * shorten URL 변환 화면
     * @param model
     * @return
     */
    @GetMapping("shorten-url")
    public String shortenUrl(Model model){
        return "shortenUrl";
    }

    /**
     * shorten URL 호출시 direct 하는 화면
     * @param newUrl
     * @return
     */
    @GetMapping("/{newUrl}")
    public String redirectOrgUrl(@PathVariable("newUrl") String newUrl){
        String orgUrl = base62.decode(newUrl);
        return "redirect:"+orgUrl;
    }

    /**
     * Url 변환 ajax 처리
     * @param model
     * @param dto
     * @return
     */
    @RequestMapping(value = "/dataSend",method = RequestMethod.POST)
    public String dataSend(Model model,MessageDTO dto,HttpServletRequest request){

        String shortUrl = base62.encode(dto.getData());
        int lastIndex = request.getRequestURL().lastIndexOf("/");
        String fullUrl = request.getRequestURL().substring(0,lastIndex)+"/"+shortUrl;
        model.addAttribute("result",fullUrl);

        return "shortenUrl :: #resultDiv";
    }

    @Data
    static class MessageDTO {
        private String data;
        private String result;
    }

}
