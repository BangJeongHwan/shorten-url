package com.shortenurl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shortenurl.Utils.Base62;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Base62 base62;

    @Test
    void hello() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void shortenUrl() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/shorten-url").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void redirectOrgUrl() throws Exception{

        String orgUrl = "https://www.musinsa.com";
        String newUrl = base62.encode(orgUrl);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/"+newUrl)
        )
        .andExpect(MockMvcResultMatchers.view().name("redirect:"+orgUrl));
    }

    @Test
    void dataSend() throws Exception{
        UrlController.MessageDTO messageDTO = new UrlController.MessageDTO();
        messageDTO.setData("https://www.musinsa.com");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/dataSend")
                .accept(MediaType.APPLICATION_JSON)
                .content(messageDTO.getData()) //Body 삽입
        )
        .andDo(MockMvcResultHandlers.print()) // 응답 데이터 출력
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("shortenUrl :: #resultDiv"));
    }
}