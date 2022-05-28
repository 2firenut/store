package com.firenut.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {

    @Autowired
    private DataSource datasource;


    @Test
    void contextLoads() {
    }

    @Test
    void getConnection() throws SQLException {  //测试连接
        System.out.println(datasource.getConnection());
    }

}
