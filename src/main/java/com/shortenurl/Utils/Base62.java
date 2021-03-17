package com.shortenurl.Utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class Base62 {
    //
    /**
     * 8 Character set counter
     */
    private static Long counter = 10000000000000L;

    private static Map<Long, String> indexToUrl = new HashMap<>();
    private static Map<String, Long> urlToIndex = new HashMap<String, Long>();
    private static Map<Long, Long> callUrlCnt = new HashMap<Long, Long>();

    /**
     * Base62 Character
     */
    static String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Encodes a original URL to its shortened URL.
     *
     * @return the base 62 string of an integer
     */
    public String encode(String longUrl) {
        if (urlToIndex.containsKey(longUrl)) {
            return base62Encode(urlToIndex.get(longUrl));
        }
        else {
            indexToUrl.put(counter, longUrl);
            urlToIndex.put(longUrl, counter);
            callUrlCnt.put(counter,0L);
            counter++;
            return base62Encode(urlToIndex.get(longUrl));
        }
    }


    /**
     * Decodes a shortened URL to its original URL.
     *
     * @return the base 62 value of a string.
     */
    public String decode(String shortUrl) {
        String base62Encoded = shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
        long decode = 0;
        for(int i = 0; i < base62Encoded.length(); i++) {
            decode = decode * 62 + base62.indexOf("" + base62Encoded.charAt(i));
        }
        if(callUrlCnt.get(decode)!=null) {
            callUrlCnt.put(decode, callUrlCnt.get(decode) + 1);
            log.info("'"+indexToUrl.get(decode)+"' Url call Count : {}건", callUrlCnt.get(decode));
        }
        return indexToUrl.get(decode);
    }

    private String base62Encode(long value) {
        StringBuilder sb = new StringBuilder();
        while (value != 0) {
            sb.append(base62.charAt((int)(value % 62)));
            value /= 62;
        }
        while (sb.length() < 6) {
            sb.append(0);
        }
        return sb.reverse().toString();
    }

}
