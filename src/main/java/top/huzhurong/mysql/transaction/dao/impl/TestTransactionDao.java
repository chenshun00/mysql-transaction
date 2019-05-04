package top.huzhurong.mysql.transaction.dao.impl;

import org.springframework.stereotype.Repository;
import top.huzhurong.mysql.transaction.bean.TestTransaction;
import top.huzhurong.mysql.transaction.bean.query.TestTransactionQuery;
import top.huzhurong.mysql.transaction.dao.BaseDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author chenshun00@gmail.com
 * @since 2019-05-03 10:03:05
 */
@Repository
public class TestTransactionDao extends BaseDao<TestTransaction> {

    public TestTransaction getTestTransactionByKey(Integer id) {
        Objects.requireNonNull(id, "id不能为null");
        Map<String, Object> param = new HashMap<>(8);
        param.put("id", id);
        return this.getSqlSessionTemplate().selectOne("TestTransaction.selectTestTransactionByKey", param);
    }

    public Integer addTestTransaction(TestTransaction testTransaction) {
        return this.getSqlSessionTemplate().insert("TestTransaction.insertTestTransaction", testTransaction);
    }

    public Integer updateTestTransactionByKey(TestTransaction testTransaction) {
        Objects.requireNonNull(testTransaction.getId(), "testTransaction.getId()不能为null");
        return this.getSqlSessionTemplate().update("TestTransaction.updateTestTransactionByKey", testTransaction);
    }

    public Integer deleteTestTransactionByKey(Integer id) {
        Objects.requireNonNull(id, "id不能为null");
        Map<String, Object> param = new HashMap<>(8);
        param.put("id", id);
        return this.getSqlSessionTemplate().delete("TestTransaction.deleteTestTransactionByKey", param);
    }

    public List<TestTransaction> getTestTransactionWithTestTransactionQuery(TestTransactionQuery testTransactionQuery) {
        return this.getSqlSessionTemplate().selectList("TestTransaction.getTestTransactionWithTestTransactionQuery", testTransactionQuery);
    }

    public List<TestTransaction> getTestTransactionWithPage(TestTransactionQuery testTransactionQuery) {
        return this.getSqlSessionTemplate().selectList("TestTransaction.getTestTransactionWithPage", testTransactionQuery);
    }

    /*
    public Integer batchInsertTestTransaction(List<TestTransaction> testTransactionList) {
        return this.batchInsert("TestTransaction.batchInsertTestTransaction", testTransactionList);
    }
    */

    /*
    public Integer batchUpdateTestTransaction(List<TestTransaction> testTransactionList) {
        return this.batchUpdate("TestTransaction.batchUpdateTestTransaction", testTransactionList);
    }
    */

    //批量删除操作
    public Integer batchDeleteTestTransaction(List<Integer> idList) {
        if (idList == null || idList.size() == 0) {
            return 0;
        }
        Map<String, Object> param = new HashMap<>(8);
        param.put("item", idList);
        return this.getSqlSessionTemplate().delete("TestTransaction.batchDeleteTestTransaction", param);
    }

}
