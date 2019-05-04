package top.huzhurong.mysql.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.huzhurong.mysql.transaction.service.TestTransactionService;

import javax.annotation.Resource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlTransactionApplicationTests {

    @Resource
    private TestTransactionService testTransactionService;

    @Test
    public void contextLoads() throws InterruptedException {
        Thread.sleep(1_000L);
        testTransactionService.testUncheckedExceptionRollback();
    }

    @Test
    public void testUncheckedException() throws SQLException {
        testTransactionService.testCheckedException();
    }

    @Test
    public void testCheckedExceptionRollback() throws SQLException {
        testTransactionService.testCheckedExceptionRollback();
    }

    @Test
    public void testNestMethod() {
        testTransactionService.testNestMethod();
    }

    @Test
    public void testNestMethodException() {
        testTransactionService.testNestMethodRuntimeException();
    }

    @Test
    public void testNestMethodUncheckException() throws SQLException {
        testTransactionService.testNestMethodUncheckException();
    }

    @Test
    public void testNestMethodUncheckExceptionRollback() throws SQLException {
        testTransactionService.testNestMethodUncheckExceptionRollback();
    }

    @Test
    public void testMainMethodNoTransactionRuntimeException() {
        testTransactionService.testMainMethodNoTransactionRuntimeException();
    }

    @Test
    public void testMainMethodNoTransactionUncheckException() throws SQLException {
        testTransactionService.testProxyObjectGetTransaction();
    }

    @Test
    public void testFirstAndSecondTransaction() {
        testTransactionService.firstTransaction();
    }
}
