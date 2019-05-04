package top.huzhurong.mysql.transaction.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.huzhurong.mysql.transaction.bean.TestTransaction;
import top.huzhurong.mysql.transaction.dao.impl.TestTransactionDao;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author chenshun00@gmail.com
 * @since 2019/5/4
 */
@Service
public class TestTransactionService {

    @Resource
    private TestTransactionDao testTransactionDao;

    /**
     * 执行sql语句,运行时异常回滚
     * INSERT INTO test_transaction ( name,email,birth ) VALUES ( 'chenshun00','chenshun00@gmail.com','2019-05-04 14:20:37.209' )
     * rollback 执行
     */
    @Transactional
    public void testUncheckedExceptionRollback() {
        testTransactionDao.addTestTransaction(build());
        throw new NullPointerException("测试checked异常会滚");
    }

    /**
     * 执行sql语句 insert，出现受检异常，并未回滚,直接commit
     * 执行sql语句,受检异常不回滚
     */
    @Transactional
    public void testCheckedException() throws SQLException {
        testTransactionDao.addTestTransaction(build());
        throw new SQLException("ggg");
    }

    /**
     * 指定SQLException异常时回滚
     */
    @Transactional(rollbackFor = {SQLException.class})
    public void testCheckedExceptionRollback() throws SQLException {
        testTransactionDao.addTestTransaction(build());
        throw new SQLException("ggg");
    }

    /**
     * nest method 共插入2条记录 sql
     * SET autocommit=0
     * select @@session.tx_read_only
     * INSERT INTO test_transaction ( name,email,birth ) VALUES ( 'chenshun00','chenshun00@gmail.com','2019-05-04 14:47:00.572' )
     * select @@session.tx_read_only
     * INSERT INTO test_transaction ( name,email,birth ) VALUES ( 'chenshun00','chenshun00@gmail.com','2019-05-04 14:47:00.635' )
     * commit
     * SET autocommit=1
     */
    @Transactional
    public void testNestMethod() {
        //insert
        testTransactionDao.addTestTransaction(build());
        this.subMethod();
    }

    private void subMethod() {
        testTransactionDao.addTestTransaction(build());
    }

    /**
     * SET autocommit=0
     * select @@session.tx_read_only
     * SELECT @@session.tx_isolation
     * INSERT INTO test_transaction ( name,email,birth ) VALUES ( 'chenshun00','chenshun00@gmail.com','2019-05-04 14:57:47.05' )
     * INSERT INTO test_transaction ( name,email,birth ) VALUES ( 'chenshun00','chenshun00@gmail.com','2019-05-04 14:57:47.141' )
     * rollback
     * SET autocommit=1
     * <p>
     * 子方法出现运行运行时异常，回滚
     */
    @Transactional
    public void testNestMethodRuntimeException() {
        testTransactionDao.addTestTransaction(build());
        this.subExceptionMethod();
    }

    private void subExceptionMethod() {
        testTransactionDao.addTestTransaction(build());
        throw new NullPointerException("运行时异常测试");
    }

    /**
     * set autocommit = 1
     * select @@session.tx_read_only
     * SELECT @@session.tx_isolation
     * set autocommit = 0
     * insert sql语句
     * insert sql语句
     * commit
     * set autocommit = 1
     * <p>
     * 受检异常直接提交
     */
    @Transactional
    public void testNestMethodUncheckException() throws SQLException {
        testTransactionDao.addTestTransaction(build());
        this.subUncheckExceptionMethod();
    }

    private void subUncheckExceptionMethod() throws SQLException {
        testTransactionDao.addTestTransaction(build());
        throw new SQLException("运行时异常测试");
    }

    /**
     * set autocommit = 1
     * select @@session.tx_read_only
     * SELECT @@session.tx_isolation
     * set autocommit = 0
     * insert sql语句
     * insert sql语句
     * rollback
     * set autocommit = 1
     * <p>
     * 指定异常回滚
     */
    @Transactional(rollbackFor = {SQLException.class})
    public void testNestMethodUncheckExceptionRollback() throws SQLException {
        testTransactionDao.addTestTransaction(build());
        this.subUncheckExceptionMethodRollback();
    }

    private void subUncheckExceptionMethodRollback() throws SQLException {
        testTransactionDao.addTestTransaction(build());
        throw new SQLException("运行时异常测试");
    }

    /**
     * 测试调用未开启事务，直接是一条sql一个事务提交
     * 没有事务原因:没有Transactional注解，没有进入proxy代理当中，没有进入代码当中就进入不了事务,
     * this调用还是没有事务，这里如果要产生事务就需要获取当前(this)对象当proxy对象，再进行方法调用会产生事务
     */
    public void testMainMethodNoTransactionRuntimeException() {
        this.subMethodTransactionRuntimeException();
    }

    @Transactional
    public void subMethodTransactionRuntimeException() {
        testTransactionDao.addTestTransaction(build());
        throw new NullPointerException("11");
    }

    /**
     * proxy代理调用产生事务
     * 需要配置 @EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
     */
    public void testProxyObjectGetTransaction() throws SQLException {
        ((TestTransactionService) AopContext.currentProxy()).testProxyObjectGetTransactionSubMethod();
    }

    @Transactional(rollbackFor = {SQLException.class})
    public void testProxyObjectGetTransactionSubMethod() throws SQLException {
        testTransactionDao.addTestTransaction(build());
        throw new SQLException("11");
    }

    /**
     * 事务1
     * <p>
     * set autocommit = 0
     * insert
     * insert
     * commit
     * set autocommit = 1
     */
    @Transactional
    public void firstTransaction() {
        testTransactionDao.addTestTransaction(build());
        this.secondTransaction();
    }

    /**
     * 事务2
     */
    @Transactional
    public void secondTransaction() {
        testTransactionDao.addTestTransaction(build());
    }

    private TestTransaction build() {
        TestTransaction testTransaction = new TestTransaction();
        testTransaction.setBirth(new Date());
        testTransaction.setEmail("chenshun00@gmail.com");
        testTransaction.setName("chenshun00");
        return testTransaction;
    }

}
