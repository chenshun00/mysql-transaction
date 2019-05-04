package top.huzhurong.mysql.transaction.dao;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenshun00@gmail.com
 * @since 2019-05-03 10:03:05
 */
public abstract class BaseDao<T> {

    private static Integer BATCH_SIZE = 20;

    @Resource
    @Getter
    @Setter
    private SqlSessionTemplate sqlSessionTemplate;

    protected Integer batchInsert(String statement, List<T> lists) {
        if (lists == null || lists.size() == 0) {
            return 0;
        }
        List<T> middle = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            middle.add(lists.get(i));
            if (i == lists.size() - 1 || (i % BATCH_SIZE == 0 && i > 0)) {
                this.sqlSessionTemplate.insert(statement, middle);
                this.sqlSessionTemplate.flushStatements();
                middle.clear();
            }
        }
        return lists.size();
    }

    protected Integer batchUpdate(String statement, List<T> lists) {
        if (lists == null || lists.size() == 0) {
            return 0;
        }
        List<T> middle = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            middle.add(lists.get(i));
            if (i == lists.size() - 1 || (i % BATCH_SIZE == 0 && i > 0)) {
                this.sqlSessionTemplate.update(statement, middle);
                this.sqlSessionTemplate.flushStatements();
                middle.clear();
            }
        }
        return lists.size();
    }
}
