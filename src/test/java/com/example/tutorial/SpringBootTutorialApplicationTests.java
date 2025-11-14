package com.example.tutorial;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

@SpringBootTest
class SpringBootTutorialApplicationTests {

    @Test
    void contextLoads() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "123456@Tan";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("Driver Name: " + metaData.getDriverName());
            System.out.println("Database Version: " + metaData.getDatabaseProductVersion());
            System.out.println("连接成功!");
            conn.close();
        } catch (Exception e) {
            System.out.println("连接失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
