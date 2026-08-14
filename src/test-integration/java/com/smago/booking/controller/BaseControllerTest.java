package com.smago.booking.controller;

import com.smago.booking.ParentTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class BaseControllerTest extends ParentTest {

    @Test
    void ping() throws UnsupportedEncodingException {
        MvcResult mvcResult = performGet("/public/ping");
        assertEquals(200, mvcResult.getResponse().getStatus());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("pong"));
    }
}