package com.openapi.shortenurl.Utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CodecTest {

    @Autowired
    private Codec codec;

    @Test
    public void Autowired_encCodec_TEST() {
        String originalURL = "https://developers.naver.com/notice";
        String newUrl = codec.encode(originalURL);
        System.out.println("ENCODE : " + newUrl + ", DECODE : " + codec.decode(newUrl));
        System.out.println("---------------------------------");
        String orgUrl = codec.decode("2Q3rKTOE");
        System.out.println("orgUrl = " + orgUrl);
        System.out.println("---------------------------------");
        assertEquals("https://developers.naver.com/notice", codec.decode(newUrl));
    }
}