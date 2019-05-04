package top.huzhurong.mysql.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class MysqlTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlTransactionApplication.class, args);
    }

}
