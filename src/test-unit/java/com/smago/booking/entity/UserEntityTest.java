package com.smago.booking.entity;

import com.smago.booking.dto.RegDataDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserEntityTest {

    @Test
    void userWithoutRoles_ThrowsException() {
        RegDataDto dto = new RegDataDto("123@ya.ru", "test-user", "password");
        assertThrows(IllegalArgumentException.class, () -> UserEntity.toUser(dto, dto.password()));
    }
}
