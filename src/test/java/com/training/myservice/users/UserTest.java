package com.training.myservice.users;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserTest {

    @Test
    public void shouldCompareEqualUsers() {
        Date birthdate = new Date();
        assertEquals(new User("Joe", birthdate), new User("Joe", birthdate));
    }

    @Test
    public void shouldCompareUnequalUsersWithDifferentName() {
        Date birthdate = new Date();
        assertNotEquals(new User("Joe", birthdate), new User("Bob", birthdate));
    }

    @Test
    public void shouldCompareUnequalUsersWithDifferentBirthdates() {
        Date birthdate1 = new Date();
        Date birthdate2 = new Date();
        birthdate2.setTime(100000l);
        assertNotEquals(new User("Joe", birthdate1), new User("Joe", birthdate2));
    }

    @Test
    public void shouldDisplayUserToStringProperly() {
        Date birthdate = new Date();
        assertEquals("User(id=null, name=Joe, birthdate=" + birthdate.toString() + ")", new User("Joe", birthdate).toString());
    }
}