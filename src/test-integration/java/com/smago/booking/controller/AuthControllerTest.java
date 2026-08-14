package com.smago.booking.controller;

import com.smago.booking.ParentTest;
import com.smago.booking.dto.RegDataDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AuthControllerTest extends ParentTest {

    @Test
    void registerTest() {
        RegDataDto dto = new RegDataDto("123@ya.ru", "test-user", "password");
        MvcResult mvcResult = performPost("/api/v1/auth/register", dto);

        assertEquals(201, mvcResult.getResponse().getStatus());
    }
}