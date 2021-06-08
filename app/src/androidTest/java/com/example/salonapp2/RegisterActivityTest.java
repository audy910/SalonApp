package com.example.salonapp2;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

public class RegisterActivityTest extends TestCase
{
    @Test
    public void workingEmailWithWeirdCharacter()
    {
        Boolean expected = false;
        assertEquals(expected,RegisterActivity.emailRegex("audreyReinhard#!$^*_.@mvusd.com"));
    }

    @Test
    public void workingEmailAllLetters()
    {
        Boolean expected = false;
        assertEquals(expected,RegisterActivity.emailRegex("audreyReinhard@mvusd.com"));
    }
    @Test
    public void workingEmailAllNumbers()
    {
        Boolean expected = false;
        assertEquals(expected,RegisterActivity.emailRegex("91085566@mvusd.net"));

    }
    @Test
    public void workingEmailWithDotIO()
    {
        Boolean expected = false;
        assertEquals(expected,RegisterActivity.emailRegex("91085566@mvusd.io"));

    }
    @Test
    public void emailWithoutEnding()
    {
        Boolean expected = true;
        assertEquals(expected,RegisterActivity.emailRegex("91085566@mvusd."));

    }
    @Test
    public void emailJustName()
    {
        Boolean expected = true;
        assertEquals(expected,RegisterActivity.emailRegex("91085566"));

    }
    @Test
    public void emailNoDot()
    {
        Boolean expected = true;
        assertEquals(expected,RegisterActivity.emailRegex("91085566@mvusd"));

    }
    @Test
    public void emailNoAt()
    {
        Boolean expected = true;
        assertEquals(expected,RegisterActivity.emailRegex("91085566mvusd.com"));

    }

}