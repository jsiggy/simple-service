package com.training.service.users;

import java.util.Calendar;
import java.util.Date;

public class UserTestUtil {
    static Date createBirthdate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
