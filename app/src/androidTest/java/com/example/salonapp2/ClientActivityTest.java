package com.example.salonapp2;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientActivityTest {
    //phoneNumberRegex tests
    @Test
    public void allLetters() {
        Boolean expected = true;
        assertEquals(expected, ClientActivity.phoneRegex("phoneeeeeeeee"));
    }
    @Test
    public void notAllExclusivleyNums() {
        Boolean expected = true;
        assertEquals(expected, ClientActivity.phoneRegex("9512033029hello"));
    }
    @Test
    public void allNumsNotEnough() {
        Boolean expected = true;
        assertEquals(expected, ClientActivity.phoneRegex("9512033"));
    }
    @Test
    public void allNumsTooMany() {
        Boolean expected = true;
        assertEquals(expected, ClientActivity.phoneRegex("9512223333444445"));
    }
    @Test
    public void numsAndDashes() {
        Boolean expected = true;
        assertEquals(expected, ClientActivity.phoneRegex("951-294-3562"));
    }
    @Test
    public void lowEdgeCorrect() {
        Boolean expected = false;
        assertEquals(expected, ClientActivity.phoneRegex("9512334567"));
    }
    @Test
    public void highEdgeCorrect() {
        Boolean expected = false;
        assertEquals(expected, ClientActivity.phoneRegex("16849512334567"));
    }
}