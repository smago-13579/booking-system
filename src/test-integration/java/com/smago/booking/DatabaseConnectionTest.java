package com.smago.booking;

import org.junit.jupiter.api.Test;

import java.sql.Connection;


public class DatabaseConnectionTest extends ParentTest {

    @Test
    void testDatabaseConnection() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            assert connection.isValid(1);
        }
    }
}
