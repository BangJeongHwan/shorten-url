package com.openapi.shortenurl.Utils;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Base62Test {
    @Test
    public void testEncodeAndDecode() throws Exception{
        String encodedReservationNo = Base62.encodeToLong(1234512345);
        System.out.println("ENCODE : "+encodedReservationNo+", DECODE : " + Base62.decodeToLong(encodedReservationNo));
        assertEquals(1234512345, Base62.decodeToLong(encodedReservationNo));
    }

    @Test
    public void testRandomEncodeAndDecode() throws Exception{
        for(int i=0; i < 10; i++) {
            Random rnd = new Random();
            long value = Math.abs(rnd.nextLong() % 10000000000L);
            System.out.println("VALUE : " + value);
            String encodedReservationNo = Base62.encodeToLong(value);
            System.out.println("ORIGINAL : " + value + ", ENCODE : " + encodedReservationNo + ", DECODE : " + Base62.decodeToLong(encodedReservationNo));
            assertEquals(value, Base62.decodeToLong(encodedReservationNo));
        }
    }
}