package com.demo.gamechangesns;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date finalDate = cal.getTime();
        Calendar calendar =Calendar.getInstance();
        Date currentDate =calendar.getTime();
        if(currentDate.before(finalDate)) {
            assertEquals(4, 2 + 2);
        }


    }
}