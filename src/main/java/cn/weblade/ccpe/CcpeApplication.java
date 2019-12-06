package cn.weblade.ccpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;


//开启所有事务
@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages = "cn.weblade.ccpe.dao")
//扫描所有需要的包
public class CcpeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcpeApplication.class, args);
    }

}
