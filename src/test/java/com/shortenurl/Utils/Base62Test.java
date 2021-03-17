package com.shortenurl.Utils;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class Base62Test {

    @Autowired
    private Base62 base62;

    @Test
    public void testEncodeAndDecode(){

        String originalURL = "https://developers.naver.com/notice";
        String newUrl =base62.encode(originalURL);
        System.out.println("ENCODE : " + newUrl + ", DECODE : " + base62.decode(newUrl));
//        System.out.println("---------------------------------");
//        String orgUrl = codec.decode("2Q3rKTOE");
//        System.out.println("orgUrl = " + orgUrl);
//        System.out.println("---------------------------------");
        assertEquals("https://developers.naver.com/notice", base62.decode(newUrl));
    }

}